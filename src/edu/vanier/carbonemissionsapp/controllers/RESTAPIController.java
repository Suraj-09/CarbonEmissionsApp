package edu.vanier.carbonemissionsapp.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

/**
 * A class that provides methods that consume REST-based APIs
 * 
 * @author Sleiman Rabah
 */
public class RESTAPIController {

    public static RESTAPIController apiController;

    
    /**
     * Calls an REST API's public method.
     *
     * @param methodURL The url of the API method to be called.
     * @return A string representing the obtained HTTP response returned by the API's method.
     */
    public String makeApiCall(String methodURL) {
        String userCredentials = "test:test123";
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
        String response = "";        
        try {
            
            
            URL url = new URL(methodURL);
            //-- Step 1) Create a URL Connection.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty ("Authorization", basicAuth);
            conn.setRequestProperty("Accept", "application/json");
            //-- Step 2) Open an InputStream to connection
            conn.connect();
            InputStream in = conn.getInputStream();
            //-- Step 3) Fetch and decode the string response using StringBuilder
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            //-- Step 4) Loop through
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            response = stringBuilder.toString();
        } catch (MalformedURLException me) {
            System.err.println(me.getMessage());

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return response;
    }
}
