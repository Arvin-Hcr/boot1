package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.CardManagerService;
import com.whqfl.service.impl.CardManagerServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetVipServlet",urlPatterns = "/GetVipServlet")
public class GetVipServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        ResponseDto responseDto = new ResponseDto();
        CardManagerService cardManagerService=new CardManagerServiceImp();
        try {
            List<Map<String, Object>> vip = cardManagerService.getVip();
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setData(vip);
        } catch (Exception e) {
            responseDto.setStatus(ResponseDto.FAILURE_CODE);
            responseDto.setMessage(e.getMessage());
            e.printStackTrace();
        }
        response.getWriter().print(new Gson().toJson(responseDto));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
