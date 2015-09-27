package ru.todo100.activer.util;

import java.security.MessageDigest;

public class Robokassa {
	private Float sum;
	private Long order;
	public String md5(String str) {
		MessageDigest md5 ;        
		StringBuffer  hexString = new StringBuffer();
		try {
			md5 = MessageDigest.getInstance("md5");
		    md5.reset();
		    md5.update(str.getBytes()); 
		    byte messageDigest[] = md5.digest();
		    for (int i = 0; i < messageDigest.length; i++) {
		        String hex=Integer.toHexString(0xff & messageDigest[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);		        
		    }                                                                        
		} 
		catch (java.security.NoSuchAlgorithmException e) {                        
		    
		}
		return hexString.toString();
	}
	
	public void setSum(Float sum) {
		this.sum = sum;
	}
	
	public void setOrder(Long order) {
		this.order = order;
	}
	
	public String getLink() {
		String str = "3dplenty.com:" + sum.toString() + ":" + order.toString() + ":ineler1";
		String hex = md5(str);
		return "https://auth.robokassa.ru/Merchant/Handler/MrchSumPreview.ashxMerchantLogin=3dplenty.com&InvId="+order.toString()+"&OutSum="+sum.toString()+"&SignatureValue=" + hex;
	}
}
