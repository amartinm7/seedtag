package org.amm.seedtag.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RadarControllerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(RadarControllerIT.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void doGet() throws Exception {
        final String expected = "Hello!";
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).isEqualToIgnoringCase(expected);
    }

    @Test
    public void doPost() throws Exception {
        final String expected = "{\"x\":0,\"y\":40}";
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/radar",
                String.class)).isEqualToIgnoringCase(expected);
    }

}
