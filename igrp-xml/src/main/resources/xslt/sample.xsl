<?xml version="1.0" encoding="UTF-8"?>
<!--
  Copyright 2020 KS, Inc. http://www.ks.org

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.

  Created by: Paulo Bogres <poolborges>
  
  https://www.programcreek.com/java-api-examples/?code=wso2%2Fcarbon-commons%2Fcarbon-commons-master%2Fcomponents%2Fwsdl2form%2Forg.wso2.carbon.wsdl2form%2Fsrc%2Fmain%2Fjava%2Forg%2Fwso2%2Fcarbon%2Fwsdl2form%2FUtil.java#

-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:output method="html" indent="yes"/>

    <!-- This stylesheet only supports a single service at a time.
         If no service name is specified in this parameter, the first one is used.  -->
    <xsl:param name="service-name" select="services/service[1]/@name"/>

    <!-- Paths to external resources can be specified here. -->
    <xsl:param name="xslt-location" select="'/xslt/formatxml.xslt'"/>
    <xsl:param name="image-path" select="'images/tryit/'"/>
    <xsl:param name="enable-header" select="'false'"/>
    <xsl:param name="enable-footer" select="'false'"/>
    <xsl:param name="message-direction" select="'request'" />
    <xsl:param name="message" />
    <xsl:param name="operation-name" />

    <xsl:param name="message-type" select="'SOAP12'" />

    <!-- Allows some html to be inserted immediately before the body. -->
    <xsl:param name="breadcrumbs" />

    <xsl:param name="viewit-documentation" select="false()" />

    <xsl:variable name="operation" select="services/service[@name=$service-name and @type=$message-type]/operations/operation[@name=$operation-name]" />
</xsl:stylesheet>
