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
 * 添加一万条记录的类
 * @author ASUS
 *
 */
public class TrainInsert {
	private static Connection con=new DataBaseConnection().getConnection();
	
	public static void main(String[] args) {
		//地址
        String[] city = {"安康市","安庆市","安顺市","安阳市","鞍山市","巴彦淖尔市","巴中市","白城市","白山市","白银市","百色市","蚌埠市","包头市","宝鸡市","保定市","保山市","北海市","本溪市","滨州市","沧州市","昌都地区","长春市","长沙市","长治市","常德市","常州市","巢湖市","朝阳市","潮州市","郴州市","成都市","承德市","池州市","赤峰市","崇左市","滁州市","达州市","大连市","大庆市","大同市","丹东市","德阳市","德州市","定西市","东莞市","东营市","鄂尔多斯市","鄂州市","防城港市","佛山市","福州市","抚顺市","抚州市","阜新市","阜阳市","甘南州","赣州市","固原市","广安市","广元市","广州市","贵港市","贵阳市","桂林市","哈尔滨市","哈密地区","海北藏族自治州","海东地区","海口市","邯郸市","汉中市","杭州市","毫州市","合肥市","河池市","河源市","菏泽市","贺州市","鹤壁市","鹤岗市","黑河市","衡水市","衡阳市","呼和浩特市","呼伦贝尔市","湖州市","葫芦岛市","怀化市","淮安市","淮北市","淮南市","黄冈市","黄山市","黄石市","惠州市","鸡西市","吉安市","吉林市","济南市","济宁市","佳木斯市","嘉兴市","嘉峪关市","江门市","焦作市","揭阳市","金昌市","金华市","锦州市","晋城市","晋中市","荆门市","荆州市","景德镇市","九江市","酒泉市","开封市","克拉玛依市","昆明市","拉萨市","来宾市","莱芜市","兰州市","廊坊市","乐山市","丽江市","丽水市","连云港市","辽阳市","辽源市","聊城市","临沧市","临汾市","临沂市","柳州市","六安市","六盘水市","龙岩市","陇南市","娄底市","泸州市","吕梁市","洛阳市","漯河市","马鞍山市","茂名市","眉山市","梅州市","绵阳市","牡丹江市","内江市","南昌市","南充市","南京市","南宁市","南平市","南通市","南阳市","宁波市","宁德市","攀枝花市","盘锦市","平顶山市","平凉市","萍乡市","莆田市","濮阳市","普洱市","七台河市","齐齐哈尔市","钦州市","秦皇岛市","青岛市","清远市","庆阳市","曲靖市","衢州市","泉州市","日照市","三门峡市","三明市","三亚市","汕头市","汕尾市","商洛市","商丘市","上饶市","韶关市","邵阳市","绍兴市","深圳市","沈阳市","十堰市","石家庄市","石嘴山市","双鸭山市","朔州市","四平市","松原市","苏州市","宿迁市","宿州市","绥化市","随州市","遂宁市","台州市","太原市","泰安市","泰州市","唐山市","天水市","铁岭市","通化市","通辽市","铜川市","铜陵市","铜仁市","吐鲁番地区","威海市","潍坊市","渭南市","温州市","乌海市","乌兰察布市","乌鲁木齐市","无锡市","吴忠市","芜湖市","梧州市","武汉市","武威市","西安市","西宁市","锡林郭勒盟","厦门市","咸宁市","咸阳市","湘潭市","襄樊市","孝感市","忻州市","新乡市","新余市","信阳市","兴安盟","邢台市","徐州市","许昌市","宣城市","雅安市","烟台市","延安市","盐城市","扬州市","阳江市","阳泉市","伊春市","伊犁哈萨克自治州","宜宾市","宜昌市","宜春市","益阳市","银川市","鹰潭市","营口市","永州市","榆林市","玉林市","玉溪市","岳阳市","云浮市","运城市","枣庄市","湛江市","张家界市","张家口市","张掖市","漳州市","昭通市","肇庆市","镇江市","郑州市","中山市","中卫市","舟山市","周口市","株洲市","珠海市","驻马店市","资阳市","淄博市","自贡市","遵义市",};
     
		Random rm=new Random();
	//	String line="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		for(int i=1000;i<9500;i++){
			//产生出发地

	int randomStartCity = rm.nextInt(city.length);
	int randomDestCity = rm.nextInt(city.length);	
			 //产生随机日期
		 Date randomDate = randomDate();
		 	//车次号
			String str="TK"+i;
			//价格
			String price=String.valueOf(rm.nextInt(200)+30);
			//数量
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
	
	//随机生成年月日
	public static Date randomDate() {
		  Calendar calendar = Calendar.getInstance();
	        //注意月份要减去1
	        calendar.set(2019,11,31);
	        calendar.getTime().getTime();
	        //根据需求，这里要将时分秒设置为0
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
	        //得到大于等于min小于max的double值
	        double randomDate = Math.random()*(max-min)+min;
	        //将double值舍入为整数，转化成long类型
	        calendar.setTimeInMillis(Math.round(randomDate));
	        return calendar.getTime();
	      
	       
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date start;
//		Date end;
////		Calendar Cstart=Calendar.getInstance();
////		Calendar Cend=Calendar.getInstance();
//		Calendar calendar=Calendar.getInstance();
//		try {
//			// 构造开始日期
//			start = format.parse(beginDate);
//			end = format.parse(endDate);// 构造结束日期
//
//			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
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
//	  // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
//	  if (rtn == begin || rtn == end) {
//	   return random(begin, end);
//	  }
//	  return rtn;
 }
	
	
	public static boolean addTicket(String trainNum, String startPlace, String dest, String goTime, Integer remainTicket,
			String price) throws SQLException {
		
		//创建插入的sql语句
		String sql=" insert into trainNumber values(?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		
		//将车次信息依次添加到表中
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
