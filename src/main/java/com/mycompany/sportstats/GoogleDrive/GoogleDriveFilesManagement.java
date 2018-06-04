package com.mycompany.sportstats.GoogleDrive;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.mortbay.util.IO;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class GoogleDriveFilesManagement {
    private GoogleDriveConnection googleDriveConnection;
    private static final String APPLICATION_NAME = "Volley statistics";


    public GoogleDriveFilesManagement() {

    }

    private void setUp () throws IOException, GeneralSecurityException{
        googleDriveConnection = GoogleDriveConnection.getInstance();
    }

    public List<File> getFilesInPath (ArrayList<String> paths) throws IOException, GeneralSecurityException {
        List<File> files;

        fillData();

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, googleDriveConnection.getJsonFactory(), googleDriveConnection.getAuthorizationToken())
                .setApplicationName(APPLICATION_NAME)
                .build();

        String parentPath = "root";

        for (String path: paths
                ) {
            FileList result = service.files().list()
                    .setPageSize(10)
                    .setFields("nextPageToken, files(id, name)")
                    .setQ("'" + parentPath + "' in parents and name = '" + path + "' and mimeType = 'application/vnd.google-apps.folder'")
                    .execute();
            parentPath = result.getFiles().get(0).getId();
        }

        FileList result = service.files().list()
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
}
