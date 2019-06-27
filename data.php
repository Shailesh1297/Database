<?php

$user="root";
$password="";
$server="localhost";
$database="college";

$conn=mysqli_connect($server,$user,$password,$database);



		$stdid=$_POST['id'];
	$stdname=$_POST['name'];
	$stdcourse=$_POST['course'];
	$stdsem=$_POST['semester'];
	$stdcity=$_POST['city'];
	
	$option=$_POST['submit'];


if($option=="insert")
{
	
	$query="insert into student values($stdid,'$stdname','$stdcourse',$stdsem,'$stdcity')";
	$data=mysqli_query($conn,$query);
	
}

if($option=="update")
{
	$query="update student set name='$stdname',course='$stdcourse',semester=$stdsem where id=$stdid";
	$data=mysqli_query($conn,$query);
}
if($option=="delete")
{
	$query="delete from student where id=$stdid";
	$data=mysqli_query($conn,$query);
}

if($option=="select")
{
	$query="select * from student";
	$data=mysqli_query($conn,$query);
	
	while($row=mysqli_fetch_array($data))
	{
		$flag[]=$row;
		
	}
	
	
	print(json_encode($flag));
	
}

mysqli_close($conn);


?>