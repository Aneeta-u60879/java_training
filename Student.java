package com.training.ust.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student implements Comparable<Student> {
	
	private String rollNo;
	private String studname;
	private String department;
	private int totalmarks;
	Map<String, Integer> marksObtained=new HashMap<String,Integer>();
	
	public int getTotalmarks() {
		return totalmarks;
	}


	public void setTotalmarks(int totalmarks) {
		this.totalmarks = totalmarks;
	}





	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", studname=" + studname + ", department=" + department + ", totalmarks="
				+ totalmarks + ", marksObtained=" + marksObtained + "]";
	}


	public Student(String studname, String department, Map<String, Integer> marksObtained) {
		// TODO Auto-generated constructor stub
		
		this.studname=studname;
		this.department=department;
		this.marksObtained=marksObtained;
	
	}
	


	public String getRollNo() {
		return rollNo;
	}


	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}


	public String getStudname() {
		return studname;
	}
	public void setStudname(String studname) {
		this.studname = studname;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}


	public Map<String, Integer> getMarksObtained() {
		return marksObtained;
	}


	public void setMarksObtained(Map<String, Integer> marksObtained) {
		this.marksObtained = marksObtained;
	}
	 public int compareTo(Student student) {
	        // TODO Auto-generated method stub
	          return this.getStudname().compareTo(student.getStudname());
	    }
}
