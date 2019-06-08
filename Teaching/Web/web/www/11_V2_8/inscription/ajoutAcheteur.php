<!DOCTYPE html>
<html>

<?php include("header.php");?>
<style>
	#block_form
	{
		display : block;
		width : 10rem;
		padding-left: 80px;

		
	}
	#button_form
	{
		padding-left: 300px;
		
		float:left;
		display : block;
	}
	.container-fluid
	{
		padding-left : 0px;
		padding-right : 0px;
	}
	label
	{
		text-align : right;
		width : 200px;
		display : block;
	}
</style>

<div class="jumbotron">
		<div class="container-fluid">
		  <h1 class="display-3">Inscription en tant que acheteur :</h1>
		</div>
</div>

<body class="container-fluid">
	<div id="block_form" class="form-group">
	
		<form action="ajoutAcheteurExe.php" method="post">
			<table>
				<tr>
				<td><label for="email">Email :</label></td>
				<td><input type="text" name="email" autofocus required /></td>
				</tr>

				<tr>
				<td><label for="password">Mot de passe : </td>
				<td><input type="text" name="password" required /></td>
				</tr>

				<tr>
				<td><label for="pseudo">Pseudo : </td>
				<td><input type="text" name="pseudo" required /></td>
				</tr>

				<tr>
				<td><label for="fName">Prénom : </td>
				<td><input type="text" name="fName" required /></td>
				</tr>

				<tr>
				<td><label for="lName">Nom de famille : </td>
				<td><input type="text" name="lName" /></td>
				</tr>

				<tr>
				<td><label for="adress">Adresse : </td>
				<td><input type="text" name="adress" /></td>
				</tr>

				<tr>
				<td><label for="city">Ville : </td>
				<td><input type="text" name="city" /></td>
				</tr>

				<tr>
				<td><label for="postCode">Code Postal : </td>
				<td><input type="text" name="postCode" /></td>
				</tr>

				<tr>
				<td><label for="country">Pays : </td>
				<td><input type="text" name="country" /></td>
				</tr>

				<tr>
				<td><label for="phone">Téléphone : </td>
				<td><input type="text" name="phone" /></td>
				</tr>

			</table>
	</div>
		
	<div id="button_form">
		<p>
			<br>
			<input id="button" type="submit" name="submit" value="Valider" class="btn btn-primary btn-lg"><br>
		</p>
	</div>
	
	</form>
	


</body>

<!-- Footer -->
<?php include("footer.php");?>

</html>