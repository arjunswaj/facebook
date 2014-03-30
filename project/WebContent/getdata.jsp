<%@page import="java.sql.PreparedStatement"%>
<%@page import="edu.iiitb.facebook.util.ConnectionPool"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List, java.sql.*"%>
<%@page import="edu.iiitb.facebook.util.ConnectionPool"%>
<%@ page import="net.sf.json.JSONObject,net.sf.json.JSONArray"%>
<%
	Connection conn = ConnectionPool.getConnection();

	String name = request.getParameter("name");

	PreparedStatement stmt = conn.prepareStatement("select first_name, last_name, email from user where first_name like '%" + name
			+ "%' or last_name like '%" + name + "%' or email like '%" + name + "%'");

	ResultSet rs = stmt.executeQuery();
	JSONArray jsonArr = new JSONArray();

	JSONObject json = new JSONObject();
	while (rs.next())
	{

		json.put("name", rs.getString(1) + " " + rs.getString(2));
		json.put("value", rs.getString(3));
		jsonArr.add(json);
	}
	ConnectionPool.freeConnection(conn);

	out.println(jsonArr);
%>


