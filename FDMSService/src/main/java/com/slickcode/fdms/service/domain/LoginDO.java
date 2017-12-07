package com.slickcode.fdms.service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class LoginDO extends BaseDO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -439258498768288095L;

	@Id
	@GeneratedValue
	@Column(name = "LOGIN_ID")
	private Integer loginId;

	@Column(name = "USERNAME", unique = true, nullable = false)
	private String userName;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@OneToOne
	@JoinColumn(name = "PERSON_ID", unique = true, nullable = false)
	private PersonDO personDO;

	@ManyToOne
	@JoinColumn(name = "SECURITY_QUESTION_ID", referencedColumnName = "ID")
	private SecurityQuestionDO securityQuestionDO;

	@Column(name = "SECURITY_ANSWER", nullable = false)
	private String securityAnswer;

	/**
	 * @return the loginId
	 */
	public Integer getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId
	 *            the loginId to set
	 */
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the personDO
	 */
	public PersonDO getPersonDO() {
		return personDO;
	}

	/**
	 * @param personDO
	 *            the personDO to set
	 */
	public void setPersonDO(PersonDO personDO) {
		this.personDO = personDO;
	}

	/**
	 * @return the securityQuestionDO
	 */
	public SecurityQuestionDO getSecurityQuestionDO() {
		return securityQuestionDO;
	}

	/**
	 * @param securityQuestionDO
	 *            the securityQuestionDO to set
	 */
	public void setSecurityQuestionDO(SecurityQuestionDO securityQuestionDO) {
		this.securityQuestionDO = securityQuestionDO;
	}

	/**
	 * @return the securityAnswer
	 */
	public String getSecurityAnswer() {
		return securityAnswer;
	}

	/**
	 * @param securityAnswer
	 *            the securityAnswer to set
	 */
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginDO [loginId=" + loginId + ", userName=" + userName + ", password=" + password + ", personDO="
				+ personDO + ", securityQuestionDO=" + securityQuestionDO + ", securityAnswer=" + securityAnswer + "]";
	}

}
