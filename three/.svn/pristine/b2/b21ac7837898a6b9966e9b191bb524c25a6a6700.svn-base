package com.whqfl.servlet;

import com.google.gson.Gson;

import com.whqfl.entity.ResponseDto;
import com.whqfl.service.ChangePassService;

import com.whqfl.service.impl.ChangePassServiceImpl;

import com.whqfl.util.IntegerUtils;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ChangPasswordServlet",urlPatterns = "/ChangPasswordServlet")
public class ChangPasswordServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        //log4j
        Logger logger = Logger.getLogger("ChangPasswordServlet");
        ChangePassService changePassService=new ChangePassServiceImpl();
        Integer staffId = IntegerUtils.ToInteger(request.getParameter("userName"));
        String password = request.getParameter("password");
        try {
            int len = changePassService.pass(staffId,password);
            responseDto.setData(len);
            if (len >0 ){
                responseDto.setMessage("修改成功");
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);

            }else {
                responseDto.setMessage("修改失败");
                responseDto.setStatus(ResponseDto.FAILURE_CODE);
            }
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
        }

        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
