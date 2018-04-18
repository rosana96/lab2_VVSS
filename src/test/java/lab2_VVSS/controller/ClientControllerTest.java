package lab2_VVSS.controller;

import lab2_VVSS.model.Client;
import lab2_VVSS.repository.DataManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Rosana Balanescu on 14.03.2018.
 */
public class ClientControllerTest {
    private ClientController clientController;
    static String ID = "defaultID";
    private static String ID2 = "defaultID2";
    static String ADDRESS = "12C,Str. Primaverii,Cluj-Napoca";
    private static String NAME = "Balanescu Rosana";


    @Before
    public void setRepo() {
        DataManager dataManager = new DataManager();
        dataManager.setFileClient("src/test/client.txt");
        dataManager.setFileIssue("src/test/issue.txt");
        dataManager.setClients(new ArrayList<Client>());
        dataManager.SaveChanges();
        clientController = new ClientController(dataManager);

    }

    @Test
    public void testAddInvalidClient() throws Exception {
        String name = "AAA Balanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu Rosana";
        Assert.assertNotEquals(null, clientController.AddClient(name, ADDRESS, ID));
        Assert.assertEquals(0, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testAddValidClient() throws Exception {
        Assert.assertEquals(null, clientController.AddClient(NAME, ADDRESS, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase3() {
        String name = "Balanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu Rosana";
        Assert.assertEquals(null, clientController.AddClient(name, ADDRESS, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase4() {
        String name="";
        Assert.assertNotEquals(null, clientController.AddClient(name, ADDRESS, ID));
        Assert.assertEquals(0, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase5() {
        String name1="R";
        Assert.assertEquals(null, clientController.AddClient(name1, ADDRESS, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase6() {
        String name255 = "alanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu Rosana";
        Assert.assertEquals(null, clientController.AddClient(name255, ADDRESS, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase7() {
        String name257 = "BBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu Rosana";
        Assert.assertNotEquals(null, clientController.AddClient(name257, ADDRESS, ID));
        Assert.assertEquals(0, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase8() {
        Assert.assertEquals(null, clientController.AddClient(NAME, ADDRESS, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase9() {
        String address="";
        Assert.assertNotEquals(null, clientController.AddClient(NAME, address, ID));
        Assert.assertEquals(0, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase10() {
        String address="12C, Str. Primaverii, Cluj,Napoca";
        Assert.assertNotEquals(null, clientController.AddClient(NAME, address, ID));
        Assert.assertEquals(0, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase11() {
        String address="12C";
        Assert.assertNotEquals(null, clientController.AddClient(NAME, address, ID));
        Assert.assertEquals(0, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase12() {
        String address="12C, Str. Primaverii";
        Assert.assertNotEquals(null, clientController.AddClient(NAME, address, ID));
        Assert.assertEquals(0, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase13() {
        String address=", Str. Primaverii, Cluj-Napoca";
        Assert.assertNotEquals(null, clientController.AddClient(NAME, address, ID));
        Assert.assertEquals(0, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase14() {
        String address="123, Str. Primaverii, Cluj-Napoca";
        Assert.assertEquals(null, clientController.AddClient(NAME, address, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase15() {
        String address="1234C, Str. Primaverii, Cluj-Napoca";
        Assert.assertEquals(null, clientController.AddClient(NAME, address, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase16() {
        String address="12345C, Str. Primaverii, Cluj-Napoca";
        Assert.assertNotEquals(null, clientController.AddClient(NAME, address, ID));
        Assert.assertEquals(0, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testCase17() {
        String address="1, Str. Primaverii, Cluj-Napoca";
        Assert.assertEquals(null, clientController.AddClient(NAME, address, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());
    }

}