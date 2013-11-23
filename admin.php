<html>
<head>
<title>Administrator Panel</title>
</head>
<body>
Below are all the receipts and other uploads:
<ul>
<?php
$dir = "/var/www/html/FlyHighBooking/uploads/";
$filelist = scandir($dir);
foreach ($filelist as $file)
{
		echo "<li><a href='uploads/" . $file . "'>" . $file . "</a>";
}
?>
</ul>
<form action="upload.php" method="post" enctype="multipart/form-data">
<label for="file">Filename:</label>
<input type="file" name="file" id="file"><br />
<input type="submit" name="submit" value="Submit">
</form>
</body>
</html>

