package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.GoodsService;
import com.whqfl.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddGoodsServlet",urlPatterns = "/AddGoodsServlet" )
public class AddGoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        GoodsService goodsService = new GoodsServiceImpl();
        String goodsId = request.getParameter("goodsId");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
       /* Integer type = Integer.parseInt(request.getParameter("type"));*/
        String categoryName = request.getParameter("categoryName");
        String unitName = request.getParameter("unitName");
        double price = Double.parseDouble(request.getParameter("price"));
        ResponseDto responseDto = new ResponseDto();
        try {
            int len =  goodsService.addGoods(goodsId, name, code,  price, categoryName);
            if (len != 0 ){
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
                responseDto.setMessage("修改成功");
                responseDto.setData(len);
            }else {
                responseDto.setStatus(ResponseDto.FAILURE_CODE);
            }
            response.getWriter().print(new Gson().toJson(responseDto));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
