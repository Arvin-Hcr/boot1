package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.CateGory;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "GetAllCategoryServlet",urlPatterns = "/GetAllCategoryServlet")
public class GetAllCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Integer pageNumber= IntegerUtils.ToInteger(request.getParameter("pageNumber"));
         Integer pageSize= IntegerUtils.ToInteger(request.getParameter("pageSize"));
        String searchId= request.getParameter("searchId");
        String searchName= request.getParameter("searchName");
        CategoryServiceImpl categoryService = new CategoryServiceImpl();

        ResponseDto responseDto= new ResponseDto();
        Map<String,Object> map=new HashMap<>();
        try {
            Map<String, Object> allCategoryInfo = categoryService.getAllCategoryInfo(pageNumber, pageSize, searchId, searchName);
            int a=categoryService.getAllCategoryCount(searchId,searchName);
           map.put("count",a);
           map.put("list",allCategoryInfo);

            if (allCategoryInfo == null && map == null){
                responseDto.setStatus(ResponseDto.FAILURE_CODE);
                responseDto.setMessage("获取失败");
            }else {
                responseDto.setMessage("获取成功");
                responseDto.setData(map);
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

