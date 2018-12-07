package org.amm.seedtag.model.message;

import org.amm.seedtag.model.protocol.Factory;
import org.amm.seedtag.model.protocol.FurthestEnemies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

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
        final String lastScansKey = "lastScans";
        final Map<String, Scan[]> mappedScans = new HashMap<>();
        mappedScans.put(lastScansKey, scans);
        Arrays.stream(protocols).forEach(protocol -> {
            Scan[] nextScans = Factory.getInstance().getProtocol(protocol).process(mappedScans.get(lastScansKey));
            mappedScans.put(lastScansKey,nextScans);
        });
        return mappedScans.get(lastScansKey)[0].getCoordinates();
/*        final List<Coordinates> coordinates = Arrays.stream(protocols)
                .map(protocol -> Factory.getInstance().getProtocol(protocol).execute(scans))
                .reduce(new ArrayList<>(), (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;});*/
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "protocols=" + Arrays.toString(protocols) +
                ", scans=" + Arrays.toString(scans) +
                '}';
    }
}
