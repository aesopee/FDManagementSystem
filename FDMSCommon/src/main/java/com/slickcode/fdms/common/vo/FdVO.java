package com.slickcode.fdms.common.vo;

import java.util.Date;

public class FdVO extends BaseVO {

	private Integer fdId;
	private Integer fdBankReferenceNumber;
	private String fdNumber;
	private BankVO bankVO;
	private PersonVO firstOwnerVO;
	private PersonVO secondOwnerVO;
	private PersonVO nomineeVO;
	private Float investedAmount;
	private Float maturityAmount;
	private Date investmentDate;
	private Date maturityDate;
	private String remark;
	private String status;
	private Integer originalFdNumber;
	private Integer renewedFrom;
	private Integer renewedTo;
	private StatusVO statusVO;

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
	 * @return the bankVO
	 */
	public BankVO getBankVO() {
		return bankVO;
	}

	/**
	 * @param bankVO
	 *            the bankVO to set
	 */
	public void setBankVO(BankVO bankVO) {
		this.bankVO = bankVO;
	}

	/**
	 * @return the firstOwnerVO
	 */
	public PersonVO getFirstOwnerVO() {
		return firstOwnerVO;
	}

	/**
	 * @param firstOwnerVO
	 *            the firstOwnerVO to set
	 */
	public void setFirstOwnerVO(PersonVO firstOwnerVO) {
		this.firstOwnerVO = firstOwnerVO;
	}

	/**
	 * @return the secondOwnerVO
	 */
	public PersonVO getSecondOwnerVO() {
		return secondOwnerVO;
	}

	/**
	 * @param secondOwnerVO
	 *            the secondOwnerVO to set
	 */
	public void setSecondOwnerVO(PersonVO secondOwnerVO) {
		this.secondOwnerVO = secondOwnerVO;
	}

	/**
	 * @return the nomineeVO
	 */
	public PersonVO getNomineeVO() {
		return nomineeVO;
	}

	/**
	 * @param nomineeVO
	 *            the nomineeVO to set
	 */
	public void setNomineeVO(PersonVO nomineeVO) {
		this.nomineeVO = nomineeVO;
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
	 * @return the statusVO
	 */
	public StatusVO getStatusVO() {
		return statusVO;
	}

	/**
	 * @param statusVO
	 *            the statusVO to set
	 */
	public void setStatusVO(StatusVO statusVO) {
		this.statusVO = statusVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FdVO [fdId=" + fdId + ", fdBankReferenceNumber="
				+ fdBankReferenceNumber + ", fdNumber=" + fdNumber
				+ ", bankVO=" + bankVO + ", firstOwnerVO=" + firstOwnerVO
				+ ", secondOwnerVO=" + secondOwnerVO + ", nomineeVO="
				+ nomineeVO + ", investedAmount=" + investedAmount
				+ ", maturityAmount=" + maturityAmount + ", investmentDate="
				+ investmentDate + ", maturityDate=" + maturityDate
				+ ", remark=" + remark + ", status=" + status
				+ ", originalFdNumber=" + originalFdNumber + ", renewedFrom="
				+ renewedFrom + ", renewedTo=" + renewedTo + "]";
	}

}
