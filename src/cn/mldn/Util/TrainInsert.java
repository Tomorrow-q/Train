package cn.mldn.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import cn.mldn.dbc.DataBaseConnection;
import cn.mldn.factory.ServiceFactory;
import cn.mldn.pojo.TrainNumber;
/**
 * ���һ������¼����
 * @author ASUS
 *
 */
public class TrainInsert {
	private static Connection con=new DataBaseConnection().getConnection();
	
	public static void main(String[] args) {
		//��ַ
        String[] city = {"������","������","��˳��","������","��ɽ��","�����׶���","������","�׳���","��ɽ��","������","��ɫ��","������","��ͷ��","������","������","��ɽ��","������","��Ϫ��","������","������","��������","������","��ɳ��","������","������","������","������","������","������","������","�ɶ���","�е���","������","�����","������","������","������","������","������","��ͬ��","������","������","������","������","��ݸ��","��Ӫ��","������˹��","������","���Ǹ���","��ɽ��","������","��˳��","������","������","������","������","������","��ԭ��","�㰲��","��Ԫ��","������","�����","������","������","��������","���ܵ���","��������������","��������","������","������","������","������","������","�Ϸ���","�ӳ���","��Դ��","������","������","�ױ���","�׸���","�ں���","��ˮ��","������","���ͺ�����","���ױ�����","������","��«����","������","������","������","������","�Ƹ���","��ɽ��","��ʯ��","������","������","������","������","������","������","��ľ˹��","������","��������","������","������","������","�����","����","������","������","������","������","������","��������","�Ž���","��Ȫ��","������","����������","������","������","������","������","������","�ȷ���","��ɽ��","������","��ˮ��","���Ƹ���","������","��Դ��","�ĳ���","�ٲ���","�ٷ���","������","������","������","����ˮ��","������","¤����","¦����","������","������","������","�����","��ɽ��","ï����","üɽ��","÷����","������","ĵ������","�ڽ���","�ϲ���","�ϳ���","�Ͼ���","������","��ƽ��","��ͨ��","������","������","������","��֦����","�̽���","ƽ��ɽ��","ƽ����","Ƽ����","������","�����","�ն���","��̨����","���������","������","�ػʵ���","�ൺ��","��Զ��","������","������","������","Ȫ����","������","����Ͽ��","������","������","��ͷ��","��β��","������","������","������","�ع���","������","������","������","������","ʮ����","ʯ��ׯ��","ʯ��ɽ��","˫Ѽɽ��","˷����","��ƽ��","��ԭ��","������","��Ǩ��","������","�绯��","������","������","̨����","̫ԭ��","̩����","̩����","��ɽ��","��ˮ��","������","ͨ����","ͨ����","ͭ����","ͭ����","ͭ����","��³������","������","Ϋ����","μ����","������","�ں���","�����첼��","��³ľ����","������","������","�ߺ���","������","�人��","������","������","������","���ֹ�����","������","������","������","��̶��","�差��","Т����","������","������","������","������","�˰���","��̨��","������","�����","������","�Ű���","��̨��","�Ӱ���","�γ���","������","������","��Ȫ��","������","���������������","�˱���","�˲���","�˴���","������","������","ӥ̶��","Ӫ����","������","������","������","��Ϫ��","������","�Ƹ���","�˳���","��ׯ��","տ����","�żҽ���","�żҿ���","��Ҵ��","������","��ͨ��","������","����","֣����","��ɽ��","������","��ɽ��","�ܿ���","������","�麣��","פ�����","������","�Ͳ���","�Թ���","������",};
     
		Random rm=new Random();
	//	String line="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		for(int i=1000;i<9500;i++){
			//����������

	int randomStartCity = rm.nextInt(city.length);
	int randomDestCity = rm.nextInt(city.length);	
			 //�����������
		 Date randomDate = randomDate();
		 	//���κ�
			String str="TK"+i;
			//�۸�
			String price=String.valueOf(rm.nextInt(200)+30);
			//����
			Integer count=rm.nextInt(70)+50;
		TrainNumber train=new TrainNumber();
		train.setTrainNum(str);
		train.setStartPlace(city[randomStartCity]);
		train.setDestination(city[randomDestCity]);
		train.setGoTime(randomDate.toString());
		train.setRemainTicket(count);
		train.setPrice(price);
		
				try {
					ServiceFactory.getITrainNumberServiceInstance().insert(train);
				} catch (Exception e) {

					e.printStackTrace();
				}finally{
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
						}
				}
				}
			
		
	}
	
	//�������������
	public static Date randomDate() {
		  Calendar calendar = Calendar.getInstance();
	        //ע���·�Ҫ��ȥ1
	        calendar.set(2019,11,31);
	        calendar.getTime().getTime();
	        //������������Ҫ��ʱ��������Ϊ0
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND,0);
	        long min = calendar.getTime().getTime();
	        calendar.set(2028,11,31);
	        calendar.set(Calendar.HOUR_OF_DAY,0);
	        calendar.set(Calendar.MINUTE,0);
	        calendar.set(Calendar.SECOND,0);
	        calendar.getTime().getTime();
	        long max = calendar.getTime().getTime();
	        //�õ����ڵ���minС��max��doubleֵ
	        double randomDate = Math.random()*(max-min)+min;
	        //��doubleֵ����Ϊ������ת����long����
	        calendar.setTimeInMillis(Math.round(randomDate));
	        return calendar.getTime();
	      
	       
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date start;
//		Date end;
////		Calendar Cstart=Calendar.getInstance();
////		Calendar Cend=Calendar.getInstance();
//		Calendar calendar=Calendar.getInstance();
//		try {
//			// ���쿪ʼ����
//			start = format.parse(beginDate);
//			end = format.parse(endDate);// �����������
//
//			// getTime()��ʾ������ 1970 �� 1 �� 1 �� 00:00:00 GMT ������ Date �����ʾ�ĺ�������
//			 if (start.getTime() >= end.getTime()) {
//				    return null;
//			 }
//				
//			 long date = random(start.getTime(), end.getTime());
//			calendar.setTime(new Date(date));
//			System.out.println( calendar.YEAR+"-"+calendar.MONTH+"-"+calendar.DAY_OF_MONTH+" "+calendar.HOUR_OF_DAY+":"+calendar.MINUTE);
//			 return calendar.YEAR+"-"+calendar.MONTH+"-"+calendar.DAY_OF_MONTH+" "+calendar.HOUR_OF_DAY+":"+calendar.MINUTE;
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//	return null;
//		 
//	}

	
//	private static long random(long begin, long end) 
//	{
//	  long rtn = begin + (long) (Math.random() * (end - begin));
//	  // ������ص��ǿ�ʼʱ��ͽ���ʱ�䣬��ݹ���ñ������������ֵ
//	  if (rtn == begin || rtn == end) {
//	   return random(begin, end);
//	  }
//	  return rtn;
 }
	
	
	public static boolean addTicket(String trainNum, String startPlace, String dest, String goTime, Integer remainTicket,
			String price) throws SQLException {
		
		//���������sql���
		String sql=" insert into trainNumber values(?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		
		//��������Ϣ������ӵ�����
		pstmt.setString(1, trainNum);
		pstmt.setString(2,startPlace );
		pstmt.setString(3,dest );
		pstmt.setString(4,goTime );
		pstmt.setInt(5, remainTicket);
		pstmt.setString(6,price );
		if(pstmt.executeUpdate()>0){
			return true;
		}
		
		return false;
	}
}
