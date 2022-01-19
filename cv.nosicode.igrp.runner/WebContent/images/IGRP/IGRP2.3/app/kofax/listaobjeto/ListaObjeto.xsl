<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-system="about:legacy-compat"/>
  <xsl:template match="/">
    <html>
      <head>
        <xsl:call-template name="IGRP-head"/>
        <!-- FORM CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/form/igrp.forms.css"/>
        <!-- TOOLSBAR CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/toolsbar/toolsbar.css"/>
        <!-- TABLE CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/igrp.tables.css"/>
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
                  <div class="row" id="row-371b0df0">
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
                                <xsl:if test="rows/content/form_1/fields/objecto_pesquisar">
                                  <div class="form-group col-sm-3   gen-fields-holder" item-name="objecto_pesquisar" item-type="text">
                                    <label for="{rows/content/form_1/fields/objecto_pesquisar/@name}">
                                      <span>
                                        <xsl:value-of select="rows/content/form_1/fields/objecto_pesquisar/label"/>
                                      </span>
                                    </label>
                                    <input type="text" value="{rows/content/form_1/fields/objecto_pesquisar/value}" class="form-control " id="{rows/content/form_1/fields/objecto_pesquisar/@name}" name="{rows/content/form_1/fields/objecto_pesquisar/@name}" maxlength="30" placeholder="Pesquisar Objecto">
                                      <xsl:call-template name="setAttributes">
                                        <xsl:with-param name="field" select="rows/content/form_1/fields/objecto_pesquisar"/>
                                      </xsl:call-template>
                                    </input>
                                  </div>
                                </xsl:if>
                              </div>
                            </div>
                            <xsl:apply-templates select="rows/content/form_1/tools-bar" mode="form-buttons"/>
                          </div>
                        </xsl:if>
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
                        <xsl:if test="rows/content/table_1">
                          <div class="box box-table-contents gen-container-item " gen-class="" item-name="table_1">
                            <div class="box-body table-box">
                              <xsl:apply-templates mode="form-hidden-fields" select="rows/content/table_1/fields"/>
                              <table id="table_1" class="table table-striped gen-data-table IGRP_contextmenu" exports="null">
                                <thead>
                                  <tr>
                                    <xsl:if test="rows/content/table_1/fields/objeto">
                                      <th align="left" class=" gen-fields-holder">
                                        <span>
                                          <xsl:value-of select="rows/content/table_1/fields/objeto/label"/>
                                        </span>
                                      </th>
                                    </xsl:if>
                                    <xsl:if test="rows/content/table_1/fields/organica">
                                      <th align="left" class=" gen-fields-holder">
                                        <span>
                                          <xsl:value-of select="rows/content/table_1/fields/organica/label"/>
                                        </span>
                                      </th>
                                    </xsl:if>
                                    <xsl:if test="rows/content/table_1/fields/estado">
                                      <th align="left" class=" gen-fields-holder">
                                        <span>
                                          <xsl:value-of select="rows/content/table_1/fields/estado/label"/>
                                        </span>
                                      </th>
                                    </xsl:if>
                                    <th class="igrp-table-ctx-th"/>
                                  </tr>
                                </thead>
                                <tbody>
                                  <xsl:for-each select="rows/content/table_1/table/value/row[not(@total='yes')]">
                                    <tr>
                                      <xsl:apply-templates mode="context-param" select="context-menu"/>
                                      <input name="p_id_fk" value="{id}" type="hidden"/>
                                      <input name="p_id_fk_desc" value="{id_desc}" type="hidden"/>
                                      <xsl:if test="objeto">
                                        <td align="left" data-row="{position()}" data-title="{../../fields/objeto/label}" class="text" item-name="objeto">
                                          <span class="">
                                            <xsl:value-of select="objeto"/>
                                          </span>
                                        </td>
                                      </xsl:if>
                                      <xsl:if test="organica">
                                        <td align="left" data-row="{position()}" data-title="{../../fields/organica/label}" class="text" item-name="organica">
                                          <span class="">
                                            <xsl:value-of select="organica"/>
                                          </span>
                                        </td>
                                      </xsl:if>
                                      <xsl:if test="estado">
                                        <td align="left" data-row="{position()}" data-title="{../../fields/estado/label}" class="text" item-name="estado">
                                          <span class="">
                                            <xsl:value-of select="estado"/>
                                          </span>
                                        </td>
                                      </xsl:if>
                                      <td class="igrp-table-ctx-td">
                                        <xsl:apply-templates select="../../context-menu" mode="table-context-inline">
                                          <xsl:with-param name="row-params" select="context-menu"/>
                                        </xsl:apply-templates>
                                      </td>
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
        <!-- TABLE JS INCLUDES -->
        <script type="text/javascript" src="{$path}/core/igrp/table/igrp.table.js"/>
        <script type="text/javascript" src="{$path}/core/igrp/table/bootstrap-contextmenu.js"/>
        <script type="text/javascript" src="{$path}/core/igrp/table/table.contextmenu.js"/>
      </body>
    </html>
  </xsl:template>
  <xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1505223142094"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1505223142094"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1505223142094"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1505223142094"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1505223142095"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-table-utils.tmpl.xsl?v=1505223142095"/>
</xsl:stylesheet>
