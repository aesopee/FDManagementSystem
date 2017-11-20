package com.slickcode.fdms.service.serviceobject;

import java.util.List;

public class BaseDomain {
	private boolean success;
	private List<String> errorList;
	
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the errorList
	 */
	public List<String> getErrorList() {
		return errorList;
	}
	/**
	 * @param errorList the errorList to set
	 */
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
}
