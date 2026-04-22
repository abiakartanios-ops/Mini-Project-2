package com.example.cardealershipclone1.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientsStore {

    private ObservableList<Client> clients = FXCollections.observableArrayList();

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void updateClient(Client oldClient, Client newClient) {
        int index = clients.indexOf(oldClient);
        if (index != -1) {
            clients.set(index, newClient);
        }
    }

    public void deleteClient(Client client) {
        clients.remove(client);
    }
}