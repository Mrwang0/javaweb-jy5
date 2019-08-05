package com.itdr.service;

import com.itdr.common.ResponseCode;
import com.itdr.dao.ProductDao;
import com.itdr.pojo.Product;


import java.util.List;

public class ProductService {
    private ProductDao pd = new ProductDao();

    //商品list
    public ResponseCode selectAll(String pageSize, String pageNum) {
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }


        List<Product> list= pd.selectAll(pageSize,pageNum);

        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(list);
        return rs;
    }
    //查找商品
    public ResponseCode selectOne(String productname, String productid) {
        ResponseCode rs = new ResponseCode();
        if (productname == null || productname.equals("") || productid == null || productid.equals("")){
            rs.setStatus(11);
            rs.setMag("请输入商品名或商品号");
            return rs;
        }
        //查找是否有这样一个商品
        Product p = pd.selectOne(productname,productid);
        //如果商品不存在
        if (p == null){
            rs.setStatus(1);
            rs.setMag("商品不存在！");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }

    //更新商品
    public ResponseCode update(String productid, String productcateid, String productname,String productsubtitle,String productprice) {
        ResponseCode rs = new ResponseCode();
        if (productid == null || productid.equals("")){
            rs.setStatus(14);
            rs.setMag("请输入商品号");
            return rs;
        }
        int pr = pd.updateBystatus(productid,productcateid,productname,productsubtitle,productprice);
        if (pr == 0){
            rs.setStatus(13);
            rs.setMag("商品不存在");
        }
        rs.setStatus(0);
        rs.setData(pr);
        return rs;
    }
}
