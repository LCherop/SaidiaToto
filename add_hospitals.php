<?php 
include('auth.php');
include('header.php');

?>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4>
                            Register Hospitals
                            <a href="index.php" class="btn btn-danger float-end">Back</a>
                        </h4>
                    </div>
                    <div>
                        <form action="save.php"method="POST">
                            <div class="form-group mb-3">
                                <label for="hname">Hospital Name</label>
                                <input type="text"name="hname" class="form-control">

                                <label for="phoneNo">Emergency Number</label>
                                <input type="number"name="phoneNo" class="form-control">

                                <label for="location">Location</label>
                                <input type="text"name="location" class="form-control">

                            </div>
                            <div class="form-group mb-3">
                                <button type="submit" name="save" class="btn btn-primary">Save</button>

                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
