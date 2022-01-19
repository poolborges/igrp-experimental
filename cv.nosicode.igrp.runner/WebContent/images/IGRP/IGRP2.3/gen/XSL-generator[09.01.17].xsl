<?xml version="1.0" encoding="utf-8"?>
<gen:stylesheet xmlns:gen="http://www.w3.org/1999/XSL/Transform" xmlns:xsl="dim-red" version="1.0" exclude-result-prefixes="xsl">
	<gen:namespace-alias stylesheet-prefix="xsl" result-prefix="xsl"/>
	<gen:variable name="pathAttr" select="'{$path}'"/>
	<gen:template match="/">
		<xsl:stylesheet version="1.0">
			<xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
			<xsl:template match="/">
				<gen:variable name="bodyClass" select="'{$bodyClass}'"/>
				<html>
					<head>
						<xsl:call-template name="IGRP-head"/>
						<gen:call-template name="gen_css_includes"/>
					</head>
					<body class="{$bodyClass} old-v">
						<xsl:call-template name="IGRP-topmenu"/>
						<form method="POST" class="IGRP-form" name="formular_default">
							<div class="container-fluid">
								<div class="row">
									<xsl:call-template name="IGRP-sidebar"/>
									<div class="col-sm-9 col-md-10 col-md-offset-2 col-sm-offset-3 main" id="igrp-contents">
										<div class="content">
											<!-- PAGE TITLE -->
											<section class="content-header gen-container-item">
												<h1>
													<xsl:value-of select="rows/content/title"/>
												</h1>
											</section>
											<!-- /PAGE TITLE -->

											<!-- MESSAGES -->
											<xsl:apply-templates mode="igrp-messages" select="rows/content/messages"/>
											<!-- /MESSAGES -->

											<!--VIEW-->
											<gen:if test="rows/content/view">
												<gen:call-template name="gen_view"/>
											</gen:if>
											<!--/VIEW-->

											<!--MENU-->
											<gen:if test="rows/content/menu">
												<gen:call-template name="gen_menu"/>
											</gen:if>
											<!-- /MENU -->

											

											<!--FORM-->
											<gen:if test="rows/content/form">
												<gen:call-template name="gen_form">
													<gen:with-param name="fields" select="rows/content/form/label/*[not(contains(@type,'filter_')) and not(@rel)]"/>
												</gen:call-template>
											</gen:if>
											<!-- /FORM -->
											<!-- FILTER -->
											<gen:if test="rows/content/filter">
												<gen:call-template name="gen_form">
													<gen:with-param name="fields" select="rows/content/filter/label/*[not(contains(@type,'filter_')) and not(@rel)]"/>
													<gen:with-param name="tag" select="'filter'"/>
												</gen:call-template>
											</gen:if>
											<!-- /FILTER -->
											<!-- TABLE -->
											<gen:if test="rows/content/table and rows/content/table/value/row and rows/content/table/value/row/*">
												<gen:apply-templates select="rows/content/table" mode="gen_table">
													<gen:with-param name="fields" select="rows/content/table/label/*[@type != 'field_copy']"/>
													<gen:with-param name="tablePath" select=" 'rows/content/table' "/>
													<gen:with-param name="form-item" select=" 'false' "/>
												</gen:apply-templates>
											</gen:if>
											<!-- /TABLE -->
										</div>
									</div>
								</div>
							</div>
							<xsl:call-template name="IGRP-bottom"/>
						</form>
						<gen:call-template name="gen_js_includes"/>
					</body>
				</html>
			</xsl:template>
			<gen:call-template name="gen_xsl_includes"/>
		</xsl:stylesheet>
	</gen:template>
	<!--/GEN CONTAINERS-->
	<gen:template name="gen_iframe">
		<p>to implement IFRAME</p>
	</gen:template>
	<gen:template name="gen_chart">
		<p>to implement CHART</p>
	</gen:template>
	<!-- GEN table-->
	<gen:template name="gen_table" mode="gen_table" match="*">
		<gen:param name="fields"/>
		<gen:param name="tablePath"/>
		<gen:param name="form-item" select="'true'"/>
		<gen:variable name="tag" select="name()"/>
		<gen:variable name="form-item-class">
			<gen:if test="$form-item='true'">gen-item-boxed</gen:if>
		</gen:variable>
		<gen:variable name="hasContextMenu">
			<gen:if test="context-menu">IGRP_contextmenu</gen:if>
		</gen:variable>
		<gen:if test="tools-bar">
			<xsl:if test="rows/content/table/tools-bar/item">
				<div class="toolsbar-holder boxed gen-container-item clearfix">
					<div class="btns-holder pull-right clearfix" role="group">
						<xsl:apply-templates select="rows/content/table/tools-bar" mode="gen-buttons">
							<xsl:with-param name="use-fa" select="'false'"/>
							<xsl:with-param name="type" select="'tools-bar'"/>
							<xsl:with-param name="vertical" select="'true'"/>
						</xsl:apply-templates>
					</div>
				</div>
			</xsl:if>
		</gen:if>
		<div class="box box-table-contents {$form-item-class} gen-container-item" item-name="{$tag}">
			<gen:if test="//rows/content/form/label/*[name() = $tag]">
				<div class="box-header">
					<h3 class="box-title">
						<xsl:value-of select="rows/content/form/label/{name()}"/>
					</h3>
				</div>
			</gen:if>
			<div class="box-body table-box">
				<xsl:apply-templates mode="form-hidden" select="rows/content/form/value">
					<xsl:with-param name="rel" select="'{$tag}'"/>
				</xsl:apply-templates>
				<gen:if test="$fields[contains(@type,'color_td')]">
					<xsl:apply-templates mode="table-legend" select="{$tablePath}/legend_color"/>
				</gen:if>
				<table id="{$tag}" class="table table-striped gen-data-table {$hasContextMenu} ">
					<thead>
						<tr>
							<gen:for-each select="$fields[not(@type='hidden')]">
								<xsl:if test="{$tablePath}/label/{name()}">
									<xsl:if test="not({$tablePath}/label/{name()}/@visible)">
										<th item-name="{name()}" item-type="{@type}">
											<gen:choose>
												<!-- radio.table th-->
												<gen:when test="@type = 'radio'">
													<gen:attribute name="align">center</gen:attribute>
													<gen:attribute name="class">bs-checkbox</gen:attribute>
													<xsl:value-of select="{$tablePath}/label/{name()}"/>
												</gen:when>
												<!-- checkbox.table th-->
												<gen:when test="@type = 'checkbox'">
													<gen:attribute name="align">center</gen:attribute>
													<gen:attribute name="class">bs-checkbox</gen:attribute>
													<input type="checkbox" class="IGRP_checkall no-validation" title="{concat('{',$tablePath,'/label/',name(),'}')}" check-rel="{name()}"/>
												</gen:when>
												<!-- color.table th-->
												<gen:when test="@type = 'color_td'">
													<gen:attribute name="class">color-th</gen:attribute>
													<xsl:value-of select="{$tablePath}/label/{name()}"/>
												</gen:when>
												<!-- default.table th-->
												<gen:otherwise>
													<xsl:value-of select="{$tablePath}/label/{name()}"/>
												</gen:otherwise>
											</gen:choose>
										</th>
									</xsl:if>
								</xsl:if>
							</gen:for-each>
						</tr>
					</thead>
					<tbody>
						<xsl:for-each select="{$tablePath}/value/row[not(@total='yes')]">
							<tr>
								<xsl:apply-templates mode="context-param" select="context-menu" />
								<gen:for-each select="$fields[not(@type='hidden')]">
									<xsl:if test="{name()}">
										<xsl:if test="not({name()}/@visible)">
											<td item-name="{name()}" item-type="{@type}" data-row="{'{position()}'}" data-title="{concat('{../../label/',name(),'}')}">
												<gen:choose>
													<!-- checkbox.table td-->
													<gen:when test="@type = 'checkbox'">
														<gen:attribute name="align">center</gen:attribute>
														<xsl:if test="{name()} != '-0'">
															<input type="checkbox" name="p_{name()}" value="{'{name()}'}" check-rel="{name()}">
																<xsl:if test="{name()}_check={name()}">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
															</input>
														</xsl:if>
													</gen:when>
													<!-- checkbox.table td-->
													<gen:when test="@type = 'checkbox'">
														<gen:attribute name="align">center</gen:attribute>
														<xsl:if test="{name()} != '-0'">
															<input type="checkbox" name="p_{name()}" value="{'{name()}'}" check-rel="{name()}">
																<xsl:if test="{name()}_check={name()}">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
															</input>
														</xsl:if>
													</gen:when>
													<!-- radio.table td-->
													<gen:when test="@type = 'radio'">
														<gen:attribute name="align">center</gen:attribute>
														<xsl:if test="{name()} != '-0'">
															<input type="radio" name="p_{name()}" value="{'{name()}'}" check-rel="{name()}">
																<xsl:if test="{name()}_check={name()}">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
															</input>
														</xsl:if>
													</gen:when>
													<!-- link.table td-->
													<gen:when test="@type = 'link'">
														<xsl:choose>
															<xsl:when test="{name()} != ''">
																<a href="{concat('{',name(),'}')}" class="link bClick" name="{name()}">
																	<gen:call-template name="gen_target_attr"/>
																	<xsl:value-of select="{name()}_desc" disable-output-escaping="yes"/>
																</a>
															</xsl:when>
															<xsl:otherwise>
																<span>
																	<xsl:value-of select="{name()}_desc" disable-output-escaping="yes"/>
																</span>
															</xsl:otherwise>
														</xsl:choose>
													</gen:when>
													<!-- color.table td-->
													<gen:when test="contains(@type,'color')">
														<xsl:call-template name="tdcolor">
															<xsl:with-param name="color" select="{name()}"/>
														</xsl:call-template>
													</gen:when>
													<!-- default.table td-->
													<gen:otherwise>
														<span>
															<gen:if test="@type='js_passa_valor'">
																<gen:attribute name="class">lookup-parser</gen:attribute>
															</gen:if>
															<xsl:value-of select="{name()}"/>
														</span>
													</gen:otherwise>
												</gen:choose>
											</td>
										</xsl:if>
										<xsl:if test="{name()}/@visible = 'false'">
											<input type="hidden" name="{@name}_fk" value="{name()}" />
										</xsl:if>
									</xsl:if>
								</gen:for-each>
								<gen:if test="$form-item = 'true'">
									<input type="hidden" name="p_{$tag}_id" value="{concat('{',$tag,'_id}')}"/>
								</gen:if>
								<gen:for-each select="$fields[@type = 'hidden']">
									<gen:variable name="tagVal" select="concat('{',name(),'}')"/>
									<gen:variable name="tagValDesc" select="concat('{',name(),'_desc}')"/>
									<input type="hidden" name="{@name}_fk" value="{$tagVal}" />
									<input type="hidden" name="{@name}_fk_desc" value="{$tagValDesc}" />
								</gen:for-each>
								<!-- <input type="hidden" name="p_{$tag}_id" value="{concat( '{',$tag,'_id}' )}"/>
								<gen:for-each select="$fields[@type = 'hidden']">
									<gen:variable name="tagvalh" select="concat(concat('{',name()),'}')"/>
									<gen:variable name="tagvaldesch" select="concat(concat('{',name()),'_desc}')"/>
									<input type="hidden" name="{@name}_fk" value="{$tagvalh}" />
									<input type="hidden" name="{@name}_fk_desc" value="{$tagvaldesch}" />
								</gen:for-each>-->
							</tr>
						</xsl:for-each>
					</tbody>
				</table>
			</div>
			<gen:if test="context-menu">
				<xsl:apply-templates mode="table-context-menu" select="{$tablePath}/context-menu"/>
			</gen:if>
		</div>
	</gen:template>
	<!--/GEN table-->
	<!-- GEN formlist-->
	<gen:template name="gen_formlist" mode="gen_formlist" match="*">
		<gen:param name="fields"/>
		<gen:variable name="type" select="@type"/>
		<gen:variable name="tag" select="name()"/>
		<div class="box box-table-contents gen-container-item gen-item-boxed" item-name="{$tag}">
			<div class="box-header">
				<h3 class="box-title">
					<xsl:value-of select="rows/content/form/label/{name()}"/>
				</h3>
			</div>
			<div class="box-body table-box">
				<xsl:apply-templates mode="form-hidden" select="rows/content/form/value">
					<xsl:with-param name="rel" select="'{$tag}'"/>
				</xsl:apply-templates>
				<table id="{$tag}" class="table table-striped gen-data-table IGRP_formlist" rel="T_{$tag}" data-control="data-{$tag}">
					<thead>
						<gen:for-each select="$fields[@type!='hidden']">
							<xsl:if test="rows/content/form/table/{$tag}/label/{name()}">
								<xsl:if test="not(rows/content/form/table/{$tag}/label/{name()}/@visible)">
									<gen:choose>
										<!--checkbox.formlist th-->
										<gen:when test="@type = 'checkbox'">
											<th class="bs-checkbox" align="center">
												<input type="checkbox" class="IGRP_checkall no-validation" check-rel="name()"/>
											</th>
										</gen:when>
										<gen:otherwise>
											<th>
												<xsl:value-of select="rows/content/form/table/{$tag}/label/{name()}"/>
											</th>
										</gen:otherwise>
									</gen:choose>
								</xsl:if>
							</xsl:if>
						</gen:for-each>
						<xsl:if test="not(rows/content/form/table/{$tag}/value/row[position() = 1]/@noupdate) or not(rows/content/form/table/{$tag}/value/row[position() = 1]/@nodelete)">
							<th class="table-btn">
								<xsl:if test="not(rows/content/form/table/{$tag}/value/row[position() = 1]/@noupdate)">
									<xsl:if test="not(@nodelete)">
										<a class="formlist-row-add" rel="{$tag}">
											<i class="fa fa-plus"></i>
										</a>
									</xsl:if>
								</xsl:if>
							</th>
						</xsl:if>
					</thead>
					<tbody>
						<xsl:for-each select="rows/content/form/table/{$tag}/value/row[not(@total='yes')]">
							<tr row="{position()}">
								<input type="hidden" name="p_{$tag}_id" value="{concat('{',$tag,'_id}')}"/>
								<gen:for-each select="$fields[@type != 'hidden']">
									<gen:variable name="v_label" select="concat('{../../label/',name(),'}')"/>
									<gen:variable name="v_name"  select="concat('{../../value/',name(),'/@name}_fk')"/>
									<xsl:if test="not({name()}/@visible)">
										<gen:variable name="tdclass">
											<gen:if test="@type != 'radio' and @type != 'checkbox'">
												<gen:value-of select="@type"/>
											</gen:if>
										</gen:variable>
										<td align="" data-row="{'{position()}'}" data-title="{$v_label}" class="{$tdclass}">
											<div class="form-group" item-name="{name()}" item-type="{@type}">
												<input type="hidden" name="{$v_name}_desc" value="{concat('{',name(),'_desc}')}"/>
												<gen:choose>
													<!--date.formlist td-->
													<gen:when test="@type = 'date'">
														<div class="input-group">
															<input rel="F_{$tag}" type="text"  name="{$v_name}" value="{concat('{',name(),'}')}" class="form-control gen-date" id="{$tag}-{name()}" format="IGRP_datePicker"/>
															<span class="input-group-btn gen-date-icon">
																<span class="btn btn-default">
																	<i class="fa fa-calendar"></i>
																</span>
															</span>
														</div>
													</gen:when>
													<!--radiolist.formlist td  /  checkboxlist.formlist td  /  select.formlist td-->
													<gen:when test="@type = 'radiolist' or @type = 'checkboxlist' or contains(@type,'select')">
														<gen:variable name="change">
															<gen:if test="contains(@type,'change')">true</gen:if>
														</gen:variable>
														<select class="form-control select2 {$change}" id="{$tag}_{name()}" name="{$v_name}" rel="F_{$tag}">
															<xsl:for-each select="../../../../list/{name()}/option">
																<option value="{'{value}'}">
																	<xsl:if test="@selected='true'">
																		<xsl:attribute name="selected">selected</xsl:attribute>
																	</xsl:if>
																	<span>
																		<xsl:value-of select="text"/>
																	</span>
																</option>
															</xsl:for-each>
														</select>
													</gen:when>
													<!--link.formlist td-->
													<gen:when test="@type = 'link'">
														<input type="hidden" name="{$v_name}" value="{concat('{',name(),'}')}" rel="F_{$tag}"/>
														<a href="{concat('{',name(),'}')}" name="p_{name()}" class="link form-link">
															<gen:call-template name="gen_target_attr"/>
															<xsl:value-of select="{name()}_desc" disable-output-escaping="yes"/>
														</a>
													</gen:when>
													<!--checkbox.formlist td-->
													<gen:when test="@type = 'checkbox'">
														<xsl:if test="{name()} != '-0'">
															<input type="checkbox" name="p_{name()}_fk" value="{concat('{',name(),'}')}" class="checkbox" rel="F_{$tag}" check-rel="name()">
																<xsl:if test="{name()}_check= {name()}">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
															</input>
														</xsl:if>
													</gen:when>
													<!--radio.formlist td-->
													<gen:when test="@type = 'radio'">
														<xsl:if test="{name()} != '-0'">
															<input type="radio" name="p_{name()}_fk" value="{concat('{',name(),'}')}" class="radio" rel="F_{$tag}" check-rel="name()">
																<xsl:if test="{name()}_check= {name()}">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
															</input>
														</xsl:if>
													</gen:when>
													<!--file.formlist td-->
													<gen:when test="@type = 'file'">
														<div class="input-group">
															<input type="text" class="form-control form-hidden" readonly=""/>
															<span class="input-group-btn">
																<span class="btn btn-default file-btn-holder">
																	<i class="fa fa-upload"></i>
																	<input name="{$v_name}" value="{concat('{',name(),'}')}" class="transparent" type="file" multiple="" rel="F_{$tag}"/>
																</span>
															</span>
														</div>
													</gen:when>
													<!--color.formlist td-->
													<gen:when test="@type = 'color'">
														<div class="input-group form-color-picker" format="hex">
															<input type="text" value="{concat('{',name(),'}')}" format="hex" class="form-control" id="{$tag}_{name()}" name="{$v_name}"/>
															<span class="input-group-addon">
																<i></i>
															</span>
														</div>
													</gen:when>
													<!--textarea.formlist td-->
													<gen:when test="@type = 'textarea'">
														<textarea name="{$v_name}" class="textarea form-control" rel="F_{$tag}">
															<xsl:value-of select="{name()}" disable-output-escaping="yes"/>
														</textarea>
													</gen:when>
													<!--texteditor.formlist td-->
													<gen:when test="@type = 'texteditor'">
														<input type="text" name="{$v_name}" value="{concat('{',name(),'}')}" class="text form-control" rel="F_{$tag}"/>
													</gen:when>
													<!--text.formlist td /  plaintext.formlist td-->
													<gen:when test="@type = 'text' or @type = 'plaintext'">
														<input type="hidden" name="{$v_name}" value="{concat('{',name(),'}')}" rel="F_{$tag}"/>
														<p item-name="{name()}" item-type="plaintext">
															<xsl:value-of select="{name()}"/>
														</p>
													</gen:when>
													<!--lookup.formlist td-->
													<gen:when test="contains(@type,'LOOKUP')">
														<div class="input-group">
															<input type="text" name="{$v_name}" value="{concat('{',name(),'}')}" class="{@type} form-control" rel="F_{$tag}"/>
															<gen:variable name="lname" select="concat('../../label/',name(),'/@name')"/>
															<gen:variable name="quto">'</gen:variable>
															<gen:call-template name="gen_lookup">
																<gen:with-param name="type" select="@type"/>
																<gen:with-param name="name" select=" concat ( 'concat(' , $lname, ',',$quto,'_fk', $quto,')' ) "/>
																<gen:with-param name="js_lookup" select="concat('../../../../lookup/',name())"/>
																<gen:with-param name="id" select=" concat ( 'concat(' , $lname, ',',$quto,'_fk', $quto,')' ) "/>
															</gen:call-template>
														</div>
													</gen:when>
													<gen:otherwise>
														<input type="{@type}" name="{$v_name}" value="{concat('{',name(),'}')}" class="{@type} form-control" rel="F_{$tag}"/>
													</gen:otherwise>
												</gen:choose>
											</div>
										</td>
									</xsl:if>
								</gen:for-each>
								<xsl:if test="not(@nodelete) or not(@noupdate)">
									<td class="table-btn" data-row="{position()}">
										<xsl:if test="not(@nodelete)">
											<span class="formlist-row-remove btn-danger" rel="{$tag}">
												<i class="fa fa-times"></i>
											</span>
										</xsl:if>
									</td>
								</xsl:if>
								<gen:for-each select="$fields[@type = 'hidden']">
									<gen:variable name="tagVal" select="concat('{',name(),'}')"/>
									<gen:variable name="tagValDesc" select="concat('{',name(),'_desc}')"/>
									<input type="hidden" name="{@name}_fk" value="{$tagVal}" />
									<input type="hidden" name="{@name}_fk_desc" value="{$tagValDesc}" />
								</gen:for-each>
							</tr>
						</xsl:for-each>
					</tbody>
				</table>
			</div>
		</div>
	</gen:template>
	<!--/GEN formlist-->
	<!--GEN separatorlist-->
	<gen:template name="gen_separatorlist" mode="gen_separatorlist" match="*">
		<gen:param name="fields"/>
		<gen:variable name="type" select="@type"/>
		<gen:variable name="tag" select="name()"/>
		<gen:variable name="dialog">
			<gen:if test="$type='separatordialog'">true</gen:if>
		</gen:variable>
		<div class="box gen-container-item gen-item-boxed" item-name="{name()}">
			<div class="box-header">
				<h3 class="box-title">
					<xsl:value-of select="rows/content/form/label/{name()}"/>
				</h3>
			</div>
			<div class="box-body IGRP-separatorlist" tag="{name()}" dialog="{$dialog}">
				<div class="splist-form-holder">
					<div class="splist-form" role="form">
						<xsl:apply-templates mode="form-hidden" select="rows/content/form/value">
							<xsl:with-param name="rel" select="'{$tag}'"/>
						</xsl:apply-templates>
						<gen:call-template name="gen_form_contents">
							<gen:with-param name="path" select="'rows/content/form'"/>
							<gen:with-param name="fields" select="$fields"/>
							<gen:with-param name="isSeparatorList" select="'true'"/>
							<gen:with-param name="fieldsClass" select="'no-validation'"/>
						</gen:call-template>
					</div>
				</div>
				<div class="table-box box-body box-table-contents splist-table">
					<table rel="T_{name()}" id="{name()}" class="table table-striped gen-data-table">
						<thead>
							<tr>
								<gen:for-each select="$fields[@type!='hidden']">
									<gen:variable name="ftype" select="@type"/>
									<xsl:if test="rows/content/form/table/{$tag}/label/{name()}">
										<xsl:if test="not(rows/content/form/table/{$tag}/label/{name()}/@visible)">
											<th align="" item-name="{name()}">
												<xsl:value-of select="rows/content/form/table/{$tag}/label/{name()}"/>
											</th>
										</xsl:if>
									</xsl:if>
								</gen:for-each>
								<th class="table-btn">
									<a class="table-row-add btn">
										<i class="fa fa-plus"></i>
									</a>
								</th>
							</tr>
						</thead>
						<tbody>
							<xsl:for-each select="rows/content/form/table/{$tag}/value/row[not(@total='yes')]">
								<tr>
									<gen:for-each select="$fields[@type!='hidden']">
										<xsl:if test="{name()}">
											<xsl:if test="not({name()}/@visible)">
												<td field="{name()}" item-name="{name()}" data-row="{'{position()}'}" class="{@type}">
													<gen:choose>
														<gen:when test="@type='link'">
															<xsl:choose>
																<xsl:when test="{name()} != ''">
																	<a href="{concat('{',name(),'}')}" class="link bClick">
																		<gen:call-template name="gen_target_attr"/>
																		<xsl:value-of select="{name()}_desc" disable-output-escaping="yes"/>
																	</a>
																</xsl:when>
																<xsl:otherwise>
																	<xsl:value-of select="{name()}_desc" disable-output-escaping="yes"/>
																</xsl:otherwise>
															</xsl:choose>
														</gen:when>
														<gen:when test="@type='color'">
															<span class="separator-list-td-val color" style="background-color:{concat('{',name(),'_desc}')};height: 10px;display: block;"></span>
															<input type="hidden" name="p_{name()}_fk" value="{ concat('{',name(),'}') }"/>
															<input type="hidden" name="p_{name()}_fk_desc" value="{ concat('{',name(),'_desc}') }"/>
														</gen:when>
														<gen:otherwise>
															<span class="separator-list-td-val">
																<xsl:value-of select="{name()}_desc" disable-output-escaping="yes"/>
															</span>
														</gen:otherwise>
													</gen:choose>
													<input type="hidden" name="p_{name()}_fk" value="{concat('{',name(),'}')}"/>
													<input type="hidden" name="p_{name()}_fk_desc" value="{concat('{',name(),'_desc}')}"/>
												</td>
											</xsl:if>
										</xsl:if>
									</gen:for-each>
									<td data-row="{'{position()}'}" class="table-btn">
										<xsl:if test="not(@nodelete)">
											<span class="table-row-edit btn btn-default" rel="{$tag}">
												<i class="fa fa-pencil"></i>
											</span>
										</xsl:if>
										<xsl:if test="not(@noupdate)">
											<span class="table-row-remove btn btn-danger" rel="{$tag}">
												<i class="fa fa-times"></i>
											</span>
										</xsl:if>
									</td>
									<input type="hidden" class="sl-row-id" name="p_{$tag}_id" value="{concat('{',$tag,'_id}')}" />
									<gen:for-each select="$fields[@type = 'hidden']">
										<gen:variable name="tagVal" select="concat('{',name(),'}')"/>
										<gen:variable name="tagValDesc" select="concat('{',name(),'_desc}')"/>
										<input type="hidden" name="{@name}_fk" value="{$tagVal}" />
										<input type="hidden" name="{@name}_fk_desc" value="{$tagValDesc}" />
									</gen:for-each>
								</tr>
							</xsl:for-each>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</gen:template>
	<!--/GEN separatorlist-->
	<!--GEN form-->
	<gen:template name="gen_form">
		<gen:param name="tag" select="'form'"/>
		<gen:param name="fields"/>
		<gen:variable name="path" select="concat('rows/content/',$tag)"/>
		<gen:variable name="isTab" select="rows/content/form/@tab='true'"/>
		<gen:if test="$tag = 'form' and rows/content/form/tools-bar">
			<xsl:if test="rows/content/form/tools-bar/item">
				<div class="toolsbar-holder boxed gen-container-item clearfix">
					<div class="btns-holder clearfix pull-right" role="group">
						<xsl:apply-templates select="rows/content/form/tools-bar" mode="gen-buttons">
							<xsl:with-param name="use-fa" select="'false'"/>
							<xsl:with-param name="vertical" select="'true'"/>
							<xsl:with-param name="type" select="'tools-bar'"/>
						</xsl:apply-templates>
					</div>
				</div>
			</xsl:if>
		</gen:if>
		<div class="box igrp-forms gen-container-item">
			<div class="box-body">
				<gen:attribute name="first-type-container">
					<gen:if test="$fields[1][@container='true']">true</gen:if>
				</gen:attribute>
				<gen:attribute name="last-type-container">
					<gen:if test="$fields[last()][@container='true']">true</gen:if>
				</gen:attribute>
				<div role="form" gen-id="drop-zone">
					<xsl:apply-templates mode="form-hidden" select="{$path}/value"/>
					<gen:call-template name="gen_form_contents">
						<gen:with-param name="path" select="$path"/>
						<gen:with-param name="fields" select="$fields"/>
						<gen:with-param name="isTab" select="$isTab"/>
					</gen:call-template>
				</div>
			</div>
			<gen:if test="$tag = 'filter'">
				<xsl:apply-templates select="{$path}/tools-bar" mode="form-buttons">
					<xsl:with-param name="use-fa" select="'false'"/>
					<xsl:with-param name="fixed-target" select="'submit'"/>
				</xsl:apply-templates>
			</gen:if>
		</div>
	</gen:template>
	<!--/GEN form-->
	<!--formfields-->
	<gen:template name="gen_form_contents">
		<gen:param name="path"/>
		<gen:param name="fields"/>
		<gen:param name="isSeparatorList"/>
		<gen:param name="isTab"/>
		<gen:param name="fieldsClass"/>
		<gen:if test="$isTab "></gen:if>
		<gen:for-each select="$fields[not(@type='hidden')]">
			<gen:variable name="labelPath" select="concat($path,'/label/',name())"/>
			<gen:variable name="valuePath" select="concat($path,'/value/',name())"/>
			<gen:variable name="tag" select="name()"/>
			<gen:variable name="nameAttr" select="concat('{',$valuePath,'/@name','}')"/>
			<gen:variable name="type" select="@type"/>
			<gen:if test="$isTab and $type='separator'">
				<gen:if test="position() &gt; 1">
					<gen:text disable-output-escaping="yes">&lt;/div&gt;</gen:text>
				</gen:if>
				<gen:text disable-output-escaping="yes">&lt;div class="form_tab"&gt;</gen:text>
			</gen:if>
			<xsl:if test="{$labelPath}">
				<gen:choose>
					<!-- form elements -->
					<gen:when test="$type!='hidden' and $type!='separatorlist' and $type!='separatordialog' and $type!='formlist' and $type!='list' and $type!='iframe' ">
						<gen:choose>
							<!-- plaintext.form -->
							<gen:when test="$type='plaintext'">
								<div class="col-md-12 clearfix clear form-group" item-name="{name()}" item-type="{$type}">
									<xsl:value-of select="{$valuePath}"/>
								</div>
							</gen:when>
							<!-- biometric.form -->
							<gen:when test="$type='biometric_view'">
								<div style="display:none;">bio to implement</div>
							</gen:when>
							<!-- date.form -->
							<gen:when test="$type='date'">
								<div class="form-group col-md-3" item-name="{name()}" item-type="{$type}">
									<label for="{$nameAttr}">
										<xsl:value-of select="{$labelPath}"/>
									</label>
									<div class="input-group">
										<input type="text" value="{concat('{',$valuePath,'}')}" class="form-control gen-date {$fieldsClass}" id="{$nameAttr}" name="{$nameAttr}" format="IGRP_datePicker"/>
										<span class="input-group-btn gen-date-icon">
											<span class="btn btn-default">
												<i class="fa fa-calendar"></i>
											</span>
										</span>
									</div>
								</div>
							</gen:when>
							<!-- select.form -->
							<gen:when test="contains($type,'select')">
								<gen:variable name="change">
									<gen:if test="contains($type,'change')">IGRP_change</gen:if>
								</gen:variable>
								<div class="form-group col-md-3 " item-name="{name()}" item-type="select">
									<label for="{$nameAttr}">
										<xsl:value-of select="{$labelPath}"/>
									</label>
									<select class="form-control select2 {$change} {$fieldsClass}" id="{$nameAttr}" name="{$nameAttr}" >
										<xsl:for-each select="{$path}/list/{name()}/option">
											<option value="{'{value}'}">
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
							</gen:when>
							<!-- link.form -->
							<gen:when test="$type='link'">
								<div class="form-group col-md-3" item-name="{name()}" item-type="{$type}">
									<gen:if test="$isSeparatorList = 'true'">
										<input type="hidden" label="{concat('{',$labelPath,'}')}" name="{$nameAttr}" value="{concat('{',$valuePath,'}')}" style="display:none;" class="{$fieldsClass}"/>
									</gen:if>
									<a href="{concat('{',$valuePath,'}')}" class="link form-link">
										<gen:call-template name="gen_target_attr"/>
										<xsl:value-of select="{$labelPath}"/>
									</a>
								</div>
							</gen:when>
							<!-- textarea.form -->
							<gen:when test="$type='textarea'">
								<div class="form-group col-md-3" item-name="{name()}" item-type="{$type}">
									<label for="{$nameAttr}">
										<xsl:value-of select="{$labelPath}"/>
									</label>
									<textarea name="{$nameAttr}" class="textarea form-control {$fieldsClass}" >
										<xsl:value-of select="{$valuePath}"/>
									</textarea>
								</div>
							</gen:when>
							<!-- checkbox.form -->
							<gen:when test="$type='checkbox'">
								<div class="col-md-3" item-name="{name()}" item-type="{$type}">
									<div class="form-group">
										<div class="checkbox form-check-offset">
											<label>
												<input type="checkbox" name="{$nameAttr}" value="1" class="checkbox {$fieldsClass}">
													<xsl:if test="{$valuePath} = '1'">
														<xsl:attribute name="checked">checked</xsl:attribute>
													</xsl:if>
												</input>
												<xsl:value-of select="{$labelPath}"/>
											</label>
										</div>
									</div>
								</div>
							</gen:when>
							<!-- radio.form -->
							<gen:when test="$type='radio'">
								<div class="col-md-3" item-name="{name()}" item-type="{$type}">
									<div class="form-group">
										<div class="radio form-check-offset">
											<label>
												<input type="radio" name="{$nameAttr}" value="1" class="radio {$fieldsClass}">
													<xsl:if test="{$valuePath} = '1'">
														<xsl:attribute name="checked">checked</xsl:attribute>
													</xsl:if>
												</input>
												<xsl:value-of select="{$labelPath}"/>
											</label>
										</div>
									</div>
								</div>
							</gen:when>
							<!-- checkboxlist.form -->
							<gen:when test="$type='checkboxlist'">
								<div class="col-md-3" item-name="{name()}" item-type="{$type}">
									<div class="form-group">
										<label for="{$nameAttr}">
											<xsl:value-of select="{$labelPath}"/>
										</label>
										<xsl:for-each select="{$path}/list/{name()}/option">
											<div class="checkbox">
												<label>
													<input type="checkbox" name="{'{../@name}'}" value="{'{value}'}" class="checkboxlist {$fieldsClass}" label="{'{text}'}">
														<xsl:if test="@selected='true'">
															<xsl:attribute name="checked">checked</xsl:attribute>
														</xsl:if>
													</input>
													<span>
														<xsl:value-of select="text"/>
													</span>
												</label>
											</div>
										</xsl:for-each>
									</div>
								</div>
							</gen:when>
							<!-- radio.form -->
							<gen:when test="$type='radiolist'">
								<div class="col-md-3" item-name="{name()}" item-type="{$type}">
									<div class="form-group">
										<label for="{$nameAttr}">
											<xsl:value-of select="{$labelPath}"/>
										</label>
										<xsl:for-each select="{$path}/list/{name()}/option">
											<div class="radio">
												<label>
													<input type="radio" name="{'{../@name}'}" value="{'{value}'}" class="checkboxlist {$fieldsClass}" label="{'{text}'}">
														<xsl:if test="@selected='true'">
															<xsl:attribute name="checked">checked</xsl:attribute>
														</xsl:if>
													</input>
													<span>
														<xsl:value-of select="text"/>
													</span>
												</label>
											</div>
										</xsl:for-each>
									</div>
								</div>
							</gen:when>
							<!-- file.form -->
							<gen:when test="$type='file'">
								<div class="form-group col-md-3" item-name="{name()}" item-type="{$type}">
									<label for="{$nameAttr}">
										<xsl:value-of select="{$labelPath}"/>
									</label>
									<div class="input-group">
										<input type="text" class="form-control not-form {$fieldsClass}" readonly=""/>
										<span class="input-group-btn">
											<span class="btn btn-default file-btn-holder">
												<i class="fa fa-upload"></i>
												<input id="{$nameAttr}" name="{$nameAttr}" value="{concat('{',$valuePath,'}')}" class="transparent {$fieldsClass}" type="file" multiple=""/>
											</span>
										</span>
									</div>
								</div>
							</gen:when>
							<!-- color.form -->
							<gen:when test="$type='color'">
								<div class="form-group col-md-3" item-name="{name()}" item-type="{$type}">
									<label for="{$nameAttr}">
										<xsl:value-of select="{$labelPath}"/>
									</label>
									<div class="input-group form-color-picker" id="{$nameAttr}_colorp">
										<input type="text" value="{concat('{',$valuePath,'}')}" format="hex" class="form-control {$fieldsClass}" id="{$nameAttr}" name="{$nameAttr}"/>
										<span class="input-group-addon">
											<i></i>
										</span>
									</div>
								</div>
							</gen:when>
							<!-- image.form / img.form-->
							<gen:when test="$type='image' or $type='img' ">
								<div class="form-group col-md-3" item-name="{name()}" item-type="{$type}">
									<img style="height:auto;width:100%;position:relative;display:block;" src="{concat('{',$valuePath,'}')}"/>
								</div>
							</gen:when>
							<!-- separator.form -->
							<gen:when test="$type='separator' or $type='group'">
								<div class="box-head subtitle clearfix" item-name="{name()}" item-type="{$type}">
									<span>
										<xsl:value-of select="{$labelPath}"/>
									</span>
								</div>
							</gen:when>
							<!-- virtualkeyboard.form -->
							<gen:when test="$type='virtualkeyboard'">
								<div>vkeyboard to implement!</div>
							</gen:when>
							<!-- texteditor.form -->
							<gen:when test="$type='texteditor'">
								<div class="form-group col-md-12" item-name="{name()}" item-type="{$type}">
									<label for="{$nameAttr}">
										<xsl:value-of select="{$labelPath}"/>
									</label>
									<textarea id="{$nameAttr}" name="{$nameAttr}" class="gen-texteditor {$fieldsClass}">
										<xsl:value-of select="{$valuePath}" disable-output-escaping="yes"/>
									</textarea>
								</div>
							</gen:when>
							<!-- lookup.form -->
							<gen:when test="contains($type,'LOOKUP')">
								<div class="form-group col-md-3" item-name="{name()}" item-type="{$type}">
									<label for="{$nameAttr}">
										<xsl:value-of select="{$labelPath}"/>
									</label>
									<div class="input-group">
										<input type="text" value="{concat('{',$valuePath,'}')}" class="form-control gen-lookup {$fieldsClass}" id="{$nameAttr}" name="{$nameAttr}" />
										<gen:call-template name="gen_lookup">
											<gen:with-param name="type" select="$type"/>
											<gen:with-param name="name" select="concat($valuePath,'/@name')"/>
											<gen:with-param name="js_lookup" select="concat($path,'/lookup/',name())"/>
											<gen:with-param name="id" select="concat($valuePath,'/@name')"/>
										</gen:call-template>
									</div>
								</div>
							</gen:when>
							<!-- default.form -->
							<gen:otherwise>
								<div class="form-group col-md-3" item-name="{name()}" item-type="{$type}">
									<label for="{$nameAttr}">
										<xsl:value-of select="{$labelPath}"/>
									</label>
									<input type="{$type}" value="{concat('{',$valuePath,'}')}" class="form-control {$fieldsClass}" id="{$nameAttr}" name="{$nameAttr}"></input>
								</div>
							</gen:otherwise>
						</gen:choose>
					</gen:when>
					<!-- container elements -->
					<gen:otherwise>
						<!-- separatorlist.form -->
						<gen:if test="$type='separatorlist' or $type='separatordialog'">
							<gen:apply-templates select="." mode="gen_separatorlist">
								<gen:with-param name="fields" select="//rows/content/form/label/*[@rel = $tag and @type !='group' and not(@visible) and not(substring(name(),(string-length(name())-4),5) =  '_desc') and not(substring(name(),(string-length(name())-5),6) =  '_check') ]"/>
							</gen:apply-templates>
						</gen:if>
						<!-- formlist.form -->
						<gen:if test="$type='formlist'">
							<gen:apply-templates select="." mode="gen_formlist">
								<gen:with-param name="fields" select="//rows/content/form/label/*[@rel = $tag and @type !='group' and not(@visible) and not(substring(name(),(string-length(name())-4),5) =  '_desc') and not(substring(name(),(string-length(name())-5),6) =  '_check') ]"/>
							</gen:apply-templates>
						</gen:if>
						<!-- list.form -->
						<gen:if test="$type='list'">
							<gen:choose>
								<gen:when test="@style">
									<div class="table-btn-list self-boxed-item">
										<div class="self-boxed-inner" list-style="{@style}">
											<gen:choose>
												<gen:when test="@style='buttonlist'">
													<gen:call-template name="gen_table_buttonlist">
														<gen:with-param name="tag" select="$tag"/>
													</gen:call-template>
												</gen:when>
											</gen:choose>
										</div>
									</div>
								</gen:when>
								<gen:otherwise>
									<gen:apply-templates select="." mode="gen_table">
										<gen:with-param name="fields" select="//rows/content/form/label/*[@rel = $tag]"/>
										<gen:with-param name="tablePath" select="concat('rows/content/form/table/',$tag)"/>
									</gen:apply-templates>
								</gen:otherwise>
							</gen:choose>
						</gen:if>
					</gen:otherwise>
				</gen:choose>
			</xsl:if>
		</gen:for-each>
		<gen:if test="$isTab ">
			<gen:text disable-output-escaping="yes">&lt;/div&gt;</gen:text>
		</gen:if>
	</gen:template>
	<!--GEN toolsbar-->
	<gen:template name="gen_toolsbar">
		<gen:param name="menuPath"/>
		<xsl:if test="{$menuPath}/item">
			<div class="toolsbar-holder">
				<div class="btns-holder pull-right" role="group">
					<xsl:apply-templates select="{$menuPath}" mode="gen-buttons"/>
				</div>
			</div>
		</xsl:if>
	</gen:template>
	<!--/GEN toolsbar-->
	<!-- GEN MENU -->
	<gen:template name="gen_menu">
		<div class="gen-menu-wrapper">
			<gen:choose>
				<gen:when test="rows/content/menu/group">
					<xsl:if test="rows/content/menu/group/item">
						<xsl:apply-templates select="rows/content/menu" mode="gen-buttons-group">
							<xsl:with-param name="use-fa" select="'false'"/>
							<xsl:with-param name="vertical" select="'true'"/>
						</xsl:apply-templates>
					</xsl:if>
				</gen:when>
				<gen:otherwise>
					<xsl:if test="rows/content/menu/item">
						<div class="toolsbar-holder boxed gen-container-item clearfix">
							<div class="btns-holder clearfix" role="group">
								<xsl:apply-templates select="rows/content/menu" mode="gen-buttons">
									<xsl:with-param name="use-fa" select="'false'"/>
									<xsl:with-param name="vertical" select="'false'"/>
								</xsl:apply-templates>
							</div>
						</div>
					</xsl:if>
				</gen:otherwise>
			</gen:choose>
		</div>
	</gen:template>
	<!--GEN view-->
	<gen:template name="gen_view">
		<gen:variable name="img" select="'{$hasImg}'"/>
		<gen:variable name="imgSrc" select="'{$viewImg}'"/>
		<xsl:if test="rows/content/view">
			<xsl:variable name="viewImg" select="rows/content/view/value/view_foto"/>
			<xsl:variable name="hasImg">
				<xsl:if test="$viewImg">true</xsl:if>
			</xsl:variable>
			<div class="box clearfix view-block gen-container-item" has-img="{$img}" item-name="gen_view">
				<div class="box-body">
					<xsl:if test="$hasImg = 'true'">
						<img src="{$imgSrc}"/>
					</xsl:if>
					<div class="view-body clearfix " gen-id="drop-zone">
						<gen:for-each select="rows/content/view/label/*[name() != 'view_foto']">
							<xsl:if test="rows/content/view/label/{name()}">
								<gen:choose>
									<gen:when test="@type = 'link'">
										<gen:variable name="viewlink" select="concat('{rows/content/view/value/',name(),'}')"/>
										<div class="view-item">
											<a href="{$viewlink}">
												<gen:call-template name="gen_target_attr"/>
												<xsl:value-of select="rows/content/view/label/{name()}"/>
											</a>
										</div>
									</gen:when>
									<gen:when test="@type = 'separator'">
										<div class="view-item view-separator">
											<span class="view">
												<xsl:value-of select="rows/content/view/label/{name()}"/>
											</span>
										</div>
									</gen:when>
									<gen:otherwise>
										<div class="view-item">
											<span class="view-label">
												<xsl:value-of select="rows/content/view/label/{name()}"/>
											</span>
											<span class="view-value">
												<xsl:value-of select="rows/content/view/value/{name()}"/>
											</span>
										</div>
									</gen:otherwise>
								</gen:choose>
							</xsl:if>
						</gen:for-each>
					</div>
				</div>
			</div>
		</xsl:if>
	</gen:template>
	<!--/GEN view-->
	<!--GEN UTILS-->
	<!--lookup template-->
	<gen:template name="gen_lookup">
		<gen:param name="type" select="'LOOKUP'"/>
		<gen:param name="name"/>
		<gen:param name="js_lookup"/>
		<gen:param name="id"/>
		<xsl:call-template name="lookup-tool">
			<gen:variable name="isDefault">
				<gen:if test="$type='LOOKUP'">true</gen:if>
			</gen:variable>
			<gen:variable name="page">
				<gen:choose>
					<gen:when test="$isDefault='true'">rows/page</gen:when>
					<gen:otherwise>'LOOKUP'</gen:otherwise>
				</gen:choose>
			</gen:variable>
			<gen:variable name="action">
				<gen:choose>
					<gen:when test="$isDefault='true'">LOOKUP</gen:when>
					<gen:otherwise>
						<gen:value-of select="$type"/>
					</gen:otherwise>
				</gen:choose>
			</gen:variable>
			<xsl:with-param name="page" select="{$page}"/>
			<gen:if test="$isDefault='true'">
				<xsl:with-param name="ad_hoc" select="'1'"/>
			</gen:if>
			<xsl:with-param name="action" select="'{$action}'"/>
			<xsl:with-param name="name" select="{$name}"/>
			<xsl:with-param name="js_lookup" select="{$js_lookup}"/>
			<xsl:with-param name="input-id" select="{$id}"/>
			<xsl:with-param name="btnClass" select="'primary'"/>
		</xsl:call-template>
	</gen:template>
	<!--formfields attr-->
	<gen:template name="gen_fields_attr"></gen:template>
	<gen:template name="gen_target_attr">
		<gen:attribute name="target">
			<gen:choose>
				<gen:when test="@target">
					<gen:value-of select="@target"/>
				</gen:when>
				<gen:otherwise>_blank</gen:otherwise>
			</gen:choose>
		</gen:attribute>
	</gen:template>
	<!-- TABLE TEMPLATES VARIATIONS -->
	<gen:template name="gen_table_buttonlist">
		<gen:param    name="tag"/>
		<gen:variable name="fields" select="//rows/content/form/label/*[@rel=$tag]"/>
		<gen:variable name="tablePath" select="concat('rows/content/form/table/',$tag)"/>
		<gen:variable name="linkTag"    select="name($fields[@type='link'])"/>
		<gen:variable name="iconTag"    select="name($fields[@type='icon'])"/>
		<gen:variable name="bgColorTag" select="name($fields[@type='color'])"/>
		<gen:variable name="sizeTag"    select="name($fields[@type='size'])"/>
		<gen:choose>
			<gen:when test="$linkTag">
				<xsl:for-each select="{$tablePath}/value/row">
					<div class="btn-list-item col-sm-6 col-md-3">
						<div class="card">
							<a href="{concat('{',$linkTag,'}')}" class="card-container">
								<gen:call-template name="gen_target_attr"/>
								<gen:attribute name="style">
									<gen:if test="$bgColorTag">
										<gen:value-of select="concat('background: {',$bgColorTag,'};')"/>
									</gen:if>
								</gen:attribute>
								<gen:if test="$iconTag">
									<div class="card-icon">
										<i class="fa fa-{concat('{',$iconTag,'}')}"></i>
									</div>
								</gen:if>
								<span class="card-text txt-ellipsis">
									<xsl:value-of select="{$linkTag}_desc"/>
								</span>
							</a>
						</div>
					</div>
				</xsl:for-each>
			</gen:when>
			<gen:otherwise>[link] is required for buttonList component!</gen:otherwise>
		</gen:choose>
	</gen:template>
	<!-- INCLUDES TEMPLATES -->
	<!--css-->
	<gen:template name="gen_css_includes">
		<link rel="stylesheet" type="text/css" href="{$pathAttr}/themes/old-gen.css"/>
		<link rel="stylesheet" type="text/css" href="{$pathAttr}/core/igrp/form/igrp.forms.css"/>
		<link rel="stylesheet" type="text/css" href="{$pathAttr}/core/igrp/table/dataTables.bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="{$pathAttr}/core/igrp/table/igrp.tables.css"/>
		<gen:if test="rows/content/menu">
			<link rel="stylesheet" type="text/css" href="{$pathAttr}/core/igrp/toolsbar/toolsbar.css"/>
		</gen:if>
		<gen:if test="rows/content/view">
			<link rel="stylesheet" type="text/css" href="{$pathAttr}/plugins/view/igrp.view.css"/>
		</gen:if>
		<gen:if test="rows/content/form/label/*/@style = 'buttonlist'">
			<link rel="stylesheet" type="text/css" href="{$pathAttr}/plugins/tablefx/buttonlist/btnlist.css"/>
		</gen:if>
		<gen:if test="rows/content/*/label/*[@type='separatorlist'] or rows/content/*/label/*[@type='separatordialog']">
			<link rel="stylesheet" type="text/css" href="{$pathAttr}/plugins/separatorlist/igrp.separatorlist.css"/>
		</gen:if>
		<gen:if test="rows/content/*/label/*[@type='formlist']">
			<link rel="stylesheet" type="text/css" href="{$pathAttr}/plugins/formlist/igrp.formlist.css"/>
		</gen:if>
		<gen:if test="rows/content/*/label/*[@type='date']">
			<link rel="stylesheet" type="text/css" href="{$pathAttr}/plugins/datetimepicker/css/datetimepicker.css"/>
		</gen:if>
		<gen:if test="rows/content/*/label/*[@type='select']">
			<link rel="stylesheet" type="text/css" href="{$pathAttr}/plugins/select2/select2.min.css"/>
			<link rel="stylesheet" type="text/css" href="{$pathAttr}/plugins/select2/select2.style.css"/>
		</gen:if>
		<gen:if test="rows/content/*/label/*[@type='color']">
			<link rel="stylesheet" type="text/css" href="{$pathAttr}/core/igrp/table/table-colors.css"/>
			<link rel="stylesheet" type="text/css" href="{$pathAttr}/plugins/colorpicker/css/bootstrap-colorpicker.min.css"/>
		</gen:if>
	</gen:template>
	<!--js-->
	<gen:template name="gen_js_includes">
		<script type="text/javascript" src="{$pathAttr}/core/igrp/form/igrp.forms.js"></script>
		<!-- <script type="text/javascript" src="{$pathAttr}/containers/table/dataTables.bootstrap.min.js"></script> -->
		<!-- TABLE JS INCLUDES -->
		<gen:if test="rows/content/table/context-menu">
			<script type="text/javascript" src="{$pathAttr}/core/igrp/table/bootstrap-contextmenu.js"></script>
			<script type="text/javascript" src="{$pathAttr}/core/igrp/table/table.contextmenu.js"></script>
		</gen:if>
		<!--SEPARATOR LIST JS INCLUDES-->
		<gen:if test="rows/content/*/label/*[@type='separatorlist'] or rows/content/*/label/*[@type='separatordialog']">
			<script type="text/javascript" src="{$pathAttr}/plugins/separatorlist/igrp.separatorlist.js"></script>
		</gen:if>
		<!--FORMLIST JS INCLUDES-->
		<gen:if test="rows/content/*/label/*[@type='formlist']">
			<script type="text/javascript" src="{$pathAttr}/plugins/formlist/igrp.formlist.js"></script>
		</gen:if>
		<!--DATE JS INCLUDES-->
		<gen:if test="rows/content/*/label/*[@type='date']">
			<script type="text/javascript" src="{$pathAttr}/plugins/datetimepicker/js/datetimepicker.js"></script>
			<script type="text/javascript" src="{$pathAttr}/plugins/datetimepicker/js/dtp.init.js"></script>
		</gen:if>
		<!--SELECT JS INCLUDES-->
		<gen:if test="rows/content/*/label/*[@type='select']">
			<script type="text/javascript" src="{$pathAttr}/plugins/select2/select2.full.min.js"></script>
			<script type="text/javascript" src="{$pathAttr}/plugins/select2/select2.init.js"></script>
		</gen:if>
		<!--COLOR JS INCLUDES-->
		<gen:if test="rows/content/*/label/*[@type='color']">
			<script type="text/javascript" src="{$pathAttr}/plugins/colorpicker/js/bootstrap-colorpicker.js"></script>
			<script type="text/javascript" src="{$pathAttr}/plugins/colorpicker/colorpicker.init.js"></script>
		</gen:if>
		<!--TEXTEDITOR JS INCLUDES-->
		<gen:if test="rows/content/*/label/*[@type='texteditor']">
			<script type="text/javascript" src="{$pathAttr}/core/ckeditor/ckeditor.js"></script>
		</gen:if>
		<!--LOOKUP JS INCLUDES-->
		<gen:if test="rows/content/*/label/*[contains(@type,'LOOKUP')]">
			<script type="text/javascript" src="{$pathAttr}/plugins/lookup/igrp.lookup.js"></script>
		</gen:if>
	</gen:template>
	<!--xsl-->
	<gen:template name="gen_xsl_includes">
		<xsl:include href="../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=20150615"/>
		<xsl:include href="../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=20150615"/>
		<xsl:include href="../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1463090556311"/>
		<xsl:include href="../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1463090556312"/>
		<xsl:include href="../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1463090556312"/>
		<gen:if test="rows/content/form/label/*[@type='list'] or rows/content/table">
			<xsl:include href="../../xsl/tmpl/IGRP-table-utils.tmpl.xsl?v=1463090556312"/>
		</gen:if>
	</gen:template>
</gen:stylesheet>