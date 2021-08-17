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
                            Register 
                            <a href="login.php" class="btn btn-danger float-end">Back</a>
                        </h4>
                    </div>
                    <div>
                        <form action="save.php"method="POST">
                            <div class="form-group mb-3">
                                <label for="">Full Name</label>
                                <input type="text"name="full_name" class="form-control">

                                <label for="">Username</label>
                                <input type="text"name="username" class="form-control">

                                <label for="">Phone Number</label>
                                <input type="number"name="phoneNo" class="form-control">

                                <label for="">Email Address</label>
                                <input type="text"name="email" class="form-control">

                                <label for="">Password</label>
                                <input type="password"name="pass" class="form-control">
                    
                            </div>
                            <div class="form-group mb-3">
                                <button type="submit" name="register" class="btn btn-primary">Register</button>

                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
