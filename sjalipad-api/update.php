<?php 

if($_SERVER['REQUEST_METHOD'] === 'POST'){

	include_once "conn.php";

	$book_id = $_POST['book_id'];
	$title = $_POST['title'];
	$content = $_POST['content'];

	$date = date('Y-m-d H:i:s'); 

	$sql = "UPDATE books SET title='$title', content='$content',updated_at='$date' WHERE id='$book_id'";

	$result = mysqli_query($conn,$sql);

	if($result){
		echo json_encode(['success'=>1,'message'=>'Book Updated!']);
	}else{
		echo json_encode(['success'=>0,'message'=>'Update failed, Try again later']);
	}
}