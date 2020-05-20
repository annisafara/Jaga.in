<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "android_api";

$table = "users";
try {
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // sql to create table
    $sql2 = "CREATE TABLE IF NOT EXISTS $table (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `description` text NOT NULL,
  `created` datetime NOT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=19 ";
$sql = "CREATE TABLE IF NOT EXISTS $table (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unique_id` varchar(23) NOT NULL unique,
  `name` varchar(50) NOT NULL,
`email` varchar(100) NOT NULL unique,
`encrypted_password` varchar(80) NOT NULL,
`salt` varchar(10) NOT NULL,
`created_at` datetime ,
`updated_at` datetime null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=19 ";

    // use exec() because no results are returned
    $conn->exec($sql);
    echo "Table MyGuests created successfully";
	return "success";
    }
catch(PDOException $e)
    {
    echo $sql . "<br>" . $e->getMessage();return "error";
    }

$conn = null;
?>