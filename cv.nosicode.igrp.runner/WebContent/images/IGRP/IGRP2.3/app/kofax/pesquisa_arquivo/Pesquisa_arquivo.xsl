<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-system="about:legacy-compat"/>
    <xsl:template match="/">
        <html>
            <head>
                <xsl:call-template name="IGRP-head"/>
                <!-- FORM CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/form/igrp.forms.css"/>
                <!-- TABLE CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/igrp.tables.css"/>
                <!-- SELECT CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.min.css"/>
                <link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.style.css"/>
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
                                    <div class="row" id="row-09ede0a9">
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
                                                                <xsl:if test="rows/content/form_1/fields/tipo_de_objecto">
                                                                    <div class="col-sm-3 form-group  gen-fields-holder" item-name="tipo_de_objecto" item-type="select">
                                                                        <label for="{rows/content/form_1/fields/tipo_de_objecto/@name}">
                                                                            <xsl:value-of select="rows/content/form_1/fields/tipo_de_objecto/label"/>
                                                                        </label>
                                                                        <select class="form-control select2 IGRP_change" id="form_1_tipo_de_objecto" name="{rows/content/form_1/fields/tipo_de_objecto/@name}">
                                                                            <xsl:call-template name="setAttributes">
                                                                                <xsl:with-param name="field" select="rows/content/form_1/fields/tipo_de_objecto"/>
                                                                            </xsl:call-template>
                                                                            <xsl:for-each select="rows/content/form_1/fields/tipo_de_objecto/list/option">
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
                                                                <xsl:if test="rows/content/form_1/fields/campo">
                                                                    <div class="col-sm-3 form-group  gen-fields-holder" item-name="campo" item-type="select">
                                                                        <label for="{rows/content/form_1/fields/campo/@name}">
                                                                            <xsl:value-of select="rows/content/form_1/fields/campo/label"/>
                                                                        </label>
                                                                        <select class="form-control select2 " id="form_1_campo" name="{rows/content/form_1/fields/campo/@name}">
                                                                            <xsl:call-template name="setAttributes">
                                                                                <xsl:with-param name="field" select="rows/content/form_1/fields/campo"/>
                                                                            </xsl:call-template>
                                                                            <xsl:for-each select="rows/content/form_1/fields/campo/list/option">
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
                                                                <xsl:if test="rows/content/form_1/fields/valor">
                                                                    <div class="form-group col-sm-3  gen-fields-holder" item-name="valor" item-type="textarea">
                                                                        <label for="{rows/content/form_1/fields/valor/@name}">
                                                                            <xsl:value-of select="rows/content/form_1/fields/valor/label"/>
                                                                        </label>
                                                                        <textarea name="{rows/content/form_1/fields/valor/@name}" class="textarea form-control " maxlength="30">
                                                                            <xsl:call-template name="setAttributes">
                                                                                <xsl:with-param name="field" select="rows/content/form_1/fields/valor"/>
                                                                            </xsl:call-template>
                                                                            <xsl:value-of select="rows/content/form_1/fields/valor/value"/>
                                                                        </textarea>
                                                                    </div>
                                                                </xsl:if>
                                                            </div>
                                                        </div>
                                                        <xsl:apply-templates select="rows/content/form_1/tools-bar" mode="form-buttons"/>
                                                    </div>
                                                </xsl:if>
                                                <xsl:if test="rows/content/table_1">
                                                    <div class="box box-table-contents gen-container-item " gen-class="" item-name="table_1">
                                                        <div class="box-body table-box">
                                                            <xsl:apply-templates mode="form-hidden-fields" select="rows/content/table_1/fields"/>
                                                            <table id="table_1" class="table table-striped gen-data-table " exports="">
                                                                <thead>
                                                                    <tr>
                                                                        <xsl:if test="rows/content/table_1/fields/tipo_de_objecto">
                                                                            <th align="left" class=" gen-fields-holder">
                                                                                <span>
                                                                                    <xsl:value-of select="rows/content/table_1/fields/tipo_de_objecto/label"/>
                                                                                </span>
                                                                            </th>
                                                                        </xsl:if>
                                                                        <xsl:if test="rows/content/table_1/fields/descricao">
                                                                            <th align="left" class=" gen-fields-holder">
                                                                                <span>
                                                                                    <xsl:value-of select="rows/content/table_1/fields/descricao/label"/>
                                                                                </span>
                                                                            </th>
                                                                        </xsl:if>
                                                                        <xsl:if test="rows/content/table_1/fields/data_registo">
                                                                            <th align="left" class=" gen-fields-holder">
                                                                                <span>
                                                                                    <xsl:value-of select="rows/content/table_1/fields/data_registo/label"/>
                                                                                </span>
                                                                            </th>
                                                                        </xsl:if>
                                                                        <xsl:if test="rows/content/table_1/fields/link_1">
                                                                            <th align="left" class=" gen-fields-holder"/>
                                                                        </xsl:if>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <xsl:for-each select="rows/content/table_1/table/value/row[not(@total='yes')]">
                                                                        <tr>
                                                                            <xsl:apply-templates mode="context-param" select="context-menu"/>
                                                                            <xsl:if test="tipo_de_objecto">
                                                                                <td align="left" data-row="{position()}" data-title="{../../fields/tipo_de_objecto/label}" class="text" item-name="tipo_de_objecto">
                                                                                    <span class="">
                                                                                        <xsl:value-of select="tipo_de_objecto"/>
                                                                                    </span>
                                                                                </td>
                                                                            </xsl:if>
                                                                            <xsl:if test="descricao">
                                                                                <td align="left" data-row="{position()}" data-title="{../../fields/descricao/label}" class="text" item-name="descricao">
                                                                                    <span class="">
                                                                                        <xsl:value-of select="descricao"/>
                                                                                    </span>
                                                                                </td>
                                                                            </xsl:if>
                                                                            <xsl:if test="data_registo">
                                                                                <td align="left" data-row="{position()}" data-title="{../../fields/data_registo/label}" class="text" item-name="data_registo">
                                                                                    <span class="">
                                                                                        <xsl:value-of select="data_registo"/>
                                                                                    </span>
                                                                                </td>
                                                                            </xsl:if>
                                                                            <xsl:if test="link_1">
                                                                                <td align="left" data-row="{position()}" data-title="{../../label/link_1}" class="link" item-name="link_1">
                                                                                    <xsl:choose>
                                                                                        <xsl:when test="link_1 != ''">
                                                                                            <a href="{link_1}" class="link bClick btn btn-primary " target-fields="" target="_blank" name="link_1">
                                                                                                <i class="fa fa-link"/>
                                                                                                <span>
                                                                                                    <xsl:value-of select="link_1_desc"/>
                                                                                                </span>
                                                                                            </a>
                                                                                        </xsl:when>
                                                                                        <xsl:otherwise>
                                                                                            <span>
                                                                                                <xsl:value-of select="link_1_desc"/>
                                                                                            </span>
                                                                                        </xsl:otherwise>
                                                                                    </xsl:choose>
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
                <!-- TABLE JS INCLUDES -->
                <script type="text/javascript" src="{$path}/core/igrp/table/igrp.table.js"/>
                <!-- SELECT JS INCLUDES -->
                <script type="text/javascript" src="{$path}/plugins/select2/select2.full.min.js"/>
                <script type="text/javascript" src="{$path}/plugins/select2/select2.init.js"/>
            </body>
        </html>
    </xsl:template>
    <xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1505154190638"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1505154190638"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1505154190638"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1505154190638"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1505154190640"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-table-utils.tmpl.xsl?v=1505154190640"/>
</xsl:stylesheet>
