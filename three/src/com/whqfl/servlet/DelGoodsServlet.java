package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.GoodsService;
import com.whqfl.service.impl.GoodsServiceImpl;
import com.whqfl.util.IntegerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelGoodsServlet ",urlPatterns="/DelGoodsServlet")
public class DelGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsService goodsService = new GoodsServiceImpl();
        Integer goodsId = IntegerUtils.ToInteger(request.getParameter("goodsId"));
        Integer status = IntegerUtils.ToInteger(request.getParameter("status"));
        ResponseDto responseDto = new ResponseDto();
        try {
            int len = goodsService.delGoods(goodsId,status);
            if (len > 0){
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            }else {
                responseDto.setStatus(ResponseDto.FAILURE_CODE);
            }
            response.getWriter().print(new Gson().toJson(responseDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
