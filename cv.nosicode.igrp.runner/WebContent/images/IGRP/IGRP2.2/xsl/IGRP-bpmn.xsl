<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
  <xsl:template match="/">
    <html>
      <head>
        <xsl:call-template name="home-header"/>
      </head>
      <body class="bodyColor">
        <div class="IGRP_overlay"/>
        <!--HOME TOP-->
        <xsl:call-template name="home-top-main"/>
        <!--BODY PAGE-->
        <div id="igrp-bodyPage">
          <xsl:if test="not(rows/target) or rows/target!='_blank'">
            <div class="bodyPageLeft">
              <xsl:apply-templates mode="slide-menu" select="document(rows/slide-menu/@file)" />
            </div>
          </xsl:if>
          <div class="bodyPageRigth">
            <!--PUT YOUT CODE HERE-->
            <!--APPLICATION DIRECTORY-->
            <form action="#" method="post" id="formular_default" name="formular_default" class="default_form" enctype="multipart/form-data">
              <xsl:apply-templates mode="bpmn" select="rows/content"/>
            </form>
            <div class="_clear"/>
          </div>
          <div class="_clear"/>
        </div>
        <!--FOOTER PAGE-->
        <div id="igrp-footerPage"></div>
      </body>
    </html>
  </xsl:template>
  <!--INCLUDE TEMPLATE-->
  <!--TEMPLATE HOME INCLUDE-->
  <xsl:include href="tmpl/IGRP-home-include.tmpl.xsl?v=1"/>
  <xsl:include href="tmpl/IGRP-slide-menu.tmpl.xsl?v=1"/>
  <xsl:include href="tmpl/IGRP-bpmn.tmpl.xsl?v=1"/>
</xsl:stylesheet>