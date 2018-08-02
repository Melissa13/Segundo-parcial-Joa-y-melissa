<#include "baseperfil.ftl">

<#macro page_head>
<title>Subir Imagen</title>
</#macro>

<#macro page_body>
    <div class="main">
        <div class="section section-nude">
            <div class="container">
                <div class="row">
                    <div class="tim-container">
                        <div class="tim-row bordec-transparent">
                            <div class="area-line">
                                <h2>Subir imagenes</h2>
                            </div>
                            <div>
                                <form method='post' enctype='multipart/form-data' action="/testImage">
                                    <input type="file" name="uploaded_file" accept='.png, .jpg, .jpeg'>
                                    <button class="btn btn-default btn-block" type="submit">Subir</button>
                                </form>
                                ${imagen.getFileName()}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>