package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.Product;
import com.itdr.service.ProductService;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/production/*")
public class ProductController extends HttpServlet {
    private ProductService po = new ProductService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //怎么获取请求路径信息
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        //判断一下是什么样的请求
        ResponseCode rs = null;

        switch (path){
            case "list":
                rs = listDo(request);
                break;
            case "search":
                rs = searchDo(request);
                break;
            case "save":
                rs = saveDo(request);
                break;
        }

        //返回响应数据
        response.getWriter().write(rs.toString());
    }



    private ResponseCode listDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();
      /*  HttpSession session = request.getSession();
        Product product = (Product) session.getAttribute("user");
        if (product == null){
            rs.setStatus(10);
            rs.setMag("用户未登录，请登录!");
            return rs;
        }*/

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        rs = po.selectAll(pageSize, pageNum);

        return rs;
    }

    //搜索商品
    private ResponseCode searchDo(HttpServletRequest request) {
        //获取参数
        String productname = request.getParameter("productname");
        String productid = request.getParameter("productid");

        ResponseCode rs = po.selectOne(productname,productid);
        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("product",rs.getData());
        return rs;
    }

    //更新商品
    private ResponseCode saveDo(HttpServletRequest request) {
        String productid = request.getParameter("productid");
        String productcateid = request.getParameter("productcateid");
        String productname = request.getParameter("productname");
        String productsubtitle = request.getParameter("productsubtitle");
        String productprice = request.getParameter("productprice");
        ResponseCode rs = po.update(productid,productcateid,productname,productsubtitle,productprice);
        return rs;
    }


}
