package com.cisco.dgc.ram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RemoteAssistManagementApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void passwordTest() {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}
	
}
