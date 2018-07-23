<#include "baseperfil.ftl">

<#macro page_head>
<title>Perfil-Editar</title>
</#macro>

<#macro page_body>
    <#if userl??>
<div class="wrapper">
    <div class="page-header page-header-small" filter-color="blue">
        <div class="page-header-image" data-parallax="true" style="background-image: url('../assets/img/bg5.jpg');">
        </div>
        <div class="container">
            <div class="content-center">
                <div class="photo-container">
                    <img src="../assets/img/default-avatar.png" alt="">
                </div>
                <h3 class="title">${userl.username}</h3>
                <p class="category"><#if userl.nombre??>(${userl.nombre})</#if></p>
                <div class="content">
                    <h2 class="title">Editar usuario</h2>
                </div>
            </div>
        </div>
    </div>
    <div class="section">
        <div class="button-container perfil-bar" >
            <a href="#button" class="btn btn-fill btn-primary espacio btn-lg" style="margin: 7px">Amigos</a>
            <a href="#button" class="btn btn-fill btn-primary espacio btn-lg redondo btn-tooltip" data-toggle="tooltip" data-placement="top" title="Follow me on Twitter">
                <i class="fa fa-twitter"></i>
            </a>
            <a href="#button" class="btn btn-fill btn-primary espacio btn-lg redondo btn-tooltip" data-toggle="tooltip" data-placement="top" title="Follow me on Instagram">
                <i class="fa fa-instagram"></i>
            </a>
        </div>
        <div class="container">
            <br/>
            <a href="/perfil" class="btn btn-primary btn-lg">
                <i class="fa fa-arrow-left"></i> Volver
            </a>
            <div class="row">
                <div class="registro-card re-colo2">
                    <h3 class="title">Editar datos del perfil</h3>
                    <form method="post" action="/perfil/editar">
                        <div class="row">
                            <label class="registro-label">Username</label>
                            <label class="registro-labelb">Password</label>
                        </div>
                        <div class="row">
                            <input type="text" class="form-control registro-form" placeholder="Username" name="username" value="${userl.username}" disabled>
                            <input type="text" class="form-control registro-formb" placeholder="Password" name="password" value="${userl.getPassword()}" required>
                        </div>

                        <div class="row">
                            <label class="registro-label">Nombre</label>
                            <label style="float: left; margin-left: 313px">Fecha de nacimiento</label>
                        </div>
                        <div class="row">
                            <input type="text" class="form-control registro-form" placeholder="Nombre" name="name" <#if userl.nombre??>value="${userl.nombre}"</#if>>
                            <input class="datepicker form-control registro-formb" type="text" name="date" <#if userl.date_birth??>value="${fecha}"</#if>/>
                        </div>

                        <div class="row">
                            <label class="registro-label">Descripcion</label>
                        </div>
                        <div class="row">
                            <textarea class="form-control registro-text" placeholder="Escriba detalles sobre usted" rows="5" name="description" ><#if userl.descripcion??>${userl.getDescripcion()}</#if></textarea>
                        </div>

                        <div class="row">
                            <label class="registro-label">Lugar de nacimento</label>
                            <label style="float: left; margin-left: 218px">Locacion actual</label>
                        </div>
                        <div class="row">
                            <input type="text" class="form-control registro-form" placeholder="Lugar de nacimiento" name="place_birth" <#if userl.place_birth??>value="${userl.place_birth}"</#if>>
                            <input type="text" class="form-control registro-formb" placeholder="Locacion actual" name="actual_place" <#if userl.actual_place??>value="${userl.actual_place}"</#if>>
                        </div>

                        <div class="row">
                            <label class="registro-label">Trabajo</label>
                            <label style="float: left; margin-left: 322px">Lugar de trabajo</label>
                        </div>
                        <div class="row">
                            <input type="text" class="form-control registro-form" placeholder="Trabajo" name="job" <#if userl.job??>value="${userl.job}"</#if>>
                            <input type="text" class="form-control registro-formb" placeholder="Lugar de trabajo" name="workplace" <#if userl.workplace??>value="${userl.workplace}"</#if>>
                        </div>

                        <div class="row">
                            <label class="registro-label" style="text-align: center">Estudios</label>
                        </div>
                        <div class="row">
                            <input type="text" class="form-control registro-formc" placeholder="Estudios realizados" name="studies" <#if userl.getStudies()??>value="${userl.getStudies()}"</#if>>
                        </div>

                        <button class="btn btn-default btn-block" type="submit"><i class="fa fa-save"></i>Guardar cambios</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

    </#if>
</#macro>