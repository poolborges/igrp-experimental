	$.fn.to2DOT2 = function(params) {
	var xml = this;

	var viewXML      = xml.find('rows content > view'),
		menuXML      = xml.find('rows content > menu'),
		formXML      = xml.find('rows content > form'),
		filterXML    = xml.find('rows content > filter'),
		tableXML     = xml.find('rows content > table');

	var hasLeft        = xml.find('rows content > *[align="left"], rows content group[align="left"]');
	var hasRight       = xml.find('rows content > *[align="right"], rows content group[align="right"]');  
	var columnsCount   = hasLeft[0] && hasRight[0] ? 3 : (hasLeft[0] || hasRight[0]) ? 2 : 1;
	var mainColumnSize = columnsCount == 1 ?  'col-1-1' : columnsCount == 2 ? 'col-9-12' : 'col-1-2';

	var columnsJson = [];

	if(hasLeft[0]) columnsJson.push({size: 'col-1-4',containers : []});
	
	columnsJson.push({
		size       : mainColumnSize,
		containers : [] 
	});

	if(hasRight[0]) columnsJson.push({size: 'col-1-4',containers : []});


	var rtnJson  = {
		rows :[{ columns: columnsJson }],
		plsql:{
			table   : '',
			package : xml.find('rows plsql package_db').text(), 
			html    : xml.find('rows plsql package_html').text(),
			replace : xml.find('rows plsql with_replace').text() == 'true' ? true : false,
			label   : xml.find('rows plsql with_label').text()   == 'true' ? true : false,
			biztalk : xml.find('rows plsql with_biztalk').text() == 'true' ? true : false
		}
	}

	var leftColumnObj  = hasLeft[0] ? rtnJson.rows[0].columns[0] : false;
	
	var mainColumnsObj = columnsCount == 1 || columnsCount == 2 && hasRight[0] ? rtnJson.rows[0].columns[0] : rtnJson.rows[0].columns[1] ;
	
	var rightColumnObj = hasRight[0] ? rtnJson.rows[0].columns[rtnJson.rows[0].columns.length-1] : false;

	//view
	var set = function(){
		//VIEW
		if(viewXML[0]){
			setContainer(Container({
				tag    : 'view_1',
				type   : 'view',
				fields :{
					xml         : viewXML.find('label > *'),
					excludeAttrs: ['required','maxlength']
				}
			}))
		}
		//MENU (SIMPLE / TAB)
		if(menuXML[0]){
			if(menuXML.attr('type') == 'tabmenu'){
				var tabMenuTag = 'gen_tab_menu';
				var groups   = [];
				var fields   = [];
				var position = menuXML.find('group').attr('align');
				//groups
				$.each($('group',menuXML),function(i,g){
					var group = {
						name : $(g).attr('title'),
						id   : "group-"+i
					}
					groups.push(group)
				});
				//fields
				fields = contextMenu($('group item',menuXML),{
					group     : true,
					parentTag : tabMenuTag
				});

				setContainer(Container({
					tag    : tabMenuTag,
					type   : 'tabmenu',
					groups : groups,
					fields : {
						json : fields
					}
				}),position);

			}else{
				var menuTag = 'gen_menu';
				setContainer(Container({
					tag    :menuTag,
					type   :'simplemenu',
					fields :{
						json : contextMenu($('menu > *',xml),{
							parentTag:menuTag
						})
					}
				}));
			}	
		}
		//FORM ELEMENTS && OTHER FORM CONTAINERS (container="true")
		if(formXML[0]){
			//form toolsbar
			if(formXML.find('> tools-bar item')[0]){
				var ftbartag   = 'gen_form_toolsbar';

				setContainer(Container({
					tag    :ftbartag,
					type   :'toolsbar',
					fields :{
						json : contextMenu(formXML.find('> tools-bar item'),{
							parentTag:ftbartag
						})
					}
				}));
			}
			var formTag    = 'gen_form_1';
			var formFields = formXML.find('> label *:not([container="true"]):not([rel])');
			//console.log(formFields)
			if(formFields[0]){
				setContainer(Container({
					tag    :formTag,
					type   :'form',
					fields :{
						xml:formFields
					}
				}));
			}
			//other containers
			var oContainers = formXML.find('> label *[container="true"], > label *[type="list"], > label *[type="formlist"], > label *[type="separatorlist"], > label *[type="separatordialog"]');
		
			if(oContainers[0]){
				$.each(oContainers,function(i,c){
					var tag  = c.tagName;
					var type = validateType($(c).attr('type'));
					var title = $(c).text();

					var formListModifies = type == 'formlist' ? {
						texteditor : 'text'
					} : null;

					//console.log(formXML.find('> label *[rel="'+tag+'"]')[0]);

					setContainer(Container({
						tag    : tag,
						type   : type,
						title  : title,
						fields : {
							xml          : formXML.find('> label *[rel="'+tag+'"]'),
							modifyTypes  : formListModifies,
							excludeAttrs : ['rel']
						}
					}));

				})
			}
		}
		//FILTER
		if(filterXML[0]){
			var filterTag = 'filter_1';
			
			var filterCtx = contextMenu($('tools-bar > *',filterXML),{
				parentTag:'filter'
			});

			setContainer(Container({
				tag:filterTag,
				type:'form',
				fields:{
					xml:$('label > *',filterXML)
				},
				contextMenu : filterCtx
			}));
		}
		//TABLE
		if(tableXML[0]){
			//table tools bar
			if($('tools-bar',tableXML)[0]){
				var tBarTag = 'gen_table_toolsbar';
				setContainer(Container({
					tag    :tBarTag,
					type   :'toolsbar',
					fields :{
						json : contextMenu($('tools-bar > *',tableXML),{
							parentTag:tBarTag
						})
					}
				}));
			}
			//table container
			var tableTag = 'gen_table';
			var otherAttrs = {};
			//table contextMenu
			var tableContextMenu = [];
			//filter
			var filter = xml.find('rows content > filter > label > filter_az');

			if(filter[0]){
				otherAttrs.filter = filter.attr('type');
			}

			if($('context-menu',tableXML)[0]){
				tableContextMenu = contextMenu($('context-menu > *',tableXML),{
					parentTag: tableTag
				});
			}

			setContainer(Container({
				tag    : tableTag,
				type   : 'table',
				fields : {
					xml:$('label > *:not([type="field_copy"])',tableXML)
				},
				otherAttrs:otherAttrs,
				contextMenu:tableContextMenu
			}));

			$.each(xml.find('[type="chart"][align="center"]'),function(i,ccharts){
				//console.log(ccharts)
				setContainer(Container({
					tag        : ccharts.tagName,
					type       : 'chart',
					otherAttrs : {
						chart_type:$(ccharts).find('chart_type').text()
					}
				}));
			});

			//LEFT
			if(hasLeft[0]){
				$.each(hasLeft,function(i,c){
					var type = $(c).attr('type');
					if(type == 'chart'){
						var tag = c.tagName;
						setContainer(Container({
							title      : $(c).find('caption').text(),
							tag        : tag,
							type       : type,
							otherAttrs : {
								chart_type:$(c).find('chart_type').text()
							}
						}),'left');

					}
				});
			}
			if(hasRight[0]){
				$.each(hasRight,function(i,c){
					var type = $(c).attr('type');
					if(type == 'chart'){
						var tag = c.tagName;
						setContainer(Container({
							title      : $(c).find('caption').text(),
							tag        : tag,
							type       : type,
							otherAttrs : {
								chart_type:$(c).find('chart_type').text()
							}
						}),'right');

					}
				});
			}
		}
	}

	var contextMenu = function(fields,p){
		var rtn = [];
		var tag = p && p.parentTag ? p.parentTag+'-ctx-' : 'btn-ctx-';
		
		$.each(fields,function(i,f){
			
			//console.log();
			
			var ctx = {
				label  : $(f).find('title').text(),
				tag    : tag+i,
				target : $(f).find('target').text(),
				type   : 'button',
				action : {
					app  : $(f).find('app').text(),
					page : $(f).find('page').text(),
					action : $(f).find('link').text(),
				}
			}

			//console.log(ctx);

			if($(f).find('img')[0])
				ctx.img = $(f).find('img').text();

			if(p.group)
				ctx.group = 'group-'+$(f).parent().index();

			rtn.push(ctx);
		});
		return rtn;
	}

	var Fields = function(fields,p){
		var rtn = [];
		
		var options = p ? p : {
			excludeAttrs: [],
			modifyTypes : null
		};

		$.each(fields.not('[type="hidden"]'),function(i,f){
			if(f.tagName.indexOf('_check') == -1 && f.tagName.indexOf('_desc') == -1){
				var fieldObj = { label : $(f).text(),tag:f.tagName };
				
				$.each(f.attributes,function(i,attr){
					var name = normalizeAttrName(attr.name);

					if(checkAttr(name,options.excludeAttrs))
						fieldObj[name] = validateAttr(attr,options.modifyTypes);
				});

				rtn.push(fieldObj);
			}
		});
		//hidden fields
		$.each(fields.not('[type!="hidden"]'),function(i,f){
			if($(f).attr('type') != 'field_copy'){
				var fieldObj = { 
					tag:f.tagName,
					name:$(f).attr('name'),
					type:'hidden'
				};

				rtn.push(fieldObj);
			}
		});

		return rtn;
	}
	var normalizeAttrName = function(name){
		var rtn = name;
		if(name == 'maxLength') rtn = 'maxlength';
		return rtn;
	}
	var checkAttr = function(attrName,excludes){
		var rtn = true;
		
		if(excludes){
			for(var i = 0; i < excludes.length; i++){
				var exclude = excludes[i];
				if(attrName == exclude ){
					rtn = false;
					break;
				}
			}
		}

		if(attrName.toUpperCase() == 'CODE')
			rtn = false;

		return rtn;
	}

	var validateType = function(type){
		var v = type;
		if(type == 'list') v = 'table';

		return v;
	}

	var validateAttr = function(attr,modifyTypes){
		var value = attr.value;

		if(attr.value == 'true')               
			value = true;
		if(attr.value == 'false')     
			value = false;
		if(attr.name  == 'type' && attr.value == 'color_td')  
			value = 'color';
		if(attr.name  == 'maxlength') 
			value = parseInt(attr.value);
		if(attr.name  == 'type' && attr.value.toLowerCase().indexOf('lookup') != -1) 
			value = 'lookup';
		
		if(modifyTypes && attr.name == 'type'){
			for(var toModify in modifyTypes){
				if (value == toModify)
					value = modifyTypes[toModify];
			}
		}

		return value;
	}

	var Container = function(p) {
		var fieldsOpts = {};
		
		if(p.fields && p.fields.excludeAttrs) fieldsOpts.excludeAttrs = p.fields.excludeAttrs;
		if(p.fields && p.fields.modifyTypes)  fieldsOpts.modifyTypes  = p.fields.modifyTypes;

		var cFields = p.fields &&  p.fields.xml ? Fields(p.fields.xml,fieldsOpts) : 
					  p.fields && p.fields.json ? p.fields.json : [];
		
		var cProps  = {
			tag  : p.tag,
			type : p.type,
		}
		
		if(p.title){
			cProps.title = p.title;
			cProps.hasTitle = true;
		}	

		var container = {
			proprieties : cProps,
			fields      : cFields
		}

		if(p.groups && p.groups[0])
			container.proprieties.groups = p.groups;
		
		if(p.otherAttrs){
			for(var a in p.otherAttrs)
				container.proprieties[a] = p.otherAttrs[a];
		}

		if(p.contextMenu && p.contextMenu[0])
			container.contextMenu = p.contextMenu;
		

		return container;
	}

	var setContainer = function(container,position){
		//var index = idx ? idx : 
		var col = position && position == 'left'  ? leftColumnObj :
				  position && position == 'right' ? rightColumnObj : mainColumnsObj;

		col.containers.push(container);
	}

	set();

	return rtnJson;
}