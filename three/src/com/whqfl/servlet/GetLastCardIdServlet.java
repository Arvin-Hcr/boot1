package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.UserService;
import com.whqfl.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetLastCardIdServlet",urlPatterns = "/GetLastCardIdServlet")
public class GetLastCardIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        UserService userService=new UserServiceImpl();
        ResponseDto responseDto=new ResponseDto();
        Gson gson = new Gson();
        Logger logger=Logger.getLogger("GetCardTypeServlet");
        Integer userLastCardId = userService.getUserLastCardId();
        if(userLastCardId>0){
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setMessage("操作成功");
            responseDto.setData(userLastCardId+1);
        }else {
            responseDto.setStatus(ResponseDto.FAILURE_CODE);
            responseDto.setMessage("操失败");
        }
        response.getWriter().print(gson.toJson(responseDto));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
