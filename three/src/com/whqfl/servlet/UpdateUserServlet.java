package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.UserService;
import com.whqfl.service.impl.UserServiceImpl;
import com.whqfl.util.IntegerUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateUserServlet",urlPatterns = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String userName=request.getParameter("userName");
        String phone=request.getParameter("userPhone");
        Integer status=IntegerUtils.ToInteger(request.getParameter("userStatus"));
        String idCard=request.getParameter("userIdCard");
        String birthday=request.getParameter("userBirthday");
        Integer sex=IntegerUtils.ToInteger(request.getParameter("userSex"));
        String address=request.getParameter("userAddress");
        String momo=request.getParameter("userMomo");
        String area=request.getParameter("area");
        Integer userID= IntegerUtils.ToInteger(request.getParameter("userId"));
        Logger logger=Logger.getLogger("UpdateUserServlet");
        Gson gson = new Gson();
        ResponseDto responseDto=new ResponseDto();
        UserService userService=new UserServiceImpl();
        try {
            int result=userService.updateUser(userName,phone,status,idCard,birthday,sex,address,momo,area,userID);
            responseDto.setData(userService.updateUser(userName,phone,status,idCard,birthday,sex,address,momo,area,userID));
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setMessage("修改成功");
        } catch (Exception e) {
           logger.error(e.getMessage());
           responseDto.setMessage("修改失败");
           responseDto.setStatus(ResponseDto.FAILURE_CODE);
        }
        response.getWriter().print(gson.toJson(responseDto));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
