<div class="container btn-group col-lg-12">
	<nav class="navbar navbar-default ">
		<ul class="nav navbar-nav">
			<li><a href="Index"><span class="glyphicon glyphicon-home"></span>
					Accueil</a></li>
			<li><a href="#">Présentation</a></li>
			<li><a href="#">FAQ</a></li>
			<c:if test="${!empty sessionScope.sessionUtilisateur}"
				var="maVariable" scope="session">
				<li><a href="liste_fichiers">Mes Fichiers</a></li>
			</c:if>
		</ul>
		<c:choose>
			<c:when test="${!empty sessionScope.sessionUtilisateur}">
				<a id="button_deconnexion" href="Deconnexion"
					class="button_menu btn btn-danger pull-right">Déconnexion</a>
				<a id="button_compte" href="Compte"
					class="button_menu btn btn-primary pull-right">${sessionScope.sessionUtilisateur.email}</a>
			</c:when>
			<c:otherwise>
				<button data-toggle="modal" data-backdrop="false"
					id="button_inscription" href="#formulaire_inscription"
					class="button_menu btn btn-primary pull-right">Inscription</button>
				<button data-toggle="modal" data-backdrop="false"
					id="button_connexion" href="#formulaire_connexion"
					class="button_menu btn btn-primary pull-right">Connexion</button>
			</c:otherwise>
		</c:choose>
	</nav>
</div>
