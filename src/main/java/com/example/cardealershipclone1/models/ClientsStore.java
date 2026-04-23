package com.example.cardealershipclone1.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.example.cardealershipclone1.database.DBConnection;

public class ClientsStore {

    public void addClient(Client client) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO clients(id,name, phone, email, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,client.getId());
            ps.setString(2, client.getName());
            ps.setString(3, client.getPhone());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getAddress());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteClient(int id) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "DELETE FROM clients WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateClient(Client client) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "UPDATE clients SET name=?, phone=?, email=?, address=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, client.getName());
            ps.setString(2, client.getPhone());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getAddress());
            ps.setInt(5, client.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}