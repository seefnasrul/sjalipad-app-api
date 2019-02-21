<?php

include_once "conn.php";

if($_SERVER['REQUEST_METHOD'] === 'POST'){
	$email = $_POST['email'];
	$pass = $_POST['password'];

	$sql = "SELECT * FROM users WHERE email='$email'";
	$data=[];
	mysqli_fetch_array(mysqli_query($sql))
}