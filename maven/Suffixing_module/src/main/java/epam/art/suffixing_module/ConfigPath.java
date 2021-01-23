package epam.art.suffixing_module;

public class ConfigPath {
    private static String path;

    public static void main(String[] args) {
        if (args.length > 0) {
            path = args[0];
        } else {
            path = "config.json";
        }
    }

    public static String getPath() {
        return path;
    }
}
