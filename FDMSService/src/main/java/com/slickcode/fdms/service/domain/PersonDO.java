package com.slickcode.fdms.service.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class PersonDO extends BaseDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "PERSON_ID")
	private Integer personId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@OneToMany(mappedBy = "primaryPersonDO")
	private List<FdDO> primaryPersonFDList;

	@OneToMany(mappedBy = "secondaryPersonDO")
	private List<FdDO> secondaryPersonFDList;

	@OneToMany(mappedBy = "nomineeDO")
	private List<FdDO> nomineeFDList;

	@OneToOne(mappedBy = "personDO")
	private LoginDO loginDO;

	/**
	 * @return the personId
	 */
	public Integer getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the primaryPersonFDList
	 */
	public List<FdDO> getPrimaryPersonFDList() {
		return primaryPersonFDList;
	}

	/**
	 * @param primaryPersonFDList
	 *            the primaryPersonFDList to set
	 */
	public void setPrimaryPersonFDList(List<FdDO> primaryPersonFDList) {
		this.primaryPersonFDList = primaryPersonFDList;
	}

	/**
	 * @return the secondaryPersonFDList
	 */
	public List<FdDO> getSecondaryPersonFDList() {
		return secondaryPersonFDList;
	}

	/**
	 * @param secondaryPersonFDList
	 *            the secondaryPersonFDList to set
	 */
	public void setSecondaryPersonFDList(List<FdDO> secondaryPersonFDList) {
		this.secondaryPersonFDList = secondaryPersonFDList;
	}

	/**
	 * @return the nomineeFDList
	 */
	public List<FdDO> getNomineeFDList() {
		return nomineeFDList;
	}

	/**
	 * @param nomineeFDList
	 *            the nomineeFDList to set
	 */
	public void setNomineeFDList(List<FdDO> nomineeFDList) {
		this.nomineeFDList = nomineeFDList;
	}

	/**
	 * @return the loginDO
	 */
	public LoginDO getLoginDO() {
		return loginDO;
	}

	/**
	 * @param loginDO
	 *            the loginDO to set
	 */
	public void setLoginDO(LoginDO loginDO) {
		this.loginDO = loginDO;
	}

}
