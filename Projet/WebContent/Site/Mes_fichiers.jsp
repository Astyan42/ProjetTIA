<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <link href="${pageContext.request.contextPath}/Site/bootstrap/css/bootstrap.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/Site/style/style_mes_fichiers.css" rel="stylesheet">
</head>
<body >
	<c:import url="menu.jsp" />
	<c:import url="formulaire_inscription.jsp"/>
	<c:import url="formulaire_connexion.jsp"/>
   	 <div id="content" class=" col-lg-offset-1 col-lg-10">
		<div id="menu">
			<div>
				<div class="add"></div> <a href=""> Fichier</a>	
			</div>
			<br>
			<div>
				<div class="add"></div> <a href=""> Projet</a>
			</div>
			<br>
			<div>
				<div class="partage"></div> <a href=""> Partage</a>
			</div>
		</div>
		<div id="contenu">
		
			<div id="chemin"><div id="retour"></div>Racine</div>
			
			<c:forEach var="projet" items="${liste_projets}">
				<div class="blocfic">
					<div class="file folder"></div>
					<a href="Projet?id=${projet.key}" class="fic">${projet.value}</a>
				</div>
			</c:forEach>
			
			<c:forEach var="fichier" items="${liste_fichiers}">
				<div class="blocfic">
				<div class="file ${fichier.value}" ></div>
				<a href="Fichier?id=${fichier.key}" class="fic">${fichier.value}</a>
			</div>
			</c:forEach>
			<c:forEach items="${ messages }" var="message" varStatus="boucle">

            <p>${ boucle.count }. ${ message }</p>

       	    </c:forEach>
			
		</div>
	</div>
  <script src="${pageContext.request.contextPath}/Site/bootstrap/js/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/Site/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>