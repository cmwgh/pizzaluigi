<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Pizza Luigi'/>

</head>
<body>
<vdab:menu/>
<h1>Pizza Luigi</h1>
<img src='<c:url value='/images/pizza.jpg'/>' alt='pizza' class='fullwidth'>
<h2>${begroeting}</h2>
<h2>De zaakvoerder</h2>
<dl>
	<dt>Naam</dt><dd>${zaakvoerder.naam}</dd>
	<dt>Aantaal kinderen</dt><dd>${zaakvoerder.aantalKinderen}</dd>
	<dt>Gehuwd</dt><dd>${zaakvoerder.gehuwd ? 'Ja' : 'Nee'}</dd>
	<dt>Adres</dt>
	<dd>${zaakvoerder.adres.straat} ${zaakvoerder.adres.huisNr}<br>
	${zaakvoerder.adres.postcode} ${zaakvoerder.adres.gemeente}</dd>
	<dt>Aantal pizza's verkocht</dt>
	<dd><fmt:formatNumber value='${aantalPizzasVerkocht}'/></dd>
</dl>
<div>Deze pagina werd ${aantalKeerBekeken} keer bekeken.</div>
<div>WebMaster:
<a href='mailto:${emailAdresWebMaster}'>${emailAdresWebMaster}</a></div>
<fmt:parseDate value="${nu}" pattern="yyyy-MM-dd" var="nuAlsDate" type="date" />
<div>Vandaag: <fmt:formatDate value='${nuAlsDate}'/></div>
</body>
</html>