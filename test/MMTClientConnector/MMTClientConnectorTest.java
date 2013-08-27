/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MMTClientConnector;

import com.montimage.mmt.client.connector.MMTFieldValueHeader;
import com.montimage.mmt.client.connector.GenericFieldValueHeader;
import com.montimage.mmt.client.connector.MMTClientConnectorConfig;
import com.montimage.mmt.client.connector.MMTClientConnector;
import com.montimage.mmt.client.connector.MMTProtocolConfig;
import com.montimage.mmt.client.exception.MMTConnectorException;
import com.montimage.mmt.client.exception.MMTInitializationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author montimage
 */
public class MMTClientConnectorTest {

    public MMTClientConnectorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getConnectorConfig method, of class MMTClientConnector.
     */
    @Test
    public void testGetConnectorConfig() {
        System.out.println("getConnectorConfig");
        MMTClientConnector instance = new MMTClientConnector(2);
        MMTClientConnectorConfig expResult = new MMTClientConnectorConfig();
        instance.setConnectorConfig(expResult);
        MMTClientConnectorConfig result = instance.getConnectorConfig();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConnectorConfig method, of class MMTClientConnector.
     */
    @Test
    public void testSetConnectorConfig() {
        System.out.println("setConnectorConfig");
        MMTClientConnectorConfig connectorConfig = new MMTClientConnectorConfig();
        MMTClientConnector instance = new MMTClientConnector(3);
        instance.setConnectorConfig(connectorConfig);
        assertEquals(connectorConfig, instance.getConnectorConfig());
    }

    /**
     * Test of getProtoConfig method, of class MMTClientConnector.
     */
    @Test
    public void testGetProtoConfig() {
        System.out.println("getProtoConfig");
        MMTClientConnector instance = new MMTClientConnector(3);
        MMTProtocolConfig expResult = new MMTProtocolConfig(1234, "1234");
        instance.setProtoConfig(expResult);
        MMTProtocolConfig result = instance.getProtoConfig();
        assertEquals(expResult, result);
    }

    /**
     * Test of setProtoConfig method, of class MMTClientConnector.
     */
    @Test
    public void testSetProtoConfig() {
        System.out.println("setProtoConfig");
        MMTProtocolConfig protoConfig = new MMTProtocolConfig(1234, "1234");
        MMTClientConnector instance = new MMTClientConnector(3);
        instance.setProtoConfig(protoConfig);
        assertEquals(protoConfig, instance.getProtoConfig());
    }

    /**
     * Test of processEvent method, of class MMTClientConnector.
     */
    @Test
    public void testProcessEvent() throws MMTConnectorException, IOException {
        ArrayList<MMTFieldValueHeader> fieldValueElements = new ArrayList<MMTFieldValueHeader>();
        MMTClientConnector instance = new MMTClientConnector(3);
        MMTClientConnectorConfig connectorConfig;
        MMTProtocolConfig protoConfig;

        connectorConfig = new MMTClientConnectorConfig();

        protoConfig = new MMTProtocolConfig(1234, "TestProto");


       // instance = new MMTClientConnector(UUID.randomUUID());
        instance.setConnectorConfig(connectorConfig);
        instance.setProtoConfig(protoConfig);

        fieldValueElements.add(new GenericFieldValueHeader("TransactionID", "1896548"));
        fieldValueElements.add(new GenericFieldValueHeader("UserID", "Wiss"));
        fieldValueElements.add(new GenericFieldValueHeader("CodeID", "1788"));

        long time = System.currentTimeMillis();
        try {
            instance.processEvent(time, "TODO", fieldValueElements);
            
        } catch (MMTInitializationException ex) {
            fail("The test case is a prototype.");
            Logger.getLogger(MMTClientConnectorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}