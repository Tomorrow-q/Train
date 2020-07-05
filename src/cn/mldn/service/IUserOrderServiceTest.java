package cn.mldn.service;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.mldn.factory.ServiceFactory;
import cn.mldn.pojo.TrainNumber;
import cn.mldn.pojo.UserOrder;
import cn.mldn.pojo.Userd;
import junit.framework.TestCase;

public class IUserOrderServiceTest {

	@Test
	public void testBuy() {
		//fail("Not yet implemented");
		String tnum="Tk99";
		Integer remain=71;
		Userd user=new Userd("root0","123450","¿∂“¡","≈Æ","640424199211087146");
		try {
			TestCase.assertTrue(ServiceFactory.getIUserOrderServiceInstance().buy(user, tnum, remain));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testFindUseOrder() {
		//fail("Not yet implemented");
		String id="640424199211087146";
		List<UserOrder> ulist=new ArrayList<UserOrder>();
		try {
			ulist=ServiceFactory.getIUserOrderServiceInstance().findUseOrder(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			TestCase.assertTrue(ulist.size()>0);
		}
		
		
		
	}

	@Test
	public void testReturnTic() {
		//fail("Not yet implemented");
		String tnum="Tk99";
		String id="640424199211087146";
		try {
			TestCase.assertTrue(ServiceFactory.getIUserOrderServiceInstance().returnTic(tnum, id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testFindManageAllOrder() {
		//fail("Not yet implemented");
		List<UserOrder> mlist=new ArrayList<UserOrder>();
		mlist=ServiceFactory.getIUserOrderServiceInstance().findManageAllOrder();
		TestCase.assertTrue(mlist.size()>0);	
	}

}
