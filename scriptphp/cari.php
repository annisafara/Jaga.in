<?php
    session_start();
    //var_dump($_SESSION);
?>

<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <title></title>
</head>


<body>

    <tr> <!--Logo-->
        <td align="center" valign="center">
            <img src="usericon.png" alt="Paris" class="centerlogo" style="margin-left : 400pt;width:20%" >
        </td>
    </tr>
    <br><br><br>
    
    <a>Halo, <?php echo $_SESSION['users.nama']; ?> !!</a><br>
    <a>Kota : <?php echo $_SESSION['users.kota']; ?></a><br>
    <a href="logout.php">Log Out</a>

    <br><br><br>

<table width="500" border="0" cellpadding="5">
<tr>

    <td align="center" valign="center">
        <img src="apotek.png" width="300" height="300"/>
        <br />
        <form action="listapotek.php" method="post">
           <hidden> <input type="text" name="cari"> </hidden>
            <input type="submit" value="Cari Apotek">
        </form>
    </td>

    <td align="center" valign="center">
        <img src="puskesmas.png" width="300" height="300"/>
        <br />
        <form action="listpuskesmas.php" method="post">
            <hidden> <input type="text" name="cari"> </hidden>
            <input type="submit" value="Cari Puskesmas">
        </form>
    </td>

    <td align="center" valign="center">
        <img src="rumahsakit.png" width="300" height="300"/>
        <br />
        <form action="listrs.php" method="post">
            <hidden> <input type="text" name="cari"> </hidden>
            <input type="submit" value="Cari RumahSakit">
        </form>
    </td>

    <td align="center" valign="center">
        <img src="ambulance.png" width="300" height="300"/>
        <br />
        <form action="listambulance.php" method="post">
            <hidden> <input type="text" name="cari"> </hidden>
            <input type="submit" value="Cari Ambulnace">
        </form>
    </td>
</tr>
</table>

</body>
</html>