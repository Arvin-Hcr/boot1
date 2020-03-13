package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.RechargeRule;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.RechargeRuleService;
import com.whqfl.service.RechargerecordService;
import com.whqfl.service.UserService;
import com.whqfl.service.impl.RechargeRuleServiceImpl;
import com.whqfl.service.impl.RechargerecordServiceImpl;
import com.whqfl.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetAllRechargeRuleServlet",urlPatterns = "/GetAllRechargeRuleServlet")
public class GetAllRechargeRuleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        RechargeRuleService rechargeRuleService=new RechargeRuleServiceImpl();
        ResponseDto responseDto=new ResponseDto();
        Gson gson = new Gson();
        List<RechargeRule> rechargeRuleList=rechargeRuleService.getAllRecharRule();
        responseDto.setData(rechargeRuleList);
        responseDto.setMessage("操作成功");
        responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        response.getWriter().print(gson.toJson(responseDto));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
