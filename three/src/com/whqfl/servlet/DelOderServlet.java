package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.OderService;
import com.whqfl.service.impl.OderServiceImpl;
import com.whqfl.util.IntegerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DelOderServlet",urlPatterns = "/DelOderServlet")
public class DelOderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseDto responseDto=new ResponseDto();
        OderService oderService=new OderServiceImpl();
        String id=req.getParameter("ruleId");
        int i = oderService.delOderService(IntegerUtils.ToInteger(id));
        responseDto.setMessage("操作成功");
        responseDto.setData(i);
        responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        resp.getWriter().print(new Gson().toJson(responseDto));
    }
}
