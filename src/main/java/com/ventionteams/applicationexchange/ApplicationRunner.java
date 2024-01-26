package com.ventionteams.applicationexchange;

import com.ventionteams.applicationexchange.model.Data;
import com.ventionteams.applicationexchange.parser.Parser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
        Data data = new Parser().parse();
        System.out.println(data.getLots().size());
    }

}
