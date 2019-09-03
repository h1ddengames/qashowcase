package com.h1ddengames.framework.utils;

import org.openqa.selenium.By;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileObjectRepositoryManager implements IObjectRepository {
    // Creating a singleton.
    // This design pattern makes sure that there is ever only ONE instance of this class at any given time.
    private static PropertyFileObjectRepositoryManager instance = null;
    private Properties properties = null;

    // A private constructor makes it so that no other class may create an instance, only this class can make an
    // instance of itself.
    private PropertyFileObjectRepositoryManager() {
        properties = new Properties();
    }

    // Getting an instance of this class.
    public static PropertyFileObjectRepositoryManager getInstance() {
        if(instance == null) {
            instance = new PropertyFileObjectRepositoryManager();
        }
        return instance;
    }

    // There might be a time when we want to reset the key-value pairs that is contained in properties.
    public void reset() {
        properties = new Properties();
    }

    public Properties getProperty() {
        return properties;
    }

    public void printAllProperties() {
        properties.forEach((k, v) -> System.out.println(k + "=" + v));
    }

    /**
     * Loads all the key-value pairs from the property files fed into this method.
     * @param fileNames Multiple filenames separated by "," (a comma)
     */
    public void load(String... fileNames) {
        if(fileNames.length == 0) {
            return;
        }

        for (String fileName: fileNames) {
            if(new File(fileName).exists()) {
                InputStream input = null;
                try {
                    input = new FileInputStream(fileName);
                    properties.load(input);
                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                throw new RuntimeException("File with the name " + fileName + " does not exist.");
            }
        }
    }

    /**
     * Gets a value from a specific key in the Object Repository.
     * @param key Is in the form of HomePage.LoginLink or ${pageName}.${elementName}
     * @return A String formed by ${locatorType:locatorString} such as ID:spree_user_email
     */
    public String getValueFromKey(String key) {
        String value = properties.getProperty(key);
        if(value != null) {
            return value;
        } else {
            throw new RuntimeException("Key: " + key + " does not exist.");
        }
    }

    /**
     * Gets the locator type (linkText, partialLinkText, id, name, className, tagName, cssSelector, xpath)
     * @param key Is in the form of HomePage.LoginLink or ${pageName}.${elementName}
     * @return A String that can be one of eight Strings: linkText, partialLinkText, id, name, className, tagName, cssSelector, or xpath.
     */
    public String getLocatorTypeString(String key) {
        String value = getValueFromKey(key);
        String locatorType = null;

        if(value != null) {
            String parts[] = value.split(":");
            if(parts != null && parts.length > 0) {
                locatorType = parts[0];
            } else {
                throw new RuntimeException("There is no value for the key: " + key + ".");
            }
        } else {
            throw new RuntimeException("There is no locator with the key " + key);
        }
        return locatorType;
    }

    /**
     * Gets the locator string (user_email, //div/div, .alert)
     * @param key Is in the form of HomePage.LoginLink or ${pageName}.${elementName}
     * @return A locator string that is used to identify WebElement(s).
     */
    public String getLocatorValueString(String key) {
        String value = getValueFromKey(key);
        String locatorType = null;

        if(value != null) {
            String parts[] = value.split(":");
            if(parts != null && parts.length > 0) {
                locatorType = parts[1];
            } else {
                throw new RuntimeException("There is no value for the key: " + key + ".");
            }
        } else {
            throw new RuntimeException("There is no locator with the key " + key);
        }
        return locatorType;
    }

    /**
     * Gets the By locator through reading the object repository.
     * @param key Is in the form of HomePage.LoginLink or ${pageName}.${elementName}
     * @return A By locator that can be used to locate WebElement(s).
     */
    public By getByFromObjectRepositoryLocator(String key) {
        By by = null;
        String locatorType = getLocatorTypeString(key);
        String locatorString = getLocatorValueString(key);
        if(locatorType != null && locatorString != null) {
            if(locatorType.equalsIgnoreCase("LINK_TEXT")) {
                by = By.linkText(locatorString);
            } else if (locatorType.equalsIgnoreCase("PARTIAL_LINK_TEXT")) {
                by = By.partialLinkText(locatorString);
            } else if (locatorType.equalsIgnoreCase("NAME")) {
                by = By.name(locatorString);
            } else if (locatorType.equalsIgnoreCase("ID")) {
                by = By.id(locatorString);
            } else if (locatorType.equalsIgnoreCase("CLASS_NAME")) {
                by = By.className(locatorString);
            } else if (locatorType.equalsIgnoreCase("TAG_NAME")) {
                by = By.tagName(locatorString);
            } else if (locatorType.equalsIgnoreCase("CSS_SELECTOR")) {
                by = By.cssSelector(locatorString);
            } else if (locatorType.equalsIgnoreCase("XPATH")) {
                by = By.xpath(locatorString);
            }
        }
        return by;
    }
}