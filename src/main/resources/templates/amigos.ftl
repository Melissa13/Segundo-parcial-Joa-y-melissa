<#include "base.ftl">

<#macro page_head>
<title>Amigos- My</title>
</#macro>

<#macro page_body>
<div class="main">
    <div class="section section-nude">
        <div class="container">
            <div class="row">
                <div class="tim-container">
                    <div class="tim-row bordec-transparent">
                        <div class="area-line">
                            <h2>Mis Amigos</h2>
                        </div>
                    </div>

                    <!-- post row -->
                    <div class="tim-row bordec" style="min-height: 300px">
                        <center><h4> Usuarios Encontrados</h4></center>
                        <legend></legend>
                        <#if lista?has_content>
                            <center>
                                <table>
                                    <tr><th>Username</th>
                                        <th>Nombre</th>
                                        <th>Perfil</th>
                                        <th>Eliminar?</th>
                                    </tr>
                                <#list lista as user>
                                <tr><td>${user.username}</td>
                                    <td>${user.nombre}</td>
                                    <td><a href="/inicio/perfil/${user.username}" class="btn btn-info btn-sm">Perfil</a></td>
                                    <td><a href="/inicio/friends/${user.username}" class="btn btn-danger btn-fill btn-sm">Eliminar</a></td>
                                </tr>
                                </#list>
                                </table>
                            </center>
                        <#else >
                            <center>
                                <h2> Usted no tiene Amigos :(</h2>
                                <a href="/inicio/friends" class="btn btn-primary btn-lg">
                                    <i class="fa fa-search"></i> Buscar Amigos?
                                </a>
                            </center>
                        </#if>
                        <legend></legend>
                    </div>
                    <!-- end row -->
                </div>
                <center>
                    <a href="/inicio" class="btn btn-primary btn-lg">
                        <i class="fa fa-arrow-circle-left"></i> Volver
                    </a>
                </center>
                <!-- end container -->
            </div>
        </div>
    </div>
</div>
</#macro>
