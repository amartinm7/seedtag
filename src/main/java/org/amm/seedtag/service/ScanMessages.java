package org.amm.seedtag.service;

import org.amm.seedtag.model.message.Coordinates;
import org.amm.seedtag.model.message.RequestMessage;

import org.springframework.stereotype.Service;

@Service
public class ScanMessages {
    public Coordinates scan(RequestMessage requestMessage){
        return requestMessage.nextCoordinates();
    }
}
