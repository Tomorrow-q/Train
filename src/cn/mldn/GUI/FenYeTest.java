package cn.mldn.GUI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.mldn.factory.ServiceFactory;
import cn.mldn.pojo.TrainNumber;

import cn.mldn.service.ITrainNumberService;
 
public class FenYeTest {
 
	static List<TrainNumber> trainList=new ArrayList<TrainNumber>();
	{
		ITrainNumberService itrainS=ServiceFactory.getITrainNumberServiceInstance();
		//List<TrainNumber> trainList=new ArrayList<TrainNumber>();
		try {
			trainList=itrainS.selectAll(1);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
//	static{
//		User user=new User("0","0");
//		User user1=new User("1","1");
//		User user2=new User("2","2");
//		User user3=new User("3","3");
//		User user4=new User("4","4");
//		User user5=new User("5","5");
//		User user6=new User("6","6");
//		User user7=new User("7","7");
//		User user8=new User("8","8");
//		list.add(user);
//		list.add(user1);
//		list.add(user2);
//		list.add(user3);
//		list.add(user4);
//		list.add(user5);
//		list.add(user6);
//		list.add(user7);
//		list.add(user8);
//	}
 
	public FenYeTest() {
	
	}
 
 
//	public static void main(String[] args) {
//		
//		
//	}
//	
	
	public static List<TrainNumber> findUsers(int currentPage,int pageSize){ 
	
		
       List<TrainNumber> list1=new ArrayList<TrainNumber>();
       int listLength=trainList.size();
       if(currentPage<1){
    	   currentPage=1;
       }
       int startIndex=(currentPage-1)*pageSize;
       int endIndex=startIndex+pageSize;
       
       if(endIndex>=listLength){
    	   endIndex=listLength;
       }
	   list1=  trainList.subList(startIndex, endIndex);  
        return list1;  
    }  
	
	
}