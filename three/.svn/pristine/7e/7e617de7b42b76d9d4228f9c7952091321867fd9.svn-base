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

@WebServlet(name = "UpdateTreeServlet",urlPatterns = "/UpdateTreeServlet")
public class UpdateTreeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String roleId=request.getParameter("roleId");
        String getNodeList=request.getParameter("getNodeList");

        RoleMangerService roleMangerService=new RoleMangerServiceImp();
        ResponseDto dto=new ResponseDto();
        try {
            int res=roleMangerService.UpdateTree(roleId,getNodeList);
            dto.setStatus(ResponseDto.SUCCESS_CODE);
            dto.setData(res);
        } catch (Exception e) {
            dto.setStatus(ResponseDto.FAILURE_CODE);
            dto.setMessage(e.getMessage());
            e.printStackTrace();
        }
        response.getWriter().print(new Gson().toJson(dto));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
