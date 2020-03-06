package com.spring.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.bo.CourierBO;
import com.spring.exception.InvalidParcelWeightException;
import com.spring.model.Courier;

public class CourierService {
	
	private CourierBO cBoObj;

	public CourierBO getcBoObj() {
		return cBoObj;
	}

	public void setcBoObj(CourierBO cBoObj) {
		this.cBoObj = cBoObj;
	}
	
	public double calculateCourierCharge(int courierId,int weight,String city) throws InvalidParcelWeightException {
		
		double courierCharge=0.0;
		//fill your code
		if(weight>0 && weight < 1000){
			ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			Courier cs = (Courier) context.getBean("courier");
			cs.setCourierId(courierId);
			cs.setWeight(weight);
			courierCharge=cBoObj.calculateCourierCharge(cs, city);
		}
		else
			throw new InvalidParcelWeightException("Invalid Parcel Weight");
			
		return courierCharge;
	}

}
