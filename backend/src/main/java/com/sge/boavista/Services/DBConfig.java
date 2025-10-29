package com.sge.boavista.Services;

import com.sge.boavista.Entities.DTO.API.DbConnectionDetails;
import com.sge.boavista.Utils.LogUtil;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DBConfig {


    public static DataSource connection;
    private static DbConnectionDetails connectionDetails;


    public static synchronized void setConnectionDetails(DbConnectionDetails newDetails) {
        connectionDetails = newDetails;
        if(connection != null) closedConnection(connection);
        connection = openConnection();

    }

    public  static synchronized DataSource openConnection() {

        try {
            HikariDataSource newDataSource = new HikariDataSource();
            newDataSource.setDriverClassName("org.firebirdsql.jdbc.FBDriver");
            newDataSource.setJdbcUrl(connectionDetails.getUrl());
            newDataSource.setUsername(connectionDetails.getUsername());
            newDataSource.setPassword(connectionDetails.getPassword());

            return newDataSource;
        } catch (Exception e) {
            LogUtil.salvar("Erro ao abrir Conexão com o Banco: " + e.getMessage());
            return null;
        }

    };

    public static synchronized void closedConnection(DataSource conn) {
        try {
            conn.getConnection().close();
        } catch (SQLException e) {
            LogUtil.salvar("Erro ao Fechar Conexão com o Banco: " + e.getMessage());
        }
    }



}
