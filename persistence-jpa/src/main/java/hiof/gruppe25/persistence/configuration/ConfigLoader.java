package hiof.gruppe25.persistence.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigLoader {

    public static Properties loadProperties(String filepath) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filepath)) {
            properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
            return properties;
        } catch (IOException e) {
            return null;
        }
    }
}
