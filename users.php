<?php 
include('auth.php');
include('header.php');

?>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <?php
                if(isset($_SESSION['status'])){
                    echo"<h5 class='alert alert-success'>".$_SESSION['status']."</h5>";
                    unset($_SESSION['status']);
                }
                ?>
                <div class="card">
                        
                    <div class="card-header">
                        <h4>
                            Authenticated Users List
                            <a href="register.php" class="btn btn-primary float-end">Register Users</a>
                        </h4>
                    </div>
                    <div class="card-body">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>User ID</th>
                                    <th>Full Name</th>
                                    <th>Phone Number</th>
                                    <th>Email</th>
                                    <th>Status</th>
                                    <th>Account</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php
                                include('dbconnect.php');
                                $users = $auth->listUsers();
                                    $i=1;
                                foreach ($users as $user) { ?>
                                    <tr>
                                            <td><?=$i++?></td>
                                            <td><?=$user->displayName?></td>
                                            <td><?=$user->phoneNumber?></td>
                                            <td><?=$user->email?></td>
                                            <td><?php
                                                    if($user->disabled){
                                                        echo"Disabled";
                                                    }else{
                                                        echo"Enabled";
                                                    }
                                            
                                            
                                            ?>
                                            
                                            </td>
                                            
                                            <td>
                                                <a href="update_users.php?id=<?=$user->uid;?>" class="btn btn-primary btn-sm">Settings</a>
                                            </td>
                                            <td>
                                                <form action="save.php"method="POST">
                                                    <input type="hidden" name="key" value="<?=$key?>">
                                                    <button type="submit" name="user_status" class="btn btn-danger btn-sm">Deactivate</button>

                                                </form>
                                            </td>
                                        </tr>
                                }
                                <?php
                                }
                                ?>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
                            

                            