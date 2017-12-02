package com.springboot.cud.operation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.cud.operation.dao.CudDao;
import com.springboot.cud.operation.model.Employee;
import com.springboot.cud.operation.response.Response;

@RestController
public class CudController {
	@Autowired
	private CudDao dao;
@RequestMapping(value ="/create", method=RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE})
public Response create(@RequestBody Employee employee) throws Exception {
	Response resp = new Response();
	List<String> valerrors = new ArrayList<String>();
	if(employee.getId()== null || employee.getfName()== null|| employee.getEmail()==null){
		String str1 ="mandatory fields is missing";
		valerrors.add(str1);
	}
	if(employee.getBirthDate().after(new Date())) {
		String str2 =" birthDate is a future date";
		valerrors.add(str2);
	}
	if(valerrors.isEmpty()) {
	Employee ExistingEmployee = dao.findEmployee(employee.getId());
	if(ExistingEmployee == null) {
	String id = dao.create(employee);
	
	resp.setResMsg("New Record Created");
	resp.setUserId(id);
	} else {
		resp.setResMsg("An employee is active with the given id");
	}
	return resp;
	}
	else {
		resp.setResMsg("New Record could not created due to validation failures");
		resp.setValErrors(valerrors);
		return resp;
	}
}@RequestMapping(value ="/update", method=RequestMethod.PUT, produces={MediaType.APPLICATION_JSON_VALUE})
public Response update(@RequestBody Employee employee) throws Exception {
	dao.findEmployee(employee.getId());
	 dao.update(employee);
	Response resp = new Response();
	resp.setResMsg(" Record updated");
	resp.setUserId(employee.getId());
	return resp;
}
@RequestMapping(value ="/delete", method=RequestMethod.DELETE, produces={MediaType.APPLICATION_JSON_VALUE})
public Response delete(@RequestBody Employee employee) throws Exception {
	dao.findEmployee(employee.getId());
	  dao.delete(employee);
	Response resp = new Response();
	resp.setResMsg("Record deactivated");
	resp.setUserId(employee.getId());
	return resp;
}

}
