<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,vaalikone.Yllapito,persist.*"%>

<%@page import="javax.ws.rs.client.Client"%>
<%@page import="javax.ws.rs.client.ClientBuilder"%>
<%@page import="javax.ws.rs.client.Entity"%>
<%@page import="javax.ws.rs.client.WebTarget"%>
<%@page import="javax.ws.rs.client.Invocation.Builder"%>
<%@page import="javax.ws.rs.core.GenericType"%>
<%@page import="javax.ws.rs.core.MediaType"%>
<%@page import="javax.ws.rs.client.Client"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kysymysten muokkaus</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<div id="container">
	<h1>Kysymyslista</h1>
	<body>

		<%
			response.setContentType("text/html;charset=UTF-8");

			String uri = "http://127.0.0.1:8080/rest/kysymyspalvelu/haeKysymykset";
			Client asiakas = ClientBuilder.newClient();

			WebTarget wt = asiakas.target(uri);
			Builder b = wt.request();
			GenericType<List<Kysymykset>> x = new GenericType<List<Kysymykset>>() {
			};

			List<Kysymykset> palautettu = b.get(x);

			for (int i = 0; i < palautettu.size(); i++) {

				Kysymykset k = palautettu.get(i);

				out.print(k.getKysymysId() + " ");
				out.print(k.getKysymys() + " ");

				out.print("<a href='MuokkaaKysymysta.jsp?id=" + k.getKysymysId() + "'>Muokkaa</a><br>");

			}
		%>

		<br>
		<a href="/YllapitoSivu.jsp">Ylläpitosivu</a>
</div>
</body>
</html>