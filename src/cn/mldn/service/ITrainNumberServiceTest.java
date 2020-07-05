package cn.mldn.service;

import java.security.Provider.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.mldn.factory.ServiceFactory;
import cn.mldn.pojo.TrainNumber;
import junit.framework.TestCase;

public class ITrainNumberServiceTest {

	@Test
	public void testInsert() {
	//	fail("Not yet implemented");
		String trainNum="R001";
		String startP="ÔÀÑô";
		String dest="Â¦µ×";
		String time="2020-07-05 16:24";
		Integer remain=65;
		String price="88";
		TrainNumber train=new TrainNumber(trainNum,startP,dest,time,remain,price);
		try {
			TestCase.assertTrue(ServiceFactory.getITrainNumberServiceInstance().insert(train)==true);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		//fail("Not yet implemented");
		String trainNum="TK98";
		try {
			TestCase.assertTrue(ServiceFactory.getITrainNumberServiceInstance().delete(trainNum));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectUser() {
		//fail("Not yet implemented");
		String startp="n";
		String destp="k";
		List<TrainNumber> list=new ArrayList<TrainNumber>();
		try {
			list=ServiceFactory.getITrainNumberServiceInstance().selectUser(startp, destp);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			TestCase.assertTrue(list.size()>=0);
		}
		
		
	}

//	@Test
//	public void testSelectAll() {
//		//fail("Not yet implemented");
//		List<TrainNumber> list=new ArrayList<TrainNumber>();
//		try {
//			list=ServiceFactory.getITrainNumberServiceInstance().selectAll();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			TestCase.assertTrue(list.size()>=0);
//		}
//		
//	}

	@Test
	public void testUpdate() {
		//fail("Not yet implemented");
		String num="TK99";
		String attribute="price";
		String content="800";
		try {
			TestCase.assertTrue(ServiceFactory.getITrainNumberServiceInstance().Update(num, attribute, content));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
