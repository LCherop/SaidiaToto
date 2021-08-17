<?php 

include('auth.php');
include('header.php');

?>
    <div class="container">
               
        <div class="row">
            <div class="col-md-6">
            <?php
                if(isset($_SESSION['status'])){
                    echo"<h5 class='alert alert-success'>".$_SESSION['status']."</h5>";
                    unset($_SESSION['status']);
                }
                ?>
                <h4>Account Settings</h4>
                <div class="card">
                
                    <div class="card-header">
                        <h4>
                            Update
                            
                        </h4>
                    </div>
                    <div>
                        <form action="save.php"method="POST">
                        <?php 
                            include('dbconnect.php');
                            if(isset($_GET['id']))
                            {
                                $uid=$_GET['id'];
                                try {
                                    $user = $auth->getUser($uid);
                        ?>
                            <div class="form-group mb-3">
                                <input type="hidden" name="user_id" value=<?=$uid?>>
                                <label for="name">Full Name</label>
                                <input type="text"name="name" value="<?=$user->displayName;?>" class="form-control">

                                <label for="tel">Phone Number</label>
                                <input type="text"name="tel" value="<?=$user->phoneNumber;?>" class="form-control">

                                <label for="eAdd">Email Address</label>
                                <input type="text"name="eAdd" value="<?=$user->email;?>" class="form-control">

                                
                    
                            </div>
                            <div class="form-group mb-3">
                                <button type="submit" name="update_user" class="btn btn-primary">Update</button>

                            </div>

                                <?php
                                }catch (\Kreait\Firebase\Exception\Auth\UserNotFound $e) {
                                    echo $e->getMessage();

                                }
                            }
                                ?>

                        </form>
                    </div>
                </div>
            </div>
            <!--Enable/Disable Code here-->
            <div>
            

            </div>
            <!--Reset Password-->
            <div class="col-md-12">
                <hr>
                <div class="col-md-6">
                    <div class="card md-4">
                        <div class="card-header">
                            <h4>Reset Password</h4>
                        <div class="card-body">
                            <form action="save.php" method="POST">
                                <?php
                                if(isset($_GET['id'])){
                                    $uid=$_GET['id'];
                                    try {
                                        $user = $auth->getUser($uid);?>

                                        
                                <div class="form-group mb-3">
                                <input type="hidden" name="pwd_user_id" value="<?$uid?>">
                                
                                    <label for="">New Password</label>
                                    <input type="password" name="new_password" required class="form_control">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="">Retype Password</label>
                                    <input type="password" name="retype_password" required class="form_control">
                                </div>
                                <div class="form-group mb-3">
                                    <button type="submit" name="change_password" class="btn btn-primary">Submit</button>
                                </div>
                                <div class="form-group mb-3">
                                    <button type="submit" name="password_reset" class="btn btn-primary">Send link</button>
                                </div>
                                <?php
                                    } catch (\Kreait\Firebase\Exception\Auth\UserNotFound $e) {
                                        //echo $e->getMessage();
                                    }
                                }else{
                                    echo"No id Found";
                                }
                                ?>
                                
                                </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                
        </div>
    </div>
