package com.spring.main;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.spring.bo.CourierBO;
import com.spring.exception.InvalidParcelWeightException;
import com.spring.model.Courier;
import com.spring.service.CourierService;

public class Driver {

	public static void main(String[] args) {
	    
		//fill the code
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the courier ID:");
		int id = in.nextInt();
		System.out.println("Enter the total weight of parcel:");
		int weight = in.nextInt();
		in.nextLine();
		System.out.println("Enter the city:");
		String city = in.nextLine();
		Courier cr = new Courier();
		cr.setCourierId(id);
		cr.setWeight(weight);
		CourierBO bo = new CourierBO();
		//System.out.println("Total Courier Charge:"+bo.calculateCourierCharge(cr, city));
		CourierService service;
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		CourierService cs = (CourierService) context.getBean("service");
		try {
			System.out.println("Total Courier Charge:"+cs.calculateCourierCharge(id, weight, city));
		} catch (InvalidParcelWeightException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
