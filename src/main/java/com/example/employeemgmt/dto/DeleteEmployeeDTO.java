package com.example.employeemgmt.dto;

/**
 * Request DTO Class for delete employees.
 * 
 * @author 144900
 *
 */
public class DeleteEmployeeDTO {

	private String accountName;
	private String band;

	public DeleteEmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeleteEmployeeDTO(String accountName, String band) {
		super();
		this.accountName = accountName;
		this.band = band;
	}

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
		return "DeleteemployeeBO [accountName=" + accountName + ", band="
				+ band + "]";
	}
}
