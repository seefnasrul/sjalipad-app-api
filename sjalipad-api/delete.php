<?php

include_once 'conn.php';

if($_SERVER['REQUEST_METHOD'] === 'POST'){

	$book_id = $_POST['book_id'];

	$sql = "DELETE FROM books WHERE id='$book_id'";

	$result = mysqli_query($conn,$sql);

	if($result){
		echo json_encode(['success'=>1,'message'=>'Book Deleted!']);
	}else{
		echo json_encode(['success'=>0,'message'=>'Delete failed, Try again later']);
	}
	
}