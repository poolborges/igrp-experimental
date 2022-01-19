<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
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
                  <div class="row" id="row-1ac62efb">
                    <div class="gen-column col-md-12">
                      <div class="gen-inner">
                        <xsl:apply-templates mode="igrp-messages" select="rows/content/messages"/>
                        <xsl:if test="rows/content/form_1">
                          <div class="box igrp-forms gen-container-item " gen-class="" item-name="form_1">
                            <div class="box-body">
                              <div role="form">
                                <xsl:apply-templates mode="form-hidden-fields" select="rows/content/form_1/fields"/>
                                <xsl:if test="rows/content/form_1/fields/tipo_objeto">
                                  <div class="col-sm-3 form-group  gen-fields-holder" item-name="tipo_objeto" item-type="select">
                                    <label for="{rows/content/form_1/fields/tipo_objeto/@name}">
                                      <xsl:value-of select="rows/content/form_1/fields/tipo_objeto/label"/>
                                    </label>
                                    <select class="form-control select2 " id="form_1_tipo_objeto" name="{rows/content/form_1/fields/tipo_objeto/@name}">
                                      <xsl:call-template name="setAttributes">
                                        <xsl:with-param name="field" select="rows/content/form_1/fields/tipo_objeto"/>
                                      </xsl:call-template>
                                      <xsl:for-each select="rows/content/form_1/fields/tipo_objeto/list/option">
                                        <option value="{value}" label="{text}">
                                          <xsl:if test="@selected='true'">
                                            <xsl:attribute name="selected">selected
                                            </xsl:attribute>
                                          </xsl:if>
                                          <span>
                                            <xsl:value-of select="text"/>
                                          </span>
                                        </option>
                                      </xsl:for-each>
                                    </select>
                                  </div>
                                </xsl:if>
                                <xsl:if test="rows/content/form_1/fields/data_de">
                                  <div class="form-group col-sm-3  gen-fields-holder" item-name="data_de" item-type="date">
                                    <label for="{rows/content/form_1/fields/data_de/@name}">
                                      <span>
                                        <xsl:value-of select="rows/content/form_1/fields/data_de/label"/>
                                      </span>
                                    </label>
                                    <div class="input-group">
                                      <input type="text" value="{rows/content/form_1/fields/data_de/value}" class="form-control gen-date " id="form_1-data_de" name="{rows/content/form_1/fields/data_de/@name}" format="IGRP_datePicker" maxlength="30">
                                        <xsl:call-template name="setAttributes">
                                          <xsl:with-param name="field" select="rows/content/form_1/fields/data_de"/>
                                        </xsl:call-template>
                                      </input>
                                      <span class="input-group-btn gen-date-icon">
                                        <span class="btn btn-default">
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
                                        <span class="btn btn-default">
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
                      </div>
                    </div>
                  </div>
                  <div class="row" id="row-6005a3d9">
                    <div class="gen-column col-sm-3">
                      <div class="gen-inner">
                        <xsl:if test="rows/content/statbox_1">
                          <div class="statbox_container gen-container-item " gen-class="" item-name="statbox_1">
                            <xsl:apply-templates mode="form-hidden-fields" select="rows/content/statbox_1/fields"/>
                            <div class="statbox {rows/content/statbox_1/fields/statbox_1_bg/value}">
                              <div class="boxchart">
                                <canvas/>
                              </div>
                              <div class="number">
                                <xsl:value-of select="rows/content/statbox_1/fields/statbox_1_val/value"/>
                                <i class="fa {rows/content/statbox_1/fields/statbox_1_icn/value}" aria-hidden="true"/>
                              </div>
                              <div class="title">
                                <xsl:value-of select="rows/content/statbox_1/fields/statbox_1_tit/value"/>
                              </div>
                              <div class="footer">
                                <a href="{rows/content/statbox_1/fields/statbox_1_url/value}" target="modal">
                                  <xsl:value-of select="rows/content/statbox_1/fields/statbox_1_txt/value"/>
                                </a>
                              </div>
                            </div>
                          </div>
                        </xsl:if>
                      </div>
                    </div>
                    <div class="gen-column col-sm-9">
                      <div class="gen-inner">
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
                                <xsl:with-param name="title" select="'Chart'"/>
                                <xsl:with-param name="url" select="rows/content/chart_1/url"/>
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
  <xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1505240083168"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1505240083168"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1505240083168"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1505240083168"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1505240083168"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-charts.tmpl.xsl?v=1505240083168"/>
</xsl:stylesheet>
