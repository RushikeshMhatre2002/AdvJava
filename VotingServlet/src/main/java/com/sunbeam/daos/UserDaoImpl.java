package com.sunbeam.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojos.User;

public class UserDaoImpl extends Dao implements UserDao {
	private PreparedStatement stmtFindByEmail;
	private PreparedStatement stmtFindById;
	private PreparedStatement stmtFindAll;
	private PreparedStatement stmtSave;
	private PreparedStatement stmtUpdate;
	private PreparedStatement stmtDeleteById;
	public UserDaoImpl() throws Exception {
		super();
		stmtFindByEmail = con.prepareStatement("SELECT * FROM users WHERE email=?");
		stmtFindById = con.prepareStatement("SELECT * FROM users WHERE id=?");
		stmtFindAll = con.prepareStatement("SELECT * FROM users");
		stmtSave = con.prepareStatement("INSERT INTO users(first_name, last_name, email, password, dob, status, role) VALUES(?, ?, ?, ?, ?, ?, ?)");
		stmtUpdate = con.prepareStatement("UPDATE users SET first_name=?, last_name=?, email=?, password=?, dob=?, status=?, role=? WHERE id=?");
		stmtDeleteById = con.prepareStatement("DELETE FROM users WHERE id=?");
	}
	@Override
	public void close() throws Exception {
		stmtDeleteById.close();
		stmtUpdate.close();
		stmtSave.close();
		stmtFindAll.close();
		stmtFindById.close();
		stmtFindByEmail.close();
		super.close();
	}	
	@Override
	public User findByEmail(String email) throws Exception {
		stmtFindByEmail.setString(1, email);
		try(ResultSet rs = stmtFindByEmail.executeQuery()) {
			if(rs.next()) {
				int userId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				email = rs.getString("email");
				String passwd = rs.getString("password");
				Date birth = rs.getDate("dob");
				boolean status = rs.getBoolean("status");
				String role = rs.getString("role");
				User user = new User(userId, firstName, lastName, email, passwd, birth, status, role);
				return user;
			}
		} // rs.close();
		return null;
	}
	@Override
	public User findById(int userId) throws Exception {
		stmtFindById.setInt(1, userId);
		try(ResultSet rs = stmtFindById.executeQuery()) {
			if(rs.next()) {
				userId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String passwd = rs.getString("password");
				Date birth = rs.getDate("dob");
				boolean status = rs.getBoolean("status");
				String role = rs.getString("role");
				User user = new User(userId, firstName, lastName, email, passwd, birth, status, role);
				return user;
			}
		} // rs.close();
		return null;
	}
	@Override
	public List<User> findAll() throws Exception {
		List<User> list = new ArrayList<>();
		try(ResultSet rs = stmtFindAll.executeQuery()) {
			while(rs.next()) {
				int userId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String passwd = rs.getString("password");
				Date birth = rs.getDate("dob");
				boolean status = rs.getBoolean("status");
				String role = rs.getString("role");
				User user = new User(userId, firstName, lastName, email, passwd, birth, status, role);
				list.add(user);
			}
		} // rs.close();
		return list;
	}
	@Override
	public int save(User u) throws Exception {
		stmtSave.setString(1, u.getFirstName());
		stmtSave.setString(2, u.getLastName());
		stmtSave.setString(3, u.getEmail());
		stmtSave.setString(4, u.getPasswd());
		stmtSave.setDate(5, u.getBirth());
		stmtSave.setBoolean(6, u.isStatus());
		stmtSave.setString(7, u.getRole());
		int cnt = stmtSave.executeUpdate();
		return cnt;
	}
	@Override
	public int update(User u) throws Exception {
		stmtUpdate.setString(1, u.getFirstName());
		stmtUpdate.setString(2, u.getLastName());
		stmtUpdate.setString(3, u.getEmail());
		stmtUpdate.setString(4, u.getPasswd());
		stmtUpdate.setDate(5, u.getBirth());
		stmtUpdate.setBoolean(6, u.isStatus());
		stmtUpdate.setString(7, u.getRole());
		stmtUpdate.setInt(8, u.getId());
		int cnt = stmtUpdate.executeUpdate();
		return cnt;
	}
	@Override
	public int deleteById(int userId) throws Exception {
		stmtDeleteById.setInt(1, userId);
		int cnt = stmtDeleteById.executeUpdate();
		return cnt;
	}
}
