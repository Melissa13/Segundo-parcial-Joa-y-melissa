<#include "base.ftl">

<#macro page_head>
<title>Inicio</title>
</#macro>

<#macro page_body>
<div class="main">
    <div class="section section-nude">
        <div class="container" style="min-height: 500px">
            <div class="fixed-bar">
                <center>
                    <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i> Agregar Post</button>
                </center>
                <br/>
                <div class="fondo">
                    <ul>
                        <li><a href="#buttons-row">Ver todo</a></li>
                        <li><a href="#checkbox-row">Ver mis Post</a></li>
                        <li><a href="#dropdown-row">Posts de mis amigos</a></li>
                    </ul>
                </div>
                <br/>
                <br/>
                <a href="/inicio" class="btn btn-primary btn-lg" ><i class="fa fa-arrow-left"></i> Volver</a>
            </div>
            <div class="row">
                <div class="leftcolumn">
                    <br/>
                </div>
                <div class="rightcolumn">
                    <div class="tim-container">

                        <div class="tim-row bordec-transparent">
                            <div class="area-line">
                                <h3>Post ID: ${post.getId()}</h3>
                            </div>
                        </div>

                        <!-- post row -->
                        <div class="tim-row bordec">

                            <#if userl.isAdministrador() || userl.username == post.getAuthorp().username>

                            <li class="dropdown" style="float: right;">
                                <button href="#" class="dropdown-toggle btn btn-info btn-sm" data-toggle="dropdown">...</button>
                                <!--                                  You can add classes for different colours on the next element -->
                                <ul class="dropdown-menu dropdown-primary dropdown-menu-right">
                                    <li class="dropdown-header">Opciones</li>
                                    <li><a href="/inicio/edit/${post.getId()}">Editar</a></li>
                                    <li class="divider"></li>
                                    <li><a href="/inicio/delete/${post.getId()}">Borrar</a></li>
                                </ul>
                            </li>
                            </#if>

                            <h2> <#if post.getTitle()??>${post.getTitle()}</#if></h2>
                            <h6> ${post.getAuthorp().username}, ${post.getDateTime()}</h6>
                            <legend></legend>
                            <center>
                                <#if post.getImage()??>
                                    <img src='/${post.getImage()}' style="max-width: 600px; height: auto; alt="">
                                </#if>
                            </center>
                            <br/>
                            <p>
                            <h5 class="texto-arreglo">${post.getBody()}</h5>
                            </p>
                            <br/>
                            <legend></legend>
                            <#if post.getTags()??>
                                <#list post.getTags() as tag>
                                    <a class="btn btn-info btn-xs" href="/tag/${tag.getId()?string["0"]}"> ${tag.getTag()} </a>
                                </#list>
                            </#if>
                            <#if post.getUserTags()??>
                                <#list post.getUserTags() as tagu>
                                    <a class="btn btn-info btn-xs" href="/tag/${tagu.username}"><i class="fa fa-user"></i> ${tagu.username} </a>
                                </#list>
                            </#if>
                            <br/>
                            <br/>

                        </div>
                        <!-- end row -->

                    </div>
                    <!-- end container -->
                </div>
            </div>
        </div>
    </div>
</div>

<!-- crear blog -->
<!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">

                <form action="/inicio/agregar" method="post" enctype="multipart/form-data">

                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Comparta sus recuerdos!</h4>
                        </div>
                        <div class="modal-body" style="background-color: #ccebff">

                            <label >Titulo</label>
                            <input type="text" class="form-control" placeholder="Titulo" name="title">
                            <label >Imagen</label>
                            <input type="file" name="uploaded_file" accept='.png, .jpg, .jpeg'/>
                            <label >Cuerpo</label>
                            <textarea class="form-control" placeholder="Escriba detalles sobre usted" rows="5" name="body" required></textarea>
                            <label >Tags</label>
                            <input type="text" class="form-control " placeholder="Tag1, tag2, tag3,..." name="tag">
                            <label >Etiqueta a tus amigos</label>(sostener Ctrl para mas de uno)
                            <br/>
                        <#if amigos??>
                            <select name="amigos" style="width: 90%;" multiple>
                                <#list amigos as amigo>
                                    <option value="${amigo.username}">${amigo.username}</option>
                                </#list>
                            </select>
                        </#if>

                        </div>
                        <div class="modal-footer">
                            <div class="left-side">
                                <button type="button" class="btn btn-default btn-simple" data-dismiss="modal"><b>Cancelar</b></button>
                            </div>
                            <div class="divider"></div>
                            <div class="right-side">
                                <button type="submit" class="btn btn-success btn-simple"><b>Publicar</b></button>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
        </div>

</#macro>
