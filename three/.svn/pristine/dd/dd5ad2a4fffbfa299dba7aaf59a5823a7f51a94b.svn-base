package com.whqfl.servlet;



import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.NewsService;
import com.whqfl.service.RechargeRuleService;
import com.whqfl.service.impl.NewsServiceImpl;
import com.whqfl.service.impl.RechargeRuleServiceImpl;
import com.whqfl.util.IntegerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelNewsServlet",urlPatterns = "/DelNewsServlet")
public class DelNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsService newsService=new NewsServiceImpl();
        ResponseDto responseDto=new ResponseDto();
        Integer id= IntegerUtils.ToInteger(req.getParameter("ruleId"));
        responseDto.setData(newsService.delNews(id));
        responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        responseDto.setMessage("操作成功");
        resp.getWriter().print(new Gson().toJson(responseDto));
    }
}
