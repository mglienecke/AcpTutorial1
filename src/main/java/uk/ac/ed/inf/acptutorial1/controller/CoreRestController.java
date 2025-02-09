package uk.ac.ed.inf.acptutorial1.controller;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uk.ac.ed.inf.acptutorial1.data.Restaurant;
import uk.ac.ed.inf.acptutorial1.data.Tuple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * the ILP Tutorial service which provides suppliers, orders and other useful things
 */
@RestController
public class CoreRestController {

    /**
     * get a buffered reader for a resource
     *
     * @param jsonResource the JSON resource this reader is required for
     * @return the buffered reader
     */
    private java.io.BufferedReader getBufferedReaderForResource(String jsonResource) {
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(jsonResource))));
    }

    /**
     * returns the restaurants in the system
     *
     * @return array of suppliers
     */
    @GetMapping("/restaurants")
    public Restaurant[] restaurants() {
        return new Gson().fromJson(getBufferedReaderForResource("json/restaurants.json"), Restaurant[].class);
    }

    /**
     * a simple alive check
     *
     * @return true (always)
     */
    @GetMapping(value = {"/isAlive"})
    public boolean isAlive() {
        return true;
    }


    /**
     * POST with a JSON data structure in the request body
     * @param postAttribute is the tuple which was pasted
     * @return a message constructed from the passed in data
     */
    @PostMapping(value = "/testPostBody",  consumes = {"*/*"})
    public String testPost(@RequestBody Tuple postAttribute) {
        return "You posted: " + postAttribute.toString();
    }

    /**
     * POST with request parameters
     * @param item1 is the first item
     * @param item2 is the second items
     * @return a combined message
     */
    @PostMapping("/testPostPath")
    public String testPost(@RequestParam("item1") String item1, @RequestParam("item2") String item2) {
        var postAttribute = new Tuple(item1, item2);
        return "You posted: " + postAttribute.toString();
    }

     @PostMapping("/testPostPath3")
    public String testPost3(@RequestParam("item1") String item1, @RequestParam(value = "item2", required = false) String item2) {
        var postAttribute = new Tuple(item1, item2);
        return "You posted: " + postAttribute.toString();
    }


    @PostMapping("/testPostPath2/{item1}/{item2}")
    public String testPost2(@PathVariable("item1") String item1, @PathVariable(value = "item2", required = false) String item2) {
        var postAttribute = new Tuple(item1, item2);
        return "You posted: " + postAttribute.toString();
    }
}
