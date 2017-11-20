package com.slickcode.fdms.common.constant;

public enum FdStatusEnum {

	CURRENT("CUR", "CURRENT"), RENEWED("REN", "RENEWED"), WITHDRAWN("WIT",
			"WITHDRAWN");

	private String code;
	private String narrative;

	FdStatusEnum(String code, String narrative) {
		this.code = code;
		this.narrative = narrative;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the narrative
	 */
	public String getNarrative() {
		return narrative;
	}

}
