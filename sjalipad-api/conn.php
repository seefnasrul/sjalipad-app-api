<?php

$server = "localhost";
$username = "root";
$password = "";
$database = "sjali_db";

$conn = mysqli_connect($server,$username,$password) or die("Koneksi gagal");

mysqli_select_db($conn,$database)or die("Database tidak bisa dibuka");

?>