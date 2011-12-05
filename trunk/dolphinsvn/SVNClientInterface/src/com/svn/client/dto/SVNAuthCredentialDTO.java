package com.svn.client.dto;

import java.util.HashMap;
import java.util.Map;

public class SVNAuthCredentialDTO {
	private String userId;
	private String password;
	public SVNAuthCredentialDTO(){
		
	}
	public SVNAuthCredentialDTO(String userId,String password){
		this.userId = userId;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("User Id",this.userId);
		map.put("password",this.password);
		return map.toString();
	}
}
