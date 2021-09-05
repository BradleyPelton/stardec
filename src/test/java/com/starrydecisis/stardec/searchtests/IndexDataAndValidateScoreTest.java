package com.starrydecisis.stardec.searchtests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class IndexDataAndValidateScoreTest {
    @Before
    public void before() throws UnirestException {
        // Create galaxy-index
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.put("http://localhost:9200/galaxy-index")
                .asString();

        // CREATE 3 GALAXIES/NEBULAE, AND INDEX
        Unirest.setTimeouts(0, 0);
        Unirest.post("http://localhost:9200/galaxy-index/_doc?refresh=wait_for")
                .header("Content-Type", "application/json")
                .body("{\"bodyName\":\"NGC 13\",\"otherName\":\"M  31\",\"bodyType\":\"GALXY\",\"constellation\":\"AND\"}")
                .asString();   // Andromeda Galaxy

        Unirest.post("http://localhost:9200/galaxy-index/_doc?refresh=wait_for")
                .header("Content-Type", "application/json")
                .body("{\"bodyName\":\"NGC 1976\",\"otherName\":\"M  42\",\"bodyType\":\"CL+NB\",\"constellation\":\"AND\"}")
                .asString();  // ORION NEBULA

        Unirest.post("http://localhost:9200/galaxy-index/_doc?refresh=wait_for")
                .header("Content-Type", "application/json")
                .body("{\"bodyName\":\"NGC 1952\",\"otherName\":\"M   1\",\"bodyType\":\"SNREM\",\"constellation\":\"TAU\"}")
                .asString();  // CRAB NEBULA
    }

    @Test
    public void insertDataAndValidateScoreTest() throws IOException, UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://localhost:9200/galaxy-index/_search")
                .header("Content-Type", "application/json")
                .body("{\r\n  \"query\": {\r\n    \"match\": {\r\n      \"constellation\": \"AND\"\r\n    }\r\n    }\r\n}")
                .asString();


        assertEquals(response.getStatus(), 200);
        assertTrue(response.getBody().toString().contains("{\"total\":{\"value\":2"));
        assertTrue(response.getBody().toString().contains("\"max_score\":0.4700036,"));
    }
    @After
    public void cleanup() throws UnirestException {
        // DELETE galaxy-index
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.delete("http://localhost:9200/galaxy-index")
                .asString();
    }
}
