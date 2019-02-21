<?php

include_once "conn.php";

if($_SERVER['REQUEST_METHOD'] === 'POST'){
	$email = $_POST['email'];
	$pass = $_POST['password'];

	$sql = "SELECT * FROM users where email='$email' AND password='$pass' ";
	$result = mysqli_query($conn,$sql);	

	$data=[];
	while($row=mysqli_fetch_array($result)){
		$data[] = $row;
	}

	if(count($data)){
		echo json_encode([
			'success'=>1,
			'message'=>'Login Success',
			'user_id'=>$data[0]['id']
		]);
	}else{
		echo json_encode(
			['success'=>0,
			'message'=>'Incorrect Email or Password'
		]);
	}

}else{

}