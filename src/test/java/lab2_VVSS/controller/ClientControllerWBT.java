package lab2_VVSS.controller;

import lab2_VVSS.model.Client;
import lab2_VVSS.repository.DataManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by rbalanescu on 3/28/2018.
 */
public class ClientControllerWBT {
    private ClientController clientController;
    private static String ID = "defaultID";
    private static String ID2 = "defaultID2";
    private static String ADDRESS = "12C, Str. Primaverii, Cluj-Napoca";
    private static String NAME = "Balanescu Rosana";
    private static Client c = new Client(NAME,ADDRESS,ID);


    @Before
    public void setRepo() {
        DataManager dataManager = new DataManager();
        dataManager.setFileIssue("src/test/issue.txt");
        dataManager.setFileClient("src/test/client.txt");
        dataManager.setClients(new ArrayList<Client>());
        dataManager.SaveChanges();
        clientController = new ClientController(dataManager);
        clientController.AddClient(NAME,ADDRESS,ID);
    }

    @Test
    public void testAddIndexInvalidYear() {
        Assert.assertEquals("Invalid year!",clientController.AddClientIndex(c,1800,12,3));
    }

    @Test
    public void testAddIndexValidYear() {
        Assert.assertEquals(null,clientController.AddClientIndex(c,2018,12,3));
    }

    @Test
    public void testCase3() {
        // line 2: FT
        Assert.assertEquals("Invalid month number!",clientController.AddClientIndex(c,2018,-1,3));
    }

    @Test
    public void testCase4() {
        // line 2: TT
        Assert.assertEquals(null,clientController.AddClientIndex(c,2018,8,3));
    }

    @Test
    public void testCase5() {
        // line 2: TF
        Assert.assertEquals("Invalid month number!",clientController.AddClientIndex(c,2018,13,3));
    }

    @Test
    public void testCase6() {
        // line 3: T
        Assert.assertEquals(null,clientController.AddClientIndex(c,2018,8,3));
    }

    @Test
    public void testCase7() {
        // line 3: F
        Assert.assertEquals("Money to pay can't be less than 0!",clientController.AddClientIndex(c,2018,8,-17));
    }
}
