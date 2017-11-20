package com.slickcode.fdms.service.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fd")
public class FdDO extends BaseDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "FD_ID")
	private Integer fdId;

	@Column(name = "FD_NUMBER")
	private String fdNumber;

	@ManyToOne
	@JoinColumn(name = "BANK_ID", unique = true, nullable = false)
	private BankDO bankDO;

	@Column(name = "RENEWED_FROM")
	private Integer renewedFrom;

	@Column(name = "RENEWED_TO")
	private Integer renewedTo;

	@Column(name = "FD_BANK_REFERENCE_NUMBER")
	private Integer fdBankReferenceNumber;

	@ManyToOne
	@JoinColumn(name = "PRIMARY_PERSON_ID", unique = true, nullable = false)
	private PersonDO primaryPersonDO;

	@ManyToOne
	@JoinColumn(name = "SECONDARY_PERSON_ID", unique = true, nullable = true)
	private PersonDO secondaryPersonDO;

	@ManyToOne
	@JoinColumn(name = "NOMINEE_ID", unique = true, nullable = true)
	private PersonDO nomineeDO;

	@Column(name = "INVESTED_AMOUNT")
	private Float investedAmount;

	@Column(name = "MATURITY_AMOUNT")
	private Float maturityAmount;

	@Column(name = "INVESTMENT_DATE")
	private Date investmentDate;

	@Column(name = "MATURITY_DATE")
	private Date maturityDate;

	@ManyToOne
	@JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
	private StatusDO statusDO;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "ORIGINAL_FD_NUMBER")
	private Integer originalFdNumber;

	/**
	 * @return the fdId
	 */
	public Integer getFdId() {
		return fdId;
	}

	/**
	 * @param fdId
	 *            the fdId to set
	 */
	public void setFdId(Integer fdId) {
		this.fdId = fdId;
	}

	/**
	 * @return the fdNumber
	 */
	public String getFdNumber() {
		return fdNumber;
	}

	/**
	 * @param fdNumber
	 *            the fdNumber to set
	 */
	public void setFdNumber(String fdNumber) {
		this.fdNumber = fdNumber;
	}

	/**
	 * @return the bankDO
	 */
	public BankDO getBankDO() {
		return bankDO;
	}

	/**
	 * @param bankDO
	 *            the bankDO to set
	 */
	public void setBankDO(BankDO bankDO) {
		this.bankDO = bankDO;
	}

	/**
	 * @return the renewedFrom
	 */
	public Integer getRenewedFrom() {
		return renewedFrom;
	}

	/**
	 * @param renewedFrom
	 *            the renewedFrom to set
	 */
	public void setRenewedFrom(Integer renewedFrom) {
		this.renewedFrom = renewedFrom;
	}

	/**
	 * @return the renewedTo
	 */
	public Integer getRenewedTo() {
		return renewedTo;
	}

	/**
	 * @param renewedTo
	 *            the renewedTo to set
	 */
	public void setRenewedTo(Integer renewedTo) {
		this.renewedTo = renewedTo;
	}

	/**
	 * @return the fdBankReferenceNumber
	 */
	public Integer getFdBankReferenceNumber() {
		return fdBankReferenceNumber;
	}

	/**
	 * @param fdBankReferenceNumber
	 *            the fdBankReferenceNumber to set
	 */
	public void setFdBankReferenceNumber(Integer fdBankReferenceNumber) {
		this.fdBankReferenceNumber = fdBankReferenceNumber;
	}

	/**
	 * @return the primaryPersonDO
	 */
	public PersonDO getPrimaryPersonDO() {
		return primaryPersonDO;
	}

	/**
	 * @param primaryPersonDO
	 *            the primaryPersonDO to set
	 */
	public void setPrimaryPersonDO(PersonDO primaryPersonDO) {
		this.primaryPersonDO = primaryPersonDO;
	}

	/**
	 * @return the secondaryPersonDO
	 */
	public PersonDO getSecondaryPersonDO() {
		return secondaryPersonDO;
	}

	/**
	 * @param secondaryPersonDO
	 *            the secondaryPersonDO to set
	 */
	public void setSecondaryPersonDO(PersonDO secondaryPersonDO) {
		this.secondaryPersonDO = secondaryPersonDO;
	}

	/**
	 * @return the nomineeDO
	 */
	public PersonDO getNomineeDO() {
		return nomineeDO;
	}

	/**
	 * @param nomineeDO
	 *            the nomineeDO to set
	 */
	public void setNomineeDO(PersonDO nomineeDO) {
		this.nomineeDO = nomineeDO;
	}

	/**
	 * @return the investedAmount
	 */
	public Float getInvestedAmount() {
		return investedAmount;
	}

	/**
	 * @param investedAmount
	 *            the investedAmount to set
	 */
	public void setInvestedAmount(Float investedAmount) {
		this.investedAmount = investedAmount;
	}

	/**
	 * @return the maturityAmount
	 */
	public Float getMaturityAmount() {
		return maturityAmount;
	}

	/**
	 * @param maturityAmount
	 *            the maturityAmount to set
	 */
	public void setMaturityAmount(Float maturityAmount) {
		this.maturityAmount = maturityAmount;
	}

	/**
	 * @return the investmentDate
	 */
	public Date getInvestmentDate() {
		return investmentDate;
	}

	/**
	 * @param investmentDate
	 *            the investmentDate to set
	 */
	public void setInvestmentDate(Date investmentDate) {
		this.investmentDate = investmentDate;
	}

	/**
	 * @return the maturityDate
	 */
	public Date getMaturityDate() {
		return maturityDate;
	}

	/**
	 * @param maturityDate
	 *            the maturityDate to set
	 */
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the originalFdNumber
	 */
	public Integer getOriginalFdNumber() {
		return originalFdNumber;
	}

	/**
	 * @param originalFdNumber
	 *            the originalFdNumber to set
	 */
	public void setOriginalFdNumber(Integer originalFdNumber) {
		this.originalFdNumber = originalFdNumber;
	}

	/**
	 * @return the statusDO
	 */
	public StatusDO getStatusDO() {
		return statusDO;
	}

	/**
	 * @param statusDO
	 *            the statusDO to set
	 */
	public void setStatusDO(StatusDO statusDO) {
		this.statusDO = statusDO;
	}

}
