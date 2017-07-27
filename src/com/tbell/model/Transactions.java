package com.tbell.model;

import com.tbell.helpers.atmManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Transactions {
    private int id;
    private String name;
    private double amount;
    private Statement statement;

    public Transactions(String name, Double amount, Statement statement) {
        this.name = name;
        this.amount = amount;
        this.statement = statement;

    }
    public Transactions(int id, String name, Double amount, Statement statement){
        this(name,amount,statement);
        this.id = id;
    }

    public void Save() throws SQLException {
        String formattedSql = String.format("INSERT INTO transactions (name, amount) VALUES ('%s', %s)", name, amount);
        statement.executeUpdate(formattedSql);

    }


    public static List<Transactions> findAll(atmManager atm) throws SQLException {
        ResultSet rs = atm.findAll("transactions");
        List<Transactions> tempCollection = new ArrayList<>();
        Statement tempStatement = atm.getStatement();

        while (rs.next()) {
            String name = rs.getString("name");
            double amount = rs.getDouble("amount");
            Transactions tempTransactions = new Transactions(rs.getInt("id"), name, amount, tempStatement);
            tempCollection.add(tempTransactions);
        }
        return tempCollection;
    }
    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "name= '" + name + '\'' +
                ", amount= " + amount +
                '}';
    }
}


