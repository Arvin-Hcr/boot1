package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.GoodsService;
import com.whqfl.service.impl.CategoryServiceImpl;
import com.whqfl.service.impl.GoodsServiceImpl;
import com.whqfl.util.IntegerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateOrAddCategoryServlet ",urlPatterns = "/UpdateOrAddCategoryServlet")
public class UpdateOrAddCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseDto responseDto = new ResponseDto();

        CategoryServiceImpl categoryService = new CategoryServiceImpl();

        Integer id = IntegerUtils.ToInteger(request.getParameter("id"));
        String name = request.getParameter("name");
        String momo = request.getParameter("momo");
        Integer status = IntegerUtils.ToInteger(request.getParameter("status"));
        try {
            int len = categoryService.updateOrAddCategory(id,name,momo,status);
            responseDto.setMessage("请求成功");
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setData(len);
            response.getWriter().print(new Gson().toJson(responseDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
