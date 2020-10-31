package com.example.employeemgmt.bo;

/**
 * This class holds data for employee deletion.
 * @author 144900
 *
 */
public class DeleteEmployeeBO {
	private String accountName;
	private String band;
	
	public DeleteEmployeeBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeleteEmployeeBO(String accountName, String band) {
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
	 * @param accountName the accountName to set
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
	 * @param band the band to set
	 */
	public void setBand(String band) {
		this.band = band;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeleteEmployeeBO [accountName=" + accountName + ", band="
				+ band + "]";
	}

	

}
