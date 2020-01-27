<?php
 $conn = mysqli_connect("localhost","root","","users");
if($_SERVER['REQUEST_METHOD']=='POST')
{
        $name = $_POST['name'];
        $contactno = $_POST['contactno'];
	$email = $_POST['email'];
	$password = $_POST['password'];
          
        $password = password_hash($password, PASSWORD_DEFAULT);
	
        $query = "insert into users_table(name,contactno,email,password) values ('$name','$contactno', '$email', '$password')";
	if(mysqli_query($conn,$query))
	{
	    $result["success"]="1";
            $result["message"]="success";
           
            echo json_encode($result);
            mysqli_close($conn);
	} else {
	
	    $result["success"]="0";
            $result["message"]="error";
           
            echo json_encode($result);
            mysqli_close($conn);
	} 
}
?>