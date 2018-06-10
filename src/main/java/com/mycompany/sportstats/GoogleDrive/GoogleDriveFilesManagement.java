package com.mycompany.sportstats.GoogleDrive;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleDriveFilesManagement {
    private GoogleDriveConnection googleDriveConnection;
    private static final String APPLICATION_NAME = "Vol Stats";
    private NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    Drive driveService;

    public GoogleDriveFilesManagement() {

    }

    private void setUp () throws IOException, GeneralSecurityException{
        googleDriveConnection = GoogleDriveConnection.getInstance();
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        driveService = new Drive.Builder(HTTP_TRANSPORT, googleDriveConnection.getJsonFactory(), googleDriveConnection.getAuthorizationToken())
            .setApplicationName(APPLICATION_NAME)
            .build();
    }

    public List<File> getFilesInPath (ArrayList<String> paths) throws IOException, GeneralSecurityException {
        List<File> files;

        fillData();

        String parentPath = "root";

        for (String path: paths
                ) {
            FileList result = driveService.files().list()
                    .setPageSize(10)
                    .setFields("nextPageToken, files(id, name)")
                    .setQ("'" + parentPath + "' in parents and name = '" + path + "' and mimeType = 'application/vnd.google-apps.folder'")
                    .execute();
            parentPath = result.getFiles().get(0).getId();
        }

        FileList result = driveService.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .setQ("'" + parentPath + "' in parents and mimeType != 'application/vnd.google-apps.folder'")
                .execute();
        files = result.getFiles();

        return files;
    }

    private void fillData() throws IOException, GeneralSecurityException {
        if (googleDriveConnection == null){
            setUp();
        }
    }

    public ArrayList<String> readFoldersOfPath(String path){
        ArrayList<String> listOfPaths = new ArrayList<>();
        listOfPaths = new ArrayList<String>(Arrays.asList(path.split("/")));
        return listOfPaths;
    }

    private GoogleDriveFile downloadFileContent(File file) throws IOException, GeneralSecurityException {
        fillData();

        OutputStream outputStream = new ByteArrayOutputStream();
        driveService.files().get(file.getId())
            .executeMediaAndDownloadTo(outputStream);

        GoogleDriveFile googleDriveFile = new GoogleDriveFile(file.getId(),
                file.getName(),
                new String (((ByteArrayOutputStream) outputStream).toByteArray()));
        return googleDriveFile;
    }

    public ArrayList<GoogleDriveFile> readGoogleFiles (ArrayList<File> files) throws GeneralSecurityException, IOException {
        ArrayList<GoogleDriveFile> treatedFiles = new ArrayList<>();
        for (File file: files
             ) {
            treatedFiles.add(downloadFileContent(file));
        }
        return treatedFiles;
    }

    private void moveFileTo (String fileId, String folderId) throws IOException{
// Retrieve the existing parents to remove
        File file = driveService.files().get(fileId)
                .setFields("parents")
                .execute();
        StringBuilder previousParents = new StringBuilder();
        for (String parent : file.getParents()) {
            previousParents.append(parent);
            previousParents.append(',');
        }
// Move the file to the new folder
        file = driveService.files().update(fileId, null)
                .setAddParents(folderId)
                .setRemoveParents(previousParents.toString())
                .setFields("id, parents")
                .execute();
    }

    public void archiveFile (GoogleDriveFile file, ArrayList<String> target) throws GeneralSecurityException, IOException{
        moveFileTo(file.getId(), folderId(target));
    }

    public String folderId (ArrayList<String> paths) throws GeneralSecurityException, IOException{
        fillData();

        String parentPath = "root";

        for (String path: paths
                ) {
            FileList result = driveService.files().list()
                    .setPageSize(10)
                    .setFields("nextPageToken, files(id, name)")
                    .setQ("'" + parentPath + "' in parents and name = '" + path + "' and mimeType = 'application/vnd.google-apps.folder'")
                    .execute();
            parentPath = result.getFiles().get(0).getId();
        }

        return parentPath;
    }
}
