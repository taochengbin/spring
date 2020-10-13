package com.example.demo.exception;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ExceptionHandle {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    private Snowflake snowflake = IdUtil.createSnowflake(1, 1);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handle(Exception exception, HttpServletRequest request) {

        /*
        创建一个json Response对象（这里写法是因为用的huto工具类） ，请求返回值形如
        data = {
                  "code": "100101",
                  "message": "用户证件类型错误",
                  "response": {
                      "reqId": "1277545207021309952"
                   }
                }
         */
        JSONObject data = JSONUtil.createObj();
        // 生成随机数（huto工具类的方法）
        data.put("reqId", snowflake.nextIdStr());

        if (exception instanceof FatherException) {
            /*
            处理自定义的FatherException异常
             */
            System.out.println();
        } else if (exception instanceof ChildLoginException) {
            /*
            处理ChildLoginException 登录的异常
             */
        } else if (exception instanceof ChildRegisterException) {
            /*
            处理ChildRegisterException 注册的异常
             */
        } else if (exception instanceof BindException) {
            // 取得请求的url
            String reqUrl = this.getRequestUrl(request);
            // 取得BindException异常提示信息
            String message = this.getBindExceptionMessage((BindException) exception);
            switch (reqUrl) {
                // 请求一：登录
                case "/login":
                    request.setAttribute("resCode", ExceptionEnum.LOGIN_PARAM_EXCEPTION.getCode());
                    return Result.newBuilder().code(Integer.valueOf(ExceptionEnum.LOGIN_PARAM_EXCEPTION.getCode())).message(message).response(data).build();
                // 请求二：注册
                case "/register":
                    request.setAttribute("resCode", ExceptionEnum.REGISTER_PARAM_EXCEPTION.getCode());
                    return Result.newBuilder().code(Integer.valueOf(ExceptionEnum.REGISTER_PARAM_EXCEPTION.getCode())).message(message).response(data).build();
            }
        } else {
            // 处理未知异常
            logger.error(exception.getMessage(), exception);
            request.setAttribute("resCode", ExceptionEnum.UNKNOWN_EXCEPTION.getCode());
            return Result.newBuilder().code(Integer.valueOf(ExceptionEnum.UNKNOWN_EXCEPTION.getCode())).message(ExceptionEnum.UNKNOWN_EXCEPTION.getMessage()).response(data).build();
        }
        return null;
    }

    /**
     * 获取参数绑定异常的FieldError信息, 若有多个以逗号(,)隔开
     *
     * @param e 参数绑定异常
     * @return 异常信息
     */
    private String getBindExceptionMessage(BindException e) {
        List<FieldError> errors = e.getFieldErrors();
        if (errors.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (FieldError error : errors) {
            sb.append(error.getDefaultMessage()).append(", ");
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 2);
    }

    /**
     * 获取request中的请求地址
     */
    public String getRequestUrl(HttpServletRequest request) {
        //请求路径
        String path = request.getRequestURL().toString();
        //通讯协议
        String protocol = path.substring(0, path.indexOf(":"));
        //请求服务名称
        String serverName = request.getServerName();
        //请求服务端口
        int serverPort = request.getServerPort();
        //请求服务应用名
        String contextPath = request.getContextPath();

        String str;
        if ((80 == serverPort && "http".equals(protocol)) || (443 == serverPort && "https".equals(protocol))) {
            str = serverName + contextPath;
        } else {
            str = serverName + ":" + serverPort + contextPath;
        }
        return path.split(str)[1];
    }
}
