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
                                    <div class="row" id="row-a986a81e">
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
                                                                <xsl:if test="rows/content/form_1/fields/conteudo">
                                                                    <div class="form-group col-sm-6   gen-fields-holder" item-name="conteudo" item-type="text">
                                                                        <label for="{rows/content/form_1/fields/conteudo/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/conteudo/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/conteudo/value}" class="form-control " id="{rows/content/form_1/fields/conteudo/@name}" name="{rows/content/form_1/fields/conteudo/@name}" maxlength="30" placeholder="Digite  alguma coisa para pesquisar o Pdf ">
                                                                            <xsl:call-template name="setAttributes">
                                                                                <xsl:with-param name="field" select="rows/content/form_1/fields/conteudo"/>
                                                                            </xsl:call-template>
                                                                        </input>
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
                                                                        <xsl:if test="rows/content/table_1/fields/caminho_do_arquivo">
                                                                            <th align="left" class=" gen-fields-holder">
                                                                                <span>
                                                                                    <xsl:value-of select="rows/content/table_1/fields/caminho_do_arquivo/label"/>
                                                                                </span>
                                                                            </th>
                                                                        </xsl:if>
                                                                        <xsl:if test="rows/content/table_1/fields/tamanho_do_arquivo">
                                                                            <th align="left" class=" gen-fields-holder">
                                                                                <span>
                                                                                    <xsl:value-of select="rows/content/table_1/fields/tamanho_do_arquivo/label"/>
                                                                                </span>
                                                                            </th>
                                                                        </xsl:if>
                                                                        <xsl:if test="rows/content/table_1/fields/arquivo">
                                                                            <th align="left" class=" gen-fields-holder"/>
                                                                        </xsl:if>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <xsl:for-each select="rows/content/table_1/table/value/row[not(@total='yes')]">
                                                                        <tr>
                                                                            <xsl:apply-templates mode="context-param" select="context-menu"/>
                                                                            <xsl:if test="caminho_do_arquivo">
                                                                                <td align="left" data-row="{position()}" data-title="{../../fields/caminho_do_arquivo/label}" class="text" item-name="caminho_do_arquivo">
                                                                                    <span class="">
                                                                                        <xsl:value-of select="caminho_do_arquivo"/>
                                                                                    </span>
                                                                                </td>
                                                                            </xsl:if>
                                                                            <xsl:if test="tamanho_do_arquivo">
                                                                                <td align="left" data-row="{position()}" data-title="{../../fields/tamanho_do_arquivo/label}" class="text" item-name="tamanho_do_arquivo">
                                                                                    <span class="">
                                                                                        <xsl:value-of select="tamanho_do_arquivo"/>
                                                                                    </span>
                                                                                </td>
                                                                            </xsl:if>
                                                                            <xsl:if test="arquivo">
                                                                                <td align="left" data-row="{position()}" data-title="{../../label/arquivo}" class="link" item-name="arquivo">
                                                                                    <xsl:choose>
                                                                                        <xsl:when test="arquivo != ''">
                                                                                            <a href="{arquivo}" class="link bClick btn btn-primary " target-fields="" target="_blank" name="arquivo">
                                                                                                <i class="fa fa-link"/>
                                                                                                <span>
                                                                                                    <xsl:value-of select="arquivo_desc"/>
                                                                                                </span>
                                                                                            </a>
                                                                                        </xsl:when>
                                                                                        <xsl:otherwise>
                                                                                            <span>
                                                                                                <xsl:value-of select="arquivo_desc"/>
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
            </body>
        </html>
    </xsl:template>
    <xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1505130546662"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1505130546662"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1505130546662"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1505130546662"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1505130546663"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-table-utils.tmpl.xsl?v=1505130546663"/>
</xsl:stylesheet>
