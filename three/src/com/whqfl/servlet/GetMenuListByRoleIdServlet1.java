package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.MenuService;
import com.whqfl.service.MenuService1;
import com.whqfl.service.impl.MenuServiceImpl;
import com.whqfl.service.impl.MenuServiceImpl1;
import com.whqfl.util.IntegerUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetMenuListByRoleIdServlet1", urlPatterns = "/GetMenuListByRoleIdServlet1")
public class GetMenuListByRoleIdServlet1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("GetMenuListByRoleIdServlet1");
        MenuService1 menuService = new MenuServiceImpl1();
        Integer roleId = IntegerUtils.ToInteger(request.getParameter("roleId"));
        //返回参数
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setData(menuService.getMenuListByRoleId(roleId));
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setMessage("操作成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseDto.setMessage(e.getMessage());
        }
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
