package uk.ac.ed.inf.acptutorial1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uk.ac.ed.inf.acptutorial1.data.Restaurant;

import java.io.IOException;
import java.net.URL;

public class TestGetClient {

    public static final String RESTAURANT_URL = "restaurants";

    public static void main(String[] args) {
        if (args.length < 1){
            System.err.println("the base URL must be provided");
            System.exit(1);
        }

        var baseUrl = args[0];
        if (!baseUrl.endsWith("/")){
            baseUrl += "/";
        }

        try {
            var temp = new URL(baseUrl);
        } catch (Exception x) {
            System.err.println("The URL is invalid: " + x);
            System.exit(2);
        }


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            var restaurants = mapper.readValue(new URL(baseUrl + RESTAURANT_URL), Restaurant[].class);
            System.out.println("read all restaurants");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
