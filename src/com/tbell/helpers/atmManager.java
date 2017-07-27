package com.tbell.helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class atmManager {
    Statement statement;

    public atmManager(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public Statement getStatement() {
        return statement;
    }

    public void CreateAtmTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE transactions (id INTEGER PRIMARY KEY, name STRING, amount INTEGER )");
    }

    public void DropAtmTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS transaction");
    }

    public ResultSet findAll(String table) throws SQLException {
        String formattedSql = String.format("SELECT * FROM %s", table);
        ResultSet rs = statement.executeQuery(formattedSql);
        return rs;
    }


}
