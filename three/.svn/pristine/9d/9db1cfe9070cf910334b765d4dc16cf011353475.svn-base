package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.RoleMangerService;
import com.whqfl.service.impl.RoleMangerServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateRoleServlet",urlPatterns = "/UpdateRoleServlet")
public class UpdateRoleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ResponseDto dto = new ResponseDto();
        RoleMangerService roleMangerService=new RoleMangerServiceImp();
        String id=request.getParameter("roleid");
        String roleName=request.getParameter("roleName");
        String status=request.getParameter("roleStatus");
        String description=request.getParameter("miao");
        try {
            roleMangerService.updataRole(id,roleName,description,status);
            dto.setStatus(ResponseDto.SUCCESS_CODE);
        } catch (Exception e) {
            dto.setMessage(e.getMessage());
            dto.setStatus(ResponseDto.FAILURE_CODE);
            e.printStackTrace();
        }
        response.getWriter().print(new Gson().toJson(dto));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
