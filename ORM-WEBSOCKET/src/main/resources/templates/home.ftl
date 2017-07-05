<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Spark Blog ORM</title>
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="/css/grayscale.min.css" rel="stylesheet">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <

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
                <li><a href="/listArtiBy/">Modificate and Delete</a></li>
                <li><a href="/listTags/"> Articles By Tags</a></li>
                <li><a href="/invalidarSesion/"> Logout</a></li>
            <#if user.username == "admin">
                <li> <a href="/chat/">CHAT</a></li>
            </#if>
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
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" >
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <button onclick="myX()" type="submit" class="btn btn-primary">Next</button>
                    <button onclick="myXback()" type="submit" class="btn btn-primary">Back</button>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
            <div id = "ola">
        <#if first = true>
            <#list listArti as item>
                <div class="post-preview">
                    <h2 class="post-title">  ${item.title}  </h2>
                    <h5> Posted by:  ${item.getAuthor().username} </h5>
                    <h5><span class="glyphicon glyphicon-time"> </span> ${item.date}  </h5>
                    <h5><span class="glyphicon glyphicon-tags"></span> ${item.getStringTags()} </h5>
                    <a href="/article/${item.id}/"> ${item.title}</a>
                    <p>  ${item.body?substring(0,70)} </p>
                    <br><br>


                </div>
            </#list>
        </#if>
            </div>
        </div>
    </div>



</div>
    <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
        <ul class="nav navbar-nav">
        <li class="hidden">
            <a href="#page-top"></a>
        </li>
        <li>

            <script>
                $(document).ready(function(){
                    $("#flip").click(function(){
                        $("#panel").slideDown("slow");
                    });
                });
            </script>
            <script>
                $(document).ready(function(){
                    $("#flip").dblclick(function(){
                        $("#panel").hide();
                    });
                });
            </script>

            <style>
                #panel, #flip {
                    padding: 5px;
                    text-align: center;
                    border: solid 1px #c3c3c3;
                }

                #panel {
                    padding: 5px;
                    display: none;
                }
            </style>

            </head>
            <body>

            <div id="flip">Login</div>
            <div id="panel">
                <form action="/chata/" method="post">
                    <div class="input-group">
                        Your Name: <input name="name" type="text" class="form-control" placeholder="Nombre " aria-describedby="basic-addon1">
                    </div>
                    <p></p>
                    <button type="submit" class="btn btn-primary">SUBMIT</button>
                </form>
            </div>

        </li>
    </ul>
</div>
<hr>




<script>
    function myX() {
     $.get("/homePage/",function (data) {
            $("#ola").html(data)
        })

    }
</script>

<script>
    function myXback() {
        $.get("/homePageBack/",function (data) {
            $("#ola").html(data)
        })

    }
</script>


<!-- Footer -->
<#include  "./footer.ftl">

</body>

</html>