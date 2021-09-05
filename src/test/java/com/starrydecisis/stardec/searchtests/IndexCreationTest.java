package com.starrydecisis.stardec.searchtests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IndexCreationTest {
    @Before
    public void init() {
    }

    @Test
    public void indexCreationTest() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.put("http://localhost:9200/galaxy-index")
                .asString();

        assertNotNull(response.getBody());
        Assert.assertEquals(response.getStatus(), 200);

        assertTrue(response.getBody().contains("\"acknowledged\":true"));
        assertTrue(response.getBody().contains("\"index\":\"galaxy-index\""));
    }

    @After
    public void cleanup() throws UnirestException {

        // DELETE THE CREATED INDEX
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.delete("http://localhost:9200/galaxy-index")
                .asString();
    }
}
