<?php
require __DIR__.'/vendor/autoload.php';

use Kreait\Firebase\Factory;
use Kreait\Firebase\Auth;

$factory = (new Factory)->withServiceAccount('saidiatoto-a66e4-firebase-adminsdk-lmscu-f99a631c4a.json')
                        ->withDatabaseUri('https://saidiatoto-a66e4-default-rtdb.firebaseio.com/');

$database = $factory->createDatabase();

$auth=$factory->createAuth();



?>