package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.ConsumeService;
import com.whqfl.service.impl.ConsumeServiceImpl;
import com.whqfl.util.IntegerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConsumGoodsListServlet",urlPatterns = "/ConsumGoodsListServlet")
public class ConsumGoodsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        ConsumeService consumeService=new ConsumeServiceImpl();
        ResponseDto responseDto=new ResponseDto();
        Integer pageNumber = IntegerUtils.ToInteger(req.getParameter("pageNumber"));
        Integer pageSize = IntegerUtils.ToInteger(req.getParameter("pageSize"));
        String searchGoodsId = req.getParameter("searchGoodsId");
        String searchName = req.getParameter("searchName");
        try {
            //返回参数

            responseDto.setData(consumeService.consumeGoodList(pageNumber, pageSize, searchGoodsId, searchName));
            responseDto.setMessage("请求成功");
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            resp.getWriter().print(new Gson().toJson(responseDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
