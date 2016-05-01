<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<link
	href="${pageContext.request.contextPath}/Site/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/Site/style/style_fichier.css"
	rel="stylesheet">
</head>
<body id="${param.id}">
	<c:import url="menu.jsp" />
	<c:import url="formulaire_inscription.jsp" />
	<c:import url="formulaire_connexion.jsp" />
	<div id="content" class="row col-lg-offset-1 col-lg-10">
		<div id="cadre_fichier" class="col-lg-8">
			<p id="titre">Titre du fichier</p>
			<form method="POST" action="Enregistrer_fichier">
				<textarea type="textarea" class="form-control" id="texte_fichier"
					placeholder="Contenu Fichier" name="message" class="message">Contenu Fichier</textarea>
				<input class="btn btn-info" type="submit" name="submit"
					value="Sauvegardez" id="envoi_fichier" />
			</form>
		</div>
		<div class="col-lg-4">
			<div id="chat">
				<div id="zone_chat"></div>
				<form class="form-inline" method="POST" action="">
					<input class="form-control" placeholder="Votre Message" name="message" class="message" />
					<input class="btn btn-info" type="submit" name="submit" value="Envoyez" id="envoi" />
				</form>

			</div>
			<input id="zone_collaborateur" class="form-control"
				placeholder="Liste Collaborateur">
			</textarea>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/Site/bootstrap/js/jquery.js"></script>
	<script>
		$('#envoi').click(function(e) {
			e.preventDefault(); // on empêche le bouton d'envoyer le formulaire
			// on sécurise les données
			var message = encodeURIComponent($('#message').val());
			var id = $('body').attr('id');
			if (message != "") { // on vérifie que les variables ne sont pas vides

				$.ajax({
					url : "Tchat", // on donne l'URL du fichier de traitement
					type : "POST", // la requête est de type POST
					data : "id=" + id + "&message=" + message // et on envoie nos données
				});
				$('#message').val('');
				$.ajax({
					url : "Tchat?id=" + id,
					type : "GET",
					success : function(html) {
						$("#zone_chat").html(html); // on veut ajouter les nouveaux messages au début du bloc #messages

					}
				});

			}
		});
		function charger() {
			var id = $('body').attr('id');
			setTimeout(function() {
				// on lance une requête AJAX
				$.ajax({
					url : "Tchat?id=" + id,
					type : "GET",
					success : function(html) {
						$("#zone_chat").html(html); // on veut ajouter les nouveaux messages au début du bloc #messages

					}
				});

				charger(); // on relance la fonction

			}, 5000); // on exécute le chargement toutes les 5 secondes

		}

		charger();
	</script>
	<script
		src="${pageContext.request.contextPath}/Site/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>