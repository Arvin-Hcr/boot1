package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.StatisticService;
import com.whqfl.service.impl.StatisticServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "GetOrderStaticServlet",urlPatterns = "/GetOrderStaticServlet")
public class GetOrderStaticServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        ResponseDto responseDto = new ResponseDto();
        StatisticService statisticService=new StatisticServiceImp();
        Map<String, Object> nearOneTypeUser = statisticService.getOrder();
        responseDto.setData(nearOneTypeUser);
        response.getWriter().print(new Gson().toJson(responseDto));
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
