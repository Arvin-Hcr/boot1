package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.GoodsService;
import com.whqfl.service.GoodsService1;
import com.whqfl.service.impl.GoodsServiceImpl;
import com.whqfl.service.impl.GoodsServiceImpl1;
import com.whqfl.util.IntegerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetGoodsAllServlet1 " ,urlPatterns="/GetGoodsAllServlet1")
public class GetGoodsAllServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        GoodsService1 goodsService = new GoodsServiceImpl1();
        Integer pageNumber = IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        Integer pageSize = IntegerUtils.ToInteger(request.getParameter("pageSize"));
        String searchGoodsId = request.getParameter("searchGoodsId");
        String searchName = request.getParameter("searchName");
        try {
            //返回参数
            ResponseDto responseDto = new ResponseDto();
            responseDto.setData(goodsService.getAllGoodsInfo(pageNumber, pageSize, searchGoodsId, searchName));
            responseDto.setMessage("请求成功");
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            response.getWriter().print(new Gson().toJson(responseDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
