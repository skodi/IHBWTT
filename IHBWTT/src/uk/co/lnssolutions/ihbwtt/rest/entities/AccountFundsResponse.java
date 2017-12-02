package uk.co.lnssolutions.ihbwtt.rest.entities;

import uk.co.lnssolutions.ihbwtt.rest.containers.Container;

public class AccountFundsResponse extends Container {

	private Double availableToBetBalance;
	private Double exposure;
	private Double retainedCommission;
	private Double exposureLimit;
	private Double discountRate;
	private Integer pointsBalance;
	private String wallet;
	

	
	public AccountFundsResponse(){
	}		
	
	public String getWallet() {
		return wallet;
	}

	@Override
	public String toString() {
		return "AccountFundsResponse [availableToBetBalance=" + availableToBetBalance + ", exposure=" + exposure
				+ ", retainedCommission=" + retainedCommission + ", exposureLimit=" + exposureLimit + ", discountRate="
				+ discountRate + ", pointsBalance=" + pointsBalance + ", wallet=" + wallet + "]";
	}

	public AccountFundsResponse(Double availableToBetBalance, Double exposure, Double retainedCommission,
			Double exposureLimit, Double discountRate, Integer pointsBalance, String wallet) {
		this.availableToBetBalance = availableToBetBalance;
		this.exposure = exposure;
		this.retainedCommission = retainedCommission;
		this.exposureLimit = exposureLimit;
		this.discountRate = discountRate;
		this.pointsBalance = pointsBalance;
		this.wallet = wallet;
	}


	public void setWallet(String wallet) {
		this.wallet = wallet;
	}

	public Double getAvailableToBetBalance() {
		return availableToBetBalance;
	}
	public void setAvailableToBetBalance(Double availableToBetBalance) {
		this.availableToBetBalance = availableToBetBalance;
	}
	public Double getExposure() {
		return exposure;
	}
	public void setExposure(Double exposure) {
		this.exposure = exposure;
	}
	public Double getRetainedCommission() {
		return retainedCommission;
	}
	public void setRetainedCommission(Double retainedCommission) {
		this.retainedCommission = retainedCommission;
	}
	public Double getExposureLimit() {
		return exposureLimit;
	}
	public void setExposureLimit(Double exposureLimit) {
		this.exposureLimit = exposureLimit;
	}
	public Double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}
	public Integer getPointsBalance() {
		return pointsBalance;
	}
	public void setPointsBalance(Integer pointsBalance) {
		this.pointsBalance = pointsBalance;
	}

	
	
}
