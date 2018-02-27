package com.bjdbd.commen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SmsUtil {


	private static String SMS_JGID = "300";
	private static String SMS_YHMC = "bjdbd";
	private static String SMS_YHMM = "bjdbd";
	private static String SMS_IpAddr = "sms.bdt360.com:8180";

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			boolean res = new SmsUtil().sendSMS("17601010562", "Java Http方式短信调试已经成功!!!!!");
			System.out.println(res);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 发送方法 其他方法同理
	 */
	public static boolean sendSMS(String mobile, String content) throws MalformedURLException, UnsupportedEncodingException {
		URL url = null;
		String JGID = SMS_JGID;// 机构ID名
		String YHMC = SMS_YHMC;// 帐户名称
		String YHMM = SMS_YHMM;// 密码
		String IpAddr = SMS_IpAddr;// 接口服务器IP地址
		String send_content = URLEncoder.encode(content, "utf-8");// 发送内容
		url = new URL("http://" + IpAddr + "/service.asmx/SendMessageStr?Id=" + JGID + "&Name=" + YHMC + "&Psw=" + YHMM + "&Message=" + send_content + "&Phone=" + mobile + "&Timestamp=0");
		BufferedReader in = null;
		String inputLine = "";
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = in.readLine();
			if (inputLine.contains("State:1")) {
				return true;
			}
		} catch (Exception e) {
			inputLine = "-1000";
		}
		return false;
	}

}
