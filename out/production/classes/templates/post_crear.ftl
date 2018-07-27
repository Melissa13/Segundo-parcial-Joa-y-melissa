<#include "base.ftl">

<#macro page_head>
<title>Post-Crear</title>
</#macro>

<#macro page_body>
<div class="main">
    <div class="section section-nude">
        <div class="container">
            <div class="row">
                <div class="tim-container">
                    <div class="tim-row bordec-transparent">
                        <div class="area-line">
                            <h2>creacion de Post</h2>
                        </div>
                    </div>

                    <!-- post row -->
                    <div class="tim-row bordec" style="min-height: 300px">
                        <center><h4> Pruena de imagenes</h4></center>
                        <legend></legend>
                        <form action="/inicio/add" method="post" enctype="multipart/form-data">

                        Imagen: <input type="file" name="filecover" value="Upload"/>

                        <button class="btn btn-default btn-block" type="submit">Log in</button>
                        </form>

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
