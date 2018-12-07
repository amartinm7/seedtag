package org.amm.seedtag.controller;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.RequestMessage;
import org.amm.seedtag.service.ScanMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600, origins = "*",
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS})
@RestController
public class RadarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RadarController.class);

    @Autowired
    private ScanMessages scanMessages;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> health(@RequestParam(value = "name", defaultValue = "World") String name) {
        LOGGER.info(String.format("%s", "Hello!"));
        return new ResponseEntity<String>("Hello!", HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/radar",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> getPoints(@RequestParam(value = "name", defaultValue = "World") String name) {
        // LOGGER.info(String.format("%s", greetings.toString()));
        return new ResponseEntity<String>("{\"x\":0,\"y\":40}", HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = "/radar",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Coordinates> postPoints(@RequestBody RequestMessage message) {
        LOGGER.info(String.format("%s", message.toString()));
        try {
            Coordinates coordinates = scanMessages.scan(message);
            return new ResponseEntity<Coordinates>(coordinates, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.info(String.format("%s", ex.toString()));
            return new ResponseEntity<Coordinates>(new Coordinates(), HttpStatus.BAD_REQUEST);
        }
    }
}
