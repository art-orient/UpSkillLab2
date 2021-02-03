package epam.art.xml_and_json;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class App {
    static String config;
    private static final Logger log = LogManager.getLogger("JSON_APPENDER");

    public static void main( String[] args ) {
        long start = System.currentTimeMillis();
        log.info("Начало работы метода main");
        org.apache.logging.log4j.core.config.Configurator.initialize(null, "log4j2.xml");
        if (args.length > 0) {
            config = args[0];
        } else {
            config = "config.xml";
        }
        log.debug("Адрес конфиг-файла: " + config);
        PrintResult result = new PrintResult();
        result.setConfigName(config);
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            schema = sf.newSchema(new File("config.xsd"));
        } catch (SAXException e) {
            e.printStackTrace();
        }
        MyFile myFile = null;
        try {
            JAXBContext context = JAXBContext.newInstance(MyFile.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setSchema(schema);
            myFile = (MyFile)unmarshaller.unmarshal(new File(config));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        log.info("Данные конфиг-файла: " + myFile);
        System.out.println(myFile);
        String notFound = checkFoundFiles(myFile);
        if (notFound == null) {
            System.out.println("All files found.");
        } else {
            System.out.println("File " + notFound + " not found.");
            log.warn("File " + notFound + " not found.");
            log.info("Файлы не переименованы, так как не найдены.");
            log.info("Завершена работа программы. При выполнении не найдены файлы.");
            System.exit(-1);
        }
        String suffix = myFile.getSuffix();
        for (String name : myFile.getFile()) {
            result.getOldFiles().add(name);
            String extend = name.substring(name.lastIndexOf("."));
            String nameFirst = name.substring(0, name.lastIndexOf("."));
            String destination = nameFirst + suffix + extend;
            try {
                Files.move(Paths.get(name), Paths.get(destination));
                log.info("Файл " + name + " переименован в " + destination);
                System.out.println(name + " -> " + destination);
                log.info("Пользователю выведено сообщение: " + name + " -> " + destination);
                result.getNewFiles().add(destination);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("Произошла ошибка ввода-вывода: ", e);
            }
        }
        log.info("Все файлы успешно переименованы.");
        long end = System.currentTimeMillis();
        long time = end - start;
        result.setTime(time);
        log.info("Время работы программы, мс - " + time);

        try {
            JAXBContext context = JAXBContext.newInstance(PrintResult.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(result, new File("result.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
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

