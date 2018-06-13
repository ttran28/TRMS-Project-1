<?php
	
	if  (isset($_SESSION['userid])) 
	{
		session_unset();
		session_destroy();
		header('Location: Login.html');
		exit();
	}
	else
	{
		header('Location: Login.html');
		exit();
     }
?>