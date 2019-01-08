package org.amm.seedtag.model.message;

import org.amm.seedtag.model.protocol.Factory;
import org.amm.seedtag.model.protocol.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class RequestMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestMessage.class);

    private String[] protocols;
    private Scan[] scans;

    public String[] getProtocols() {
        return protocols;
    }

    public void setProtocols(String[] protocols) {
        this.protocols = protocols;
    }

    public Scan[] getScans() {
        return scans;
    }

    public void setScans(Scan[] scans) {
        this.scans = scans;
    }

    public Coordinates nextCoordinates() {
        LOGGER.info("getting nextCoordinates...");
        final Scan[] lastScans = nextCoordinates(scans, Arrays.asList(protocols).iterator());
        return lastScans[0].getCoordinates();

    }

    public Scan[] nextCoordinates (Scan[] scans, Iterator<String> protocols) {
        if(protocols.hasNext()){
            final String protocolName = protocols.next();
            LOGGER.info("applying protocol {}...",protocolName);
            final Protocol protocol = Factory.getInstance().getProtocol(protocolName);
            final Scan[] nextScan = protocol.process(scans);
            return nextCoordinates (nextScan, protocols);
        } else {
            return scans;
        }
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "protocols=" + Arrays.toString(protocols) +
                ", scans=" + Arrays.toString(scans) +
                '}';
    }
}
