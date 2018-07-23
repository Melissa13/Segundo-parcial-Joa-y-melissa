<#include "base.ftl">

<#macro page_head>
<title>Inicio</title>
</#macro>

<#macro page_body>
<div class="main">
    <div class="section section-nude">
        <div class="container">
            <div class="row">
                    <div class="tim-container">
                        <div class="tim-row bordec-transparent">
                            <div class="area-line">
                                <h2>Gestion de usuarios</h2>
                            </div>
                        </div>

                        <!-- post row -->
                        <div class="tim-row bordec">
                            <h2> Tooltips</h2>
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
                        <!-- end row -->

                    </div>
                    <!-- end container -->
            </div>
        </div>
    </div>
</div>
</#macro>
