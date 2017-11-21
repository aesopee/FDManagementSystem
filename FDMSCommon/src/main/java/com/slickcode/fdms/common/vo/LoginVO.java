package com.slickcode.fdms.common.vo;

import java.io.Serializable;

public class LoginVO extends BaseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2318290294009658123L;

	private Integer loginId;
	private PersonVO personVO;
	private String userName;
	private String password;
	private SecurityQuestionVO securityQuestion;
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
	 * @return the personVO
	 */
	public PersonVO getPersonVO() {
		return personVO;
	}

	/**
	 * @param personVO
	 *            the personVO to set
	 */
	public void setPersonVO(PersonVO personVO) {
		this.personVO = personVO;
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
	 * @return the securityQuestion
	 */
	public SecurityQuestionVO getSecurityQuestion() {
		return securityQuestion;
	}

	/**
	 * @param securityQuestion
	 *            the securityQuestion to set
	 */
	public void setSecurityQuestion(SecurityQuestionVO securityQuestion) {
		this.securityQuestion = securityQuestion;
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
		return "Login [loginId=" + loginId + ", personVO=" + personVO + ", userName=" + userName + ", password="
				+ password + ", securityQuestion=" + securityQuestion + ", securityAnswer=" + securityAnswer + "]";
	}
}
