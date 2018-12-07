package org.amm.seedtag.model.message;

import org.amm.seedtag.model.protocol.Factory;

import java.util.Arrays;

public class RequestMessage {

    // {"protocols":["avoid-mech"],
    // "scans":[{"coordinates":{"x":0,"y":40},"enemies":{"type":"soldier","number":10}}]}

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
        //Object o = Arrays.stream(protocols).map(protocol -> Factory.getInstance().getProtocol(protocol).execute(scans));
        return Factory.getInstance().getProtocol(protocols[0]).execute(scans).get(0);
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "protocols=" + Arrays.toString(protocols) +
                ", scans=" + Arrays.toString(scans) +
                '}';
    }
}
