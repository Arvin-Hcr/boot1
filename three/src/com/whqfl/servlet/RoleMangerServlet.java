package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.RoleMangerService;
import com.whqfl.service.StaffMangerService;
import com.whqfl.service.impl.RoleMangerServiceImp;
import com.whqfl.service.impl.StaffMangerServiceImp;
import com.whqfl.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 这个是显示角色的servlet
 */
@WebServlet(name = "RoleMangerServlet",urlPatterns = "/RoleMangerServlet")
public class RoleMangerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Integer pageNumber = IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        Integer pageSize=IntegerUtils.ToInteger(request.getParameter("pageSize"));
        Integer roleId=IntegerUtils.ToInteger(request.getParameter("roleId"));
        Integer roleStatus=0;
        if(!StringUtils.isBlank(request.getParameter("roleStatus"))){
            roleStatus=IntegerUtils.ToInteger(request.getParameter("roleStatus"));
        }
        String roleName=request.getParameter("searchName");

        ResponseDto dto = new ResponseDto();
        RoleMangerService roleMangerService=new RoleMangerServiceImp();
        try {
            Map<String, Object> showStaff = roleMangerService.showRole(pageNumber, pageSize, roleName, roleStatus, roleId);
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
