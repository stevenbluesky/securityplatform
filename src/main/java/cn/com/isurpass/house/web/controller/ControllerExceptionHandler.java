package cn.com.isurpass.house.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.result.JsonResult;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult runtimeExceptionHandler() {
		return new JsonResult(-1,"出错啦~");
	}
}
