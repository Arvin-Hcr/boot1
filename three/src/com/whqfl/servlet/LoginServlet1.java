package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.StaffService;
import com.whqfl.service.StaffService1;
import com.whqfl.service.impl.StaffServiceImpl;
import com.whqfl.service.impl.StaffServiceImpl1;
import com.whqfl.util.IntegerUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LoginServlet1",urlPatterns = "/LoginServlet1")
public class LoginServlet1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        //log4j
        Logger logger = Logger.getLogger("LoginServlet1");
        StaffService1 staffService = new StaffServiceImpl1();
        Integer staffId = IntegerUtils.ToInteger(request.getParameter("userName"));
        String password = request.getParameter("password");
        try {
            Map<String, Object> map = staffService.login(staffId,password);
            responseDto.setData(map);
            if (map != null){
                responseDto.setMessage("登陆成功");
            }else {
                responseDto.setMessage("登陆失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseDto.setMessage(e.getMessage());
        }
        responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
