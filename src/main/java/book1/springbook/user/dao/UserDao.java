package book1.springbook.user.dao;

import book1.springbook.user.domain.User;

import java.sql.*;

public class UserDao {
	public void add(User user) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		// com.mysql.cj.jdbc.Driver 으로 바꿔주기
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springStudy","root","0000");

		PreparedStatement ps = c.prepareStatement(
				"insert into users(id,name,password) value(?,?,?)");
		ps.setString(1,user.getId());
		ps.setString(2,user.getName());
		ps.setString(3,user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springStudy","root","0000");

		PreparedStatement ps = c.prepareStatement(
				"select * from users where id = ?");
		ps.setString(1,id);

		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("Name"));
		user.setPassword(rs.getString("Password"));

		rs.close();
		ps.close();
		c.close();

		return user;

	}
}
