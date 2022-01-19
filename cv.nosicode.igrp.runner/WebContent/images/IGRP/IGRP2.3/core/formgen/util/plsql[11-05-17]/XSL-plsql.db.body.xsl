<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:preserve-space elements="*"/>
    <!--Generate a package DB SPEC-->
    <xsl:template name="generateDBBody">
        <xsl:param name="packageName"/>

        
        <xsl:call-template name="genPackageCab">
            <xsl:with-param name="packageName" select="$packageName"/>
            <xsl:with-param name="body" select="'true'"/>
            <xsl:with-param name="replace" select="$with_replace"/>
        </xsl:call-template>

        <xsl:value-of select="$enter"/>
        
        <xsl:call-template name="genPreserveYourCode">
            <xsl:with-param name="procName" select="'BIZTALK'" />
        </xsl:call-template>
       
        <xsl:call-template name="generateDbBodySelectAll">
            <xsl:with-param name="procName" select="'dml_select'" />
        </xsl:call-template>

        <xsl:call-template name="generateDbBodySelectAll"/>

        <xsl:call-template name="generateDbBodySelectAll">
            <xsl:with-param name="procName" select="'dml_insert'" />
        </xsl:call-template>

        <xsl:call-template name="generateDbBodySelectAll">
            <xsl:with-param name="procName" select="'dml_update'" />
        </xsl:call-template>

        <xsl:call-template name="generateDbBodySelectAll">
            <xsl:with-param name="procName" select="'dml_delete'" />
        </xsl:call-template>

        <xsl:call-template name="generateDbBodyTable" />

        <xsl:call-template name="generateDbBodyToolsbar" />

        <xsl:call-template name="genPackageEndCab"/>

    </xsl:template>
    <!--Generate Spec Toolbars-->
    <xsl:template name="generateDbBodySelectAll">
        <xsl:param name="procName" select="'dml_select_all'" />
        <xsl:call-template name="genProcedureCab">
            <xsl:with-param name="procedureName" select="$procName"/>
        </xsl:call-template>
        <xsl:value-of select="$entertab"/>
        <xsl:text>BEGIN</xsl:text>
        <xsl:call-template name="genComment">
            <xsl:with-param name="comment" select="$procName"/>
        </xsl:call-template>
        <xsl:value-of select="$enter"/>
        <xsl:value-of select="$enter2tab"/>
        <xsl:text>NULL</xsl:text>
        <xsl:value-of select="$endline"/>

        <xsl:call-template name="genProcedureEndCab">
            <xsl:with-param name="procedureName" select="$procName"/>
            <xsl:with-param name="preserve" select="'true'"/>
        </xsl:call-template>

    </xsl:template>

    <xsl:template name="generateDbBodyToolsbar">
        <xsl:for-each select="$all_toolsbars_plsql">
            <xsl:call-template name="genDbBodyToolsbar">
                <xsl:with-param name="title" select="title"/>
                <xsl:with-param name="app" select="app" />
                <xsl:with-param name="page" select="page" />
                <xsl:with-param name="action" select="link" />
            </xsl:call-template>
        </xsl:for-each>
    </xsl:template>
    
    <xsl:template name="generateDbBodyTable">
        <xsl:for-each select="$all_fields_list">
            <xsl:call-template name="genDbTableGroup">
                <xsl:with-param name="title" select="@title"/>
                <xsl:with-param name="type" select="@type" />
                <xsl:with-param name="tag" select="name()" />
                <xsl:with-param name="fields" select="fields/*" />
            </xsl:call-template>
        </xsl:for-each>
    </xsl:template>
    <xsl:include href="XSL-plsql.body.toolsbar.db.xsl"/>
    <xsl:include href="XSL-plsql.body.table.db.xsl"/>
</xsl:stylesheet>
