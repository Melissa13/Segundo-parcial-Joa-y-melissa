<#include "baselogin.ftl">

<#macro page_head>
<title>Registro</title>
</#macro>

<#macro page_body>
    <div class="container">
        <div class="row">
                <div class="registro-card re-color">
                    <h3 class="title">Registro de usuario</h3>
                    <form method="post" action="/registrar">
                        <div class="row">
                            <label class="registro-label">Username</label>
                            <label class="registro-labelb">Password</label>
                        </div>
                        <div class="row">
                            <input type="text" class="form-control registro-form" placeholder="Username" name="username" required>
                            <input type="text" class="form-control registro-formb" placeholder="Password" name="password" required>
                        </div>

                        <div class="row">
                            <label class="registro-label">Nombre</label>
                            <label style="float: left; margin-left: 313px">Fecha de nacimiento</label>
                        </div>
                        <div class="row">
                            <input type="text" class="form-control registro-form" placeholder="Nombre" name="name">
                            <input class="datepicker form-control registro-formb" type="text" name="date"/>
                        </div>

                        <div class="row">
                            <label class="registro-label">Descripcion</label>
                        </div>
                        <div class="row">
                            <textarea class="form-control registro-text" placeholder="Escriba detalles sobre usted" rows="5" name="description"></textarea>
                        </div>

                        <div class="row">
                            <label class="registro-label">Lugar de nacimento</label>
                            <label style="float: left; margin-left: 218px">Locacion actual</label>
                        </div>
                        <div class="row">
                            <input type="text" class="form-control registro-form" placeholder="Lugar de nacimiento" name="place_birth">
                            <input type="text" class="form-control registro-formb" placeholder="Locacion actual" name="actual_place">
                        </div>

                        <div class="row">
                            <label class="registro-label">Trabajo</label>
                            <label style="float: left; margin-left: 322px">Lugar de trabajo</label>
                        </div>
                        <div class="row">
                            <input type="text" class="form-control registro-form" placeholder="Trabajo" name="job">
                            <input type="text" class="form-control registro-formb" placeholder="Lugar de trabajo" name="workplace">
                        </div>

                        <div class="row">
                            <label class="registro-label" style="text-align: center">Estudios</label>
                        </div>
                        <div class="row">
                            <input type="text" class="form-control registro-formc" placeholder="Estudios realizados" name="studies">
                        </div>

                        <button class="btn btn-default btn-block" type="submit"><i class="fa fa-user"></i>Registrarse</button>
                    </form>
                </div>
        </div>
    </div>
</#macro>