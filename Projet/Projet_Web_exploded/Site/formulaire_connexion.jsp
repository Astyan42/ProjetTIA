<div class="container">
    <div class="modal fade" id="formulaire_connexion">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">x</button>
                    <h4 class="modal-title">Connexion :</h4>
                </div>
                <div class="modal-body">
				
                    <form id="form_connexion" action="Connexion" method="POST">
					
                    <div class="form-group">
                        <label for="nom">Email</label>
                        <input type="text" class="form-control" name ="connexion_mail" id="connexion_mail" placeholder="Votre Email">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" name="connexion_password" id="connexion_password" placeholder="Votre Mot de passe">
                    </div>
                    <button type="submit" class="btn btn-default">Envoyer</button>
					
					</form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-info" data-dismiss="modal">Annuler</button>
            </div>
        </div>
    </div>
</div>
</div>
