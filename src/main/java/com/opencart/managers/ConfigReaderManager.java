package com.opencart.managers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderManager {

    private static final Logger logger = LogManager.getLogger(ConfigReaderManager.class);
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    private static Properties properties;

    public static void initProperties() {

        try {
            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
            logger.log(Level.DEBUG, "The config reader path is: " + CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException exceptionObject) {
            exceptionObject.printStackTrace();
        }
    }

    public static String getProperty(String key) {

        if (properties == null) {
            initProperties();
        }
        String keyValue = properties.getProperty(key);
        logger.log(Level.DEBUG, "The value of the key: " + key + " is: " + keyValue);
        return keyValue;
    }
}
