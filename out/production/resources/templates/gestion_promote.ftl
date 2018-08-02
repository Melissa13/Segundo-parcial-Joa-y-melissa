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
                    <br/>
                    <#if eluser.isAdministrador() >
                        <div class="alert alert-success alerta">
                            <h3><b>Exito: </b> usuario ${eluser.username} ha sido promovido a administrador satisfactoriamente</h3>
                        </div>
                    <#else >
                        <div class="alert alert-success alerta">
                            <h3><b>Exito: </b> usuario ${eluser.username} ha sido degradado a usuario normal satisfactoriamente</h3>
                        </div>
                    </#if>
                    <br/>
                    <br/>

                </div>
                <center>
                    <a href="/gestion" class="btn btn-primary btn-lg">
                        <i class="fa fa-arrow-circle-left"></i> Volver
                    </a>
                </center>
                <!-- end container -->
            </div>
        </div>
    </div>
</div>
</#macro>
