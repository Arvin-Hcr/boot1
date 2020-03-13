package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.NewsService;
import com.whqfl.service.RechargeRuleService;
import com.whqfl.service.impl.NewsServiceImpl;
import com.whqfl.service.impl.RechargeRuleServiceImpl;
import com.whqfl.util.IntegerUtils;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebFault;
import java.io.IOException;
import java.net.HttpCookie;
@WebServlet(name = "GetNewsServlet", urlPatterns = "/GetNewsServlet")
public class GetNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取参数
        Integer pageNumber = IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        Integer pageSize = IntegerUtils.ToInteger(request.getParameter("pageSize"));
        String searchStartTime = request.getParameter("searchStartTime");
        String searchEndTime = request.getParameter("searchEndTime");
        String searchName = request.getParameter("searchName");
        Integer searchStatus = IntegerUtils.ToInteger(request.getParameter("searchStatus"));
        NewsService newsService= new NewsServiceImpl();
        //返回
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setData(newsService.getNewsList(pageNumber,pageSize,searchStartTime,searchEndTime,searchName,searchStatus));
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseDto.setMessage("操作成功");
        responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        response.getWriter().print(new Gson().toJson(responseDto));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
