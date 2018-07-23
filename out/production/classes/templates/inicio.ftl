<#include "base.ftl">

<#macro page_head>
<title>Inicio</title>
</#macro>

<#macro page_body>
<div class="main">
    <div class="section section-nude">
        <div class="container">
            <div class="fondo fixed-bar">
                <ul>
                    <li><a href="#buttons-row">Buttons</a></li>
                    <li><a href="#checkbox-row">Checkbox/Radio</a></li>
                    <li><a href="#dropdown-row">Dropdown</a></li>
                    <li><a href="#inputs-row">Inputs</a></li>
                    <li><a href="#textarea-row">Textarea</a></li>
                    <li><a href="#navbar-row">Navigation</a></li>
                    <li><a href="#subscription-row">Footers</a></li>
                    <li><a href="#pagination-row">Pagination</a></li>
                    <li><a href="#progressbar-row">Progress Bars</a></li>
                    <li><a href="#sliders-row">Sliders</a></li>
                    <li><a href="#labels-row">Labels</a></li>
                    <li><a href="#datepicker-row">Datepicker</a></li>
                    <li><a href="#modal-row">Modals</a></li>
                    <li><a href="#tooltip-row">Tooltips/Popovers</a></li>
                    <li><a href="#notification-row">Notification</a></li>

                </ul>
            </div>
            <div class="row">
                <div class="leftcolumn">
                    <br/>
                </div>
                <div class="rightcolumn">
                    <div class="tim-container">
                        <div class="tim-row bordec">
                            <center>
                            <a href="/prueba" class="btn btn-primary btn-lg">
                            <i class="fa fa-plus"></i> Agregar Post
                            </a>
                            </center>
                        </div>

                        <div class="tim-row">
                            <div class="area-line">
                                <h2>Ultimos Posts</h2>
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
