package lab2_VVSS.repository;


import lab2_VVSS.model.Client;
import lab2_VVSS.model.Issue;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DataManager {
    private String fileClient = "src/main/client.txt";
    private String fileIssue = "src/main/issue.txt";
    private ArrayList<Client> Clients;
    private ArrayList<Issue> Issues;

    public void setClients(ArrayList<Client> clients) {
        Clients = clients;
    }

    public void setIssues(ArrayList<Issue> issues) {
        Issues = issues;
    }

    public DataManager() {
        Clients = new ArrayList<>();
        Issues = new ArrayList<>();

        LoadClient();
        LoadIssues();
    }

    private void LoadClient() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileClient));
            String line;
            while ((line = br.readLine()) != null) {
                int i1 = line.indexOf(",");
                String[] tokens = line.split(",");
                String name = tokens[0];
                String address = tokens[1];
                String id = tokens[2];
                Clients.add(new Client(name, address, id));
            }
            br.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void LoadIssues() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileIssue));
            String line;
            Boolean clientLine = true;
            String name = "";
            String address = "";
            String id = "";
            String sYear;
            String sMonth;
            String sToPay;
            String sPaid;
            String[] tokens;

            while ((line = br.readLine()) != null) {
                if (clientLine) {
                    tokens = line.split(",");
                    name = tokens[0];
                    address = tokens[1];
                    id = tokens[2];
                    clientLine = false;
                } else {
                    tokens = line.split(",");
                    sYear = tokens[0];
                    sMonth = tokens[1];
                    sToPay = tokens[2];
                    sPaid = tokens[3];

                    Issues.add(new Issue(
                            new Client(name, address, id),
                            Integer.parseInt(sYear), Integer.parseInt(sMonth), Float.parseFloat(sToPay), Float.parseFloat(sPaid)));
                    clientLine = true;
                }
            }
            br.close();
        } catch (NumberFormatException nfe) {
            System.err.println("Number format exception when reading from file");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void SaveChanges() {
        try {
            File fClient = new File(fileClient);
            File fIssue = new File(fileIssue);
            FileWriter pwClient = new FileWriter(fClient.getAbsolutePath());
            FileWriter pwIssue = new FileWriter(fIssue.getAbsolutePath());
            String s;
            try (BufferedWriter bwc = new BufferedWriter(pwClient)) {
                s = "";
                for (Iterator<Client> i = Clients.iterator(); i.hasNext(); ) {
                    Client c = i.next();
                    s += c.toString() + System.getProperty("line.separator");
                }
                bwc.write(s);
            }
            try (BufferedWriter bwi = new BufferedWriter(pwIssue)) {
                s = "";
                for (Iterator<Issue> i = Issues.iterator(); i.hasNext(); ) {
                    Issue iss = i.next();
                    s += iss.toString() + System.getProperty("line.separator");
                }
                bwi.write(s);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<Issue> getIssuesList() {
        return Issues;
    }

    public ArrayList<Client> getClientsList() {
        return Clients;
    }

    public String getFileClient() {
        return fileClient;
    }

    public void setFileClient(String fileClient) {
        this.fileClient = fileClient;
    }

    public String getFileIssue() {
        return fileIssue;
    }

    public void setFileIssue(String fileIssue) {
        this.fileIssue = fileIssue;
    }


}
