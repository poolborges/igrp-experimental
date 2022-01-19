<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- ASTest_Appl_Events.xml -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:MSEvent="http://schemas.microsoft.com/win/2004/08/events/event">

    <xsl:template match="/">
        <html>
            <body>
                <title>ASTest Appl Events</title>
	
                <table>
                    <tr>
                        <td>
                            <h3>ASTest Application Events</h3>
                        </td>
                        <td width="100"> </td>
                    </tr>
                </table>
	
                <table border="1" style="border-collapse:collapse;font-size:14px;" cellpadding="4">
                    <tr bgcolor="black" style="color:white">
                        <td>Server</td>
                        <td>Event Type</td>
                        <td>Provider</td>
                        <td>Event ID</td>
                        <td>Time (Mar-Oct subtract 4 hours, Nov-Feb subract 5 hours)</td>
                        <td>Level</td>
                        <td>Event Data</td>
                        <td>User Data</td>
                    </tr>
                    <xsl:for-each select="ASTestApplEvents/MSEvent:Event">
                        <xsl:sort select="MSEvent:System/MSEvent:TimeCreated/@SystemTime" order="descending" />
                        <tr bgcolor="lightgray">
                            <td>
                                <xsl:value-of select="MSEvent:System/MSEvent:Computer"/>
                            </td>
                            <td>
                                <xsl:value-of select="MSEvent:System/MSEvent:Channel"/>
                            </td>
                            <td>
                                <xsl:value-of select="MSEvent:System/MSEvent:Provider/@Name"/>
                            </td>
                            <td>
                                <xsl:value-of select="MSEvent:System/MSEvent:EventID"/>
                            </td>
                            <td>
                                <xsl:value-of select="MSEvent:System/MSEvent:TimeCreated/@SystemTime"/>
                            </td>
                            <xsl:choose>
                                <xsl:when test="MSEvent:System/MSEvent:Level=1">
                                    <td style="color:orange;">CRITICAL<br></br>(
                                        <xsl:value-of select="MSEvent:System/MSEvent:Level"/>)
                                    </td>
                                </xsl:when>
                                <xsl:when test="MSEvent:System/MSEvent:Level=2">
                                    <td style="color:red;">ERROR<br></br>(
                                        <xsl:value-of select="MSEvent:System/MSEvent:Level"/>)
                                    </td>
                                </xsl:when>
                                <xsl:when test="MSEvent:System/MSEvent:Level=3">
                                    <td style="color:green;">WARNING<br></br>(
                                        <xsl:value-of select="MSEvent:System/MSEvent:Level"/>)
                                    </td>
                                </xsl:when>
                                <xsl:otherwise>
                                    <td>(<xsl:value-of select="MSEvent:System/MSEvent:Level"/>)</td>
                                </xsl:otherwise>
                            </xsl:choose>
                            <td>
                                <xsl:for-each select="MSEvent:EventData/MSEvent:Data">
                                    <img src="Blue_dot(sm).jpg"/> 
                                    <strong>
                                        <em>
                                            <xsl:value-of select="./@Name"/>:</em>
                                    </strong> 
                                    <xsl:value-of select="."/>
                                    <br></br>
                                </xsl:for-each>
                            </td>
                            <td>
                                <xsl:for-each select="MSEvent:UserData">
                                    <img src="Blue_dot(sm).jpg"/> 
                                    <strong>
                                        <em>
                                            <xsl:value-of select="./@Name"/>:</em>
                                    </strong> 
                                    <xsl:value-of select="."/>
                                </xsl:for-each>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
	
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
