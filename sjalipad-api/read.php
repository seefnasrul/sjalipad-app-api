<?php

include_once 'conn.php';

if($_SERVER['REQUEST_METHOD'] === 'GET'){

	$book_id = $_GET['book_id'];

	$sql = "SELECT books.id,books.title, books.content, books.likes, books.created_at, books.updated_at, books.user_id, users.name FROM books join users on books.user_id=users.id WHERE books.id='$book_id'";
	$result = mysqli_query($conn, $sql);

	$data=[];
	while($row=mysqli_fetch_array($result)){
		$data[] = $row;
	}

	if(count($data)){
		echo json_encode(['succcess'=>1,'message'=>'Get Data Success','data'=>$data[0]]);
	}else{
		echo json_encode(['succcess'=>0,'message'=>'Book Does no exist!']);
	}

}