package com.ane.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;




public abstract class ResourceResponseSupport {

	protected RestResultResponse buildErrorRestResultResponse(Exception ex) {
		Throwable reason = ex.getCause();

		if (reason == null) {
			reason = ex;
		}

		RestResultResponse response;

		if (reason instanceof IllegalArgumentException || reason instanceof IllegalStateException) {
			response = new RestResultResponse(ResultStatus.ERROR, new ErrorResult(reason.getMessage()));
		} else {
			response = new RestResultResponse(ResultStatus.ERROR, new ErrorResult(ex.getMessage()));
		}

		return response;
	}

	protected RestResultResponse buildSuccessRestResultResponse(Object aResult) {
		return new RestResultResponse(ResultStatus.SUCCESS, aResult);
	}

	protected RestResultResponse buildSuccessRestResultResponse() {
		return this.buildSuccessRestResultResponse("");
	}

	protected RestResultResponse buildErrorRestResultResponse(String aResult) {
		return new RestResultResponse(ResultStatus.ERROR, new ErrorResult(aResult));
	}

	protected RestResultResponse buildErrorRestResultResponse(String aResult, ErrorType errorType) {
		return new RestResultResponse(ResultStatus.ERROR, new ErrorResult(aResult, errorType.getType()));
	}

	protected RestResultResponse buildErrorRestResultResponse() {
		return this.buildErrorRestResultResponse("");
	}

	protected RestResultResponse buildErrorRestResultResponse(Object aResult) {
		return new RestResultResponse(ResultStatus.ERROR, aResult);
	}
	
	protected PageCommon getPageCommon()
	{
		return new PageCommon(this.getRequest());
	}
	
	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	protected ModelAndView getModelAndView(){
		return new ModelAndView();
	}
}
