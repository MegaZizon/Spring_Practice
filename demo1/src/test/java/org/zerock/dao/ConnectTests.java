package org.zerock.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {

    @Test
    public void test1(){
        int v1 = 10;

        Assertions.assertEquals(v1,10);
    }


    @Test
    public void testConnection() throws Exception{
        Class.forName("org.mariadb.jdbc.Driver");


        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/webdb",
                "webuser",
                "webuser");

        Assertions.assertNotNull(connection);
        connection.close();

    }
}
