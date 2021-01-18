package com.training.ust.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class StudOperations {

	static int ecRollNum = 1;
	static int csRollNum = 1;
	static int mecRollNum = 1;
	static int eeeRollNum = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Student> studentList = new ArrayList<>();

		Map<String, Integer> student1 = new HashMap<String, Integer>();
		student1.put("Math", 70);
		student1.put("English", 60);
		student1.put("Computer", 50);
		Student s1 = new Student("Rahul", "CS", student1);

		Map<String, Integer> student2 = new HashMap<String, Integer>();
		student2.put("Math", 57);
		student2.put("English", 50);
		student2.put("Computer", 78);
		Student s2 = new Student("Sravan", "EC", student2);

		Map<String, Integer> student3 = new HashMap<String, Integer>();
		student3.put("Math", 55);
		student3.put("English", 90);
		student3.put("Computer", 50);
		Student s3 = new Student("Shibu", "MEC", student3);

		Map<String, Integer> student4 = new HashMap<String, Integer>();
		student4.put("Math", 55);
		student4.put("English", 70);
		student4.put("Computer", 50);
		Student s4 = new Student("Vaishnavi", "EEE", student4);

		Map<String, Integer> student5 = new HashMap<String, Integer>();
		student5.put("Math", 77);
		student5.put("English", 50);
		student5.put("Computer", 40);
		Student s5 = new Student("Tony", "CS", student5);

		Map<String, Integer> student6 = new HashMap<String, Integer>();
		student6.put("Math", 62);
		student6.put("English", 57);
		student6.put("Computer", 78);
		Student s6 = new Student("Mani", "EC", student6);

		Map<String, Integer> student7 = new HashMap<String, Integer>();
		student7.put("Math", 55);
		student7.put("English", 95);
		student7.put("Computer", 52);
		Student s7 = new Student("Manu", "MEC", student7);

		Map<String, Integer> student8 = new HashMap<String, Integer>();
		student8.put("Math", 55);
		student8.put("English", 72);
		student8.put("Computer", 50);
		Student s8 = new Student("Nithya", "EEE", student8);

		Map<String, Integer> student9 = new HashMap<String, Integer>();
		student9.put("Math", 55);
		student9.put("English", 35);
		student9.put("Computer", 52);
		Student s9 = new Student("Archa", "MEC", student9);

		Map<String, Integer> student10 = new HashMap<String, Integer>();
		student10.put("Math", 55);
		student10.put("English", 72);
		student10.put("Computer", 50);
		Student s10 = new Student("Sumith", "EEE", student10);

		

		/* Add new student */

		Scanner sc = new Scanner(System.in); 
		System.out.println("Enter The student Name:");
		String nameString = sc.next();
		System.out.println("Enter Department:");
		String departmentString = sc.next();
		Map<String, Integer> markMap = new HashMap<>();
		for (int i = 0; i < 3; i++) {
			System.out.println("Enter subject:");
			String subjectString = sc.next();
			System.out.println("Enter mark:");
			int marks = sc.nextInt();
			markMap.put(subjectString, marks);
		}

		Student addStudent = new Student(nameString, departmentString, markMap);

		studentList.add(s1);
		studentList.add(s2);
		studentList.add(s3);
		studentList.add(s4);
		studentList.add(s5);
		studentList.add(s6);
		studentList.add(s7);
		studentList.add(s8);
		studentList.add(s9);
		studentList.add(s10);

		studentList.add(addStudent);

		Collections.sort(studentList);
		// System.out.println(studentList);

		List<Student> newListEc = studentList.stream().filter(student -> student.getDepartment().equalsIgnoreCase("EC"))
				.peek(students -> students.setRollNo("EC" + ecRollNum++)).collect(Collectors.toList());
		List<Student> newListMec = studentList.stream()
				.filter(student -> student.getDepartment().equalsIgnoreCase("MEC"))
				.peek(students -> students.setRollNo("MEC" + mecRollNum++)).collect(Collectors.toList());
		List<Student> newListCs = studentList.stream().filter(student -> student.getDepartment().equalsIgnoreCase("CS"))
				.peek(students -> students.setRollNo("CS" + csRollNum++)).collect(Collectors.toList());
		List<Student> newListEee = studentList.stream()
				.filter(student -> student.getDepartment().equalsIgnoreCase("EEE"))
				.peek(students -> students.setRollNo("CS" + eeeRollNum++)).collect(Collectors.toList());

		/*
		 * System.out.println(newListEc); System.out.println(newListIt);
		 * System.out.println(newListCs);
		 */

		List<Student> studentDetailsList = new ArrayList();

		studentDetailsList.addAll(newListEc);
		studentDetailsList.addAll(newListMec);
		studentDetailsList.addAll(newListCs);
		studentDetailsList.addAll(newListEee);

		System.out.println(studentDetailsList);
		Map<String, List<Student>> byDepartmentMap = studentDetailsList.stream()
				.collect(Collectors.groupingBy(Student::getDepartment));

		System.out.println(byDepartmentMap);

		/* Display students of the given department */

		System.out.println("Enter The Department Name:");
		String departmentName = sc.next();

		List<Student> givenDepartmentList = studentDetailsList.stream()
				.filter(department -> department.getDepartment().equalsIgnoreCase(departmentName))
				.collect(Collectors.toList());
		System.out.println(givenDepartmentList);

		/* Delete a student record by roll no and dept */

		System.out.println("Enter The Department Name:");
		String departName = sc.next();
		System.out.println("Enter The roll no:");
		String rollNumberString = sc.next();
		List<Student> givenDepartmentList1 = studentDetailsList.stream()
				.filter(student -> student.getDepartment().equalsIgnoreCase(departName)
						&& student.getRollNo().equalsIgnoreCase(rollNumberString))
				.collect(Collectors.toList());
		studentDetailsList.removeAll(givenDepartmentList1);
		System.out.println(studentDetailsList);

		/* Search a student by rollno. and dept */
		System.out.println("Enter The Department Name:");
		String departmentNameString = sc.next();
		System.out.println("Enter The roll no:");
		String rollNum = sc.next();
		List<Student> givenList = studentDetailsList.stream()
				.filter(department -> department.getDepartment().equalsIgnoreCase(departmentNameString)
						&& department.getRollNo().equalsIgnoreCase(rollNum))
				.collect(Collectors.toList());
		System.out.println(givenList);

		/*
		 * Display the Topper in each department based on their total marks(Use:
		 * java stream,reduce )
		 */

		byDepartmentMap.entrySet().forEach(entry -> entry.getValue().stream().forEach(student->student.setTotalmarks(student.getMarksObtained().values().stream().reduce(0,(mark1,mark2)->mark1+mark2,Integer::sum))));
		Map<String,List<Student>> newMap=new HashMap<>();
		byDepartmentMap.entrySet().stream().forEach(entry ->{
		int maxValue=entry.getValue().stream().max(Comparator.comparingInt(student->student.getTotalmarks())).get().getTotalmarks();
		List<Student> newList=entry.getValue().stream().filter(value->value.getTotalmarks()==maxValue).collect(Collectors.toList());
		newMap.put(entry.getKey(), newList);
		});
		System.out.println("Toppers"+newMap);
		
	}

}
