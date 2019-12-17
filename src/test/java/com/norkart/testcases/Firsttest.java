package com.norkart.testcases;

import org.testng.annotations.Test;

import com.norkart.base.TestBase;
import com.norkart.pages.Complaint;

public class Firsttest extends TestBase{
	
	@Test
	public void run() throws InterruptedException {
		
	//	login();
		
		Complaint comp=new Complaint();
		
		comp.createNewComplaint();
				
	}

}
