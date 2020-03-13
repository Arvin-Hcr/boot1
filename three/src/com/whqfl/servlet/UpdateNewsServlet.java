package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.NewsService;
import com.whqfl.service.RechargeRuleService;
import com.whqfl.service.impl.NewsServiceImpl;
import com.whqfl.service.impl.RechargeRuleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "UpdateNewsServlet",urlPatterns = "/UpdateNewsServlet")
public class UpdateNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;chaset=utf-8");
        ResponseDto responseDto=new ResponseDto();
        NewsService newsService=new NewsServiceImpl();
        String ruleId = req.getParameter("ruleId");
        String ruleName = req.getParameter("ruleName");
        String ruleCoff = req.getParameter("ruleCoff");
        String ruleStatus = req.getParameter("ruleStatus");
        String ruleCreatedDate = req.getParameter("ruleCreatedDate");
        String ruleEndDate = req.getParameter("ruleEndDate");
        String ruleAmount = req.getParameter("ruleAmount");
        try {
            int byId = newsService.updateNewsId(ruleId, ruleId, ruleName, ruleCoff, ruleStatus, ruleCreatedDate, ruleEndDate, ruleAmount);
            responseDto.setData(byId);
            responseDto.setMessage("操作成功");
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            resp.getWriter().print(new Gson().toJson(responseDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
