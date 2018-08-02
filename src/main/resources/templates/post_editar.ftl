<#include "base.ftl">

<#macro page_head>
<title>Post-Editar</title>
</#macro>

<#macro page_body>
<div class="main">
    <div class="section section-nude">
        <div class="container">
            <div class="row">
                <div class="tim-container">
                    <div class="tim-row bordec-transparent">
                        <div class="area-line">
                            <h2>Edicion de post</h2>
                        </div>
                    </div>

                    <!-- post row -->

                    <div class="row">
                        <div class="registro-card re-colo2">
                            <h3 class="title">Editar datos del Post ID:${post.getId()}</h3>

                            <form action="/inicio/edit/${post.getId()}" method="post" enctype="multipart/form-data">

                                <label >Titulo</label>
                                <input type="text" class="form-control" placeholder="Titulo" name="title" <#if post.getTitle()??>value="${post.getTitle()}"</#if>>
                                <br/>
                                <#if post.getImage()??>
                                    <label >Imagen actual</label>
                                    <img src='/${post.getImage()}' style="max-width: 250px; height: auto; alt="">
                                </#if>
                                <br/>
                                <label >Cambiar/agregar imagen?</label>
                                <input type="file" name="uploaded_file" accept='.png, .jpg, .jpeg'/>
                                <label >Cuerpo</label>
                                <textarea class="form-control" placeholder="Escriba detalles sobre usted" rows="5" name="body" required>${post.getBody()}</textarea>
                                <label >Tags</label>
                                <input type="text" class="form-control " placeholder="Tag1, tag2, tag3,..." name="tag" <#if tag??>value="${tag}"</#if>>
                                <label >Etiqueta a tus amigos</label>(sostener Ctrl para mas de uno)
                                        <br/>
                                <#if amigos??>
                                    <select name="amigos" style="width: 90%;" multiple>
                                        <#if amigos_tag??>
                                            <#list amigos_tag as amigo1>
                                                <option style="color: black;" value="${amigo1.username}" selected>${amigo1.username}</option>
                                            </#list>
                                        </#if>
                                        <#if amigos_notag??>
                                            <#list amigos_notag as amigo2>
                                                <option style="color: black;" value="${amigo2.username}">${amigo2.username}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </#if>

                                <center><button class="btn btn-default btn-block" type="submit"><i class="fa fa-save"></i>Guardar cambios</button></center>

                            </form>
                        </div>
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
