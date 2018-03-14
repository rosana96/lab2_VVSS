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
        String name = "AAA Balanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu Rosana" +
                "Balanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu RosanaBalanescu Rosana";
        String address = "my address";
        String id = "id1";
        Assert.assertNotEquals(null, clientController.AddClient(name, address, id));
        Assert.assertEquals(0, clientController.get_dataManager().getClientsList().size());
    }

    @Test
    public void testAddValidClient() throws Exception {
        String name = "Balanescu Rosana";
        String address = "my address";
        String id = "id2";
        Assert.assertEquals(null, clientController.AddClient(name, address, id));
        Assert.assertEquals(1, clientController.get_dataManager().getClientsList().size());

    }

}