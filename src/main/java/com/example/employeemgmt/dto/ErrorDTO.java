package com.example.employeemgmt.dto;

/**
 * DTO class for holding error details.
 * 
 * @author 144900
 *
 */
public class ErrorDTO {

	private Integer id;
	private String message;

	public ErrorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorDTO [id=" + id + ", message=" + message + "]";
	}

}
