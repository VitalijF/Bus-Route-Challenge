package com.vitaliif.busroutechalange.controller;

import com.vitaliif.util.TestHelper;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:integrationtest.properties")
public class BusStationsControllerIntegrationTest {

    @LocalServerPort
    private int port;

    final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testRoadExist() throws IOException, JSONException {
        final ResponseEntity<String> actualResponse = restTemplate.exchange(
                createURLWithPort(20, 243), HttpMethod.GET, null, String.class
        );
        final String expectedResponse = TestHelper.readFromFile("src/test/resources/integration/roadExistResponse.json");

        Assert.assertNotNull(actualResponse);
        Assert.assertNotNull(actualResponse.getStatusCode());
        Assert.assertNotNull(actualResponse.getBody());
        Assert.assertSame(actualResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(expectedResponse, actualResponse.getBody());
        JSONAssert.assertEquals(
                expectedResponse,
                actualResponse.getBody(),
                JSONCompareMode.STRICT_ORDER
        );
    }

    @Test
    public void testNonExistingRoad() throws IOException, JSONException {
        final ResponseEntity<String> actualResponse = restTemplate.exchange(
                createURLWithPort(118, 5), HttpMethod.GET, null, String.class
        );
        final String expectedResponse = TestHelper.readFromFile("src/test/resources/integration/roadNotExistResponse.json");

        Assert.assertNotNull(actualResponse);
        Assert.assertNotNull(actualResponse.getStatusCode());
        Assert.assertNotNull(actualResponse.getBody());
        Assert.assertSame(actualResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(expectedResponse, actualResponse.getBody());
        JSONAssert.assertEquals(
                expectedResponse,
                actualResponse.getBody(),
                JSONCompareMode.STRICT_ORDER
        );
    }

    private String createURLWithPort(int depSid, int arrSid) {
        return "http://localhost:" + port + "/api/direct?dep_sid=" + depSid + "&arr_sid=" + arrSid;
    }
}
