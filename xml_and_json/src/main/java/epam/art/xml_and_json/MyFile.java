package epam.art.xml_and_json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "files")
@XmlType( propOrder = { "file", "suffix"} )

public class MyFile {
    private List<String> file;
    private String suffix;

    public MyFile(List<String> file, String suffix) {
        this.file = file;
        this.suffix = suffix;
    }

    public MyFile() {
    }

    public List<String> getFile() {
        return file;
    }

    @XmlElement( name = "file" )
    public void setFile(String filename) {
        file.add(filename);
    }

    public void setFile(List<String> file) {
        this.file = file;
    }

    public String getSuffix() {
        return suffix;
    }

    @XmlElement( name = "suffix" )
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "file=" + file +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}
