package cn.mldn.service;


import java.io.IOException;

import org.junit.Test;

import cn.mldn.factory.ServiceFactory;
import cn.mldn.pojo.Userd;
import junit.framework.TestCase;

public class IUserdServiceTest {

	@Test
	public void testLogin() {
		//fail("Not yet implemented");
		String name="root0";
		String word="123450";
		Userd user=new Userd();
		TestCase.assertTrue(ServiceFactory.getIUserdServiceInstance().login(name, word, user));
		
	}

	@Test
	public void testRegister() {
		//fail("Not yet implemented");
		Userd user=new Userd("123321","1233210","花花","女","421055411132014588");
		try {
			TestCase.assertTrue(ServiceFactory.getIUserdServiceInstance().register(user));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testChangeMessage() {
		//fail("Not yet implemented");
		Userd user=new Userd("root23","1234423","雷艺","女","450102197110100461");
		TestCase.assertTrue(ServiceFactory.getIUserdServiceInstance().changeMessage(user));
		
		
	}

	@Test
	public void testIsEqual() {
		//fail("Not yet implemented");
		Userd user=new Userd("root25","1234525","东心元","男","37140019530505988X");
		String pass="1234525";
		TestCase.assertTrue(ServiceFactory.getIUserdServiceInstance().isEqual(user, pass));
	}

	@Test
	public void testCloseAccount() {
		//fail("Not yet implemented");
		Userd user=new Userd("root24","1234524","滑筠悦","女","340200197801224129");
		TestCase.assertTrue(ServiceFactory.getIUserdServiceInstance().closeAccount(user));
		
	}

}
