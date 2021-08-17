<?php 

include('header.php');
if(isset($_SESSION['verified_user_id'])){
    $_SESSION['status']="You're already logged in";
    header('Location:login.php');
    exit();
}

?>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h4>
                            Login
                            <a href="register.php" class="btn btn-danger float-end">Sign Up</a>
                        </h4>
                    </div>
                    <div>
                        <form action="process_login.php"method="POST">
                            <div class="form-group mb-3">
                                
                                <label for="">Email Address</label>
                                <input type="text"name="email" class="form-control">

                                <label for="">Password</label>
                                <input type="password"name="pass" class="form-control">
                    
                            </div>
                            <div class="form-group mb-3">
                                <button type="submit" name="login" class="btn btn-primary">Login</button>

                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
