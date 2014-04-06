<%@page import="java.sql.PreparedStatement"%>
<%@page import="edu.iiitb.facebook.util.ConnectionPool"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List, java.sql.*"%>
<%@page import="edu.iiitb.facebook.util.ConnectionPool"%>
<%@ page import="net.sf.json.JSONObject,net.sf.json.JSONArray"%>
<%
	Connection conn = ConnectionPool.getConnection();

	String name = "%" + request.getParameter("name") + "%";

	PreparedStatement stmt = conn
			.prepareStatement("select id,first_name, last_name, email from user where first_name like ? or last_name like ? or email like ?");

	stmt.setString(1, name);
	stmt.setString(2, name);
	stmt.setString(3, name);
	ResultSet rs = stmt.executeQuery();
	JSONArray jsonArr = new JSONArray();

	JSONObject json = new JSONObject();
	while (rs.next())
	{

		json.put("name", rs.getString(2) + " " + rs.getString(3));
		json.put("value", rs.getString(4));
		json.put("userid", rs.getString(1));
		jsonArr.add(json);
	}
	ConnectionPool.freeConnection(conn);

	out.println(jsonArr);
%>


