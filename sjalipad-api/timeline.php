<?php 

include_once "conn.php";

if($_SERVER['REQUEST_METHOD'] === 'GET'){

	$sql = "SELECT books.id,books.title,users.name,books.likes,books.updated_at FROM books join users on books.user_id = users.id order by books.updated_at desc";

	$query = mysqli_query($conn,$sql) or die(mysqli_error($conn));

	while($row = mysqli_fetch_array($query)){
		$data[] = $row;
	}

	echo json_encode($data);

}