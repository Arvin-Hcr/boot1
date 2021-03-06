package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.OderService;
import com.whqfl.service.impl.OderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "UpdateOderByIdServlet",urlPatterns = "/UpdateOderByIdServlet")
public class UpdateOderByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        ResponseDto responseDto=new ResponseDto();
        OderService oderService=new OderServiceImpl();
        String ruleId=req.getParameter("ruleId");
        String ruleOrderId=req.getParameter("ruleOrderId");
        String ruleCardId=req.getParameter("ruleCardId");
        String ruleCardType=req.getParameter("ruleCardType");
        String rulePrice=req.getParameter("rulePrice");
        String rulePay=req.getParameter("rulePay");
        String ruleCredit=req.getParameter("ruleCredit");
        String ruleStatus=req.getParameter("ruleStatus");
        String ruleMomo=req.getParameter("ruleMomo");
        String ruleCreatedTime=req.getParameter("ruleCreatedTime");
        try{
            int i = oderService.updateOderByIdService(ruleId, ruleOrderId, ruleCardId, ruleCardType, rulePrice, rulePay, ruleCredit, ruleMomo, ruleCreatedTime);
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setData(i);
            responseDto.setMessage("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().print(new Gson().toJson(responseDto));

    }
}
