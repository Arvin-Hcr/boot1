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
import java.util.Map;


@WebServlet(name = "ShowStaffServlet" ,urlPatterns = "/ShowStaffServlet")
public class ShowStaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Integer pageNumber = IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        Integer pageSize=IntegerUtils.ToInteger(request.getParameter("pageSize"));
        String searchStatus=request.getParameter("searchStatus");
        String staffName=request.getParameter("searchName");
        String createdTime=request.getParameter("searchStartTime");
        ResponseDto dto = new ResponseDto();
        StaffMangerService staffMangerService = new StaffMangerServiceImp();
        try {
            Map<String, Object> showStaff = staffMangerService.showStaff(pageNumber, pageSize, searchStatus, staffName, createdTime);

            dto.setData(showStaff);
            dto.setStatus(ResponseDto.SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().print(new Gson().toJson(dto));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
