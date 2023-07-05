package book1.springbook.user.dao;

import book1.springbook.user.domain.User;

import java.sql.SQLException;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		UserDao dao = new UserDao();

		User user = new User();
		user.setId("happy");
		user.setName("해피");
		user.setPassword("happydog");

		dao.add(user);

		System.out.println(user.getId() + "등록성공");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId() + "조회 성공");

	}

}