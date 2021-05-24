package com.sledz.services.ProductProvider.Allegro;

import com.sledz.SledzApplication;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AllegroSecrets {
    private static String _apiKey = null;
    private static String _apiSecret = null;
    @Nullable
    public static String getKey()
    {
        if(_apiKey == null) {
            try (InputStream input = SledzApplication.class.getClassLoader().getResourceAsStream("secret.properties")) {

                Properties prop = new Properties();

                if (input == null) {
                    System.out.println("Sorry, unable to find secret.properties");
                    return null;
                }

                //load a properties file from class path, inside static method
                prop.load(input);

                //get the property value and print it out
                _apiKey = prop.getProperty("allegro.apiKey");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return _apiKey;
    }
    @Nullable
    public static String getSecret()
    {
        if(_apiSecret == null) {
            try (InputStream input = SledzApplication.class.getClassLoader().getResourceAsStream("secret.properties")) {

                Properties prop = new Properties();

                if (input == null) {
                    System.out.println("Sorry, unable to find secret.properties");
                    return null;
                }

                //load a properties file from class path, inside static method
                prop.load(input);

                //get the property value and print it out
                _apiSecret = prop.getProperty("allegro.apiSecret");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return _apiSecret;
    }
}
