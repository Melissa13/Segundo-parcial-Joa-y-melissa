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
<body>
<!--barra de navegacion navbar -->
    <div id="navbar-dropdown">
        <nav class="navbar navbar-ct-primary fixed-top" >
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
                                <a href="/gestion" class="btn btn-fill btn-primary">Gestión</a>
                            </li>
                            </#if>
                        </#if>

                        <li class="dropdown">
                            <button href="#" class="dropdown-toggle btn btn-fill btn-primary" data-toggle="dropdown">Amigos<b class="caret"></b></button>
                            <!--                                  You can add classes for different colours on the next element -->
                            <ul class="dropdown-menu dropdown-primary dropdown-menu-right">
                                <li class="dropdown-header">Buscar por:</li>
                                <li><a href="/inicio/friends">General</a></li>
                                <li><a href="#">Lugar</a></li>
                                <li><a href="#">Estudio</a></li>
                                <li class="divider"></li>
                                <li><a href="/inicio/friend/my">Mis amigos</a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="/inicio/news" <#if userl.getNews()?has_content>class="btn btn-fill btn-danger"<#else>class="btn btn-fill btn-primary"</#if>><i class="fa fa-bell"></i></a>
                        </li>

                        <!-- usuario-->
                        <#if userl??>
                        <li class="dropdown">
                            <button href="#" class="dropdown-toggle espacio" data-toggle="dropdown">
                                <div class="logo-container">
                                    <div class="logo">
                                        <img src="/assets/img/default-avatar.png" alt="Creative Tim Logo">
                                    </div>
                                    <div class="brand">
                                        ${userl.username}
                                        <b class="caret"></b>
                                    </div>
                                </div>
                            </button>
                            <ul class="dropdown-menu dropdown-primary dropdown-menu-right">
                                <li class="dropdown-header">Dropdown header</li>
                                <li><a href="/perfil">Perfil</a></li>
                                <li><a href="#">Album</a></li>
                                <li><a href="/inicio/friend/my">Amigos</a></li>
                                <li class="divider"></li>
                                <li><a href="/perfil/editar">Editar Perfil</a></li>
                                <li class="divider"></li>
                                <li><a href="/logout"><i class="fa fa-sign-in"></i>Log out</a></li>
                            </ul>
                        </li>
                        </#if>


                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

    </div><!--  end navbar -->


<!-- Contenido-->
<@page_body/>

<!--footer -->

<footer class="footer-demo section-dark">
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
</html>