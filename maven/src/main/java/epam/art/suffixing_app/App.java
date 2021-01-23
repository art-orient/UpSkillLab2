package epam.art.suffixing_app;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import epam.art.suffixing_module.ConfigPath;

public class App {
    static String config;

    public static void main( String[] args ) {
        ConfigPath.main(args);
        config = ConfigPath.getPath();
        System.out.println(config);
        MyFile myFile = parse(config);
        System.out.println(myFile);
        String notFound = checkFoundFiles(myFile);
        if (notFound == null) {
            System.out.println("All files found.");
        } else {
            System.out.println("File " + notFound + " not found.");
            System.exit(-1);
        }
        String suffix = myFile.getSuffix();
        for (String name : myFile.getFile()) {
            String extend = name.substring(name.lastIndexOf("."));
            String nameFirst = name.substring(0, name.lastIndexOf("."));
            String destination = nameFirst + suffix + extend;
            try {
                Files.move(Paths.get(name), Paths.get(destination));
                System.out.println(name + " -> " + destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static MyFile parse(String str) {
        MyFile myFile = new MyFile();
        Gson g = new Gson();
        try {
            myFile = g.fromJson(new FileReader(str), MyFile.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myFile;
    }

    public static String checkFoundFiles(MyFile myFile) {
        for (String path : myFile.getFile()) {
            File file = new File(path);
            if (!file.exists()) {
                return path;
            }
        }
        return null;
    }
}
