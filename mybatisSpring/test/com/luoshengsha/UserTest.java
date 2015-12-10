package com.luoshengsha;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.luoshengsha.bean.User;
import com.luoshengsha.service.UserService;

public class UserTest {
protected static UserService userService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			userService = (UserService)cxt.getBean("userServiceImpl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void save() {
		User user = new User();
		user.setId("2");
		user.setName("блжЯ");
		user.setPassword("123456");
		userService.save(user);
	}
	
	@Test
	public void find() {
		User user = userService.find("2");
		System.out.println("name: " + user.getName());
	}
	
	@Test
	public void update() {
		User user = userService.find("2");
		user.setName("блжЯ2");
		userService.update(user);
	}
	
	@Test
	public void delete() {
		userService.delete("2");
	}
}
