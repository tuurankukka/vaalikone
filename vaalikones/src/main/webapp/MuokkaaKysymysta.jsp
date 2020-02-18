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
<title>Kysymyksen muokkaus</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<div id="container">
	<h1>Kysymyksen muokkaus</h1>

	<body>

		<%
			response.setContentType("text/html;charset=UTF-8");

			String id = request.getParameter("id");

			String uri = "http://127.0.0.1:8080/rest/kysymyspalvelu/haeYksiKysymys/" + id;
			Client asiakas = ClientBuilder.newClient();

			WebTarget wt = asiakas.target(uri);
			Builder b = wt.request();

			Kysymykset k = b.get(Kysymykset.class);
		%>

		<table>
			<tbody>
				<tr>
					<td>
						<form id='lomake2' onsubmit="return false;">
							<tr>
								<td><label>Kysymys </label></td>
								<td><input type='text' name='kysymys'
									value="<%=k.getKysymys()%>" /> <input type='hidden'
									name='kysymysid' value="<%=k.getKysymysId()%>" /></td>
							</tr>
						</form>
					</td>
				</tr>

				<tr>
					<td>
						<button onclick="poistakysymys();">Poista</button>
						<button onclick="muokkaakysymysta();">Muokkaa</button>
					</td>
				</tr>
			</tbody>
		</table>
</div>
</body>

<script>
	function muokkaakysymysta() {

		var lomake = document.getElementById("lomake2");

		var kyssari = new Object();

		kyssari.kysymysId = lomake.kysymysid.value;
		kyssari.kysymys = lomake.kysymys.value;

		var jsonKysymys = JSON.stringify(kyssari);

		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var palautettu = JSON.parse(this.responseText);
			}
		};

		xhttp.open("POST", "./rest/kysymyspalvelu/muokkaaKysymys", true);
		//		xhttp.setRequestHeader("Content-type","text/plain");
		xhttp.setRequestHeader("Content-type", "application/json");
		//		xmlhttp.setRequestHeader("Data-type","json");
		xhttp.send(jsonKysymys);

		window.location = "//localhost:8080/KysymysLista.jsp";
	}

	function poistakysymys() {

		var lomake = document.getElementById("lomake2");

		var kyssari = new Object();

		kyssari.kysymysId = lomake.kysymysid.value;
		kyssari.kysymys = lomake.kysymys.value;

		var jsonKysymys = JSON.stringify(kyssari);

		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var palautettu = JSON.parse(this.responseText);
			}
		};

		xhttp.open("POST", "./rest/kysymyspalvelu/poistaKysymys", true);
		//		xhttp.setRequestHeader("Content-type","text/plain");
		xhttp.setRequestHeader("Content-type", "application/json");
		//		xmlhttp.setRequestHeader("Data-type","json");
		xhttp.send(jsonKysymys);

		window.location = "//localhost:8080/KysymysLista.jsp";

	}
</script>
</html>