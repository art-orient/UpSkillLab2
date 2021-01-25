package epam.art.logging;

import java.util.List;

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

    public void setFile(List<String> file) {
        this.file = file;
    }

    public String getSuffix() {
        return suffix;
    }

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
