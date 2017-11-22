package com.slickcode.fdms.service.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "security_question")
public class SecurityQuestionDO extends StaticDataDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9106215838866695312L;
}
