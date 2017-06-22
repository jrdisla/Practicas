<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="css/clean-blog.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Spark Blog ORM</title>
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>


    <title>Clean Blog</title>



</head>

<body>


<!-- Navigation -->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/home/">Home</a></li>
                <li><a href="/addArti/">Add Article</a></li>
            <#if user.username == "admin">
                <li><a href="/addUser/">Add User</a></li>
            </#if>
                <li class="active" ><a href="/listArtiBy/">Modificate and Delete</a></li>
                <li><a href="/listTags/"> Articles By Tags</a></li>
                <li><a href="/invalidarSesion/"> Logout</a></li>
            </ul>
            <form class="navbar-form navbar-right" role="search">
                <div class="form-group input-group">
                    <input type="text" class="form-control" placeholder="Search..">
                    <span class="input-group-btn">
            <button class="btn btn-default" type="button">
              <span class="glyphicon glyphicon-search"></span>
            </button>
          </span>
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span>Hello: ${user.username}</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->


<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">

        ${code}
            <br><br>


            <a href="/preferences/likes/1" class="btn btn-danger"  role="button"><span class="badge">${cout}</span> LIKES </a>
            <a href="/preferences/likes/2" class="btn btn-danger"  role="button"><span class="badge">${cout_dis}</span> DISLIKES</a>

            Likes: ${cout}
            Dislikes: ${cout_dis}

            <br>
            <h4>Leave a Comment:</h4>

            <form action="/articulo/valida/" method="post" role="form">
                <div class="form-group">
                    <textarea name="comment" class="form-control" rows="3" required></textarea>
                </div>
                <button type="submit" class="btn btn-success">Submit</button>
                ${code2}
                Likes: ${cout_c}
                Dislikes: ${cout_dis_c}
                <br><br>
            </form>
            <br><br>
        </div>
    </div>
</div>

<hr>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <li>
            <a href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li><a href="/home/1/">1</a></li>
        <li><a href="/home/2/">2</a></li>
        <li><a href="/home/3/">3</a></li>
        <li><a href="/home/4/">4</a></li>
        <li><a href="/home/5/">5</a></li>
        <li>
            <a href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
<!-- Footer -->
<#include  "./footer.ftl">

</body>

</html>