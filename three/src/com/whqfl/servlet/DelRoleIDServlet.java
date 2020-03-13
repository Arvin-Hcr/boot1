package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.RoleMangerService;
import com.whqfl.service.StaffMangerService;
import com.whqfl.service.impl.RoleMangerServiceImp;
import com.whqfl.service.impl.StaffMangerServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelRoleIDServlet",urlPatterns = "/DelRoleIDServlet")
public class DelRoleIDServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleMangerService roleMangerService=new RoleMangerServiceImp();
        ResponseDto responseDto=new ResponseDto();
        String id=request.getParameter("Id");
        try {
            roleMangerService.updataStaffRoid(id);
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setStatus(ResponseDto.FAILURE_CODE);
        }
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
