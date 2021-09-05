package com.starrydecisis.stardec.searchtests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HealthCheckTest {
    @Test
    public void verifyGreenStatusTest() throws IOException, UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("http://localhost:9200/_cluster/health?wait_for_status=yellow&timeout=50s&pretty")
                .asString();

        assertEquals(response.getStatus(), 200);
        assertTrue(response.getBody().toString().contains("\"status\" : \"green\""));
    }
}

