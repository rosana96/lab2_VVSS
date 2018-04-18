package lab2_VVSS.controller;


import lab2_VVSS.model.Client;
import lab2_VVSS.model.Issue;
import lab2_VVSS.repository.DataManager;

import java.util.List;

public class ClientController {
    private DataManager _dataManager;

    public ClientController(DataManager _dataManager) {
        this._dataManager = _dataManager;
    }

    public DataManager get_dataManager() {
        return _dataManager;
    }

    private String ValidateClient(String name, String address, String id) {
        if (!name.equals("") && !name.equals(" ")) {
            for (int i = 0; i < name.length(); i++) {
                if (!(Character.isUpperCase(name.charAt(i)) || Character.isLowerCase(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))) {
                    return "Invalid character: " + name.charAt(i);
                }
            }
            if (name.length() > 256) {
                return "Invalid name - too long!";
            }
        } else {
            return "Name cannot be empty!";
        }

        String[] addressTokens = address.split(",");
        if (addressTokens.length != 3) {
            return "Street name, number and/or city is missing!";
        } else if (addressTokens[0].length() > 5 || addressTokens[0].length() == 0)
            return "Invalid number of street";
        return null;

    }

    public String AddClient(String name, String address, String id) {
        //validation
        String valid;
        if ((valid = ValidateClient(name, address, id)) != null) {
            return valid;
        }
        Client c = new Client(name, address, id);
        //uniqueness
        for (int j = 0; j < _dataManager.getClientsList().size(); j++) {
            if (_dataManager.getClientsList().get(j).equals(c)) {
                return "Client already exists!";
            }
        }
        try {
            _dataManager.getClientsList().add(c);
            _dataManager.SaveChanges();
            return null;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public String AddClientIndex(Client c, int year, int month, float toPay) {
        if (year > 1900) {
            if (month > 0 && month <= 12) {
                if (toPay >= 0) {
                    //validate client attributes
                    String valid;
                    if ((valid = ValidateClient(c.getName(), c.getAddress(), c.getIdClient())) == null) {
                        //check if client exist
                        Boolean exist = existsClient(c);
                        if (exist) {
                            Issue i = new Issue(c, year, month, toPay, 0);
                            //uniqueness
                            for (int j = 0; j < _dataManager.getIssuesList().size(); j++) {
                                if (_dataManager.getIssuesList().get(j).equals(i)) {
                                    return "Monthly index already exists!";
                                }
                            }

                            _dataManager.getIssuesList().add(i);
                            _dataManager.SaveChanges();
                            return null;
                        } else {
                            return "Client does not exist!";
                        }
                    } else {
                        return valid;
                    }
                } else {
                    return "Money to pay can't be less than 0!";
                }
            } else {
                return "Invalid month number!";
            }
        } else {
            return "Invalid year!";
        }
    }

    private Boolean existsClient(Client c) {
        Boolean exist = false;
        for (int i = 0; i < _dataManager.getClientsList().size(); i++) {
            if (_dataManager.getClientsList().get(i).equals(c)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public List<Issue> getIssues(Client c) throws Exception {

        if (!existsClient(c))
            throw new Exception("Client does not exist");

        return _dataManager.getIssuesList();

    }

}
