package com.slickcode.fdms.service.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class StaticDataDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 178445005828792867L;

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false, insertable= false, updatable = false)
	private Integer id;
	
	@Column(name = "CODE", nullable = false, insertable= false, updatable = false)
	private String code;

	@Column(name = "NARRATIVE", nullable = false, insertable= false, updatable = false)
	private String narrative;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the narrative
	 */
	public String getNarrative() {
		return narrative;
	}

	/**
	 * @param narrative the narrative to set
	 */
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

}
