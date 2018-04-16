package lab2_VVSS.controller;

import lab2_VVSS.model.Client;
import lab2_VVSS.model.Issue;
import lab2_VVSS.repository.DataManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rbalanescu on 3/28/2018.
 */
public class ClientControllerWBT {
    private static String ID = "defaultID";
    private static String ID2 = "defaultID2";
    private static String ADDRESS = "12C,Str. Primaverii,Cluj-Napoca";
    private static String NAME = "Balanescu Rosana";
    private static Client c = new Client(NAME, ADDRESS, ID);
    private ClientController clientController;
    private Issue i1 = new Issue(c, 2018, 1, 3, 0);
    private Issue i2 = new Issue(c, 2018, 2, 3, 0);
    private Issue i3 = new Issue(c, 2018, 3, 3, 0);
    private Issue i4 = new Issue(c, 2018, 4, 3, 0);
    private Issue i5 = new Issue(c, 2018, 5, 3, 0);
    private DataManager dataManager = new DataManager();


    @Before
    public void setRepo() {
        dataManager.setFileIssue("src/test/issue.txt");
        dataManager.setFileClient("src/test/client.txt");
        dataManager.setClients(new ArrayList<Client>());
        dataManager.setIssues(new ArrayList<Issue>(Arrays.asList(i1, i2, i3, i4, i5)));
        dataManager.SaveChanges();
        clientController = new ClientController(dataManager);
        clientController.AddClient(NAME, ADDRESS, ID);
    }

    @Test
    public void testAddIndexInvalidYear() {
        Assert.assertEquals("Invalid year!", clientController.AddClientIndex(c, 1800, 12, 3));
    }

    @Test
    public void testAddIndexValidYear() {
        Assert.assertEquals(null, clientController.AddClientIndex(c, 2018, 12, 3));
    }

    @Test
    public void testCase3() {
        // line 2: FT
        Assert.assertEquals("Invalid month number!", clientController.AddClientIndex(c, 2018, -1, 3));
    }

    @Test
    public void testCase4() {
        // line 2: TT
        Assert.assertEquals(null, clientController.AddClientIndex(c, 2018, 8, 3));
    }

    @Test
    public void testCase5() {
        // line 2: TF
        Assert.assertEquals("Invalid month number!", clientController.AddClientIndex(c, 2018, 13, 3));
    }

    @Test
    public void testCase6() {
        // line 3: T
        Assert.assertEquals(null, clientController.AddClientIndex(c, 2018, 8, 3));
    }

    @Test
    public void testCase7() {
        // line 3: F
        Assert.assertEquals("Money to pay can't be less than 0!", clientController.AddClientIndex(c, 2018, 8, -17));
    }

    @Test
    public void testCase8() {
        // line 5: F
        Client invalid = new Client(c);
        invalid.setAddress("1,a");
        Assert.assertEquals("Street name, number and/or city is missing!", clientController.AddClientIndex(invalid, 2018, 8, 3));
    }

    @Test
    public void testCase9() {
        // line 7: F
        Client inexistent = new Client(c);
        inexistent.setIdClient("lala");
        Assert.assertEquals("Client does not exist!", clientController.AddClientIndex(inexistent, 2018, 8, 3));
    }

    @Test
    public void testCase10() {
        // enters in for once
        Assert.assertEquals("Monthly index already exists!", clientController.AddClientIndex(c, 2018, 1, 3));
    }

    @Test
    public void testCase11() {
        // does not enter in for
        dataManager.setIssues(new ArrayList<Issue>());
        Assert.assertEquals(null, clientController.AddClientIndex(c, 2018, 1, 3));
        Assert.assertEquals(1, dataManager.getIssuesList().size());
    }

    @Test
    public void testCase12() {
        // enters in for twice
        Assert.assertEquals("Monthly index already exists!", clientController.AddClientIndex(c, 2018, 2, 3));
    }

    @Test
    public void testCase13() {
        // enters in for n-1 times
        Assert.assertEquals("Monthly index already exists!", clientController.AddClientIndex(c, 2018, 4, 3));
    }

    @Test
    public void testCase14() {
        // enters in for n times
        Assert.assertEquals("Monthly index already exists!", clientController.AddClientIndex(c, 2018, 5, 3));
    }
}
