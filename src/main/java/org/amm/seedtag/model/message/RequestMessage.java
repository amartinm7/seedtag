package org.amm.seedtag.model.message;

import org.amm.seedtag.model.protocol.Factory;
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

    public Coordinates nextCoordinates(){
        LOGGER.info("getting nextCoordinates...");
        final String lastScansKey = "lastScans";
        final Map<String, Scan[]> mappedScans = new HashMap<>();
        mappedScans.put(lastScansKey, scans);
        Arrays.stream(getProtocols()).forEach(protocol -> {
            Scan[] nextScans = Factory.getInstance().getProtocol(protocol).process(mappedScans.get(lastScansKey));
            mappedScans.put(lastScansKey,nextScans);
        });
        return mappedScans.get(lastScansKey)[0].getCoordinates();
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "protocols=" + Arrays.toString(protocols) +
                ", scans=" + Arrays.toString(scans) +
                '}';
    }
}
