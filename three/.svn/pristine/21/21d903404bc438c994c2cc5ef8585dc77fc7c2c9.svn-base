package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.Rechargerecord;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.RechargerecordService;
import com.whqfl.service.impl.RechargerecordServiceImpl;
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

@WebServlet(name = "GetRechargerecordBycardIdServlet",urlPatterns = "/GetRechargerecordBycardIdServlet")
public class GetRechargerecordBycardIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Integer cardId= IntegerUtils.ToInteger(request.getParameter("cardId"));
        Integer pageNumber=IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        Integer pageSize=IntegerUtils.ToInteger(request.getParameter("pageSize"));
        ResponseDto responseDto=new ResponseDto();
        Gson gson = new Gson();
        Logger logger=Logger.getLogger("GetRechargerecordBycardIdServlet");
        RechargerecordService rechargerecordService=new RechargerecordServiceImpl();
        Map<String,Object> map=new HashMap<>();
        try {
            List<Rechargerecord> rechargerecordList=rechargerecordService.getRechargerecordBycardId(cardId,pageNumber,pageSize);
            Integer count=rechargerecordService.getRechargerecordBycardIdCount(cardId);
            if(rechargerecordList!=null && rechargerecordList.size()>0){
                map.put("count",count);
                map.put("list",rechargerecordList);
                responseDto.setData(map);
                responseDto.setMessage("操作成功");
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            }else {
                responseDto.setMessage("操作失败");
                responseDto.setStatus(ResponseDto.FAILURE_CODE);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        response.getWriter().print(gson.toJson(responseDto));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
    }
}
