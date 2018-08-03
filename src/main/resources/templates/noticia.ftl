<#include "base.ftl">

<#macro page_head>
<title>Notificacion-Anuncio</title>
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
                    <div class="tim-row bordec">
                        <br/>
                        <legend></legend>
                        <div class="alert alert-warning alerta">
                            <h1>${msg}</h1>
                        </div>
                        <legend></legend>
                        <br/>
                    </div>
                    <!-- end row -->
                </div>
                <center>
                    <a href="/inicio/news" class="btn btn-primary btn-lg">
                        <i class="fa fa-arrow-circle-left"></i> Volver
                    </a>
                </center>
                <!-- end container -->
            </div>
        </div>
    </div>
</div>
</#macro>
