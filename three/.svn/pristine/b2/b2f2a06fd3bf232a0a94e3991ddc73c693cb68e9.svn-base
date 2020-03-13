package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.entity.Role;
import com.whqfl.service.RoleMangerService;
import com.whqfl.service.impl.RoleMangerServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QueryRoleServlet",urlPatterns = "/QueryRoleServlet")
public class QueryRoleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ResponseDto dto = new ResponseDto();
        RoleMangerService roleMangerService=new RoleMangerServiceImp();
        try {
            List<Role> roles = roleMangerService.querAll();
            dto.setStatus(ResponseDto.SUCCESS_CODE);
            dto.setData(roles);
        } catch (Exception e) {
            dto.setStatus(ResponseDto.FAILURE_CODE);
            e.printStackTrace();
        }
        response.getWriter().print(new Gson().toJson(dto));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
