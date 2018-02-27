package com.bjdbd.commen;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;



/**
 * ����һ�����������ַ����Ĺ�����
 */
public final class StringHelper {
	
	/**
	 * �ж�һ���ַ����Ƿ�Ϊ null �� �մ� �� �հ�
	 * @param source ��Ҫ�жϵ��ַ���
	 * @return ���ַ���Ϊ null �� Ϊ �հס��մ� ʱ���� true
	 */
	public static boolean empty( String source ) {
		return source == null || source.trim().isEmpty()  ;
	}
	
	/**
	 * �ж�һ���ַ����Ƿ���null�Ҳ��ǿմ������ǿհ�
	 * @param source ��Ҫ�жϵ��ַ���
	 * @return �� �ַ����ǲ���null�Ҳ��ǿմ�Ҳ���ǿհ�ʱ���� true
	 */
	public static boolean notEmpty( String source ) {
		return source != null && source.trim().length() > 0 ;
	}
	
	/**
	 * �ж�һ���ַ��������Ƿ�Ϊ null
	 * @param source ��Ҫ�жϵ��ַ���
	 * @return �� �ַ������� Ϊ null ʱ���� true
	 */
	public static boolean isNull( String source ) {
		return source == null ;
	}
	
	/**
	 * �ж�һ���ַ����Ƿ�Ϊ �մ�
	 * @param source ��Ҫ�жϵ��ַ���
	 * @return ���ַ����е�ֵ�� �մ� �� �հ� ��ʱ���� true
	 */
	public static boolean emptyString( String source ) {
		return ( source != null ) && source.length() == source.trim().length() ;
	}
	
	/**
	 * �ж�һ���ַ����Ƿ�Ϊ �հ� ��
	 * @param source ��Ҫ�жϵ��ַ���
	 * @return ���ַ����е�ֵ�� �հ� ��ʱ���� true
	 */
	public static boolean blank( String source ){
		return ( source != null ) && source.length() > source.trim().length()  ;
	}
	
	/**
	 * �Ƚ������ǿ�(����null�����ǿմ������ǿհ�)�ַ����Ƿ�"���"
	 * @param one ��һ����Ҫ�Ƚϵ��ַ���
	 * @param theOther ��һ������Ƚϵ��ַ���
	 * @return �� �����ַ��� ����Ϊ�մ� �� ������ȫһ�� (�޳���β�հ׺󡢴�СдҲһ��)ʱ���� true
	 */
	public static boolean equals( String one , String theOther) {
		return equals(one, theOther,true,false);
	}
	
	/**
	 * �Ƚ������ַ����Ƿ� "���"
	 * @param one ����Ƚϵĵ�һ���ַ���
	 * @param theOther ����Ƚϵ���һ���ַ���
	 * @param escapeSpace �Ƿ���Ҫ�޳���β�հ� ( true ��ʾ��Ҫ�޳���β�հף�false ��ʾ���޳� )
	 * @param ignoreCase �Ƿ���Դ�Сд ( true ��ʾ���Դ�Сд ��false ��ʾ�����Դ�Сд )
	 * @return
	 */
	public static boolean equals( String one , String theOther , boolean escapeSpace , boolean ignoreCase) {
		
		if( one == null || theOther == null ){
			return false ;
		}
		
		if( escapeSpace ){
			one = one.trim();
			theOther = theOther.trim();
		}
		
		return ignoreCase ? one.equalsIgnoreCase( theOther ) : one.equals( theOther ) ;
	}
	
	/**
	 * �������һ�� 32 λ���ȵ� �ַ���( UUID )
	 * @return
	 */
	public static String random(){
		UUID uuid = UUID.randomUUID();//36λ����(������ �ĸ� - )
		String uuidString = uuid.toString();
		uuidString = uuidString.replace("-", "");
		uuidString = uuidString.toUpperCase();
		return uuidString;
	}
	
	/**
	 * ���ַ�������MD5����
	 * @param source ��Ҫ���ܵ��ַ���
	 * @return ���ؼ��ܺ���ַ���
	 */
	public static final String MD5(String source){
		if(source != null){
			StringBuffer md5 = new StringBuffer();
			MessageDigest md = null;
			try{
				md = MessageDigest.getInstance("MD5");
				md.update(source.getBytes());
				byte[] mdBytes = md.digest();
				
				for(int i = 0;i < mdBytes.length;i++){
					int temp;
					if(mdBytes[i] < 0){
						temp = 256+mdBytes[i];
					}else{
						temp = mdBytes[i];
					}
					if(temp < 16){
						md5.append("0");
					}
					md5.append(Integer.toString(temp,16 ));
				}
			}catch(NoSuchAlgorithmException e){
				e.printStackTrace();
			}
			return md5.toString().toUpperCase();
		}
		return null;
	}

}
