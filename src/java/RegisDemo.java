import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisDemo extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<body>");
		
		String username = request.getParameter("un");
		String password = request.getParameter("up");
		
		Connection con = null;
		Statement st = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajava77?useSSL=false", "root", "root");
			st = con.createStatement();
			String sql = "INSERT INTO login VALUES ('" + username + "', '" + password + "')";
			st.executeUpdate(sql);
			
			response.sendRedirect("Login01.html");
		} catch (ClassNotFoundException | SQLException e) {
			out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				out.println("Error closing resources: " + e.getMessage());
			}
		}
		
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
