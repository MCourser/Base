package com.cisco.dgc.ram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.machao.sp.dao.UserRepository;
import com.machao.sp.feign.SteamService;
import com.machao.sp.service.PermissionService;
import com.machao.sp.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RemoteAssistManagementApplicationTests {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private SteamService steamService;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void passwordTest() {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}

	
	@Test
	public void steamClientTest() {
		String result = steamService.listInventory("76561198099048876", 570, 2, null, 0);
		System.out.println(result);
	}

	
}
