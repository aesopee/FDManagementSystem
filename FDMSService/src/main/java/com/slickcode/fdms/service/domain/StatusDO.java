package com.slickcode.fdms.service.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class StatusDO extends StaticDataDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -357388287475733379L;
}
