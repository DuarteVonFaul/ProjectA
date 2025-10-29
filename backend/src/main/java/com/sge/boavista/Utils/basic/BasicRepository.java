package com.sge.boavista.Utils.basic;

import com.sge.boavista.Services.DBConfig;

import javax.sql.DataSource;

public class BasicRepository {

    protected DataSource dataSource() {
        if(DBConfig.connection == null){
            DBConfig.openConnection();
        }
        return DBConfig.connection;
    }

}
