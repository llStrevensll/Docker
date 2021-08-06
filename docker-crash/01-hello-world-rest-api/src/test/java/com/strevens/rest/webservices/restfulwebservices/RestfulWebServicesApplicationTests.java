package com.strevens.rest.webservices.restfulwebservices;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RestfulWebServicesApplicationTests {
	
	String message = "Angel";
	String message1 = "HelloWorld [message=Angel]";
	HelloWorld messageTesPrint = new HelloWorld(message);
	HelloWorld messageTestToString = new HelloWorld(message);
	
	@Test
	public void testPrintMessage() {
		assertEquals(message,messageTesPrint.printMessage());
		
	}
	
	@Test
	public void testToStringMessage() {
		assertEquals(message1,messageTestToString.toString());
	}
	
	
	@Test
	public void contextLoads() {
	}

}
