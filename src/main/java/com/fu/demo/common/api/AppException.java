package com.fu.demo.common.api;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
//	protected String errorCode;
	/**
	 * 错误信息
	 */
	protected String errorMsg;

//	public AppException() {
//		super();
//	}
//
//	public AppException(IErrorCode errorInfoInterface) {
//		super(errorInfoInterface.getMessage());
//		
//		this.errorCode = errorInfoInterface.getResultCode();
//		this.errorMsg = errorInfoInterface.getResultMsg();
//	}
//	
//	public AppException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
//		super(errorInfoInterface.getResultCode(), cause);
//		this.errorCode = errorInfoInterface.getResultCode();
//		this.errorMsg = errorInfoInterface.getResultMsg();
//	}
	
	public AppException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	
//	public AppException(String errorCode, String errorMsg) {
//		super(errorCode);
//		this.errorCode = errorCode;
//		this.errorMsg = errorMsg;
//	}

//	public AppException(String errorCode, String errorMsg, Throwable cause) {
//		super(errorCode, cause);
//		this.errorCode = errorCode;
//		this.errorMsg = errorMsg;
//	}
	

//	public String getErrorCode() {
//		return errorCode;
//	}

//	public void setErrorCode(String errorCode) {
//		this.errorCode = errorCode;
//	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

//	public String getMessage() {
//		return errorMsg;
//	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

}