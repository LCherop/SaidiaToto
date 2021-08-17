<?php
session_start();
include('dbconnect.php');

if(isset($_SESSION['verified_user_id'])){

    $uid=$_SESSION['verified_user_id'];
    $idTokenString =$_SESSION['idTokenString'];

        try {
            $verifiedIdToken = $auth->verifyIdToken($idTokenString);
        } catch (InvalidToken $e) {
            //echo 'The token is invalid: '.$e->getMessage();
            $_SESSION['expiry_status']="Session Expired. Please Login";
            header('Location:logout.php');
            exit();
        } catch (\InvalidArgumentException $e) {
            //echo 'The token could not be parsed: '.$e->getMessage();
        }

}else{
    $_SESSION['status']="Login to access this page";
     header('Location:login.php');
     exit();
}

?>