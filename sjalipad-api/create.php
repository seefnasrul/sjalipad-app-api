<?php

include_once 'conn.php';

if($_SERVER['REQUEST_METHOD'] === 'POST'){

	$title = $_POST['title'];
	$content = $_POST['content'];
	$user_email = $_POST['email'];

	$sql = "SELECT * FROM users WHERE email='$user_email'";

	$result = mysqli_query($conn,$sql);
	$data = [];
	
	while($row=mysqli_fetch_array($result)){
		$data[]=$row;
	}

	if(count($data)){
		$user_id = $data[0]['id'];
		$date = date('Y-m-d H:i:s');
		$sql = "INSERT INTO books(title,content,created_at,updated_at,user_id) values('$title','$content','$date','$date','$user_id')";

		$result = mysqli_query($conn,$sql);

		if($result){
			echo json_encode(['success'=>1,'message'=>'Book has been Posted']);
		}else{
			echo json_encode(['success'=>0,'message'=>'Sorry something went wrong, try again later.']); 
		}

	}else{
		echo json_encode(['success'=>0,'message'=>'User is not regitered']);
	}
}

