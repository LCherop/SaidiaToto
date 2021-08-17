<?php 
session_start();
include('dbconnect.php');

//Deactivate User
if(isset($_POST['user_status'])){
    $uid=$_POST['key'];
    try{
        $updatedUser = $auth->disableUser($uid);
    
    if( $updatedUser){
        $_SESSION['status']="Account Deactivated";
        header('Location:update_users.php');
        exit();
    }else{
        $_SESSION['status']="Deactivation Failed";
        header('Location:update_users.php');
        exit();
     }
    }catch(Exception $e){
        $_SESSION['status']="Account Not Found";
        header('Location:update_users.php');
        exit();
    }

}

//Send Password Reset Link
    
//Reset Password
if(isset($_POST['change_password'])){

    $new_password=$_POST['new_password'];
    $retype_password=$_POST['retype_password'];
    
    $uid =$_POST['pwd_user_id'] ;

    if($new_password==$retype_password){
        
        $updatedUser = $auth->changeUserPassword($uid,$new_password);

        if($updatedUser){
        $_SESSION['status']="Password Updated Successfully";
                        header('Location:update_users.php');
                        exit();
        }else{
            $_SESSION['status']="Password Update Failed";
                        header('Location:update_users.php');
                        exit();
        }
    

    }else{
        $_SESSION['status']="Passwords don't match";
                 header("Location:update_users.php?id=$uid");
                exit();
    }
}else{
    $_SESSION['status']="User Not Found";
    header("Location:update_users.php?id=$uid");
   exit();
}

//Update User Details
if(isset($_POST['update_user'])){
    $displayName=$_POST['name'];
    $tel=$_POST['tel'];
    $eAdd=$_POST['eAdd'];

    $uid =$_POST['user_id'];
    $properties = [
        'displayName' =>$displayName,
        'phoneNumber'=>$tel,
        'email'=>$eAdd,
    ];

    $updatedUser = $auth->updateUser($uid, $properties);

    if($updatedUser){
        $_SESSION['status']="Updated Successfully";
                    header('Location:users.php');
                    exit();
    }else{
        $_SESSION['status']="Update Failed";
                    header('Location:users.php');
                    exit();
    }

}

//Register Authenticating Users
if(isset($_POST['register']))
{
        $fname=$_POST['full_name'];
        $phoneno=$_POST['phoneNo'];
        $eAddress=$_POST['email'];
        $pass=$_POST['pass'];
        $uname=$_POST['username'];

        $userProperties = [
            'email' => $eAddress,
            'emailVerified' => false,
            'phoneNumber' => '+254'.$phoneno,
            'password' => $pass,
            'displayName' => $fname,
            
        ];
        $createdUser = $auth->createUser($userProperties);

            if($createdUser)
            {
                $_SESSION['status']="User Created Successfully";
                header('Location:users.php');
                exit();
            }else{
                $_SESSION['status']="User Creation Failed";
                header('Location:users.php');
                exit();
            }
    }









//Add a confirmation popup before doing the action
if(isset($_POST['delete'])){

    $del_id=$_POST['delete'];
    $key=$_POST['key'];
    $ref_table="hospitals/".$key;

    $delete_result=$database->getReference($ref_table)->remove();  

    if($delete_result)
    {
        $_SESSION['status']="Deleted Successful";
        header('Location:index.php');
        exit();
    }else{
        $_SESSION['status']="Deleting Failed";
        header('Location:index.php');
        exit();
    } 
}
//Update Hospital Table
    if(isset($_POST['update'])){
        $key=$_POST['key'];
        $hname=$_POST['hname'];
        $phoneNo=$_POST['phoneNo'];
        $location=$_POST['location'];
        
    $updateData = [
                
                'fname'=>$hname,
                'number'=>$phoneNo,
                'location'=>$location,
    ];
 $ref_table="hospitals/".$key;
 $update_result = $database->getReference($ref_table)->push($updateData);

        if($update_result)
        {
            $_SESSION['status']="Update Successful";
            header('Location:index.php');
            exit();
        }else{
            $_SESSION['status']="Update Failed";
            header('Location:index.php');
            exit();
        }
    }
    
//Register Hospitals
    if(isset($_POST['save']))
    {
        $fullname=$_POST['hname'];
        $phoneNo=$_POST['phoneNo'];
        $location=$_POST['location'];
        
        $postData = [
                
                'fname'=>$fullname,
                'number'=>$phoneNo,
                'location'=>$location,
                
    ];
 $ref_table="hospitals";
$postRef_result = $database->getReference($ref_table)->push($postData);

    if($postRef_result)
    {
        $_SESSION['status']="Successful";
        header('Location:index.php');
    }else{
        $_SESSION['status']="Failed";
        header('Location:index.php');
    }
}

?>