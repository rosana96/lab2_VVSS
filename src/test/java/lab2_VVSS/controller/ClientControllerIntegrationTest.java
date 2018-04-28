package lab2_VVSS.controller;

import lab2_VVSS.model.Client;
import lab2_VVSS.model.Issue;
import lab2_VVSS.repository.DataManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static lab2_VVSS.controller.ClientControllerWBT.*;

public class ClientControllerIntegrationTest {
    private static String ID = "defaultID";
    private static String ADDRESS = "12C,Str. Primaverii,Cluj-Napoca";
    private static String NAME = "Balanescu Rosana";
    private ClientController clientController;
    private DataManager dataManager = new DataManager();

    @Before
    public void setRepo() {
        dataManager.setFileIssue("src/test/issue.txt");
        dataManager.setFileClient("src/test/client.txt");
        dataManager.setClients(new ArrayList<Client>());
        dataManager.setIssues(new ArrayList<Issue>(Arrays.asList(i1)));
        dataManager.SaveChanges();
        clientController = new ClientController(dataManager);
    }


    //BIG BANG
    @Test
    public void integrationTest() throws Exception {
        //a
        Assert.assertEquals(null, clientController.AddClient(NAME, ADDRESS, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());

        //b
        Assert.assertEquals(null, clientController.AddClientIndex(c, 2018, 8, 3));

        //c
        Assert.assertEquals(2, clientController.getIssues(c).size());
    }

    //INCREMENTAL
    @Test
    public void incrementalTestA() {
        Assert.assertEquals(null, clientController.AddClient(NAME, ADDRESS, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void incrementalTestAB() {
        Assert.assertEquals(null, clientController.AddClient(NAME, ADDRESS, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());

        Assert.assertEquals(null, clientController.AddClientIndex(c, 2018, 8, 3));

    }

    @Test
    public void incrementalTestABC() throws Exception {
        Assert.assertEquals(null, clientController.AddClient(NAME, ADDRESS, ID));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());

        Assert.assertEquals(null, clientController.AddClientIndex(c, 2018, 8, 3));

        Assert.assertEquals(2, clientController.getIssues(c).size());
    }
}
