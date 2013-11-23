<?php
if ($_FILES["file"]["error"] > 0)
{
	echo "Error:  " . $_FILES["file"]["error"] . "<br />";
}
else
{
	echo "Upload:  " . $_FILES["file"]["name"] . "<br />";
	$safe_filename = preg_replace(
		array("/\s+/", "/[^-\.\w]+/"),
		array("_", ""),
		trim($_FILES["file"]["name"]));
	if (move_uploaded_file($_FILES["file"]["tmp_name"], "/var/www/html/FlyHighBooking/uploads/" . $safe_filename))
	{
		echo "Stored in:  " . "/var/www/html/FlyHighBooking/uploads/" . $safe_filename . "<br />";
	}
	else
	{
		echo "error<br />";
	}

	echo '<a href="admin.php">Back to administrator panel</a><br />';
}
?>
