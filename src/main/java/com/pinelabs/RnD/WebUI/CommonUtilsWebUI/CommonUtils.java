package com.pinelabs.RnD.WebUI.CommonUtilsWebUI;

import com.mongodb.client.*;
import com.pinelabs.RnD.AndroidUI.Constants.FilePaths;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.bson.Document;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.Properties;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

public class CommonUtils {
    static MongoCollection<Document> tableName;
    static MongoDatabase database;
    static MongoClient client;

    public static Properties readPropertyfile(String propertyFilePath) {

        Properties p = null;
        try {
            FileReader reader = new FileReader(propertyFilePath);
            p = new Properties();
            p.load(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    public static void writePropertyFile(String propertyKey, String propertyValue, String propertyFilePath) {
        File f = new File(propertyFilePath);
        PropertiesConfiguration config = new PropertiesConfiguration();
        PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
        try {
            try {
                layout.load(new InputStreamReader(new FileInputStream(f)));
            } catch (org.apache.commons.configuration.ConfigurationException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        config.setProperty(propertyKey, propertyValue);
        try {
            try {
                layout.save(new FileWriter(propertyFilePath, false));
            } catch (org.apache.commons.configuration.ConfigurationException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static MongoDatabase dbValidation() {

        client = MongoClients.create(CommonUtils.readPropertyfile(FilePaths.dbPropertiesPath).getProperty("mongoDbconnectionURL"));
        MongoIterable<String> dbname = client.listDatabaseNames();
        for (String db : dbname) {
            if (db.equals("admin")) {
                database = client.getDatabase(db);
                System.out.println("Connected to admin database");
            }
        }
        return database;
    }

    public static MongoCollection<Document> findCollectionFromDb(String collectionName) {
        dbValidation();
        tableName = database.getCollection(collectionName);
        return tableName;
    }

    public static Document findRowBasedOnColumn(String collectionName, String columnName, Object columnValue) {
        findCollectionFromDb(collectionName);
        return tableName.find(eq(columnName, columnValue)).first();
    }

    public static FindIterable<Document> sortWithcolumnName(String collectionName, String columnName) {
        tableName = findCollectionFromDb(collectionName);
        return tableName.find().sort(ascending(columnName));
    }

    public static Document findRowBasedOnHardwareID(String collectionName, String hardwareId) {
        tableName = findCollectionFromDb(collectionName);
        return tableName.find(eq("HARDWARE_ID", hardwareId)).first();
    }

    public static void closeConnection() {
        client.close();
    }

    public static int generateRandonNumber(int length) {
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length); // bound is exclusive
        Random random = new Random();
        return random.nextInt(max - min) + min;

    }

    public void generateRandomString(int length) {
        System.out.println(RandomStringUtils.randomAlphabetic(length));
    }


    /**
     * This method captures the imageElement which displays captcha then after capturing
     * the screenshot of the imageelement.With the help of Tessract interface, the
     * doOCR method of tessract interface provides character recognition support for common
     * image formats and then text is replaced with all the spaces it has(only the digits or the
     * characters will be left in the captcha text)
     *
     * @return
     * @author:Vidushi
     */
    public static String getCaptchaText(WebElement imageelement) {
        String captchatext = null;
        try {
            String paths= captureScreenshot(imageelement);
            File src = new File(paths);
            ITesseract tess = new Tesseract();
            String path = System.getProperty("user.dir") + "/captchaimages/captcha.png";
            FileUtils.copyFile(src, new File(path));
            System.out.println("Tesseract");
            captchatext = tess.doOCR(new File(path));
            System.out.println(captchatext);
            String finaltext = captchatext.replaceAll("[^a-zA-Z0-9]", "");
            System.out.println(finaltext);
            Thread.sleep(2000);
            System.out.println("CaptchaText: " + finaltext);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return captchatext;
    }

    public static String captureScreenshot(WebElement imageelement) {
        File src = imageelement.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/captchascreenshots/captcha.png";
        File dest = new File(path);
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
