package cn.mldn.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.mldn.dao.ITrainNumberDao;
import cn.mldn.pojo.TrainNumber;

/**
 * 这是对车次信息操作（ITrainNumberDao）的实现类
 * @author ASUS
 *
 */
public class TrainNumberDaoImpl implements ITrainNumberDao {
	private Connection conn=null;//定义数据库连接对象,需要利用Connection对象操作
	private PreparedStatement pstmt;//定义预编译对象
	
	/**
	 * 开发中业务层要调用数据层，数据库的打开与关闭交由业务层处理
	 * @param conn 数据库连接对象
	 */
	public TrainNumberDaoImpl(Connection conn){
		super();
		this.conn=conn;//取得数据库连接
	}
	
	/**
	 * 这是对火车车次信息增加的功能
	 * @param train 欲增加的车次对象
	 * @return  是否添加成功，成功为ture，否则返回false
	 * @throws SQLException 
	 */
	@Override
//	public boolean addTicket(String trainNum, String startPlace, String dest, String goTime, Integer remainTicket,
//			String price) throws SQLException {
		public boolean addTicket(TrainNumber train) throws SQLException {

		//创建插入的sql语句
		String sql=" insert into trainNumber values(?,?,?,?,?,?)";
		//创建预编译对象
		this.pstmt=this.conn.prepareStatement(sql);
		//将车次信息依次添加到表中
		this.pstmt.setString(1, train.getTrainNum());
		this.pstmt.setString(2,train.getStartPlace());
		this.pstmt.setString(3,train.getDestination());
		this.pstmt.setString(4,train.getGoTime());
		this.pstmt.setInt(5, train.getRemainTicket());
		this.pstmt.setString(6,train.getPrice());
		if(pstmt.executeUpdate()>0){
			return true;
		}
		
		return false;
	}

	
	/**
	 * 这是用户对火车车次信息查找的功能
	 * @param startPlace  始发地
	 * @param destPlace   目的地
	 * @return 查询的车次的集合，如果没有该车次，则list集合的size为0
	 * @throws SQLException 
	 */
	@Override
	public List<TrainNumber> findUserTicket(String startPlace, String destPlace) throws SQLException {
		//创建集合存入用户查询的车次信息
		List<TrainNumber> messList=new ArrayList<TrainNumber>();
		//查询sql语句：从trainNumber表中查找用户输入的始发地和目的地
		String sql="select * from trainNumber where startPlace = ? and destination = ?";
		
		//获取预编译对象
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setString(1, startPlace);
		this.pstmt.setString(2, destPlace);
		//获取结果集
		ResultSet rs=this.pstmt.executeQuery();
		while(rs.next()){
			TrainNumber tn=new TrainNumber();
			tn.setTrainNum(rs.getString(1));
			tn.setStartPlace(rs.getString(2));
			tn.setDestination(rs.getString(3));
			tn.setGoTime(rs.getString(4));
			tn.setRemainTicket(rs.getInt(5));
			tn.setPrice(rs.getString(6));
			//将查询到的每条数据加入集合
			messList.add(tn);
			
		}
		
		return messList;
	}

	
	/**
	 * 这是管理员对火车车次删除的操作
	 * @param trainNum 车次号
	 * @return 是否删除成功，成功为true
	 * @throws SQLException 
	 */
	@Override
	public boolean deleteTicket(String trainNum) throws SQLException {
		//查询sql语句
		String sql="delete from trainNumber where trainNum ='"+trainNum+"'";
		//获取预编译对象
		this.pstmt=this.conn.prepareStatement(sql);
		int count =pstmt.executeUpdate();
		if(count>0){
			return true;
		}
		return false;
	}

	
	/**
	 * 这是管理员对车次信息进行修改的功能
	 * @param trainNum  车次号
	 * @param attribute 要修改的属性
	 * @param content   修改后的内容
	 * @return 是否修改成功，成功为true
	 * @throws SQLException 
	 */
	@Override
	public boolean updateTicket(String trainNum, String attribute, String content) throws SQLException {
		//查询sql语句,将TrainNumber 表中车次号为trainNum的属性为attribute修改为content
		String sql="Update TrainNumber set "+attribute+" =? where  trainNum=? ";
		//获取预编译对象
		this.pstmt=this.conn.prepareStatement(sql);
		int attribContent;
		if(attribute.equals("remainTicket")){
			attribContent=Integer.parseInt(content);
			pstmt.setInt(1, attribContent);
			pstmt.setString(2, trainNum);
		}else{
			pstmt.setString(1, content);
			pstmt.setString(2, trainNum);
		}
		
		if(pstmt.executeUpdate()>0){
			return true;
		}

		
		return false;
	}

	
	
	/**
	 * 这是管理员查询所有车次信息的功能
	 * @return 查询的所有车次信息的集合
	 * @throws SQLException 
	 */
	@Override
	public List<TrainNumber> findAllTicket(int num) throws SQLException {
		
		List<TrainNumber> trainList =new ArrayList<TrainNumber>();
		String sql="select * from trainNumber";
		this.pstmt=this.conn.prepareStatement(sql);
		//获得查询结果集
		ResultSet rs=this.pstmt.executeQuery();
		while(rs.next()){
			TrainNumber tn=new TrainNumber();
			tn.setTrainNum(rs.getString("trainNum"));
			tn.setStartPlace(rs.getString("startPlace"));
			tn.setDestination(rs.getString("destination"));
			tn.setRemainTicket(rs.getInt("remainTicket"));
			tn.setGoTime(rs.getString("goTime"));
			tn.setPrice(rs.getString("price"));
			//将查询的结果存入集合
			trainList.add(tn);
		}
		switch(num){
		
		
	case 1://升序
		Collections.sort(trainList, new Comparator<TrainNumber>() {
			public int compare(TrainNumber t1,TrainNumber t2) {
				return (Integer.parseInt(t1.getPrice())-Integer.parseInt(t2.getPrice()));
			}
		});
		break;
		
	case 2://降序
		Collections.sort(trainList, new Comparator<TrainNumber>() {
			public int compare(TrainNumber t1,TrainNumber t2) {
				return  (Integer.parseInt(t2.getPrice())-Integer.parseInt(t1.getPrice()));
			}
		});
		}
		return trainList;
	}


	/**
	 * 这是判断管理员输入的车次信息是否已在数据库中存在
	 * @param train 车次对象
	 * @return 0为票数增加，即有相同的车次；1是有相同车次号，不可重复主键值，不可增加；2可增加该车次
	 * @throws SQLException
	 */
	@Override
//	public int isExist(String trainNum, String startPlace, String dest, String goTime,Integer retick,String price ) throws SQLException {
	public int isExist(TrainNumber train) throws SQLException {	
	//查询插入的车次是否存在，存在则只需票数增加
		//查询的sql语句：从trainNumber表中查找车次号为trainNum的车次的车次号、始发地、目的地、剩余车票和出发时间
		String psql="select startPlace,destination,remainTicket, goTime,price from trainNumber where trainNum = ?";
		this.pstmt=this.conn.prepareStatement(psql);
		pstmt.setString(1, train.getTrainNum());
		ResultSet rs=pstmt.executeQuery();
		int flag=2;
		//判断结果集是否为空，不为空则表示查询到了数据， 有相同的车次号信息，则直接增加该车次票数
		if(rs!=null){
			while(rs.next()){
				String num=rs.getString(1);
					String start=rs.getString("startPlace");
					String desti=rs.getString("destination");
					String time=rs.getString("goTime");
					//获取原车票
					Integer remain=rs.getInt("remainTicket");
					String pric=rs.getString("price");
					if(train.getTrainNum().equals(num)&&train.getStartPlace().equals(start)&&train.getDestination().equals(desti)&&train.getGoTime().equals(time)&&train.getPrice().equals(pric)){
						remain+=train.getRemainTicket();
						pstmt.setInt(5, remain);
						//返回true表示插入的车次信息已存在数据库中，只需票数增加
						flag=0;//已增加票数
							break;
					}else if(flag!=0&&train.getTrainNum().equals(num)){
						flag=1;
					}
						
			}
			
		}
		//0为票数增加，1为已存在车次号不可增加，2是没有该车次,可增加
		return flag;
		
	}

	@Override
	public List<TrainNumber> sort(int num) {
		List<TrainNumber> trainList=null;
		try {
			trainList=findAllTicket(num);
			switch(num){
			case 1://升序
				Collections.sort(trainList, new Comparator<TrainNumber>() {
					public int compare(TrainNumber t1,TrainNumber t2) {
						return t1.getTrainNum().compareTo(t2.getTrainNum());
					}
				});
				break;
				
			case 2://降序
				Collections.sort(trainList, new Comparator<TrainNumber>() {
					public int compare(TrainNumber t1,TrainNumber t2) {
						return t2.getTrainNum().compareTo(t1.getTrainNum());
					}
				});
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return trainList;
	}


//	@Override
//	public List<UserOrder> findOrder() throws SQLException {
//		List<UserOrder> trainlist= new ArrayList<UserOrder>();
//		String sql="select trainNum,passengerName,IDCard from userOrder";
//		this.pstmt=this.conn.prepareStatement(sql);
//		ResultSet rs=this.pstmt.executeQuery();
//		while(rs.next()){
//			UserOrder uo=new UserOrder();
//			uo.setTrainName(rs.getString("trainNum"));
//			uo.setPassengName(rs.getString("passengerName"));
//			uo.setIDCard(rs.getString("IDCard"));
//			
//			//加入集合
//			trainlist.add(uo);
//		}
//		
//		return trainlist;
//	}


	
}
