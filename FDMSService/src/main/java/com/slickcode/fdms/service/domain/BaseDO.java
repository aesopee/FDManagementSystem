package com.slickcode.fdms.service.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public class BaseDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1960853232911213926L;

	@Version
	@Column(name = "VERSION")
	private int version = 1;
	
	@Column(name = "CREATION_DATE")
	@Temporal(value =  TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Column(name = "LST_MOD_DATE")
	@Temporal(value =  TemporalType.TIMESTAMP)
	private Date modificationDate;
	
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the modificationDate
	 */
	public Date getModificationDate() {
		return modificationDate;
	}

	/**
	 * @param modificationDate the modificationDate to set
	 */
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

}
