package com.slickcode.fdms.common.vo;

public class StaticDataVO extends BaseVO {

	private Integer id;
	private String code;
	private String narrative;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the question
	 */
	public String getNarrative() {
		return narrative;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

}
