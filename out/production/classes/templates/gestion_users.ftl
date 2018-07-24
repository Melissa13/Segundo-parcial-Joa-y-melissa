<#include "base.ftl">

<#macro page_head>
<title>Inicio</title>
</#macro>

<#macro page_body>
<div class="main">
    <div class="section section-nude">
        <div class="container">
            <div class="row">
                    <div class="tim-container">
                        <div class="tim-row bordec-transparent">
                            <div class="area-line">
                                <h2>Gestion de usuarios</h2>
                            </div>
                        </div>

                        <!-- post row -->
                        <div class="tim-row bordec" style="min-height: 300px">
                            <center><h4> Usuarios existentes</h4></center>
                            <legend></legend>

                            <#if lista??>
                            <center>
                            <table>
                                <tr><th>username</th>
                                    <th>Nombre</th>
                                    <th>Administrador</th>
                                    <th>Estatus?</th>
                                    <th>editar</th>
                                    <th>Eliminar</th>
                                </tr>
                                <#list lista as user>
                                <tr><td>${user.username}</td>
                                    <td>${user.nombre}</td>
                                    <td>${user.isAdministrador()?string('Si','No')}</td>
                                    <td><a href="/gestion/promove/${user.username}" <#if user.isAdministrador() >class="btn btn-fill btn-warning btn-sm"<#else >class="btn btn-fill btn-success btn-sm"</#if>>
                                           <#if user.isAdministrador() >Degradar<#else >Promover</#if>
                                        </a></td>
                                    <td><a href="/gestion/edit/${user.username}" class="btn btn-info btn-sm">Editar</a></td>
                                    <td><a href="/gestion/delete/${user.username}" class="btn btn-danger btn-sm">Eliminar</a></td>
                                </tr>
                                </#list>
                            </table>
                            </center>
                            <#else >
                            <center>
                            <h2> No hay usuarios registrados en el Sistema</h2>
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
