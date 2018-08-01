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
                        <#if posts??>
                            <#list posts as post>
                                <div class="tim-row bordec">
                                    <h2> ${post.getTitle()}</h2>
                                    <h6> ${post.getAuthorp().username}, ${post.getDateTime()}</h6>
                                    <legend></legend>
                                    <center>
                                        <img src='/${post.getImage()}' style="max-width: 600px; height: auto; alt="">
                                    </center>
                                    <p style="margin-bottom: 45px; margin-top: 15px">
                                        ${post.getBody()}
                                    </p>
                                    <center><a  href="#" type="button" class="btn btn-primary">Leer mas</a></center>
                                    <br/>
                                    <legend></legend>
                                    <#if post.getTags()??>
                                        <#list post.getTags() as tag>
                                            <a class="btn btn-info btn-xs" href="/tag/${tag.getId()?string["0"]}"> ${tag.getTag()} </a>
                                        </#list>
                                    </#if>
                                    <#if post.getUserTags()??>
                                        <#list post.getUserTags() as tagu>
                                            <a class="btn btn-info btn-xs" href="/tag/${tagu.username}"> ${tagu.username} </a>
                                        </#list>
                                    </#if>
                                    <br/>
                                    <br/>

                                </div>
                            </#list>
                        </#if>
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
