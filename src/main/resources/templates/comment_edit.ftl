<#include "base.ftl">

<#macro page_head>
<title>Comment-edit</title>
</#macro>

<#macro page_body>
<div class="main">
    <div class="section section-nude">
        <div class="container" style="min-height: 500px">

            <!-- post row -->

            <div class="row">
                <div class="registro-card re-colo2">
                    <h3 class="title">Editar datos del Comentario ID:${comm.getId()}</h3>

                    <form action="/post/editc/${comm.getId()}" method="post">

                        <label >Comentario</label>
                        <textarea class="form-control" placeholder="Escriba detalles sobre usted" rows="5" name="body" required>${comm.getText()}</textarea>
                        <br/>
                        <center><button class="btn btn-default btn-block" type="submit"><i class="fa fa-save"></i>Guardar cambios</button></center>

                    </form>
                </div>
            </div>
            <!-- end row -->
        </div>
        <center>
            <a href="/inicio/post/${comm.getPost().getId()}" class="btn btn-primary btn-lg">
                <i class="fa fa-arrow-circle-left"></i> Volver
            </a>
        </center>
        <!-- end container -->

        </div>
    </div>
</div>


</#macro>
