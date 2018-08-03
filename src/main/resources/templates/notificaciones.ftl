<#include "base.ftl">

<#macro page_head>
<title>Gestion- news</title>
</#macro>

<#macro page_body>
<div class="main">
    <div class="section section-nude">
        <div class="container">
            <div class="row">
                <div class="tim-container">
                    <div class="tim-row bordec-transparent">
                        <div class="area-line">
                            <h2>Gestion de Notificaciones</h2>
                        </div>
                    </div>

                    <!-- post row -->
                    <div class="tim-row bordec" style="min-height: 300px">
                        <center><h4> Notificaciones pendientes</h4></center>
                        <legend></legend>

                            <#if userl.getNews()?has_content>
                            <center>
                                <table>
                                    <tr><th>ID</th>
                                        <th>Tipo</th>
                                        <th>Usuario</th>
                                        <th>Mensaje</th>
                                        <th>Accion?</th>
                                        <th>Ignorar</th>
                                    </tr>
                                <#list userl.getNews() as news>
                                <tr><td>${news.getId()?string["0"]}</td>
                                    <td>${news.tipo()}</td>
                                    <td><a href="/inicio/perfil/${news.getOrigen().username}" class="btn btn-info btn-sm">${news.getOrigen().username}</a></td>
                                    <td>${news.getMensaje()}</td>
                                    <td><a href="/inicio/news/see/${news.getId()?string["0"]}" class="btn btn-success btn-sm">Ver / Aceptar</a></td>
                                    <td><a href="/inicio/news/delete/${news.getId()?string["0"]}" class="btn btn-default btn-sm">Ignorar</a></td>
                                </tr>
                                </#list>
                                </table>
                            </center>
                            <#else >
                            <center>
                                <h2> No hay notificaciones pendientes</h2>
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
