package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.entity.User;
import com.whqfl.service.UserService;
import com.whqfl.service.impl.UserServiceImpl;
import com.whqfl.util.IntegerUtils;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetAllUserServlet",urlPatterns = "/GetAllUserServlet")
public class GetAllUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Integer pageSize= IntegerUtils.ToInteger(request.getParameter("pageSize"));
        Integer pageNumber=IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        String searchId=request.getParameter("searchId");
        String cardId= request.getParameter("cardId");
        String searchName=request.getParameter("searchName");
        UserService userService=new UserServiceImpl();
        ResponseDto responseDto=new ResponseDto();
        Gson gson = new Gson();
        Logger logger=Logger.getLogger("GetAllUserServlet");
        Map<String,Object> map=new HashMap<>();
        try {
            int count=userService.getAllUsercount(searchId,searchName);
            List<User> userList=userService.getAllUser(pageSize,pageNumber,searchId,searchName,cardId);
            map.put("count",count);
            map.put("userlist",userList);
            responseDto.setData(map);
            responseDto.setMessage("操作成功");
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
        } catch (Exception e) {
            responseDto.setMessage("操作失败");
            responseDto.setStatus(ResponseDto.FAILURE_CODE);
            logger.error(e.getMessage());
        }
        response.getWriter().print(gson.toJson(responseDto));
    }

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
