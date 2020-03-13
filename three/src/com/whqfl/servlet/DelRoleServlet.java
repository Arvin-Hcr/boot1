package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.StaffMangerService;
import com.whqfl.service.impl.StaffMangerServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelRoleServlet",urlPatterns = "/DelRoleServlet")
public class DelRoleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        StaffMangerService staffMangerService=new StaffMangerServiceImp();
        ResponseDto responseDto=new ResponseDto();
        String id=request.getParameter("id");
        try {
            staffMangerService.updataStaffRoid(id);
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setStatus(ResponseDto.FAILURE_CODE);
        }
        response.getWriter().print(new Gson().toJson(responseDto));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
