package com.springboot.cud.operation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.springboot.cud.operation.model.Employee;

@Component
@Repository
public class CudDao {
@Autowired
private JdbcTemplate jdbcTemplate;

public String create(final Employee employee) {
	final String sql ="insert into Employee(fName,lName,email,pinCode,birthDate,isActive)values(?,?,?,?,?,?,)";
	KeyHolder holder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {
		
		public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			// TODO Auto-generated method stub
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, employee.getfName());
			ps.setString(2, employee.getlName());

			ps.setString(3, employee.getEmail());

			ps.setString(4, employee.getPinCode().toString());

			ps.setString(5, employee.getBirthDate().toString());

			ps.setString(6, "true");

			
			return ps;
		}
	},holder);
	
	return holder.getKey().toString();
}
 public void update(Employee employee) {
	 final String sql = "update Employee set pinCode=?,birthDate=? where id=?";
	 jdbcTemplate.update(sql,new Object[]{employee.getPinCode(),employee.getBirthDate(),employee.getId()});
 }
 public void delete(Employee employee) {
	 final String sql ="update Employee set isActive= ? where id=?";
	 jdbcTemplate.update(sql, new Object[]{"false",employee.getId()});
 } 
 public Employee findEmployee(String id) throws Exception {
	 final String sql ="select * from Employee where id=? and isActive=?";
	 try{
	 return jdbcTemplate.queryForObject(sql, new Object[]{id,"true"}, new EmployeeMapper());
	 } catch(Exception e) {
		 throw new Exception("Employee Not Active");
	 }
 }
 class EmployeeMapper implements RowMapper<Employee> {

	public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Employee emp = new Employee();
		emp.setfName(rs.getString("fName"));
		emp.setlName(rs.getString("lName"));
		emp.setId(rs.getString("id"));
		emp.setEmail(rs.getString("email"));
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
		Date date = null;
		try {
			date = format.parse(rs.getString("birthDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		emp.setBirthDate(date);
		emp.setPinCode(Integer.valueOf(rs.getString("pinCode")));
		emp.setIsActive(rs.getBoolean(6));
		return null;
	}

	
	 
 }
}
