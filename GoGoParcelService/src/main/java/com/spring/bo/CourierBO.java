package com.spring.bo;

import java.util.Map;

import com.spring.model.Courier;

public class CourierBO  {
	
	public double calculateCourierCharge(Courier cObj,String city) {
		double courierCharge=0.0;
		//fill the code
		for (Map.Entry<String,Float> entry : cObj.getServiceCharge().getLocationServiceCharge().entrySet()) {
			if(entry.getKey().equals(city)){
				courierCharge = cObj.getWeight()*cObj.getChargePerKg();
				courierCharge = courierCharge + entry.getValue();
			}
			else{
				courierCharge = cObj.getWeight()*cObj.getChargePerKg();
			}
		}
	    return courierCharge;
	}

}
