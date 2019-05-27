package com.barzi;

import com.barzi.util.FTPSUtil;

import java.util.List;
import java.util.function.Consumer;

public class Application {

    public static void main(String args[]){
        System.out.println("Running application TestFtp");
        String host;
        int port;
        String user;
        String password;
        String dir;
        try {
            System.out.println("Checking for command line arguments");
            host=args[0];
            port=Integer.parseInt(args[1]);
            user=args[2];
            password=args[3];
            dir=args[4];
            System.out.println("Command line arguments are set " +
                    "[ Host : "+host+" Port :"+port+" User : "+user+" Password : "+password+" Director : "+dir+"]");
        }catch (Exception e){
            System.out.println("No command line arguments provided");
            host="";
            port=22;
            user="";
            password="";
            dir="";
            System.out.println("Setting default command line arguments " +
                    "[ Host : "+host+" Port :"+port+" User : "+user+" Password : "+password+" "+dir+"]");
        }

        System.out.println("Listing files");
        final List<String> fileListings = FTPSUtil.listFiles(host, port, user, password, dir);
        System.out.println("File listing completed.");
        fileListings.forEach(
                new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println("["+s+"]");
                    }
                }
        );

        System.out.println("TestFTP application finished");
    }
}
