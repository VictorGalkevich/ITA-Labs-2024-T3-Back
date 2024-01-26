package com.ventionteams.applicationexchange.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ventionteams.applicationexchange.model.Data;

import java.io.File;
import java.io.IOException;

public class Parser {
    ObjectMapper objectMapper = new ObjectMapper();
    public Data parse() {
        Data data = null;
        try {
             data = objectMapper.readValue(new File("mock-entities.json"), Data.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
