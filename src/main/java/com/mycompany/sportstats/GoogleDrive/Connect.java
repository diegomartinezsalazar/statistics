package com.mycompany.sportstats.GoogleDrive;

import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class Connect {
    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.

        ArrayList<String> listaPaths = new ArrayList<>();
        listaPaths.add("Application Data");
        listaPaths.add("diegovol@hotmail.com");
        listaPaths.add("Files");
        listaPaths.add("Pending Files");

        GoogleDriveFilesManagement googleDriveFilesManagement = new GoogleDriveFilesManagement();

        List<File> lista = googleDriveFilesManagement.getFilesInPath(listaPaths);
    }
}
