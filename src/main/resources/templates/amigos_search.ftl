<#include "base.ftl">

<#macro page_head>
<title>Amigos- Search</title>
</#macro>

<#macro page_body>
<div class="main">
    <div class="section section-nude">
        <div class="container">
            <div class="row">
                <div class="tim-container">
                    <div class="tim-row bordec-transparent">
                        <div class="area-line">
                            <h2>Busqueda de Amigos</h2>
                        </div>
                    </div>

                    <!-- post row -->
                    <div class="tim-row bordec" style="min-height: 300px">
                        <center><h4> Usuarios Encontrados</h4></center>
                        <legend></legend>
                        <#if lista??>
                            <center>
                                <table>
                                    <tr><th>Username</th>
                                        <th>Nombre</th>
                                        <th>Perfil</th>
                                        <th>Solicitud?</th>
                                    </tr>
                                <#list lista as user>
                                <tr><td>${user.username}</td>
                                    <td>${user.nombre}</td>
                                    <td><a href="/inicio/perfil/${user.username}" class="btn btn-info btn-sm">Perfil</a></td>
                                    <td>
                                        <#if user.esamigo(userl)><a href="/inicio/friends/${user.username}" class="btn btn-warning btn-sm">Solicitud enviada/ Cancelar?</a>
                                        <#elseif userl.esamigo(user)><a href="#" class="btn btn-default btn-sm">Te envio Solicitud</a>
                                        <#else ><a href="/inicio/friends/${user.username}" class="btn btn-info btn-sm">Enviar solicitud</a> </#if>
                                    </td>
                                </tr>
                                </#list>
                                </table>
                            </center>
                        <#else >
                            <center>
                                <h2> No hay usuarios que cumplan con la condicion</h2>
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
