package cn.mldn.service;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.mldn.factory.ServiceFactory;
import junit.framework.TestCase;

public class IManageServiceTest {

	@Test
	public void testManageLogin() {
		//fail("Not yet implemented");
		String name="admin0";
		String pass="admin0";
		TestCase.assertTrue(ServiceFactory.getIManageServiceInstance().ManageLogin(name, pass));
		
	}

}
