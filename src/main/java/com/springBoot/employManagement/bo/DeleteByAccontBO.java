package com.springBoot.employManagement.bo;

public class DeleteByAccontBO {
	private String accountName;
	private String band;

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the band
	 */
	public String getBand() {
		return band;
	}

	/**
	 * @param band
	 *            the band to set
	 */
	public void setBand(String band) {
		this.band = band;
	}

	@Override
	public String toString() {
		return "DeleteByAccontBO [accountName=" + accountName + ", band="
				+ band + "]";
	}
}
