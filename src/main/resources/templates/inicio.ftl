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
            </div>
            <div class="row">
                <div class="leftcolumn">
                    <br/>
                </div>
                <div class="rightcolumn">
                    <div class="tim-container">

                        <div class="tim-row bordec-transparent">
                            <div class="area-line">
                                <h3>Ultimos Posts</h3>
                            </div>
                        </div>

                        <!-- post row -->
                        <#if posts??>
                            <#list posts as post>
                                <div class="tim-row bordec">

                                    <div>
                                        <a href="/inicio/post/${post.getId()}" class="btn btn-info btn-sm opcion"><i class="fa fa-comment"></i>${post.getComments()?size}</a>

                                        <li class="dropdown opcion">
                                            <button href="#" class="dropdown-toggle btn btn-info btn-sm" data-toggle="dropdown"><i class="fa fa-heart"></i>${post.getLikes()?size}</button>
                                            <!--                                  You can add classes for different colours on the next element -->
                                            <ul class="dropdown-menu dropdown-primary dropdown-menu-right">
                                                <li class="dropdown-header">Likes:</li>
                                                <#if post.getLikes()??>
                                                    <#list post.getLikes() as like>
                                                    <li><a href="/inicio/perfil/${like.getLike().username}"><i class="fa fa-user"></i>${like.getLike().username}</a></li>
                                                    </#list>
                                                </#if>
                                            </ul>
                                        </li>

                                        <#if userl.isAdministrador() || userl.username == post.getAuthorp().username>

                                        <li class="dropdown opcion">
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
                                    </div>

                                    <h2> <#if post.getTitle()??>${post.getTitle()}</#if></h2>
                                    <h6> ${post.getAuthorp().username}, ${post.getDateTime()}</h6>
                                    <legend></legend>
                                    <center>
                                        <#if post.getImage()??>
                                            <img src='/${post.getImage()}' style="max-width: 600px; height: auto; alt="">
                                        </#if>
                                    </center>
                                    <p>
                                        <h5 class="texto-arreglo">${post.getBody()}</h5>
                                    </p>
                                    <center><a  href="/inicio/post/${post.getId()}" type="button" class="btn btn-info">Leer mas</a></center>
                                    <br/>
                                    <legend></legend>
                                    <a <#if post.validar(userl) >href="/post/dislike/${post.getId()}" class="btn btn-success btn-fill btn-sm opcion"<#else >href="/post/like/${post.getId()}" class="btn btn-dark btn-sm opcion"</#if>><i class="fa fa-thumbs-up"></i>Like</a>
                                    <#if post.getTags()??>
                                        <#list post.getTags() as tag>
                                            <a class="btn btn-info btn-xs" href="/tag/${tag.getId()?string["0"]}"> ${tag.getTag()} </a>
                                        </#list>
                                    </#if>
                                    <#if post.getUserTags()??>
                                        <#list post.getUserTags() as tagu>
                                            <a class="btn btn-info btn-xs" href="/inicio/perfil/${tagu.username}"><i class="fa fa-user"></i>${tagu.username} </a>
                                        </#list>
                                    </#if>
                                    <br/>
                                    <br/>

                                </div>
                            </#list>
                        </#if>
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
