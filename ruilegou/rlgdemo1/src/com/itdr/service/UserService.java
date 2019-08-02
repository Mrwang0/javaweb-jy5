package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Users;

import java.util.List;

public class UserService {
    private UserDao ud = new UserDao();

    public ResponseCode selectAll(String pageSize, String pageNum) {
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }


        List<Users> li = ud.selectAll(pageSize,pageNum);

        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    //用户登录
    public ResponseCode selectOne(String username, String password) {
        ResponseCode rs = new ResponseCode();
        if (username == null || username.equals("") || password == null || password.equals("")){
            rs.setStatus(1);
            rs.setMag("密码错误");
            return rs;
        }
        //查找是否有这样一个用户
        Users u = ud.selectOne(username,password);
        //如果用户不存在
        if (u == null){
            rs.setStatus(1);
            rs.setMag("密码错误");
            return rs;
        }
        //用户权限
        if (u.getType() != 1){
            rs.setStatus(2);
            rs.setMag("没有操作权限！！！");
            return rs;
        }

        rs.setStatus(0);
        rs.setData(u);

        return rs;
    }

    //用户禁用
    public ResponseCode selectOne(String uids) {
        ResponseCode rs = new ResponseCode();
        if (uids  == null || uids.equals("")){
            rs.setStatus(Const.USER_IF_CODE);
            rs.setMag(Const.USER_IF_MSG);
            return rs;
        }

        //字符串转数值
        Integer uid = null;
        try {
             uid = Integer.parseInt(uids);
        }catch (Exception e){
            rs.setStatus(Const.USER_ILLEGAL_CODE);
            rs.setMag(Const.USER_ILLEGAL_MSG);
            return rs;
        }



        //查找是否有这样一个用户
        Users u = ud.selectOne(uid);
        //如果用户不存在
        if (u == null){
            rs.setStatus(Const.USER_NO_CODE);
            rs.setMag(Const.USER_NO_MSG);
            return rs;
        }
        //用户禁用情况
        if (u.getStats() == 1){
            rs.setStatus(Const.USER_DISABLE_CODE);
            rs.setMag(Const.USER_DISABLE_MSG);
            return rs;
        }

        //禁用用户
        int row = ud.UpdateByUid(uid);
        if (row <= 0){
            rs.setStatus(Const.USER_UPDATE_CODE);
            rs.setMag(Const.USER_UPDATE_MSG);
        }

        rs.setStatus(0);
        rs.setData(row);

        return rs;
    }
}
