<?php 

include('header.php');

?>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4>
                            Edit & Update Hospitals
                            <a href="index.php" class="btn btn-danger float-end">Back</a>
                        </h4>
                    </div>
                    <div class="card-body">
                        <form action="save.php"method="POST">   
                        <?php 
                            include('dbconnect.php');
                            if(isset($_GET['id']))
                            {
                                $uid=$_GET['id'];
                                
                                    $ref_table='hospitals';
                                    $getdata = $database->getReference($ref_table)->getChild($uid)->getValue();
                                   if($getdata >0){
                                    ?>
                                   
                        <div class="form-group mb-3">
                                    
                                    <label for="">Hospital Name</label>
                                    <input type="text"name="hname" value="<?=$getdata['fname'];?>"class="form-control">

                                    <label for="">Phone Number</label>
                                    <input type="text"name="phoneNo" value="<?=$getdata['location'];?>"class="form-control">

                                    <label for="">Location</label>
                                    <input type="text"name="location" value="<?=$getdata['number'];?>" class="form-control">

                    
                        </div>
                            <div class="form-group mb-3">
                                <button type="submit" name="update" class="btn btn-primary">Update</button>

                            </div>

                               <?php 
                        }else{
                                    $_SESSION['status']="Invalid Id";
                                    header('Location:index.php');
                                    exit();
                                   }
                            }else{
                                    $_SESSION['status']="Not Found";
                                    header('Location:index.php');
                                    exit();
                                }
                           
                              ?>  
                        
                        
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
