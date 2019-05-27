package com.barzi.util;

import com.barzi.ext.ExtendedFTPSClient;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FTPSUtil {

    private static ExtendedFTPSClient openNewClient(String host, int port, String user, String password ) {

        final ExtendedFTPSClient ftp;
        ftp = new ExtendedFTPSClient(true);
        try {

            int reply;
            if ( port > 0 ) {
                ftp.connect( host, port );
            } else {
                ftp.connect( host);
            }
            ftp.login( user, password );

            ftp.execPBSZ(0);
            ftp.execPROT("P");
            ftp.type(FTP.BINARY_FILE_TYPE);

            ftp.printWorkingDirectory();
            ftp.setFileType( FTP.BINARY_FILE_TYPE );

            ftp.enterLocalPassiveMode();

        } catch ( IOException e ) {
            if ( ftp.isConnected() ) {
                try {
                    ftp.disconnect();
                } catch ( IOException ignore ) {}
            }
            // Log.error( "Could not connect to server." );
            e.printStackTrace();
        }
        return ftp;
    }

    public static List<String> listFiles(String host, int port, String user, String password, String dir) {

        ExtendedFTPSClient ftp;
        final List<String> fileListings = new ArrayList<String>();

        try {
            System.out.println("Opening FTPS Client");
            ftp = FTPSUtil.openNewClient(host, port, user, password);
            System.out.println("Client created");
            for ( FTPFile file : ftp.listFiles( dir ) ) {
                fileListings.add( file.getName());
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return fileListings;
    }

}
