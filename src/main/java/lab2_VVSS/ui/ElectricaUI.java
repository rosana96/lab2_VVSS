package lab2_VVSS.ui;



import lab2_VVSS.controller.ClientController;
import lab2_VVSS.model.Client;
import lab2_VVSS.model.Issue;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class ElectricaUI {
    public ClientController ctrl;
    Scanner in;

    public ElectricaUI(ClientController ctrl) {
        this.ctrl = ctrl;
        this.in = new Scanner(System.in);
    }

    public ClientController getCtrl() {
        return this.ctrl;
    }

    public void setCtrl(ClientController newCtrl) {
        this.ctrl = newCtrl;
    }

    public Scanner getIn() {
        return this.in;
    }

    public void setIn(Scanner newIn) {
        this.in = newIn;
    }

    public void printMenu() {
        String menu;
        menu = "Electrica Admin Menu: \n";
        menu += "\t 1 - to add a new client; \n";
        menu += "\t 2 - to add a new index; \n";
        menu += "\t 3 - to list the current invoices (and possible penalties or pending payment) of the subscribers; \n";
        menu += "\t 0 - exit \n";

        System.out.println(menu);
    }


    public void Run() {
        printMenu();

        try {
            int cmd = in.nextInt();
            in.nextLine();

            while (cmd != 0) {
                if (cmd == 1) {
                    System.out.println("Enter name:");
                    String name = in.nextLine();
                    System.out.println("Enter address:");
                    String address = in.nextLine();
                    System.out.println("Enter id:");
                    String id = in.nextLine();

                    System.out.println(ctrl.AddClient(name, address, id));

                }
                if (cmd == 2) {


                    System.out.println("Enter name:");
                    String name = in.nextLine();
                    System.out.println("Enter address:");
                    String address = in.nextLine();
                    System.out.println("Enter id:");
                    String id = in.nextLine();
                    Client c = new Client(name, address, id);


                    System.out.println("Enter the YEAR:");
                    String yearS = in.nextLine();
                    int year = Integer.parseInt(yearS);
                    System.out.println("Enter the MONTH:");
                    String monthS = in.nextLine();
                    int month = Integer.parseInt(monthS);

                    System.out.println("Enter toPay:");
                    String sumToPayS = in.nextLine();
                    float sumToPay = Float.parseFloat(sumToPayS);

                    System.out.println(ctrl.AddClientIndex(c, year, month, sumToPay));

                }
                if (cmd == 3) {
                    System.out.println("list the current invoices :");

                    System.out.println("Enter name:");
                    String name = in.nextLine();
                    System.out.println("Enter address:");
                    String address = in.nextLine();
                    System.out.println("Enter id:");
                    String id = in.nextLine();
                    Client c = new Client(name, address, id);

                    try {
                        StringBuilder s = new StringBuilder();
                        String pen = "";
                        List<Issue> issues = ctrl.getIssues(c);
                        for (int i = 0; i < issues.size(); i++) {
                            if (issues.get(i).getClient().equals(c)) {
                                Issue issue = issues.get(i);
                                pen = String.format("Year: %d, Month: %d, Paid: %2.0f, To pay: %2.0f\n", issue.getYear(), issue.getMonth(), issue.getPaid(), issue.getToPay());
                                s.append(pen);
                            }
                        }
                        if (s.toString().equals(""))
                            System.out.println("Client has no invoices!");
                        else
                            System.out.println(s.toString());
                    }
                    catch(Exception e) {
                        System.out.println(e.getMessage());
                    }

                }

                printMenu();
                cmd = in.nextInt();
                in.nextLine();
            }
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input!");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number format!");
            System.out.println(ex.getMessage());
        }
    }
}

