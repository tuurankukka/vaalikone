<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,vaalikone.Yllapito,persist.*"%>
<%@page import="persist.Ehdokkaat"%>


<%
	List<Ehdokkaat> kaikkiEhdokkaat = (List<Ehdokkaat>) (request.getAttribute("ehdokasLista"));

	if (kaikkiEhdokkaat == null) {
		System.out.println("JSP Ei ehdokkaita");
	} else {
		System.out.println("JSP ON ehdokkaita");
	}

	Ehdokkaat e = null;
	try {
		e = (Ehdokkaat) (kaikkiEhdokkaat.get(0));
	} catch (Exception z) {
		out.println("Ehdokkaan poiminta on mukavaa toimintaa");
	}
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EhdokkaanMuokkaus</title>
<link href="style.css" rel="stylesheet" type="text/css">
<div id="container">
</head>
<h1>Ehdokkaan Muokkaus</h1>
<body>
	<form action="MuokkaaEhdokas" id="EhdokkaanLisays" method="post">
		<input type="hidden" name="muokattavaId"
			value='<%=e.getEhdokasId()%>'>
		<table>
			<tbody>
				<tr>
					<td>Sukunimi</td>
					<td><input type="text" name="sukunimi"
						value="<%=e.getSukunimi()%>" /></td>
				</tr>
				<tr>
					<td>Etunimi</td>
					<td><input type="text" name="etunimi"
						value="<%=e.getEtunimi()%>" /></td>
				</tr>
				<tr>
					<td>Puolue</td>
					<td><input type="text" name="puolue"
						value="<%=e.getPuolue()%>" /></td>
				</tr>
				<tr>
					<td>Kotipaikkakunta</td>
					<td><input type="text" name="kotipaikkakunta"
						value="<%=e.getKotipaikkakunta()%>" /></td>
				</tr>
				<tr>
					<td>Ikä</td>
					<td><input type="text" name="ika" value="<%=e.getIka()%>" /></td>
				</tr>
				<tr>
					<td>Miksi eduskuntaan?</td>
					<td><input type="text" name="miksieduskuntaan"
						value="<%=e.getMiksiEduskuntaan()%>" /></td>
				</tr>
				<tr>
					<td>Mitä asioita haluat edustaa?</td>
					<td><input type="text" name="mitahaluat"
						value="<%=e.getMitaAsioitaHaluatEdistaa()%>" /></td>
				</tr>
				<tr>
					<td>Ammatti</td>
					<td><input type="text" name="ammatti"
						value="<%=e.getAmmatti()%>" /></td>
				</tr>
				<tr>
					<td><input type="submit" id="submitnappi2"
						formaction="/PoistaEhdokas" value="Poista" /> <input
						type="submit" id="submitnappi1" value="Päivitä" /></td>
				</tr>
			</tbody>
		</table>
	</form>

</body>
</div>
</html>