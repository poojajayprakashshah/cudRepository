package com.springboot.cud.operation.response;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class Response {
private String resMsg;
private String userId;
private List<String> valErrors;
public String getResMsg() {
	return resMsg;
}
public void setResMsg(String resMsg) {
	this.resMsg = resMsg;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public List<String> getValErrors() {
	return valErrors;
}
public void setValErrors(List<String> valErrors) {
	this.valErrors = valErrors;
}
}
