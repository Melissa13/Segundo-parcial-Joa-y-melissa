<html lang="en">
<head>
    <meta charset="utf-8" />
    <link rel="icon" type="image/png" href="/assets/paper_img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <@page_head/>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href="/bootstrap3/css/bootstrap.css" rel="stylesheet" />
    <link href="/assets/css/ct-paper.css" rel="stylesheet"/>
    <link href="/assets/css/demo.css" rel="stylesheet" />
    <link rel="stylesheet" href="/assets/css/detalles.css">
    <link rel="stylesheet" href="/assets/css/complemento.css">


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>

</head>
<body class="profile-page">
<!--barra de navegacion navbar -->
<div id="navbar-dropdown">
<nav class="navbar navbar-ct-primary bg-primary fixed-top navbar-transparent " color-on-scroll="400">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/inicio">Social Diary</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav pull-right">
                <!--                                       primary dropdown -->
                <#if userl??>
                    <#if userl.isAdministrador() >
                            <li>
                                <a href="/prueba" class="btn btn-fill btn-primary">Gestión</a>
                                </a>
                            </li>
                    </#if>
                </#if>

                <li class="dropdown">
                    <button href="#" class="dropdown-toggle btn btn-fill btn-primary" data-toggle="dropdown">Primary 2<b class="caret"></b></button>
                    <!--                                  You can add classes for different colours on the next element -->
                    <ul class="dropdown-menu dropdown-primary dropdown-menu-right">
                        <li class="dropdown-header">Dropdown header</li>
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <button href="#" class="dropdown-toggle btn btn-fill btn-primary" data-toggle="dropdown">Primary 3 <b class="caret"></b></button>
                    <!--                                  You can add classes for different colours on the next element -->
                    <ul class="dropdown-menu dropdown-primary dropdown-menu-right">
                        <li class="dropdown-header">Dropdown header</li>
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>

                <!-- usuario-->
                <li class="dropdown">
                    <button href="#" class="dropdown-toggle espacio" data-toggle="dropdown">
                        <div class="logo-container">
                            <div class="logo">
                                <img src="../assets/img/default-avatar.png" alt="Creative Tim Logo">
                            </div>
                            <div class="brand">
                                <#if userl??>
                                ${userl.username}
                                </#if>
                                <b class="caret"></b>
                            </div>
                        </div>
                    </button>
                    <ul class="dropdown-menu dropdown-primary dropdown-menu-right">
                        <li class="dropdown-header">Dropdown header</li>
                        <li><a href="/perfil">Perfil</a></li>
                        <li><a href="#">Album</a></li>
                        <li><a href="#">Amigos</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Editar Perfil</a></li>
                        <li class="divider"></li>
                        <li><a href="/logout"><i class="fa fa-sign-in"></i>Log out</a></li>
                    </ul>
                </li>


            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
</div>
<!-- End Navbar -->


<!-- Contenido-->
<@page_body/>

<!--footer -->

<footer class="footer-demo section-dark" style="margin-bottom: -1">
    <div class="container">
        <nav class="pull-left">
            <ul>

                <li>
                    <a href="https://github.com/Melissa13/Segundo-parcial-Joa-y-melissa">
                        Repositorio
                    </a>
                </li>
                <li>
                    <a href="https://www.pucmm.edu.do/">
                        PUCMM
                    </a>
                </li>
            </ul>
        </nav>
        <div class="copyright pull-right">
            &copy; 2018, Trabajo segundo parcial <i class="fa fa-star"></i> by Juan Joa y Melissa
        </div>
    </div>
</footer>

</body>

<script src="/assets/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="/assets/js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>

<script src="/bootstrap3/js/bootstrap.js" type="text/javascript"></script>

<!--  Plugins -->
<script src="/assets/js/ct-paper-checkbox.js"></script>
<script src="/assets/js/ct-paper-radio.js"></script>
<script src="/assets/js/bootstrap-select.js"></script>
<script src="/assets/js/bootstrap-datepicker.js"></script>

<script src="/assets/js/ct-paper.js"></script>


<!--   Core JS Files   -->
<script src="../assets/js/core/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="../assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="../assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="../assets/js/plugins/bootstrap-switch.js"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="../assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
<!--  Plugin for the DatePicker, full documentation here: https://github.com/uxsolutions/bootstrap-datepicker -->
<script src="../assets/js/plugins/bootstrap-datepicker.js" type="text/javascript"></script>
Control Center for Now Ui Kit: parallax effects, scripts for the example pages etc -->
<script src="../assets/js/now-ui-kit.js?v=1.1.0" type="text/javascript"></script>
</html>