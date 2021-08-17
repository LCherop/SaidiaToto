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
                            Hospitals
                            <a href="add_hospitals.php" class="btn btn-primary float-end">Add</a>
                        </h4>

                    </div>
                    <div class="card-body">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>Hospital ID</th>
                                    <th>Name</th>
                                    <th>Location</th>
                                    <th>Emergency Number</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php
                                include('dbconnect.php');
                                $ref_table="hospitals";
                                $fetchdata = $database->getReference($ref_table)->getValue();
                                 
                                if($fetchdata>0){
                                    $i=1;
                                    foreach($fetchdata as $key=> $row)
                                    {
                                        ?>
                                        <tr>
                                            <td><?=$i++?></td>
                                            <td><?=$row['fname']?></td>
                                            <td><?=$row['location']?></td>
                                            <td><?=$row['number']?></td>
                                            <td>
                                                <a href="edit_users.php?id=<?=$key;?>" class="btn btn-primary btn-sm">Edit</a>
                                            </td>
                                            <td>
                                                
                                            
                                                <form action="save.php"method="POST">
                                                    <input type="hidden" name="key" value="<?=$key?>">
                                                    <button type="submit" name="delete" value="<?=$key?>"class="btn btn-danger btn-sm">Delete</button>

                                                </form>
                                            </td>
                                        </tr>
                                        <?php
                                    }
                                }else{
                                    ?>
                                    <tr>
                                    <td colspan="2">No record Found</td>
                                </tr>
                                <?php
                                }
                                
                                ?>
                                <tr>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    