<div class="container">
  <div id="div_inscription">
  </div>
  <div class="modal fade" id="formulaire_inscription">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">x</button>
          <h4 class="modal-title">Vos infos :</h4>
        </div>
        <div class="modal-body">
          
          <form id="form_inscription" action="Inscription" method="POST">
		  
          <div class="form-group">
            <label for="mail">Nom</label>
            <input type="mail" class="form-control" name="inscription_nom" id="inscription_nom" placeholder="Votre Nom">
          </div>
		  
          <div class="form-group">
            <label for="mail">Email</label>
            <input type="mail" class="form-control" name="inscription_mail" id="inscription_mail" placeholder="Votre Email">
          </div>

          <div class="form-group">
            <label for="mdp">Saississez un Mot de passe</label>
            <input type="password" class="form-control" name="inscription_password" id="inscription_password" placeholder="Votre mot de passe">
          </div>
          <div class="form-group">
            <label for="password">Retapez votre mot de passe</label>
            <input type="password" class="form-control" name="mdp_veril" id="mdp_verif" >
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
