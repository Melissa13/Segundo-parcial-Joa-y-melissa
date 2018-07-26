<#include "base.ftl">

<#macro page_head>
<title>Inicio</title>
</#macro>

<#macro page_body>
<div class="main">
    <div class="section section-nude">
        <div class="container">
            <div class="fixed-bar">
                <center>
                    <a href="/prueba" class="btn btn-primary btn-lg">
                        <i class="fa fa-plus"></i> Agregar Post
                    </a>
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
                        <div class="tim-row bordec">
                            <h2> Titulo del post</h2>
                            <h6> Autor, fecha</h6>
                            <legend></legend>
                            <center>imagen</center>
                            <p style="margin-bottom: 45px; margin-top: 15px">
                                cuerpo del post
                            </p>
                            <center><a  href="#" type="button" class="btn btn-primary">Leer mas</a></center>
                            <br/>
                            <legend></legend>
                            Tag1, tag2, tag3
                            <br/>
                            <br/>

                        </div>
                        <!-- end row -->
                        <div class="tim-row bordec">
                            <h2> Post 1</h2>
                            <legend></legend>
                            <p style="margin-bottom: 45px;">
                                We restyled the Bootstrap tooltip.
                            </p>
                            <button type="button" class="btn btn-default btn-tooltip" data-toggle="tooltip" data-placement="top" title="Tooltip on top" data-trigger="manual">Button with Tooltip</button>
                            <div class="area-line">
                                <a data-target="#tooltipMarkup" href="javascript: void(0);" data-toggle="collapse">See Markup and Javascript</a>
                                <div id="tooltipMarkup" class="collapse">
                                </div>
                            </div>
                        </div>
                        <div class="tim-row bordec">
                            <h2> Post 2</h2>
                            <legend></legend>
                            <p style="margin-bottom: 45px;">
                                We restyled the Bootstrap tooltip.
                            </p>
                            <button type="button" class="btn btn-default btn-tooltip" data-toggle="tooltip" data-placement="top" title="Tooltip on top" data-trigger="manual">Button with Tooltip</button>
                            <div class="area-line">
                                <a data-target="#tooltipMarkup" href="javascript: void(0);" data-toggle="collapse">See Markup and Javascript</a>
                                <div id="tooltipMarkup" class="collapse">
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- end container -->
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
