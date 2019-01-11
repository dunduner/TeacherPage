package cn.yunhe.teacherPage.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yunhe.teacherPage.entity.PageView;
import cn.yunhe.teacherPage.entity.Student;
import cn.yunhe.teacherPage.util.JdbcUtil;


/**
 * Servlet implementation class PageServlet
 */
@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//分页代码
		//通过localhost:8080/TeacherPage/PageServlet方法进入此函数
		String requestCurrentpage = request.getParameter("currentpage");
		if(requestCurrentpage==null){
			requestCurrentpage= "1";//拿不到值就直接默认为第一页
		}else{
			//传的有值，就啥也不干  就是requestCurrentpage 从客户端拿到的值
		}
		//request获取的都是字符串  需要强转为int型
		int currentpage = Integer.parseInt(requestCurrentpage);
		//每页显示多少条
		int maximum = 5;
		

		Connection conn = null;
		Statement st = null;
		JdbcUtil jdbcUtil = new JdbcUtil();
		conn = jdbcUtil.getConnection();
		try {
			st = conn.createStatement();
			//分页sql语句   limit 第一个参数  为当前页-1乘以 每页展示数    第二个参数为 最大展示数 --切记逗号隔开
			String sql = "select * from student limit " + (currentpage - 1) * maximum + "," + maximum;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("分页查询语句："+sql);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			ResultSet rs = st.executeQuery(sql);
			
			
			List<Student> studentList = new ArrayList<Student>();
			
			
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String sex = rs.getString("sex");
				Date birth = rs.getDate("birth");
				//通过resultset的get语法 获取每个对象的值 然后通过构造方法创建对象
				Student stu = new Student(id, name, age, sex, birth);
				list.add(stu);
			}
			
			//查询student表中的总记录数
			String sql2 = "select * from student";
			ResultSet rs2 = st.executeQuery(sql2);
			int count = 0;
			while (rs2.next()) {
				count++;
			}
			long totalrecordnumber = count;//总记录数
			// 将数据都封装到pageView
			PageView pageView = new PageView(list, totalrecordnumber, currentpage, maximum);
			request.setAttribute("pageView", pageView);
			request.setAttribute("list", list);
//			通过转发  把上面的2个对象传到studentlist页面
			request.getRequestDispatcher("studentlist.jsp").forward(request, response);
		} catch (SQLException e) {
			System.out.println("数据库处理异常");
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			jdbcUtil.releaseConn();
		}
		
		
		

		
	}

}
