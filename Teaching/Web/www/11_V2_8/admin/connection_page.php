<!DOCTYPE html>
<!--
	Auteurs : Clément LARIVIERE	TD10
			  Théo MERCIER		TD04
			  Antoine PAINCHAUX TD01
-->
<html lang="fr">

<?php 
///METTRE REQUETE SQL
session_start();

?>


<main role="main">

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
		  <h1 class="display-3">Connectez-vous en tant qu'admin</h1>
		</div>
	</div>
	<div class="container-fluid" id="block_info">
		  <p class="h2">Vos identifiants :</p>
		  <form  id="emailForm" action="loginAdminEXE.php" method="POST" name="emailForm">
			  <label for="user_name">Adresse email :</label> <input type="email" id="email" name="email" autofocus required><br>
			  <label for="user_surname">Mot de Passe :</label> <input type="password" id="password" name="password" required><br>
			  <input id="button" type="submit" name="submit" value="Valider" class="btn btn-primary btn-lg"><br>
		  </form>
		
	</div>
</main>

</html>