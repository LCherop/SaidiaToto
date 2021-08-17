<?php
session_start();
include('dbconnect.php');

if(isset($_POST['login']))
{
    $email=$_POST['email'];
    $clearTextPassword=$_POST['pass'];

    try {
        $user = $auth->getUserByEmail($email);

        try{
        $signInResult = $auth->signInWithEmailAndPassword($email, $clearTextPassword);
        $idTokenString =$signInResult->idToken();

        
            try {
                $verifiedIdToken = $auth->verifyIdToken($idTokenString);
                $uid = $verifiedIdToken->claims()->get('sub');

                $_SESSION['idTokenString']=$uid;
                $_SESSION['verified_user_id']=$idTokenString;

                $_SESSION['status']="Welcome";
                header('Location:users.php');
                exit();

                
            } catch (InvalidToken $e) {
                echo 'The token is invalid: '.$e->getMessage();
            } catch (\InvalidArgumentException $e) {
                echo 'The token could not be parsed: '.$e->getMessage();
            }
            } catch (\Kreait\Firebase\Exception\Auth\UserNotFound $e) {
            
                $_SESSION['status']="Invalid Email/Password";
                header('Location:login.php');
                exit();
            }
            }catch (Exception $e){
                $_SESSION['status']="Invalid Email/Password";
                header('Location:login.php');
                exit();
             }
}



?>