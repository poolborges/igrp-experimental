<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
     <xsl:preserve-space elements="*"/>
    <!-- Generate a package DB SPEC -->
    <xsl:template name="generateDBSpec">
        <xsl:param name="packageName"/>
        
        <xsl:call-template name="genPackageCab">
            <xsl:with-param name="packageName" select="$packageName"/>
            <xsl:with-param name="body" select="'false'"/>
            <xsl:with-param name="replace" select="$with_replace"/>
        </xsl:call-template>
        
        <xsl:value-of select="$enter"/> 
        
        <xsl:call-template name="genRecordType" />
        
        <xsl:call-template name="genProcedureSpec">
            <xsl:with-param name="procedureName" select="'dml_select_all'"/>
        </xsl:call-template>
        
        <xsl:call-template name="generateDbSpecTable" />
        
        <xsl:call-template name="generateDbSpecToolsbar" />
                        
        <xsl:call-template name="genPackageEndCab"/>
    </xsl:template>

   
    
    <!-- Generate Spec Toolbars -->
    <xsl:template name="generateDbSpecToolsbar">
        
        <xsl:for-each select="rows/content/*[@type = 'table' or @type = 'separatorlist' or @type = 'separatordialog' or @type = 'list' or @type = 'formlist']/table/context-menu/*|rows/content/*[@type = 'toolsbar']/*">
            <xsl:call-template name="genDbSpecToolsbar">
                <xsl:with-param name="title" select="title"/>
                <xsl:with-param name="page" select="page" />
                <xsl:with-param name="action" select="link" />
            </xsl:call-template>
        </xsl:for-each>
    </xsl:template>
    
    <xsl:template name="genDbSpecToolsbar">
        <xsl:param name="title" />        
        <xsl:param name="page" />
        <xsl:param name="action" /> 
        
        <xsl:variable name="code_action">
            <xsl:call-template name="sanitizeToolbar">
                <xsl:with-param name="title" select="$title"/>
                <xsl:with-param name="selfaction" select="$selfaction"/>
                <xsl:with-param name="page" select="$page"/>
                <xsl:with-param name="action" select="$action"/>
            </xsl:call-template>
        </xsl:variable>        
        
        <xsl:variable name="procName" select="concat('dml_',$code_action)" />
        <xsl:call-template name="genProcedureSpec">
            <xsl:with-param name="procedureName" select="$procName"/>
            <xsl:with-param name="params" select="concat(concat(concat('p_page',$space,'OUT',$space,'VARCHAR2,',$space),concat('p_action',$space,'OUT',$space,'VARCHAR2,',$space)),concat('p_app',$space,'OUT',$space,'VARCHAR2'))"/>
        </xsl:call-template>
    </xsl:template>
    
    <xsl:template name="genDbSpecTableGroup">
        <xsl:param name="title" />
        <xsl:param name="type" />
        <xsl:param name="tag" />
        <xsl:param name="type_pkg" select="'html'"/>
        
        <xsl:variable name="procName" select="concat('VALUE_',$tag)" />
        
        <xsl:call-template name="genProcedureSpec">
            <xsl:with-param name="procedureName" select="$procName"/>
            <xsl:with-param name="params" select="concat(concat('p',$space,'OUT',$space,$pkg_type,'.T_FIELD,',$space),concat('params',$space,'OUT',$space,$pkg_type,'.T_PARAM'))"/>
        </xsl:call-template>
        
    </xsl:template>
    
    <xsl:template name="generateDbSpecTable">
        
        <xsl:for-each select="rows/content/*[@type = 'table' or @type = 'separatorlist' or @type = 'separatordialog' or @type = 'list' or @type = 'formlist']">
            <xsl:call-template name="genDbSpecTableGroup">
                <xsl:with-param name="title" select="@title"/>
                <xsl:with-param name="type" select="@type" />
                <xsl:with-param name="tag" select="name()" />
            </xsl:call-template>
        </xsl:for-each>
        
    </xsl:template>
    
    
</xsl:stylesheet>
