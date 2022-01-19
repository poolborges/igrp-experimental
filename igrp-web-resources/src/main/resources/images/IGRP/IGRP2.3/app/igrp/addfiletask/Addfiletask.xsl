<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" omit-xml-declaration="yes" encoding="utf-8" indent="yes" doctype-system="about:legacy-compat"/>
  <xsl:template match="/"> 
    <html>
      <head>
        <xsl:call-template name="IGRP-head"/>
        <!-- FORMLIST CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/plugins/formlist/igrp.formlist.css?v={$version}"/>
        <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/igrp.tables.css?v={$version}"/>
        <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/dataTables.bootstrap.css?v={$version}"/>
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
                  <div class="row row-msg">
                    <div class="gen-column col-md-12">
                      <div class="gen-inner">
                        <xsl:apply-templates mode="igrp-messages" select="rows/content/messages"/>
                      </div>
                    </div>
                  </div>
                  <div class="row " id="row-2f520e68">
                    <div class="gen-column col-md-12">
                      <div class="gen-inner">
                        <xsl:if test="rows/content/formlist_documento_task">
                          <div class="box box-table-contents gen-container-item " gen-class="" item-name="formlist_documento_task">
                            <div class="box-body table-box">
                              <xsl:apply-templates mode="form-hidden-fields" select="rows/content/formlist_documento_task/fields"/>
                              <table id="formlist_documento_task" class="table table-striped gen-data-table  IGRP_formlist noupdate nodelete" rel="T_formlist_documento_task" data-control="data-formlist_documento_task">
                                <thead>
                                  <tr>
                                    <xsl:if test="rows/content/formlist_documento_task/fields/formlist_documento_task_nome">
                                      <th align="" class=" gen-fields-holder">
                                        <span>
                                          <xsl:value-of select="rows/content/formlist_documento_task/fields/formlist_documento_task_nome/label"/>
                                        </span>
                                      </th>
                                    </xsl:if>
                                    <xsl:if test="rows/content/formlist_documento_task/fields/formlist_documento_task_descricao">
                                      <th align="" class=" gen-fields-holder">
                                        <span>
                                          <xsl:value-of select="rows/content/formlist_documento_task/fields/formlist_documento_task_descricao/label"/>
                                        </span>
                                      </th>
                                    </xsl:if>
                                    <xsl:if test="rows/content/formlist_documento_task/fields/formlist_documento_task_obrigatoriedade">
                                      <th align="" class=" gen-fields-holder">
                                        <span>
                                          <xsl:value-of select="rows/content/formlist_documento_task/fields/formlist_documento_task_obrigatoriedade/label"/>
                                        </span>
                                      </th>
                                    </xsl:if>
                                    <xsl:if test="rows/content/formlist_documento_task/fields/formlist_documento_task_documento">
                                      <th align="" class=" gen-fields-holder">
                                        <span>
                                          <xsl:value-of select="rows/content/formlist_documento_task/fields/formlist_documento_task_documento/label"/>
                                        </span>
                                      </th>
                                    </xsl:if>
                                    <xsl:if test="rows/content/formlist_documento_task/fields/formlist_documento_task_mostrar">
                                      <th align="" class=" gen-fields-holder"/>
                                    </xsl:if>
                                    <xsl:if test="not(rows/content/formlist_documento_task/table/value/row[position() = 1]/@noupdate) or not(rows/content/formlist_documento_task/table/value/row[position() = 1]/@nodelete)">
                                      <th class="table-btn add">
                                        <xsl:if test="not(rows/content/formlist_documento_task/table/value/row[position() = 1]/@noupdate)">
                                          <a class="formlist-row-add btn btn-primary" rel="formlist_documento_task">
                                            <i class="fa fa-plus"/>
                                          </a>
                                        </xsl:if>
                                      </th>
                                    </xsl:if>
                                  </tr>
                                </thead>
                                <tbody>
                                  <xsl:for-each select="rows/content/formlist_documento_task/table/value/row">
                                    <tr row="{position()}">
                                      <input type="hidden" name="p_formlist_documento_task_id" value="{formlist_documento_task_id}"/>
                                      <input type="hidden" name="p_formlist_documento_id_tp_doc_fk" value="{formlist_documento_id_tp_doc}"/>
                                      <input type="hidden" name="p_formlist_documento_id_tp_doc_fk_desc" value="{formlist_documento_id_tp_doc_desc}"/>
                                      <xsl:if test="formlist_documento_task_nome">
                                        <xsl:if test="not(formlist_documento_task_nome/@visible)">
                                          <td align="" data-row="{position()}" data-title="{../../fields/formlist_documento_task_nome/label}" class="text" item-name="formlist_documento_task_nome">
                                            <input type="hidden" name="{../../../fields/formlist_documento_task_nome/@name}_fk_desc" value="{formlist_documento_task_nome_desc}"/>
                                            <div class="form-group" item-name="formlist_documento_task_nome" item-type="text">
                                              <input type="text" name="{../../../fields/formlist_documento_task_nome/@name}_fk" value="{formlist_documento_task_nome}" class="text form-control" rel="F_formlist_documento_task" readonly="readonly">
                                                <xsl:call-template name="setAttributes">
                                                  <xsl:with-param name="field" select="rows/content/formlist_documento_task/fields/formlist_documento_task_nome"/>
                                                </xsl:call-template>
                                              </input>
                                            </div>
                                          </td>
                                        </xsl:if>
                                      </xsl:if>
                                      <xsl:if test="formlist_documento_task_descricao">
                                        <xsl:if test="not(formlist_documento_task_descricao/@visible)">
                                          <td align="" data-row="{position()}" data-title="{../../fields/formlist_documento_task_descricao/label}" class="text" item-name="formlist_documento_task_descricao">
                                            <input type="hidden" name="{../../../fields/formlist_documento_task_descricao/@name}_fk_desc" value="{formlist_documento_task_descricao_desc}"/>
                                            <div class="form-group" item-name="formlist_documento_task_descricao" item-type="text">
                                              <input type="text" name="{../../../fields/formlist_documento_task_descricao/@name}_fk" value="{formlist_documento_task_descricao}" class="text form-control" rel="F_formlist_documento_task" readonly="readonly">
                                                <xsl:call-template name="setAttributes">
                                                  <xsl:with-param name="field" select="rows/content/formlist_documento_task/fields/formlist_documento_task_descricao"/>
                                                </xsl:call-template>
                                              </input>
                                            </div>
                                          </td>
                                        </xsl:if>
                                      </xsl:if>
                                      <xsl:if test="formlist_documento_task_obrigatoriedade">
                                        <xsl:if test="not(formlist_documento_task_obrigatoriedade/@visible)">
                                          <td align="" data-row="{position()}" data-title="{../../fields/formlist_documento_task_obrigatoriedade/label}" class="text" item-name="formlist_documento_task_obrigatoriedade">
                                            <input type="hidden" name="{../../../fields/formlist_documento_task_obrigatoriedade/@name}_fk_desc" value="{formlist_documento_task_obrigatoriedade_desc}"/>
                                            <div class="form-group" item-name="formlist_documento_task_obrigatoriedade" item-type="text">
                                              <input type="text" name="{../../../fields/formlist_documento_task_obrigatoriedade/@name}_fk" value="{formlist_documento_task_obrigatoriedade}" class="text form-control" rel="F_formlist_documento_task" readonly="readonly">
                                                <xsl:call-template name="setAttributes">
                                                  <xsl:with-param name="field" select="rows/content/formlist_documento_task/fields/formlist_documento_task_obrigatoriedade"/>
                                                </xsl:call-template>
                                              </input>
                                            </div>
                                          </td>
                                        </xsl:if>
                                      </xsl:if>
                                      <xsl:if test="formlist_documento_task_documento">
                                        <xsl:if test="not(formlist_documento_task_documento/@visible)">
                                          <td align="" data-row="{position()}" data-title="{../../../fields/formlist_documento_task_documento/label}" class="file" item-name="formlist_documento_task_documento">
                                            <input type="hidden" name="{../../../fields/formlist_documento_task_documento/@name}_fk_desc" value="{formlist_documento_task_documento_desc}"/>
                                            <div class="form-group" item-name="formlist_documento_task_documento" item-type="file">
                                              <div class="input-group">
                                                <input type="text" class="form-control form-hidden" readonly=""/>
                                                <span class="input-group-btn">
                                                  <span class="btn btn-default file-btn-holder">
                                                    <i class="fa fa-upload"/>
                                                    <input id="{../../../fields/formlist_documento_task_documento/@name}_fk" name="{../../../fields/formlist_documento_task_documento/@name}_fk" value="{formlist_documento_task_documento}" class="transparent" type="file" accept="" rel="F_formlist_documento_task">
                                                      <xsl:call-template name="setAttributes">
                                                        <xsl:with-param name="field" select="rows/content/formlist_documento_task/fields/formlist_documento_task_documento"/>
                                                      </xsl:call-template>
                                                    </input>
                                                  </span>
                                                </span>
                                              </div>
                                            </div>
                                          </td>
                                        </xsl:if>
                                      </xsl:if>
                                      <xsl:if test="formlist_documento_task_mostrar">
                                        <xsl:if test="not(formlist_documento_task_mostrar/@visible)">
                                          <td align="" data-row="{position()}" data-title="{../../../fields/formlist_documento_task_mostrar/label}" class="link" item-name="formlist_documento_task_mostrar">
                                            <input type="hidden" name="{../../../fields/formlist_documento_task_mostrar/@name}_fk_desc" value="{formlist_documento_task_mostrar_desc}"/>
                                            <input type="hidden" name="{../../../fields/formlist_documento_task_mostrar/@name}_fk" value="{formlist_documento_task_mostrar}" rel="F_formlist_documento_task"/>
                                            <xsl:choose>
                                              <xsl:when test="formlist_documento_task_mostrar != ''">
                                                <a href="{formlist_documento_task_mostrar}" name="p_formlist_documento_task_mostrar" class="link  btn btn-link form-link" target-fields="" target="_newtab" request-fields="">
                                                  <i class="fa fa-link"/>
                                                  <span>
                                                    <xsl:value-of select="formlist_documento_task_mostrar_desc"/>
                                                  </span>
                                                </a>
                                              </xsl:when>
                                              <xsl:otherwise>
                                                <xsl:value-of select="formlist_documento_task_mostrar_desc"/>
                                              </xsl:otherwise>
                                            </xsl:choose>
                                          </td>
                                        </xsl:if>
                                      </xsl:if>
                                      <xsl:if test="not(@nodelete) or not(@noupdate)">
                                        <td class="table-btn delete" data-row="{position()}">
                                          <xsl:if test="not(@nodelete)">
                                            <span class="formlist-row-remove btn btn-danger" rel="formlist_documento_task">
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
        <!-- FORMLIST JS INCLUDES -->
        <script type="text/javascript" src="{$path}/core/igrp/table/igrp.table.js?v={$version}"/>
        <script type="text/javascript" src="{$path}/plugins/formlist/igrp.formlist.js?v={$version}"/>
        <script type="text/javascript" src="{$path}/core/igrp/form/igrp.forms.js?v={$version}"/>
      </body>
    </html>
  </xsl:template>
  <xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1530274911268"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1530274911268"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1530274911268"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1530274911268"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1530274911268"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-table-utils.tmpl.xsl?v=1530274911268"/>
</xsl:stylesheet>
