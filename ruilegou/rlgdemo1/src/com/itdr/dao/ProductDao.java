package com.itdr.dao;

import com.itdr.pojo.Product;
import com.itdr.utils.ToolUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {

        //查找所有商品
        public List<Product> selectAll(String pageSize, String pageNum) {
            ComboPooledDataSource co = ToolUtil.getCom();
            QueryRunner qr = new QueryRunner(co);
            String sql = "select * from product";
            List<Product> list = null;
            try {
                list= qr.query(sql,new BeanListHandler<Product>(Product.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }

    public Product selectOne(String productname, String productid) {
        ComboPooledDataSource co = ToolUtil.getCom();
        QueryRunner qr = new QueryRunner(co);
        String sql = "select * from product where name = ? and id = ?";
        Product p = null;
        try {
            p= qr.query(sql,new BeanHandler<Product>(Product.class),productname,productid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    //更新商品
    public int updateBystatus(String productid, String productcateid, String productname,String productsubtitle,String productprice) {
        ComboPooledDataSource co = ToolUtil.getCom();
        QueryRunner qr = new QueryRunner(co);
        String sql = "update product set categoryId = ?,name =?,subtitle = ?,price = ? where id = ? && status = 0";
        int row = 0;
        try {
            row = qr.update(sql,productcateid,productname,productsubtitle,productprice,productid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;

    }


}
