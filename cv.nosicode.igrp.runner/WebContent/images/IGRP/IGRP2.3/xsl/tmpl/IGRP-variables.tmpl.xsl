<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 
  <!-- Bootstrap Version -->
  <xsl:variable name="bs-v" select="'3.3.7'"/>
  <!-- jQuery Version -->
  <xsl:variable name="jq-v" select="'2.1'"/>
  <!-- target is blank? popup -->
  <xsl:variable name="hasMenu">
    <xsl:choose>
      <xsl:when test="rows/target = '_blank'">false</xsl:when>
      <xsl:otherwise>true</xsl:otherwise>
    </xsl:choose>
  </xsl:variable>

  <!-- body class -->
  <xsl:variable name="bodyClass">
    <xsl:choose>
      <xsl:when test="$hasMenu = 'true'">fixed-nav</xsl:when>
      <xsl:otherwise>fluid</xsl:otherwise>
    </xsl:choose>
  </xsl:variable>
  <!--/body class -->

  <!-- igrp path -->
  <xsl:variable name="path">
      <xsl:choose>
        <xsl:when test="/rows/link_img">
          <xsl:value-of select="/rows/link_img" />
        </xsl:when>
        <xsl:otherwise>
          <xsl:text>/images/IGRP/IGRP2.3</xsl:text>
        </xsl:otherwise>
      </xsl:choose>
  </xsl:variable>
  <!--/igrp path -->

  <xsl:variable name="version">
      <xsl:choose>
        <xsl:when test="rows/version">
          <xsl:value-of select="rows/version"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="'01'"/>
        </xsl:otherwise>
      </xsl:choose>
  </xsl:variable>

  <!-- app theme -->
  <xsl:variable name="theme">
      <xsl:choose>
        <xsl:when test="rows/template and rows/template != ''">
          <xsl:text>/themes/</xsl:text><xsl:value-of select="rows/template"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:text>/themes/default</xsl:text>
        </xsl:otherwise>
      </xsl:choose>
  </xsl:variable>
  <!--/app theme -->

  <!-- theme path-->
  <xsl:variable name="themePath" select="concat($path,$theme)"/>
  <!--/theme path-->
  
  <!-- default theme path-->
  <xsl:variable name="themePathDefault" select="concat($path,'/themes/default')"/>
  <!-- /default theme path-->

  <!-- config theme file-->
  <xsl:variable name="themeConfigPath" select="concat($themePath,'/config.xml')"/>
  <!--/config theme file-->

  <!-- theme config data-->
  <xsl:variable name="themeConfigData" select="document($themeConfigPath)/style"/>
  <!--/theme config data-->


</xsl:stylesheet>