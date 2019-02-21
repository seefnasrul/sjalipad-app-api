	<?php

include_once 'conn.php';

if($_SERVER['REQUEST_METHOD'] === 'GET'){

	$user_id = $_GET['user_id'];

	$sql = "SELECT id,name,email FROM users WHERE id='$user_id'";

	$profile_data = mysqli_fetch_assoc(mysqli_query($conn,$sql));

	if(!$profile_data){
		echo json_encode(['success'=>0,'message'=>'User not found']);
		die();
	}

	$sql = "SELECT books.id,users.name,books.title,books.likes,books.updated_at,books.user_id FROM books JOIN users on books.user_id=users.id WHERE user_id='$user_id' ORDER BY updated_at desc";
	$result = mysqli_query($conn,$sql);

	$data = [];
	while($row=mysqli_fetch_assoc($result)){
		$data[]=$row;
	}
	
	echo json_encode([
		'success'=>1,
		'message'=>'Get data success',
		'profile'=>$profile_data,
		'data'=>$data
	]);

}