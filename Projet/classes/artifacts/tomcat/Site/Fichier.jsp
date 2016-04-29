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
		<textarea id="textarea" type="textarea" class="form-control"></textarea>
	 </div>
  <script src="${pageContext.request.contextPath}/Site/bootstrap/js/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/Site/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>