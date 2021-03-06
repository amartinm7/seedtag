package org.amm.seedtag.model.protocol;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

public class FactoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FactoryTest.class);

    @Test(expected = RuntimeException.class)
    public void invalidProtocol() throws Exception {
        final Class<ClosestEnemies> expected = ClosestEnemies.class;
        final Protocol protocol = Factory.getInstance().getProtocol("protocol-xxx");
        LOGGER.info(protocol.toString());
    }

    @Test
    public void createClosestEnemies() throws Exception {
        final Class<ClosestEnemies> expected = ClosestEnemies.class;
        final Protocol protocol = Factory.getInstance().getProtocol(Factory.Key.CLOSEST_ENEMIES);
        LOGGER.info(protocol.toString());
        assertEquals(protocol.getClass(), expected);
    }

    @Test
    public void createFuthestEnemies() throws Exception {
        final Class<FurthestEnemies> expected = FurthestEnemies.class;
        final Protocol protocol = Factory.getInstance().getProtocol(Factory.Key.FURTHEST_ENEMIES);
        LOGGER.info(protocol.toString());
        assertEquals(protocol.getClass(), expected);
    }

    @Test
    public void createAssistAllies() throws Exception {
        final Class<AssistAllies> expected = AssistAllies.class;
        final Protocol protocol = Factory.getInstance().getProtocol(Factory.Key.ASSIST_ALLIES);
        LOGGER.info(protocol.toString());
        assertEquals(protocol.getClass(), expected);
    }

    @Test
    public void createAvoidCrossfire() throws Exception {
        final Class<AvoidCrossfire> expected = AvoidCrossfire.class;
        final Protocol protocol = Factory.getInstance().getProtocol(Factory.Key.AVOID_CROSSFIRE);
        LOGGER.info(protocol.toString());
        assertEquals(protocol.getClass(), expected);
    }

    @Test
    public void createPrioritizeMech() throws Exception {
        final Class<PrioritizeMech> expected = PrioritizeMech.class;
        final Protocol protocol = Factory.getInstance().getProtocol(Factory.Key.PRIORITIZE_MECH);
        LOGGER.info(protocol.toString());
        assertEquals(protocol.getClass(), expected);
    }

    @Test
    public void createAvoidMech() throws Exception {
        final Class<AvoidMech> expected = AvoidMech.class;
        final Protocol protocol = Factory.getInstance().getProtocol(Factory.Key.AVOID_MECH);
        LOGGER.info(protocol.toString());
        assertEquals(protocol.getClass(), expected);
    }
}