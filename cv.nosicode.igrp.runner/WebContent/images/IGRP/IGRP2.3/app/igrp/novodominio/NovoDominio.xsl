<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
    <xsl:template match="/">
        <html>
            <head>
                <xsl:call-template name="IGRP-head"/>
                <!-- TOOLSBAR CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/toolsbar/toolsbar.css"/>
                <!-- FORM CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/form/igrp.forms.css"/>
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
                                    <div class="row">
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
                                                <xsl:if test="rows/content/toolsbar_1">
                                                    <div class="toolsbar-holder default gen-container-item " gen-structure="toolsbar" gen-fields=".btns-holder a.btn" gen-class="" item-name="toolsbar_1">
                                                        <div class="btns-holder   pull-right" role="group">
                                                            <xsl:apply-templates select="rows/content/toolsbar_1" mode="gen-buttons">
                                                                <xsl:with-param name="vertical" select="'true'"/>
                                                            </xsl:apply-templates>
                                                        </div>
                                                    </div>
                                                </xsl:if>
                                                <xsl:if test="rows/content/form_1">
                                                    <div class="box igrp-forms gen-container-item " gen-class="" item-name="form_1">
                                                        <div class="box-body">
                                                            <div role="form">
                                                                <xsl:apply-templates mode="form-hidden-fields" select="rows/content/form_1/fields"/>
                                                                <xsl:if test="rows/content/form_1/fields/dominio">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="dominio" item-type="text" required="required">
                                                                        <label for="{rows/content/form_1/fields/dominio/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/dominio/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/dominio/value}" class="form-control " id="{rows/content/form_1/fields/dominio/@name}" name="{rows/content/form_1/fields/dominio/@name}" required="required" maxlength="30" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/valor">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="valor" item-type="text" required="required">
                                                                        <label for="{rows/content/form_1/fields/valor/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/valor/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/valor/value}" class="form-control " id="{rows/content/form_1/fields/valor/@name}" name="{rows/content/form_1/fields/valor/@name}" required="required" maxlength="30" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/significado">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="significado" item-type="text" required="required">
                                                                        <label for="{rows/content/form_1/fields/significado/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/significado/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/significado/value}" class="form-control " id="{rows/content/form_1/fields/significado/@name}" name="{rows/content/form_1/fields/significado/@name}" required="required" maxlength="30" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/tipo">
                                                                    <div class="col-md-3 form-group  gen-fields-holder" item-name="tipo" item-type="select">
                                                                        <label for="{rows/content/form_1/fields/tipo/@name}">
                                                                            <xsl:value-of select="rows/content/form_1/fields/tipo/label"/>
                                                                        </label>
                                                                        <select class="form-control select2 " id="form_1_tipo" name="{rows/content/form_1/fields/tipo/@name}">
                                                                            <xsl:for-each select="rows/content/form_1/fields/tipo/list/option">
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
                                                                <xsl:if test="rows/content/form_1/fields/tabela_view">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="tabela_view" item-type="text">
                                                                        <label for="{rows/content/form_1/fields/tabela_view/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/tabela_view/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/tabela_view/value}" class="form-control " id="{rows/content/form_1/fields/tabela_view/@name}" name="{rows/content/form_1/fields/tabela_view/@name}" maxlength="30" placeholder=""></input>
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
                                </div>
                            </div>
                        </div>
                    </div>
                    <xsl:call-template name="IGRP-bottom"/>
                </form>
                <!-- FORM JS INCLUDES -->
                <script type="text/javascript" src="{$path}/core/igrp/form/igrp.forms.js"/>
                <!-- SELECT JS INCLUDES -->
                <script type="text/javascript" src="{$path}/plugins/select2/select2.full.min.js"/>
                <script type="text/javascript" src="{$path}/plugins/select2/select2.init.js"/>
            </body>
        </html>
    </xsl:template>
    <xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1493309821284"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1493309821284"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1493309821284"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1493309821284"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1493309821284"/>
</xsl:stylesheet>
