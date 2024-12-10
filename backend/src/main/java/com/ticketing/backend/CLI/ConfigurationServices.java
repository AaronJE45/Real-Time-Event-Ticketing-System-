package com.ticketing.backend.CLI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ConfigurationServices {
    private static String CONFIG_FILE = "config.json";
    private Gson gson;

    public ConfigurationServices(){
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void setConfiguration(Configuration config){
        try{
            FileWriter writer = new FileWriter(CONFIG_FILE);
            gson.toJson(config, writer);
            writer.close();
            System.out.println("Configuration saved successfully to " + CONFIG_FILE);
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public Configuration getConfiguration(){
        try{
            FileReader reader = new FileReader(CONFIG_FILE);
            return gson.fromJson(reader,Configuration.class);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            return null;
        }
    }
}
