package task3;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
    private static final String CONFIG_FILE = "config.properties";
    private static final String DEFAULT_URL = "max.ge/final/t3/75629103/index.php";
    private static final String DEFAULT_BOT_NAME = "BlogBot";

    private Properties properties;

    public ConfigManager() {
        this.properties = new Properties();
        loadConfiguration();
    }

    private void loadConfiguration() {
        File configFile = new File(CONFIG_FILE);

        if (!configFile.exists()) {
            createDefaultConfig();
        }

        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            System.err.println("Using default configuration...");
            setDefaultProperties();
        }
    }

    private void createDefaultConfig() {
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            Properties defaultProps = new Properties();
            defaultProps.setProperty("server.url", DEFAULT_URL);
            defaultProps.setProperty("bot.name", DEFAULT_BOT_NAME);

            defaultProps.store(fos, "ChatBot Configuration File");
            System.out.println("Created default configuration file: " + CONFIG_FILE);

            this.properties = defaultProps;
        } catch (IOException e) {
            System.err.println("Error creating default configuration: " + e.getMessage());
            setDefaultProperties();
        }
    }

    private void setDefaultProperties() {
        properties.setProperty("server.url", DEFAULT_URL);
        properties.setProperty("bot.name", DEFAULT_BOT_NAME);
    }

    public String getServerUrl() {
        return properties.getProperty("server.url", DEFAULT_URL);
    }

    public String getBotName() {
        return properties.getProperty("bot.name", DEFAULT_BOT_NAME);
    }

    public void updateConfig(String key, String value) {
        properties.setProperty(key, value);
        saveConfiguration();
    }

    private void saveConfiguration() {
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            properties.store(fos, "ChatBot Configuration File - Updated");
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }
}
