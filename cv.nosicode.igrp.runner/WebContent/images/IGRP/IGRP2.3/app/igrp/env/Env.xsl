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
                                                    <div class="toolsbar-holder boxed gen-container-item " gen-structure="toolsbar" gen-fields=".btns-holder a.btn" gen-class="" item-name="toolsbar_1">
                                                        <div class="btns-holder  pull-right" role="group">
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
                                                                <xsl:if test="rows/content/form_1/fields/dad">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="dad" item-type="text" required="required">
                                                                        <label for="{rows/content/form_1/fields/dad/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/dad/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/dad/value}" class="form-control " id="{rows/content/form_1/fields/dad/@name}" name="{rows/content/form_1/fields/dad/@name}" required="required" maxlength="30" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/name">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="name" item-type="text" required="required">
                                                                        <label for="{rows/content/form_1/fields/name/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/name/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/name/value}" class="form-control " id="{rows/content/form_1/fields/name/@name}" name="{rows/content/form_1/fields/name/@name}" required="required" maxlength="50" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/description">
                                                                    <div class="form-group col-md-3  gen-fields-holder" item-name="description" item-type="textarea">
                                                                        <label for="{rows/content/form_1/fields/description/@name}">
                                                                            <xsl:value-of select="rows/content/form_1/fields/description/label"/>
                                                                        </label>
                                                                        <textarea name="{rows/content/form_1/fields/description/@name}" class="textarea form-control " maxlength="500">
                                                                            <xsl:value-of select="rows/content/form_1/fields/description/value"/>
                                                                        </textarea>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/img_src">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="img_src" item-type="text">
                                                                        <label for="{rows/content/form_1/fields/img_src/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/img_src/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/img_src/value}" class="form-control " id="{rows/content/form_1/fields/img_src/@name}" name="{rows/content/form_1/fields/img_src/@name}" maxlength="50" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/action_fk">
                                                                    <div class="col-md-3 form-group  gen-fields-holder" item-name="action_fk" item-type="select">
                                                                        <label for="{rows/content/form_1/fields/action_fk/@name}">
                                                                            <xsl:value-of select="rows/content/form_1/fields/action_fk/label"/>
                                                                        </label>
                                                                        <select class="form-control select2 " id="form_1_action_fk" name="{rows/content/form_1/fields/action_fk/@name}">
                                                                            <xsl:for-each select="rows/content/form_1/fields/action_fk/list/option">
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
                                                                <xsl:if test="rows/content/form_1/fields/host">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="host" item-type="text">
                                                                        <label for="{rows/content/form_1/fields/host/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/host/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/host/value}" class="form-control " id="{rows/content/form_1/fields/host/@name}" name="{rows/content/form_1/fields/host/@name}" maxlength="30" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/apache_dad">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="apache_dad" item-type="text">
                                                                        <label for="{rows/content/form_1/fields/apache_dad/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/apache_dad/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/apache_dad/value}" class="form-control " id="{rows/content/form_1/fields/apache_dad/@name}" name="{rows/content/form_1/fields/apache_dad/@name}" maxlength="30" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/link_menu">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="link_menu" item-type="text">
                                                                        <label for="{rows/content/form_1/fields/link_menu/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/link_menu/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/link_menu/value}" class="form-control " id="{rows/content/form_1/fields/link_menu/@name}" name="{rows/content/form_1/fields/link_menu/@name}" maxlength="2000" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/link_center">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="link_center" item-type="text">
                                                                        <label for="{rows/content/form_1/fields/link_center/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/link_center/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/link_center/value}" class="form-control " id="{rows/content/form_1/fields/link_center/@name}" name="{rows/content/form_1/fields/link_center/@name}" maxlength="2000" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/templates">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="templates" item-type="text">
                                                                        <label for="{rows/content/form_1/fields/templates/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/templates/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/templates/value}" class="form-control " id="{rows/content/form_1/fields/templates/@name}" name="{rows/content/form_1/fields/templates/@name}" maxlength="30" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/flg_old">
                                                                    <div class="col-md-3  gen-fields-holder" item-name="flg_old" item-type="checkbox">
                                                                        <div class="form-group">
                                                                            <div class="checkbox form-check-offset">
                                                                                <label>
                                                                                    <input type="checkbox" name="{rows/content/form_1/fields/flg_old/@name}" value="1" class="checkbox " label="{rows/content/form_1/fields/flg_old/label}">
                                                                                        <xsl:if test="rows/content/form_1/fields/flg_old/value = '1'">
                                                                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                                                                        </xsl:if>
                                                                                    </input>
                                                                                    <span>
                                                                                        <xsl:value-of select="rows/content/form_1/fields/flg_old/label"/>
                                                                                    </span>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/flg_external">
                                                                    <div class="col-md-3  gen-fields-holder" item-name="flg_external" item-type="checkbox">
                                                                        <div class="form-group">
                                                                            <div class="checkbox form-check-offset">
                                                                                <label>
                                                                                    <input type="checkbox" name="{rows/content/form_1/fields/flg_external/@name}" value="1" class="checkbox " label="{rows/content/form_1/fields/flg_external/label}">
                                                                                        <xsl:if test="rows/content/form_1/fields/flg_external/value = '1'">
                                                                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                                                                        </xsl:if>
                                                                                    </input>
                                                                                    <span>
                                                                                        <xsl:value-of select="rows/content/form_1/fields/flg_external/label"/>
                                                                                    </span>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/status">
                                                                    <div class="col-md-3  gen-fields-holder" item-name="status" item-type="checkbox">
                                                                        <div class="form-group">
                                                                            <div class="checkbox form-check-offset">
                                                                                <label>
                                                                                    <input type="checkbox" name="{rows/content/form_1/fields/status/@name}" value="1" class="checkbox " label="{rows/content/form_1/fields/status/label}">
                                                                                        <xsl:if test="rows/content/form_1/fields/status/value = '1'">
                                                                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                                                                        </xsl:if>
                                                                                    </input>
                                                                                    <span>
                                                                                        <xsl:value-of select="rows/content/form_1/fields/status/label"/>
                                                                                    </span>
                                                                                </label>
                                                                            </div>
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
    <xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1493298656216"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1493298656216"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1493298656216"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1493298656216"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1493298656216"/>
</xsl:stylesheet>
