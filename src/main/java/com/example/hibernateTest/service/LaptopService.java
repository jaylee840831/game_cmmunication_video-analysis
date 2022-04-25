package com.example.hibernateTest.service;

import org.springframework.stereotype.Service;

import com.example.hibernateTest.config.ConfigProp;
import com.example.hibernateTest.dto.Laptop;

@Service
public class LaptopService {

	public Laptop setLaptop(int lid,String laptop) {
		
//		Laptop L=new Laptop();
//		L.setLid(lid);
//		L.setLname(laptop);
		
		return null;
	}
	
	public Laptop getLaptop(int lid) {
		ConfigProp configProp=new ConfigProp();
		return configProp.getData(lid);
	}
	
	public Laptop getLaptop() {
		ConfigProp configProp=new ConfigProp();
		return configProp.getData();
	}
}
