<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-system="about:legacy-compat"/>
    <xsl:template match="/">
        <html>
            <head>
                <xsl:call-template name="IGRP-head"/>
                <!-- FORM CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/form/igrp.forms.css"/>
                <!-- STATBOX CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/plugins/statbox/statbox.css"/>
                <!-- CHART CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/plugins/highcharts/igrp.charts.css"/>
                <!-- SELECT CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.min.css"/>
                <link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.style.css"/>
                <!-- DATE CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/plugins/datetimepicker/css/datetimepicker.css"/>
                <style/>
            </head>
            <body class="{$bodyClass} sidebar-off">
                <xsl:call-template name="IGRP-topmenu"/>
                <form method="POST" class="IGRP-form" name="formular_default" enctype="multipart/form-data">
                    <div class="container-fluid">
                        <div class="row">
                            <xsl:call-template name="IGRP-sidebar"/>
                            <div class="col-sm-9 col-md-10 col-md-offset-2 col-sm-offset-3 main" id="igrp-contents">
                                <div class="content">
                                    <div class="row" id="row-78dfa58b">
                                        <div class="gen-column col-md-12">
                                            <div class="gen-inner">
                                                <xsl:if test="rows/content/sectionheader_1">
                                                    <section class="content-header gen-container-item " gen-class="" item-name="sectionheader_1">
                                                        <h2>
                                                            <xsl:value-of select="rows/content/sectionheader_1/fields/sectionheader_1_text/value"/>
                                                        </h2>
                                                    </section>
                                                </xsl:if>
                                                <xsl:apply-templates mode="igrp-messages" select="rows/content/messages"/>
                                                <xsl:if test="rows/content/form_1">
                                                    <div class="box igrp-forms gen-container-item " gen-class="" item-name="form_1">
                                                        <div class="box-body">
                                                            <div role="form">
                                                                <xsl:apply-templates mode="form-hidden-fields" select="rows/content/form_1/fields"/>
                                                                <xsl:if test="rows/content/form_1/fields/entidade">
                                                                    <div class="col-sm-3 form-group  gen-fields-holder" item-name="entidade" item-type="select">
                                                                        <label for="{rows/content/form_1/fields/entidade/@name}">
                                                                            <xsl:value-of select="rows/content/form_1/fields/entidade/label"/>
                                                                        </label>
                                                                        <select class="form-control select2 " id="form_1_entidade" name="{rows/content/form_1/fields/entidade/@name}">
                                                                            <xsl:call-template name="setAttributes">
                                                                                <xsl:with-param name="field" select="rows/content/form_1/fields/entidade"/>
                                                                            </xsl:call-template>
                                                                            <xsl:for-each select="rows/content/form_1/fields/entidade/list/option">
                                                                                <option value="{value}" label="{text}">
                                                                                    <xsl:if test="@selected='true'">
                                                                                        <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                    </xsl:if>
                                                                                    <span>
                                                                                        <xsl:value-of select="text"/>
                                                                                    </span>
                                                                                </option>
                                                                            </xsl:for-each>
                                                                        </select>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/data_de_">
                                                                    <div class="form-group col-sm-3  gen-fields-holder" item-name="data_de_" item-type="date">
                                                                        <label for="{rows/content/form_1/fields/data_de_/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/data_de_/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <div class="input-group">
                                                                            <input type="text" value="{rows/content/form_1/fields/data_de_/value}" class="form-control gen-date " id="form_1-data_de_" name="{rows/content/form_1/fields/data_de_/@name}" format="IGRP_datePicker" maxlength="30">
                                                                                <xsl:call-template name="setAttributes">
                                                                                    <xsl:with-param name="field" select="rows/content/form_1/fields/data_de_"/>
                                                                                </xsl:call-template>
                                                                            </input>
                                                                            <span class="input-group-btn gen-date-icon">
                                                                                <span class="btn btn-primary">
                                                                                    <i class="fa fa-calendar"/>
                                                                                </span>
                                                                            </span>
                                                                        </div>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/data_ate">
                                                                    <div class="form-group col-sm-3  gen-fields-holder" item-name="data_ate" item-type="date">
                                                                        <label for="{rows/content/form_1/fields/data_ate/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/data_ate/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <div class="input-group">
                                                                            <input type="text" value="{rows/content/form_1/fields/data_ate/value}" class="form-control gen-date " id="form_1-data_ate" name="{rows/content/form_1/fields/data_ate/@name}" format="IGRP_datePicker" maxlength="30">
                                                                                <xsl:call-template name="setAttributes">
                                                                                    <xsl:with-param name="field" select="rows/content/form_1/fields/data_ate"/>
                                                                                </xsl:call-template>
                                                                            </input>
                                                                            <span class="input-group-btn gen-date-icon">
                                                                                <span class="btn btn-primary">
                                                                                    <i class="fa fa-calendar"/>
                                                                                </span>
                                                                            </span>
                                                                        </div>
                                                                    </div>
                                                                </xsl:if>
                                                            </div>
                                                        </div>
                                                        <xsl:apply-templates select="rows/content/form_1/tools-bar" mode="form-buttons"/>
                                                    </div>
                                                </xsl:if>
                                                <xsl:if test="rows/content/box_1">
                                                    <div class="box igrp-box-holder gen-container-item " gen-class="" item-name="box_1">
                                                        <xsl:call-template name="box-header">
                                                            <xsl:with-param name="title" select="rows/content/box_1/@title"/>
                                                            <xsl:with-param name="collapsible" select="'true'"/>
                                                        </xsl:call-template>
                                                        <div class="box-body" gen-preserve-content="true">
                                                            <xsl:apply-templates mode="form-hidden-fields" select="rows/content/box_1/fields"/>
                                                            <div>
                                                                <div class="row" id="row-fa1ad49b">
                                                                    <div class="gen-column col-sm-4">
                                                                        <div class="gen-inner">
                                                                            <xsl:if test="rows/content/agenda_solicitada">
                                                                                <div class="statbox_container gen-container-item " gen-class="" item-name="agenda_solicitada">
                                                                                    <xsl:apply-templates mode="form-hidden-fields" select="rows/content/agenda_solicitada/fields"/>
                                                                                    <div class="statbox {rows/content/agenda_solicitada/fields/agenda_solicitada_bg/value}">
                                                                                        <div class="boxchart">
                                                                                            <canvas/>
                                                                                        </div>
                                                                                        <div class="number">
                                                                                            <xsl:value-of select="rows/content/agenda_solicitada/fields/agenda_solicitada_val/value"/>
                                                                                            <i class="fa {rows/content/agenda_solicitada/fields/agenda_solicitada_icn/value}" aria-hidden="true"/>
                                                                                        </div>
                                                                                        <div class="title">
                                                                                            <xsl:value-of select="rows/content/agenda_solicitada/fields/agenda_solicitada_tit/value"/>
                                                                                        </div>
                                                                                        <div class="footer">
                                                                                            <a href="{rows/content/agenda_solicitada/fields/agenda_solicitada_url/value}" target="modal">
                                                                                                <xsl:value-of select="rows/content/agenda_solicitada/fields/agenda_solicitada_txt/value"/>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </xsl:if>
                                                                        </div>
                                                                    </div>
                                                                    <div class="gen-column col-sm-4">
                                                                        <div class="gen-inner">
                                                                            <xsl:if test="rows/content/agendas_confirmadas">
                                                                                <div class="statbox_container gen-container-item " gen-class="" item-name="agendas_confirmadas">
                                                                                    <xsl:apply-templates mode="form-hidden-fields" select="rows/content/agendas_confirmadas/fields"/>
                                                                                    <div class="statbox {rows/content/agendas_confirmadas/fields/agendas_confirmadas_bg/value}">
                                                                                        <div class="boxchart">
                                                                                            <canvas/>
                                                                                        </div>
                                                                                        <div class="number">
                                                                                            <xsl:value-of select="rows/content/agendas_confirmadas/fields/agendas_confirmadas_val/value"/>
                                                                                            <i class="fa {rows/content/agendas_confirmadas/fields/agendas_confirmadas_icn/value}" aria-hidden="true"/>
                                                                                        </div>
                                                                                        <div class="title">
                                                                                            <xsl:value-of select="rows/content/agendas_confirmadas/fields/agendas_confirmadas_tit/value"/>
                                                                                        </div>
                                                                                        <div class="footer">
                                                                                            <a href="{rows/content/agendas_confirmadas/fields/agendas_confirmadas_url/value}" target="modal">
                                                                                                <xsl:value-of select="rows/content/agendas_confirmadas/fields/agendas_confirmadas_txt/value"/>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </xsl:if>
                                                                        </div>
                                                                    </div>
                                                                    <div class="gen-column col-sm-4">
                                                                        <div class="gen-inner">
                                                                            <xsl:if test="rows/content/agendas_realizadas">
                                                                                <div class="statbox_container gen-container-item " gen-class="" item-name="agendas_realizadas">
                                                                                    <xsl:apply-templates mode="form-hidden-fields" select="rows/content/agendas_realizadas/fields"/>
                                                                                    <div class="statbox {rows/content/agendas_realizadas/fields/agendas_realizadas_bg/value}">
                                                                                        <div class="boxchart">
                                                                                            <canvas/>
                                                                                        </div>
                                                                                        <div class="number">
                                                                                            <xsl:value-of select="rows/content/agendas_realizadas/fields/agendas_realizadas_val/value"/>
                                                                                            <i class="fa {rows/content/agendas_realizadas/fields/agendas_realizadas_icn/value}" aria-hidden="true"/>
                                                                                        </div>
                                                                                        <div class="title">
                                                                                            <xsl:value-of select="rows/content/agendas_realizadas/fields/agendas_realizadas_tit/value"/>
                                                                                        </div>
                                                                                        <div class="footer">
                                                                                            <a href="{rows/content/agendas_realizadas/fields/agendas_realizadas_url/value}" target="modal">
                                                                                                <xsl:value-of select="rows/content/agendas_realizadas/fields/agendas_realizadas_txt/value"/>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </xsl:if>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </xsl:if>
                                                <xsl:if test="rows/content/chart_1">
                                                    <div class="box gen-container-item " gen-structure="graphic" gen-class="" item-name="chart_1">
                                                        <xsl:call-template name="box-header">
                                                            <xsl:with-param name="title" select="rows/content/chart_1/@title"/>
                                                            <xsl:with-param name="collapsible" select="'false'"/>
                                                        </xsl:call-template>
                                                        <div class="box-body">
                                                            <xsl:call-template name="igrp-graph">
                                                                <xsl:with-param name="table" select="rows/content/chart_1"/>
                                                                <xsl:with-param name="chart_type" select="rows/content/chart_1/chart_type"/>
                                                                <xsl:with-param name="height" select="'250'"/>
                                                                <xsl:with-param name="title" select="'Agendamentos por balcão'"/>
                                                                <xsl:with-param name="url" select="rows/content/chart_1/url"/>
                                                            </xsl:call-template>
                                                        </div>
                                                    </div>
                                                </xsl:if>
                                                <xsl:if test="rows/content/box_2">
                                                    <div class="box igrp-box-holder gen-container-item " gen-class="" item-name="box_2">
                                                        <div class="box-body" gen-preserve-content="true">
                                                            <xsl:apply-templates mode="form-hidden-fields" select="rows/content/box_2/fields"/>
                                                            <div>
                                                                <div class="row" id="row-44a16d61">
                                                                    <div class="gen-column col-sm-6">
                                                                        <div class="gen-inner">
                                                                            <xsl:if test="rows/content/chart_2">
                                                                                <div class="box gen-container-item " gen-structure="graphic" gen-class="" item-name="chart_2">
                                                                                    <xsl:call-template name="box-header">
                                                                                        <xsl:with-param name="title" select="rows/content/chart_2/@title"/>
                                                                                        <xsl:with-param name="collapsible" select="'false'"/>
                                                                                    </xsl:call-template>
                                                                                    <div class="box-body">
                                                                                        <xsl:call-template name="igrp-graph">
                                                                                            <xsl:with-param name="table" select="rows/content/chart_2"/>
                                                                                            <xsl:with-param name="chart_type" select="rows/content/chart_2/chart_type"/>
                                                                                            <xsl:with-param name="height" select="'250'"/>
                                                                                            <xsl:with-param name="title" select="'Agendamentos por assunto'"/>
                                                                                            <xsl:with-param name="url" select="rows/content/chart_2/url"/>
                                                                                        </xsl:call-template>
                                                                                    </div>
                                                                                </div>
                                                                            </xsl:if>
                                                                        </div>
                                                                    </div>
                                                                    <div class="gen-column col-sm-6">
                                                                        <div class="gen-inner">
                                                                            <xsl:if test="rows/content/chart_3">
                                                                                <div class="box gen-container-item " gen-structure="graphic" gen-class="" item-name="chart_3">
                                                                                    <xsl:call-template name="box-header">
                                                                                        <xsl:with-param name="title" select="rows/content/chart_3/@title"/>
                                                                                        <xsl:with-param name="collapsible" select="'false'"/>
                                                                                    </xsl:call-template>
                                                                                    <div class="box-body">
                                                                                        <xsl:call-template name="igrp-graph">
                                                                                            <xsl:with-param name="table" select="rows/content/chart_3"/>
                                                                                            <xsl:with-param name="chart_type" select="rows/content/chart_3/chart_type"/>
                                                                                            <xsl:with-param name="height" select="'250'"/>
                                                                                            <xsl:with-param name="title" select="'Agendamentos por estado'"/>
                                                                                            <xsl:with-param name="url" select="rows/content/chart_3/url"/>
                                                                                        </xsl:call-template>
                                                                                    </div>
                                                                                </div>
                                                                            </xsl:if>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </xsl:if>
                                                <xsl:if test="rows/content/chart_5">
                                                    <div class="box gen-container-item " gen-structure="graphic" gen-class="" item-name="chart_5">
                                                        <xsl:call-template name="box-header">
                                                            <xsl:with-param name="title" select="rows/content/chart_5/@title"/>
                                                            <xsl:with-param name="collapsible" select="'false'"/>
                                                        </xsl:call-template>
                                                        <div class="box-body">
                                                            <xsl:call-template name="igrp-graph">
                                                                <xsl:with-param name="table" select="rows/content/chart_5"/>
                                                                <xsl:with-param name="chart_type" select="rows/content/chart_5/chart_type"/>
                                                                <xsl:with-param name="height" select="'250'"/>
                                                                <xsl:with-param name="title" select="'Agendamento por Serviço'"/>
                                                                <xsl:with-param name="url" select="rows/content/chart_5/url"/>
                                                            </xsl:call-template>
                                                        </div>
                                                    </div>
                                                </xsl:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <xsl:call-template name="IGRP-bottom"/>
                </form>
                <!-- FORM JS INCLUDES -->
                <script type="text/javascript" src="{$path}/core/igrp/form/igrp.forms.js"/>
                <!-- CHART JS INCLUDES -->
                <script type="text/javascript" src="{$path}/plugins/highcharts/highcharts.js"/>
                <script type="text/javascript" src="{$path}/plugins/highcharts/highcharts-more.js"/>
                <script type="text/javascript" src="{$path}/plugins/highcharts/exporting.js"/>
                <script type="text/javascript" src="{$path}/plugins/highcharts/funnel.js"/>
                <script type="text/javascript" src="{$path}/plugins/highcharts/heatmap.js"/>
                <script type="text/javascript" src="{$path}/plugins/highcharts/treemap.js"/>
                <script type="text/javascript" src="{$path}/plugins/highcharts/igrp.charts.js"/>
                <!-- SELECT JS INCLUDES -->
                <script type="text/javascript" src="{$path}/plugins/select2/select2.full.min.js"/>
                <script type="text/javascript" src="{$path}/plugins/select2/select2.init.js"/>
                <!-- DATE JS INCLUDES -->
                <script type="text/javascript" src="{$path}/plugins/datetimepicker/js/datetimepicker.js"/>
                <script type="text/javascript" src="{$path}/plugins/datetimepicker/js/dtp.init.js"/>
            </body>
        </html>
    </xsl:template>
    <xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1504792075317"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1504792075317"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1504792075317"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1504792075317"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1504792075318"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-charts.tmpl.xsl?v=1504792075318"/>
</xsl:stylesheet>
