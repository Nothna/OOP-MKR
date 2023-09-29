package com.example.carsharing.dataWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class DataWriter {
    private String filepath;
    private JSONParser parser = new JSONParser();
    private FileReader reader;


    // class constructor
    public DataWriter(String filepath) throws Exception{
        this.filepath = filepath;
        try{
            this.reader = new FileReader(filepath);
        }catch (Exception e){
            throw e;
        }

    }


    public JSONArray get() throws Exception {
        try (FileReader reader = new FileReader(filepath)) {
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(reader);
        }
    }

    public JSONObject get(long id) throws Exception {
        try (FileReader reader = new FileReader(filepath)) {
            JSONParser parser = new JSONParser();
            JSONArray users = (JSONArray) parser.parse(reader);
            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                if (user.get("id").equals(id)) {
                    return user;
                }
            }
        }
        return null;
    }

    public JSONObject get(String email) throws Exception {
        try (FileReader reader = new FileReader(filepath)) {
            JSONParser parser = new JSONParser();
            JSONArray users = (JSONArray) parser.parse(reader);
            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                if (user.get("email").equals(email)) {
                    return user;
                }
            }
        }
        return null;
    }


    public void create() {

    }


    public void update() {

    }


    public void delete() {

    }
}
