package cn.bx.bid.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
	public final static String epid = "AHHF1307647";
	public final static String User_Name = "admin";
	public final static String password = "49ba59abbe56e057";

	public static String sendMsm(String phone,String content) {
		String str="";
		try {
			// 1、创建服务的地址
			URL url = new URL("http://access.xx95.net:8886/Connect_Service.asmx");
			// 2、打开服务的连接
			HttpURLConnection ct = (HttpURLConnection) url.openConnection();
			// 3、设置亲请求参数
			ct.setRequestMethod("POST");
			// 4、设置数据格式
			ct.setRequestProperty("host", "access.xx95.net");
			ct.setRequestProperty("content-type", "test/xml;charset=utf-8");
			ct.setRequestProperty("content-length", getXml(phone,content).getBytes().length + "");
			ct.setRequestProperty("SOAPAction", "http://access.xx95.net:8886/SendSms");
			// 5、设置输入输出的权限
			ct.setDoInput(true);
			ct.setDoOutput(true);
			// 6、组织soap xml文件，发送短信
			OutputStream out = ct.getOutputStream();
			out.write(getXml(phone,content).getBytes("utf-8"));
			// 7、获得响应状态码
			int responseCode = ct.getResponseCode();
			if (200 == responseCode) {// 表示服务端响应成功
				InputStream is = ct.getInputStream();
				InputStreamReader isr = new InputStreamReader(is,"utf-8");
				BufferedReader br = new BufferedReader(isr);
				StringBuilder sb = new StringBuilder();
				String temp = null;
				while (null != (temp = br.readLine())) {
					sb.append(temp);
				}
				String res = sb.toString();
				System.out.println(res);
				int s = res.indexOf("<SendSmsResult>");
				int e = res.indexOf("</SendSmsResult>");
				System.out.println("---------------------------------");
				// 8、获得的发送信息的状态码：00表示成功
				System.out.println(res.substring(s + 15, e));
				System.out.println(res);
				str=res.substring(s + 15, e);
				is.close();
				isr.close();
				br.close();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getXml(String phone,String content) {
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + //
				"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ //
				"  <soap:Body>\r\n" + //
				"    <SendSms xmlns=\"http://access.xx95.net:8886/\">\r\n" + //
				"      <epid>" + epid + "</epid>\r\n" + //
				"      <User_Name>" + User_Name + "</User_Name>\r\n" + //
				"      <password>" + password + "</password>\r\n" + //
				"      <phone>"+phone+"</phone>\r\n" + //
				"      <content>" + content + "</content>\r\n" + //
				"    </SendSms>\r\n" + //
				"  </soap:Body>\r\n" + //
				"</soap:Envelope>";
		return xml;
	}


	public static void main(String[] args){

		sendMsm("18697543616","邀请你明天来开会");
	}
}
