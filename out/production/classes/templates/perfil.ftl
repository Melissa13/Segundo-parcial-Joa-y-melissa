<#include "baseperfil.ftl">

<#macro page_head>
<title>Perfil</title>
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
                    <div class="social-description">
                        <h2>
                            ${userl.getFriends()?size}
                        </h2>
                        <p>Amigos</p>
                    </div>
                    <div class="social-description">
                        <h2>0</h2>
                        <p>Posts</p>
                    </div>
                    <div class="social-description">
                        <h2>${userl.getAlbun()?size}</h2>
                        <p>Fotos</p>
                    </div>
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
            <h3 class="title">Sobre mi</h3>
            <h5 class="description"><#if userl.descripcion??>${userl.getDescripcion()}<#else >Informacion faltante</#if></h5>
            <div class="row">
                <h4 class="title text-center">Detalles</h4>
                <!-- Tab panes -->
                <div class="nav-tabs-navigation tab-color-title">
                    <div class="nav-tabs-wrapper">
                        <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                            <li class="active"><a href="#personal" data-toggle="tab">Personal</a></li>
                            <li><a href="#profile" data-toggle="tab">Profecional</a></li>
                            <li><a href="#actividad" data-toggle="tab">Actividad</a></li>
                        </ul>
                    </div>
                </div>
                <div id="my-tab-content" class="tab-content text-center tab-color">
                    <div class="tab-pane active" id="personal">
                        <div class="row">
                            <div class="magic-text">
                                <h4><b>Fecha de nacimiento: </b><#if userl.date_birth??>${fecha}<#else >Desconocido</#if></h4>
                            </div>
                            <div class="magic-textb">
                                <h4><b>Edad: </b><#if userl.date_birth??>${edad}<#else >Desconocido</#if></h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="magic-text">
                                <h4><b>Lugar de nacimiento: </b><#if userl.place_birth??>${userl.place_birth}<#else >Desconocido</#if></h4>
                            </div>
                            <div class="magic-textb">
                                <h4><b>Direccion Actual: </b><#if userl.actual_place??>${userl.actual_place}<#else >Desconocido</#if></h4>
                            </div>
                        </div>
                        <br/>
                    </div>
                    <div class="tab-pane" id="profile">
                        <div class="row">
                            <h4 style="margin-left: 75px"><b>Estudios: </b><#if userl.getStudies()??>${userl.getStudies()}<#else >Desconocido</#if></h4>
                        </div>
                        <div class="row">
                            <div class="magic-text">
                                <h4><b>Trabajo: </b><#if userl.job??>${userl.job}<#else >Desconocido</#if></h4>
                            </div>
                            <div class="magic-textb">
                                <h4><b>Lugar de trabajo: </b><#if userl.workplace??>${userl.workplace}<#else >Desconocido</#if></h4>
                            </div>
                        </div>
                        <br/>
                    </div>
                    <div class="tab-pane" id="Actividad">
                        <p>Here are your messages.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

    </#if>
</#macro>