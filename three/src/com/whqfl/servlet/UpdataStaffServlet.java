package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.StaffMangerService;
import com.whqfl.service.impl.StaffMangerServiceImp;
import com.whqfl.util.IntegerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdataStaffServlet",urlPatterns = "/UpdataStaffServlet")
public class UpdataStaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StaffMangerService staffMangerService=new StaffMangerServiceImp();
        ResponseDto responseDto=new ResponseDto();
        Integer id= IntegerUtils.ToInteger(request.getParameter("id"));
        Integer staffId=IntegerUtils.ToInteger(request.getParameter("staffId"));
        String staffName=request.getParameter("staffName");
        String idCard=request.getParameter("staffIdcard");
        String phone=request.getParameter("staffPhone");
        String adress=request.getParameter("staffAdress");
        String createdTime=request.getParameter("staffData");
        Integer status=IntegerUtils.ToInteger(request.getParameter("staffStatus"));
        Integer releId=IntegerUtils.ToInteger(request.getParameter("roldId"));

        try {
            int i = staffMangerService.updataStaff(id, staffId, staffName, idCard, phone, adress, createdTime, status, releId);
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        } catch (Exception e) {
            responseDto.setStatus(ResponseDto.FAILURE_CODE);
            e.printStackTrace();
        }
        response.getWriter().print(new Gson().toJson(responseDto));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
