<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-system="about:legacy-compat"/>
  <xsl:template match="/">
    <html>
      <head>
        <xsl:call-template name="IGRP-head"/>
        <!-- TOOLSBAR CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/toolsbar/toolsbar.css"/>
        <!-- FORM CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/form/igrp.forms.css"/>
        <!-- FORMLIST CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/plugins/formlist/igrp.formlist.css"/>
        <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/igrp.tables.css"/>
        <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/dataTables.bootstrap.css"/>
        <!-- SELECT CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.min.css"/>
        <link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.style.css"/>
        <!-- DATE CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/plugins/datetimepicker/css/datetimepicker.css"/>
        <!-- IMG CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/plugins/croppie/croppie.css"/>
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
                  <div class="row" id="row-c733f24c">
                    <div class="gen-column col-md-12">
                      <div class="gen-inner">
                       <div class="igrp-msg-wrapper">
                       </div>
                        <xsl:apply-templates mode="igrp-messages" select="rows/content/messages"/>
                        <xsl:if test="rows/content/toolsbar_1">
                          <div class="toolsbar-holder boxed gen-container-item " gen-structure="toolsbar" gen-fields=".btns-holder a.btn" gen-class="" item-name="toolsbar_1">
                            <div class="btns-holder  pull-right" role="group">
                              <xsl:apply-templates select="rows/content/toolsbar_1" mode="gen-buttons">
                                <xsl:with-param name="vertical" select="'true'"/>
                                <xsl:with-param name="outline" select="'false'"/>
                              </xsl:apply-templates>
                            </div>
                          </div>
                        </xsl:if>
                        <xsl:if test="rows/content/form_1">
                          <div class="box igrp-forms gen-container-item " gen-class="" item-name="form_1">
                            <div class="box-body">
                              <div role="form">
                                <xsl:apply-templates mode="form-hidden-fields" select="rows/content/form_1/fields"/>
                                <xsl:if test="rows/content/form_1/fields/identificacao">
                                  <div class="box-head subtitle gen-fields-holder" text-color="1">
                                    <span>
                                      <xsl:value-of select="rows/content/form_1/fields/identificacao/label"/>
                                    </span>
                                  </div>
                                </xsl:if>
                                <xsl:if test="rows/content/form_1/fields/tipo_objeto">
                                  <div class="col-sm-3 form-group  gen-fields-holder" item-name="tipo_objeto" item-type="select" required="required">
                                    <label for="{rows/content/form_1/fields/tipo_objeto/@name}">
                                      <xsl:value-of select="rows/content/form_1/fields/tipo_objeto/label"/>
                                    </label>
                                    <select class="form-control select2 IGRP_change" id="form_1_tipo_objeto" name="{rows/content/form_1/fields/tipo_objeto/@name}" required="required">
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
                                <xsl:if test="rows/content/form_1/fields/data_de_registo">
                                  <div class="form-group col-sm-3  gen-fields-holder" item-name="data_de_registo" item-type="date" required="required">
                                    <label for="{rows/content/form_1/fields/data_de_registo/@name}">
                                      <span>
                                        <xsl:value-of select="rows/content/form_1/fields/data_de_registo/label"/>
                                      </span>
                                    </label>
                                    <div class="input-group">
                                      <input type="text" value="{rows/content/form_1/fields/data_de_registo/value}" class="form-control gen-date " id="form_1-data_de_registo" name="{rows/content/form_1/fields/data_de_registo/@name}" required="required" format="IGRP_datePicker" maxlength="30">
                                        <xsl:call-template name="setAttributes">
                                          <xsl:with-param name="field" select="rows/content/form_1/fields/data_de_registo"/>
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
                                <xsl:if test="rows/content/form_1/fields/descricao">
                                  <div class="form-group col-sm-6   gen-fields-holder" item-name="descricao" item-type="text">
                                    <label for="{rows/content/form_1/fields/descricao/@name}">
                                      <span>
                                        <xsl:value-of select="rows/content/form_1/fields/descricao/label"/>
                                      </span>
                                    </label>
                                    <input type="text" value="{rows/content/form_1/fields/descricao/value}" class="form-control " id="{rows/content/form_1/fields/descricao/@name}" name="{rows/content/form_1/fields/descricao/@name}" maxlength="100" placeholder="">
                                      <xsl:call-template name="setAttributes">
                                        <xsl:with-param name="field" select="rows/content/form_1/fields/descricao"/>
                                      </xsl:call-template>
                                    </input>
                                  </div>
                                </xsl:if>
                                <xsl:if test="rows/content/form_1/fields/adicionar_ficheiro">
                                  <div class="box-head subtitle gen-fields-holder" text-color="1">
                                    <span>
                                      <xsl:value-of select="rows/content/form_1/fields/adicionar_ficheiro/label"/>
                                    </span>
                                  </div>
                                </xsl:if>
                                <xsl:if test="rows/content/form_1/fields/imagem">
                                  <div class="col-sm-12  holder-croppie gen-fields-holder" item-name="imagem">
                                    <div class="row">
                                      <img src="{rows/content/form_1/fields/imagem/value}" data-label="{rows/content/form_1/fields/imagem/label}" name="imagem" id="id-imagem" width="500" height="400" class=" croppie"/>
                                    </div>
                                  </div>
                                </xsl:if>
                              </div>
                            </div>
                            <xsl:apply-templates select="rows/content/form_1/tools-bar" mode="form-buttons"/>
                          </div>
                        </xsl:if>
                        <xsl:if test="rows/content/formlist_1">
                          <div class="box box-table-contents gen-container-item " gen-class="" item-name="formlist_1">
                            <div class="box-body table-box">
                              <xsl:apply-templates mode="form-hidden-fields" select="rows/content/formlist_1/fields"/>
                              <table id="formlist_1" class="table table-striped gen-data-table IGRP_formlist  " rel="T_formlist_1" data-control="data-formlist_1">
                                <thead>
                                  <tr>
                                    <xsl:if test="rows/content/formlist_1/fields/campo">
                                      <th align="" class=" gen-fields-holder">
                                        <span>
                                          <xsl:value-of select="rows/content/formlist_1/fields/campo/label"/>
                                        </span>
                                      </th>
                                    </xsl:if>
                                    <xsl:if test="rows/content/formlist_1/fields/valor">
                                      <th align="" class=" gen-fields-holder">
                                        <span>
                                          <xsl:value-of select="rows/content/formlist_1/fields/valor/label"/>
                                        </span>
                                      </th>
                                    </xsl:if>
                                    <xsl:if test="not(rows/content/formlist_1/table/value/row[position() = 1]/@noupdate) or not(rows/content/formlist_1/table/value/row[position() = 1]/@nodelete)">
                                      <th class="table-btn add">
                                        <xsl:if test="not(rows/content/formlist_1/table/value/row[position() = 1]/@noupdate)">
                                          <a class="formlist-row-add" rel="formlist_1">
                                            <i class="fa fa-plus"/>
                                          </a>
                                        </xsl:if>
                                      </th>
                                    </xsl:if>
                                  </tr>
                                </thead>
                                <tbody>
                                  <xsl:for-each select="rows/content/formlist_1/table/value/row">
                                    <tr row="{position()}">
                                      <input type="hidden" name="p_formlist_1_id" value="{formlist_1_id}"/>
                                      <xsl:if test="campo">
                                        <xsl:if test="not(campo/@visible)">
                                          <td align="" data-row="{position()}" data-title="{../../fields/campo/label}" class="plaintext" item-name="campo">
                                            <input type="hidden" name="{../../../fields/campo/@name}_fk_desc" value="{campo_desc}"/>
                                            <input type="hidden" name="{../../../fields/campo/@name}_fk" value="{campo}" class="plaintext form-control" rel="F_formlist_1"/>
                                            <p item-name="campo" item-type="plaintext">
                                              <xsl:value-of select="campo"/>
                                            </p>
                                          </td>
                                        </xsl:if>
                                      </xsl:if>
                                      <xsl:if test="valor">
                                        <xsl:if test="not(valor/@visible)">
                                          <td align="" data-row="{position()}" data-title="{../../fields/valor/label}" class="text" item-name="valor">
                                            <input type="hidden" name="{../../../fields/valor/@name}_fk_desc" value="{valor_desc}"/>
                                            <div class="form-group" item-name="valor" item-type="text">
                                              <input type="text" name="{../../../fields/valor/@name}_fk" value="{valor}" class="text form-control" rel="F_formlist_1">
                                                <xsl:call-template name="setAttributes">
                                                  <xsl:with-param name="field" select="rows/content/formlist_1/fields/valor"/>
                                                </xsl:call-template>
                                              </input>
                                            </div>
                                          </td>
                                        </xsl:if>
                                      </xsl:if>
                                      <xsl:if test="not(@nodelete) or not(@noupdate)">
                                        <td class="table-btn delete" data-row="{position()}">
                                          <xsl:if test="not(@nodelete)">
                                            <span class="formlist-row-remove btn btn-danger" rel="formlist_1">
                                              <i class="fa fa-times"/>
                                            </span>
                                          </xsl:if>
                                        </td>
                                      </xsl:if>
                                    </tr>
                                  </xsl:for-each>
                                </tbody>
                              </table>
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
        <!-- FORMLIST JS INCLUDES -->
        <script type="text/javascript" src="{$path}/core/igrp/table/igrp.table.js"/>
        <script type="text/javascript" src="{$path}/plugins/formlist/igrp.formlist.js"/>
        <!-- SELECT JS INCLUDES -->
        <script type="text/javascript" src="{$path}/plugins/select2/select2.full.min.js"/>
        <script type="text/javascript" src="{$path}/plugins/select2/select2.init.js"/>
        <!-- DATE JS INCLUDES -->
        <script type="text/javascript" src="{$path}/plugins/datetimepicker/js/datetimepicker.js"/>
        <script type="text/javascript" src="{$path}/plugins/datetimepicker/js/dtp.init.js"/>
        <!-- IMG JS INCLUDES -->
        <script type="text/javascript" src="{$path}/plugins/croppie/croppie.min.js"/>
        <script type="text/javascript" src="{$path}/plugins/croppie/croppie.js"/>
      </body>
    </html>
  </xsl:template>
  <xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1505222428960"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1505222428960"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1505222428960"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1505222428960"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1505222428960"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-table-utils.tmpl.xsl?v=1505222428960"/>
</xsl:stylesheet>
