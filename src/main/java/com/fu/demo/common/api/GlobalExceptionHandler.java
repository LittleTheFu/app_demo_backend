package com.fu.demo.common.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
//	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 处理自定义的业务异常
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
//    @ExceptionHandler(value = BizException.class)  
//    @ResponseBody  
//	public  ResultBody bizExceptionHandler(HttpServletRequest req, BizException e){
//    	logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
//    	return ResultBody.error(e.getErrorCode(),e.getErrorMsg());
//    }

	/**
	 * 处理空指针的异常
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
//	@ExceptionHandler(value =NullPointerException.class)
//	@ResponseBody
//	public CommonResult exceptionHandler(HttpServletRequest req, NullPointerException e){
////		logger.error("发生空指针异常！原因是:",e);
//		
//		
//		return CommonResult.failed(e.getMessage());
//	}

	/**
	 * 处理其他异常
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public CommonResult exceptionHandler(HttpServletRequest req, Exception e) {
//    	logger.error("未知异常！原因是:",e);
		return CommonResult.failed(e.getMessage());
	}
}