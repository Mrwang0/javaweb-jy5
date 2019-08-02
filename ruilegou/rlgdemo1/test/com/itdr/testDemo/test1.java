package com.itdr.testDemo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test1 {
    @Test
    public void test1() throws SQLException {
        ComboPooledDataSource co = new ComboPooledDataSource();
        Connection connection = co.getConnection();
        String sql = "select * from Users";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString(2));
        }
    }
}
