package com.mycompany.sportstats.GoogleDrive;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.ClassLoader;


public class GoogleDriveConnection {
    private Credential authorizationToken;
    private static final String APPLICATION_NAME = "Volley statistics";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "resources"; // Directory to store user credentials.

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved credentials/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_METADATA_READONLY);
    private static final String CLIENT_SECRET_DIR = "client_secret.json";

    public GoogleDriveConnection(Credential authorizationToken){
        this.authorizationToken = authorizationToken;
    }

    public Credential getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(Credential authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public List<File> getFilesInPath (ArrayList<String> paths) throws IOException, GeneralSecurityException {
        List<File> files;

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, authorizationToken)
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
            //files = result.getFiles().get(0).getId();
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

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If there is no client_secret.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        //InputStream in = Connect.class.getResourceAsStream(CLIENT_SECRET_DIR);
        InputStream in = classloader.getResourceAsStream(CLIENT_SECRET_DIR);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
}
