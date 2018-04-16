package lab2_VVSS.model;

import java.util.Objects;

public class Client {
    private String Name;
    private String Address;
    private String idClient;

    public Client() {
        this.Name = "";
        this.Address = "";
        this.idClient = "";
    }

    public Client(Client client) {
        this.Name = client.Name;
        this.Address = client.Address;
        this.idClient = client.idClient;
    }

    public Client(String name, String address, String idC) {
        this.Name = name;
        this.Address = address;
        this.idClient = idC;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s", this.Name, this.Address, this.idClient);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (!(object instanceof Client)) return false;
        Client c = (Client) object;

        if ((c.idClient.equals(this.idClient))) return true;

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.Name);
        hash = 59 * hash + Objects.hashCode(this.Address);
        return hash;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
}
