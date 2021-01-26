package epam.art.xml_and_json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "result")
@XmlType( propOrder = { "configName", "time", "oldFiles", "newFiles"} )
public class PrintResult {
    private String configName;
    private long time;
    private List<String> oldFiles;
    private List<String> newFiles;

    public PrintResult() {
        oldFiles = new ArrayList<>();
        newFiles = new ArrayList<>();
    }

    @XmlElement( name = "Name_config_file" )
    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @XmlElement( name = "Name_old_file" )
    public List<String> getOldFiles() {
        return oldFiles;
    }

    public void setOldFiles(List<String> oldFiles) {
        this.oldFiles = oldFiles;
    }

    @XmlElement( name = "Name_new_file" )
    public List<String> getNewFiles() {
        return newFiles;
    }

    public void setNewFiles(List<String> newFiles) {
        this.newFiles = newFiles;
    }
}
