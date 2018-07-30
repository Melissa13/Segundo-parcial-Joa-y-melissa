<#include "baselogin.ftl">

<#macro page_head>
<title>Login</title>
</#macro>

<#macro page_body>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1 ">
                <div class="register-card">
                    <h3 class="title">Welcome</h3>
                    <form class="register-form" method="post" action="/login">
                        <label>Username</label>
                        <input type="text" class="form-control" placeholder="Username" name="username" required>

                        <label>Password</label>
                        <input type="password" class="form-control" placeholder="Password" name="pass" required>
                        <button class="btn btn-default btn-block" type="submit"><i class="fa fa-sign-in"></i>Log in</button>
                    </form>
                    <div class="forgot">
                        Aun no tienes cuenta?<a href="/registrar" class="btn btn-simple btn-default" style="margin-top: -1px">Registrate aqui</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>