package uk.ac.ed.inf.acptutorial1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TestPostClient {

    public static final String TESTPOST_URL = "testPostBody";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("the base URL must be provided");
            System.exit(1);
        }

        var baseUrl = args[0];
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        try {
            var temp = new URL(baseUrl);
        } catch (Exception x) {
            System.err.println("The URL is invalid: " + x);
            System.exit(2);
        }


        ObjectMapper mapper = new ObjectMapper();
        try {
            // create the URL, set POST and Input / Output
            var postUrl = new URL(baseUrl + TESTPOST_URL);
            HttpURLConnection con = (HttpURLConnection) postUrl.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);

            // we are passing JSON
            con.setRequestProperty("Content-Type", "application/json");

            // write the stuff to the server
            OutputStream os = con.getOutputStream();
            byte[] input = "{ \"item1\" : \"AAA\",  \"item2\" : \"BBB\"  }".getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);

            // read the result (a string) back
            var postResult = new StringBuilder();
            Reader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            int c = 0;
            while ((c = reader.read()) != -1) {
                postResult.append((char) c);
            }

            System.out.println("read result: " + postResult.toString());
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e);
        }
    }
}
