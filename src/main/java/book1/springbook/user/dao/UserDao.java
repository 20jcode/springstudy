package book1.springbook.user.dao;

import book1.springbook.user.domain.User;

import java.sql.*;

public class UserDao {
	private ConnectionMaker connectionMaker; // 어떤 클래스인지 정보를 몰라도 된다 생각했지만?

	public UserDao(){
		connectionMaker = new DConnectionMaker(); // 또다시 클래스의 이름을 알아야함
	}
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection(); //인터페이스에 정의디ㅚㄴ 메소드 사용. 클래스 바뀌어도 메서드는 안변함

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
		Connection c = connectionMaker.makeConnection();

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
		Connection c = connectionMaker.makeConnection();

		PreparedStatement ps = c.prepareStatement(
				"delete from users where id = ?"
		);
		ps.setString(1,id);

		ps.executeUpdate();
		ps.close();
		c.close();
	}



}
