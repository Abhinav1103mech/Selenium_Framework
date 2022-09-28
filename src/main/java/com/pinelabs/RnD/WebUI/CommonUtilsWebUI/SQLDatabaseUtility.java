package com.pinelabs.RnD.WebUI.CommonUtilsWebUI;

import com.pinelabs.RnD.AndroidUI.Constants.FilePaths;

import java.sql.*;
import java.util.Map;

public class SQLDatabaseUtility {
    public static Connection con;
    public static Statement stmt;
    public static ResultSet row;
    static Map<Object, Object> value;
    static String query;

    public static Connection setUPConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(CommonUtils.readPropertyfile(FilePaths.dbPropertiesPath).getProperty("sqlDbConnectionURL"));

            System.out.println("SQL Connection established");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return con;
    }

    private static String fetchQuery(String query, Map<Object, Object> values) {

        for (Object key : values.keySet()) {
            query = query.replaceAll(key.toString(), values.get(key).toString());
        }
        System.out.println(query);
        return query;
    }


    public static void executeSelectquery(String sqlQuery, Map<Object, Object> values) {
        query = fetchQuery(sqlQuery, values);

        try {
            stmt = con.createStatement();
            row = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeUpdateQuery(String sqlQuery, Map<Object, Object> values) {
        query = fetchQuery(sqlQuery, values);

        try {
            stmt = con.createStatement();
            row = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



