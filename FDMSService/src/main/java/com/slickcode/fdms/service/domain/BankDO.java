package com.slickcode.fdms.service.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bank")
public class BankDO extends BaseDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3862960358581449620L;

	@Id
	@GeneratedValue
	@Column(name = "BANK_ID")
	private Integer bankId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "BRANCH")
	private String branch;

	@OneToMany(mappedBy = "bankDO")
	private List<FdDO> fdOfBankList;

	/**
	 * @return the bankId
	 */
	public Integer getBankId() {
		return bankId;
	}

	/**
	 * @param bankId
	 *            the bankId to set
	 */
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * @param branch
	 *            the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * @return the fdOfBankList
	 */
	public List<FdDO> getFdOfBankList() {
		return fdOfBankList;
	}

	/**
	 * @param fdOfBankList
	 *            the fdOfBankList to set
	 */
	public void setFdOfBankList(List<FdDO> fdOfBankList) {
		this.fdOfBankList = fdOfBankList;
	}

}
