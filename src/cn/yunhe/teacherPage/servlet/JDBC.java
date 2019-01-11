package cn.yunhe.teacherPage.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yunhe.teacherPage.entity.Liuruirui;
import cn.yunhe.teacherPage.util.JdbcUtil;

/**
 * Servlet implementation class JDBC
 */
@WebServlet("/JDBC")
public class JDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JDBC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("hello world");
		//我想查数据库
		JdbcUtil jdbcUtil = new JdbcUtil();
		Connection connection = jdbcUtil.getConnection();
		String sql = "SELECT * from student";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String sex = rs.getString("sex");	
				Date birth = rs.getDate("birth");
				Liuruirui liuruirui = new Liuruirui();
				liuruirui.setId(id);
				liuruirui.setName(name);
				liuruirui.setAge(age);
				liuruirui.setSex(sex);
				liuruirui.setBirth(birth);
				System.out.println(liuruirui.toString());
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
