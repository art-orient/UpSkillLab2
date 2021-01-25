package epam.art.logging;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    static String config;
    private static final Logger log = LogManager.getLogger(App.class.getName());

    public static void main( String[] args ) {
        org.apache.logging.log4j.core.config.Configurator.initialize(null, "log4j2.xml");
        log.info("Начало работы метода main");
        if (args.length > 0) {
            config = args[0];
        } else {
            config = "config.json";
        }
        System.out.println(config);
        log.debug("Адрес конфиг-файла: " + config);
        MyFile myFile = parse(config);
        log.info("Данные конфиг-файла: " + myFile);
        System.out.println(myFile);
        String notFound = checkFoundFiles(myFile);
        if (notFound == null) {
            System.out.println("All files found.");
        } else {
            System.out.println("File " + notFound + " not found.");
            log.warn("File " + notFound + " not found.");
            log.info("Завершена работа программы. Файлы не переименованы, так как не найдены.");
            System.exit(-1);
        }
        String suffix = myFile.getSuffix();
        for (String name : myFile.getFile()) {
            String extend = name.substring(name.lastIndexOf("."));
            String nameFirst = name.substring(0, name.lastIndexOf("."));
            String destination = nameFirst + suffix + extend;
            try {
                Files.move(Paths.get(name), Paths.get(destination));
                log.info("Файл " + name + " переименован в " + destination);
                System.out.println(name + " -> " + destination);
                log.info("Пользователю выведено сообщение: " + name + " -> " + destination);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("Произошла ошибка ввода-вывода: ", e);
            }
        }
        log.info("Завершена работа программы.");
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

