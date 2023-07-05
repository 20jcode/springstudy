package book1.springbook.user.dao;

import book1.springbook.user.domain.User;

import java.sql.*;

public abstract class UserDao {
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();

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
		Connection c = getConnection();

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

	public void del(String id) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();

		PreparedStatement ps = c.prepareStatement(
				"delete from users where id = ?"
		);
		ps.setString(1,id);

		ps.executeUpdate();
		ps.close();
		c.close();
	}

	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
	/*
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springStudy","root","0000"
		);
		return c;
	}
	*/

}
