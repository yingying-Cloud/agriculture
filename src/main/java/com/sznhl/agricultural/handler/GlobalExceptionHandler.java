package com.sznhl.agricultural.handler;

import com.sznhl.agricultural.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qingm.
 *
 * @author qingm
 * @date 2019-11-18
 * @description 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, BusinessException e){
        logger.error("业务异常！原因是:{}",e.getErrorMsg());
        return new Result(300, e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("空指针异常！原因是:{}",e.getMessage());
        return new Result(400, e.getMessage());
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("未知异常！原因是:{}", e.getMessage());
        return new Result(500, e.getMessage());
    }

}
