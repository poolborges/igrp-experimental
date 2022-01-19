<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:preserve-space elements="*"/>
    <!--Generate Record Type-->
    <xsl:template name="genRecordType">
        <xsl:call-template name="genComment">
            <xsl:with-param name="comment" select="'SQL variables'"/>
        </xsl:call-template>
        <xsl:call-template name="genSQLVariables">
            <xsl:with-param name="fields" select="$all_fields_sql"/>
        </xsl:call-template>
        <xsl:call-template name="genComment">
            <xsl:with-param name="comment" select="'Legend variables'"/>
        </xsl:call-template>
        <xsl:call-template name="genSQLVariables">
            <xsl:with-param name="fields" select="$all_fields_group|$all_fields_table"/>
            <xsl:with-param name="prefix" select="'LEG_'"/>
        </xsl:call-template>
        <xsl:call-template name="genComment">
            <xsl:with-param name="comment" select="'CHART variables'"/>
        </xsl:call-template>
        <xsl:call-template name="genChartVariables">
            <xsl:with-param name="fields" select="$all_fields_chart"/>
        </xsl:call-template>
        <xsl:call-template name="genComment">
            <xsl:with-param name="comment" select="'JSLookup variables'"/>
        </xsl:call-template>
        <xsl:call-template name="genJSVariables">
            <xsl:with-param name="fields" select="$all_fields_lookup"/>
        </xsl:call-template>
        <xsl:call-template name="genComment">
            <xsl:with-param name="comment" select="'Array variables'"/>
        </xsl:call-template>
        <xsl:for-each select="$all_fields_group">
            <xsl:call-template name="genArray">
                <xsl:with-param name="tag" select="concat(name(),'_id')"/>
                <xsl:with-param name="duplicate" select="'false'"/>
            </xsl:call-template>
            <xsl:call-template name="genArray">
                <xsl:with-param name="tag" select="concat(name(),'_del')"/>
                <xsl:with-param name="duplicate" select="'false'"/>
            </xsl:call-template>
        </xsl:for-each>
        <xsl:for-each select="$all_fields_group">
            <xsl:call-template name="genArrayVariables">
                <xsl:with-param name="fields" select="fields/*[not(.=preceding::*)]"/>
            </xsl:call-template>
        </xsl:for-each>
        <xsl:if test="$with_label='true'">
            <xsl:call-template name="genComment">
                <xsl:with-param name="comment" select="'Label variables'"/>
            </xsl:call-template>
            <xsl:call-template name="genRecord">
                <xsl:with-param name="fields" select="$all_fields|$all_fields_chart|$all_toolsbars|$all_fields_table|$all_fields_group"/>
                <xsl:with-param name="name" select="'label'" />
                <xsl:with-param name="type" select="'label'" />
            </xsl:call-template>
        </xsl:if>
        <xsl:call-template name="genComment">
            <xsl:with-param name="comment" select="'Permission variables'"/>
        </xsl:call-template>
        <xsl:call-template name="genRecord">
            <xsl:with-param name="fields" select="$all_fields|$all_fields_chart|$all_toolsbars|$all_fields_table|$all_fields_group"/>
            <xsl:with-param name="name" select="'permission'" />
            <xsl:with-param name="type" select="'permission'" />
        </xsl:call-template>
        <xsl:call-template name="genComment">
            <xsl:with-param name="comment" select="'Parameter variables'"/>
        </xsl:call-template>
        <xsl:call-template name="genRecord">
            <xsl:with-param name="fields" select="$all_toolsbars"/>
            <xsl:with-param name="name" select="'parameter'" />
            <xsl:with-param name="type" select="'parameter'" />
        </xsl:call-template>
        <xsl:call-template name="genComment">
            <xsl:with-param name="comment" select="'Record variables'"/>
        </xsl:call-template>
        <xsl:call-template name="genRecord">
            <xsl:with-param name="fields" select="$all_fields|$all_fields_group"/>
        </xsl:call-template>
        <xsl:for-each select="$all_fields_table">
            <xsl:call-template name="genRecord">
                <xsl:with-param name="fields" select="fields/*"/>
                <xsl:with-param name="name" select="name()"/>
                <xsl:with-param name="type" select="'list'"/>
            </xsl:call-template>
        </xsl:for-each>
    </xsl:template>
    <xsl:template name="genRecord">
        <xsl:param name="fields" />
        <xsl:param name="name" select="'RECORD'"/>
        <xsl:param name="type" select="'record'"/>
        <xsl:variable name="v_name">
            <xsl:call-template name="UpperCase">
                <xsl:with-param name="text" select="$name" />
            </xsl:call-template>
        </xsl:variable>
        <xsl:value-of select="$enter" />
        <xsl:value-of select="$entertab" />
        <xsl:text>TYPE</xsl:text>
        <xsl:value-of select="$space" />
        <xsl:value-of select="concat('R_',$v_name)" />
        <xsl:value-of select="$space" />
        <xsl:text>IS</xsl:text>
        <xsl:value-of select="$space" />
        <xsl:text>RECORD(</xsl:text>
        <xsl:for-each select="$fields">
            <xsl:variable name="tag">
                <xsl:choose>
                    <xsl:when test="@type='chart'">
                        <xsl:value-of select="concat('chart_',name())"/>
                    </xsl:when>
                    <xsl:when test="name()='item' and (@type='specific' or @type='' or not(@type))">
                        <xsl:variable name="button_type">
                            <xsl:choose>
                                <xsl:when test="local-name(..)='group'">
                                    <xsl:value-of select="local-name(../..)"/>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="local-name(..)"/>
                                </xsl:otherwise>
                            </xsl:choose>
                        </xsl:variable>
                        <xsl:variable name="what_button">
                            <xsl:call-template name="genWhatButton">
                                <xsl:with-param name="type" select="$button_type"/>
                            </xsl:call-template>
                        </xsl:variable>
                        <xsl:variable name="button_name">
                            <xsl:call-template name="sanitizeTag">
                                <xsl:with-param name="tag" select="title"/>
                            </xsl:call-template>
                        </xsl:variable>
                        <xsl:value-of select="concat($what_button,'_',$button_name)"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="name()"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:variable>
            <xsl:variable name="label">
                <xsl:choose>
                    <xsl:when test="@type='chart'">
                        <xsl:value-of select="caption"/>
                    </xsl:when>
                    <xsl:when test="name()='item' and (@type='specific' or @type='' or not(@type))">
                        <xsl:value-of select="title"/>
                    </xsl:when>
                    <xsl:when test="@type = 'table' or @type = 'separatorlist' or @type = 'separatordialog' or @type = 'list' or @type = 'formlist'">
                        <xsl:value-of select="@title"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="label"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:variable>
            <xsl:variable name="whattype">
                <xsl:choose>
                    <xsl:when test="$type='permission'">
                        <xsl:text>BOOLEAN:=TRUE</xsl:text>
                    </xsl:when>
                    <xsl:when test="$type='label'">
                        <xsl:text>VARCHAR2(100):=</xsl:text>
                        <xsl:value-of select="$quotes" />
                        <xsl:value-of select="$label" />
                        <xsl:value-of select="$quotes" />
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:call-template name="plsql_type">
                            <xsl:with-param name="type" select="@type"/>
                            <xsl:with-param name="size" select="@maxlength"/>
                        </xsl:call-template>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:variable>
            <xsl:value-of select="$enter2tab" />
            <xsl:value-of select="$tag" />
            <xsl:value-of select="$space" />
            <xsl:value-of select="$whattype" />
            <xsl:if test="position()&lt;count($fields)">
                <xsl:value-of select="$comma" />
            </xsl:if>
        </xsl:for-each>
        <xsl:value-of select="$entertab" />
        <xsl:text>)</xsl:text>
        <xsl:value-of select="$endline" />
        <xsl:if test="$type!='list'">
            <xsl:value-of select="$enter" />
            <xsl:value-of select="$entertab" />
            <xsl:value-of select="concat('TP_',$v_name)" />
            <xsl:value-of select="$space" />
            <xsl:value-of select="concat('R_',$v_name)" />
            <xsl:value-of select="$endline" />
            <xsl:value-of select="$enter" />
        </xsl:if>
        <xsl:if test="$type='list'">
            <xsl:value-of select="$enter" /><xsl:value-of select="$entertab" /><xsl:text>TYPE</xsl:text><xsl:value-of select="$space" /><xsl:value-of select="concat('T_',$v_name)" /><xsl:value-of select="$space" /><xsl:text>IS</xsl:text><xsl:value-of select="$space" /><xsl:text>TABLE</xsl:text><xsl:value-of select="$space" /><xsl:text>OF</xsl:text><xsl:value-of select="$space" /><xsl:value-of select="concat('R_',$v_name)" /><xsl:value-of select="$space" /><xsl:text xml:space="preserve">INDEX BY PLS_INTEGER</xsl:text><xsl:value-of select="$endline" /><xsl:value-of select="$enter" /><xsl:value-of select="$entertab" /><xsl:value-of select="concat('TBL_',$v_name)" /><xsl:value-of select="$space" /><xsl:value-of select="concat('T_',$v_name)" /><xsl:value-of select="$endline" /><xsl:value-of select="$enter" />
        </xsl:if>
    </xsl:template>
    <xsl:template name="genSQLVariables">
        <xsl:param name="fields" />
        <xsl:param name="prefix" select="'SQL_'"/>
        <xsl:for-each select="$fields">
            <xsl:value-of select="$enter" />
            <xsl:value-of select="$entertab" />
            <xsl:value-of select="concat($prefix,name(),$tab, 'VARCHAR2(4000):=')" />
            <xsl:value-of select="$sql_options" />
            <xsl:value-of select="$endline" />
        </xsl:for-each>
    </xsl:template>
    <xsl:template name="genChartVariables">
        <xsl:param name="fields" />
        <xsl:for-each select="$fields">
            <xsl:value-of select="$enter" />
            <xsl:value-of select="$entertab" />
            <xsl:value-of select="concat('CHART_',name(),$tab, 'VARCHAR2(4000):=')" />
            <xsl:value-of select="$sql_chart" />
            <xsl:value-of select="$endline" />
        </xsl:for-each>
    </xsl:template>
    <xsl:template name="genJSVariables">
        <xsl:param name="fields" />
        <xsl:for-each select="$fields">
            <xsl:value-of select="$enter" />
            <xsl:value-of select="$entertab" />
            <xsl:value-of select="concat('JS_',name(),$tab, 'VARCHAR2(4000):=')" />
            <xsl:if test="@type='LOOKUP'">
                <xsl:value-of select="concat($pkg_core,'.LOOKUP(')"/>
                <xsl:text>P_APP=></xsl:text>
                <xsl:value-of select="$quotes" />
                <xsl:value-of select="@app" />
                <xsl:value-of select="$quotes" />
                <xsl:value-of select="$comma" />
                <xsl:text>P_PAGE=></xsl:text>
                <xsl:value-of select="$quotes" />
                <xsl:value-of select="@page" />
                <xsl:value-of select="$quotes" />
                <xsl:value-of select="$comma" />
                <xsl:text>P_ACTION=></xsl:text>
                <xsl:value-of select="$quotes" />
                <xsl:value-of select="@action" />
                <xsl:value-of select="$quotes" />
                <xsl:text>)</xsl:text>
                <xsl:value-of select="$concat" />
            </xsl:if>
            <xsl:value-of select="$js_lookup" />
            <xsl:value-of select="translate(@type,'LOOKUP_','')" />
            <xsl:value-of select="$quotes" />
            <xsl:value-of select="$endline" />
        </xsl:for-each>
    </xsl:template>
    <xsl:template name="genArray">
        <xsl:param name="tag" />
        <xsl:param name="duplicate" select="'true'"/>
        <xsl:value-of select="$enter" />
        <xsl:value-of select="$entertab" />
        <xsl:value-of select="concat('v_',$tag,$tab, 'OWA.VC_ARR')" />
        <xsl:value-of select="$endline" />
        <xsl:if test="$duplicate='true'">
            <xsl:value-of select="$enter" />
            <xsl:value-of select="$entertab" />
            <xsl:value-of select="concat('v_',$tag,'_desc',$tab, 'OWA.VC_ARR')" />
            <xsl:value-of select="$endline" />
        </xsl:if>
    </xsl:template>
    <xsl:template name="genArrayVariables">
        <xsl:param name="fields" />
        <xsl:for-each select="$fields">
            <xsl:call-template name="genArray">
                <xsl:with-param name="tag" select="name()"/>
            </xsl:call-template>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>