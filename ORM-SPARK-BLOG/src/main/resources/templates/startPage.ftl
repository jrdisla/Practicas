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

</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                Menu <i class="fa fa-bars"></i>
            </button>

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
                        <form action="/login/" method="post">
                        <div class="input-group">
                            Username: <input name="username" type="text" class="form-control" placeholder="Nombre " aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group">
                            Password: <input name="password" type="password" class="form-control" placeholder="Your Password" aria-describedby="basic-addon1">
                        </div>
                                <p></p>
                            <button type="submit" class="btn btn-primary">Login</button>
                        </form>
                        </div>



                </li>

            </ul>
        </div>
    </div>
</nav>
<header class="intro">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <h1 class="brand-heading">Spark Blog</h1>
                    <p class="intro-text">
                        <br>Created by Julio Disla.</p>
                    <div class="row">
                        <div class="col-xs-12">
                            <form action="/signup/" method="post" role="form">
                                <legend>SIGN UP FOR COMPLETE ACCESS</legend>

                                <div class="row">
                                    <div class="form-group col-xs-12 col-md-6">
                                        <span class="glyphicon glyphicon-user" aria-hidden="true">
                                        <label for="username"><i class="fa fa-user"></i> Nombre de Usuario</label>
                                        <input type="text" class="form-control"
                                               value=''
                                               name="username" id="username" placeholder="" required>
                                    </div>
                                    <div class="form-group col-xs-12 col-md-6">

                                        <label for="fullname">Nombre Completo</label>
                                        <input type="text" class="form-control"
                                               value=''
                                               name="fullname" id="fullname" placeholder="">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-xs-12 col-md-6">
                                        <span class="glyphicon glyphicon-lock" aria-hidden="true">
                                        <label for="password"><i class="fa fa-key"></i> Contrasena</label>
                                        <input type="password" class="form-control"
                                               name="password" id="password" placeholder="">
                                    </div>
                                    <div class="form-group col-xs-12 col-md-6">
                                         <span class="glyphicon glyphicon-lock" aria-hidden="true">
                                        <label for="password2">Repetir Contrasena</label>
                                        <input type="password" class="form-control"
                                               name="password2" id="password2" placeholder="">
                                    </div>
                                </div>

                              <p></p>
                                        <button type="submit" class="btn btn-primary btn-block">Registrarse</button>
                                    </div>

                            </form>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</header>
<footer>
    <div class="container text-center">
        <p>Copyright &copy; Spark Blog 2017</p>
    </div>
</footer>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRngKslUGJTlibkQ3FkfTxj3Xss1UlZDA&sensor=false"></script>
</body>
</html>
