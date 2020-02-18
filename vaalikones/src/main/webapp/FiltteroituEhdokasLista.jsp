<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,vaalikone.Yllapito,persist.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Filtteroitu Ehdokaslista</title>
<div id="container">
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
<h1>Filtteroitu Ehdokaslista</h1>
<body>

	<%
		List<Ehdokkaat> kaikkiEhdokkaat = (List<Ehdokkaat>) (request.getAttribute("ehdokasLista"));

		for (int i = 0; i < kaikkiEhdokkaat.size(); i++) {
			Ehdokkaat x = kaikkiEhdokkaat.get(i);

			out.print(x.getEhdokasId() + " ");
			out.print(x.getSukunimi() + " ");
			out.print(x.getEtunimi() + " ");
			out.print("<a href='HaeEhdokas?id=" + x.getEhdokasId() + "'>Hae</a><br>");
		}
	%>

</body>
</div>
</html>