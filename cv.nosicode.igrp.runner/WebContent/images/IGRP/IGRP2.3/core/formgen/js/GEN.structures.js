var GENSTRUCTURES = function(GEN){
	var STRUC = this;

	//var GEN.UTILS.link_preview = 'http://igrp.teste.gov.cv/images/IGRP';
	//var GEN = VARS.getGen();

	console.log(GEN)

	STRUC.GET = function(p){
		var xml;

		if(p.object && p.object.genType){
			switch(p.object.genType){
				case 'field':
					xml = genFieldStruc(p);
				break;
				case 'container':
					xml = genContainerStruc(p);
				break;
			}
		}

		return xml.replaceAll('&','&amp;');
	}

	var genContainerStruc = function(p){
		var rtn = "";
		var container = p.object;
		
		var tag        = $.trim(container.GET.tag()),
			type       = container.GET.type(),
			title      = container.GET.title ? container.GET.title() : '',
			xmlType    = container.xml.structure ? container.xml.structure : type,
			gType 	   = container.xml.genType ? container.xml.genType : 'container',
			gGroup     = container.xml.genGroup ? 'true' : '',
			//clean 	   = container.xml.clean    ? 'clean="true"' : '',
			//fields     = container.GET.fields(),
			typeAttr   = type,
			
			attributes = ' type="'+type+'" xml-type="'+xmlType+'"';

			if(container.copyOptions){
				var cpackageDB   = container.copyOptions.plsql ? container.copyOptions.plsql.package : '',
					cpackageHTML = container.copyOptions.plsql ? container.copyOptions.plsql.html : '';
				
				attributes+= ' copy="true" copy-tag="'+container.copyOptions.container+'" copy-package-db="'+cpackageDB+'" copy-package-html="'+cpackageHTML+'"'
			}
			
		if(tag){

			if(container.GET.hasTitle && container.GET.hasTitle() && container.GET.title)
				attributes+=' title="'+container.GET.title()+'"';

			if(container.GET.dynamic && container.GET.dynamic())
				attributes+=' dynamic-menu="'+container.GET.dynamic()+'"';

			if(container.xml.genRemote)
				attributes+=' gen-remote="'+container.xml.genRemote+'"';

			if(container.xml.clean)
				attributes+=' gen-clean="true" ';

			for(var p in container.proprieties){
				if(container.proprieties[p].xmlAttr)
					attributes+=' '+p+'="'+container.GET[p]()+'"';
			}

			rtn+='<'+tag+attributes+' gen-type="'+gType+'" gen-group="'+gGroup+'">';

			if(container.xml.getStructure){

				rtn += container.xml.getStructure({
					tag      : tag,
					genType  : gType,
					genGroup : gGroup
				})

			}else{

				
				
				if(container.xml.gen){
					rtn+=container.xml.gen();
				}else{

					if(container.xml.type == 'fields'){
						rtn+=genFields(container);

						if(container.xml.table)
							rtn+=genTable(container);	
						
						else if (container.contextMenu)
							rtn+=genContextMenu(container);
					};

					if(container.xml.type == 'calendar')
						rtn+=genCalendar(container);

					if(container.xml.type == 'group')
						rtn+=genGroup(container);
					
					if(container.xml.type == 'items')
						rtn+=genItems(container.GET.fields());

					if(container.xml.type == 'tabs')
						rtn+=genTabs(container.contents);
					
					if(container.xml.type == 'graphic')
						rtn+=genGraphic(container);

					if(container.xml.type == 'fingerprint')
						rtn+=genFingerPrint(container);

					if(container.GET.type() == 'treemenu')
						rtn+=genTreeMenu(container);

					if(container.xml.type == 'text'){
						var value   = container.GET.text ? container.GET.text() : '';
						rtn+='<fields><'+tag+'_text type="text" name="p_'+tag+'_text" persist="true" maxlength="'+container.GET.maxlength()+'"><value><![CDATA['+value+']]></value></'+tag+'_text></fields>';
					}

					if(container.xml.type == 'map')
						rtn+=genMap(container);
				}

				
			}

			rtn+='</'+tag+'>';

		}

		return rtn;
	}

	var fieldXMLStructute = function(){

	}

	var genFieldStruc = function(p){
		var rtn           = "";
		var field 		  = p.field;
		var tag  		  = field.xml.tag ? field.xml.tag : field.GET.tag(),
			type 		  = field.GET.type(),
			//label         = field.GET.label ? field.GET.label() : '',
			attributesStr = getAttrsArr(field);

		//ALL HIDDEN FIELDS HAVE HIDDEN TAG !!
		/*if(type == 'hidden')
			tag = 'hidden';*/
		//------------------------------------

		if(tag){

			rtn+='<'+tag+attributesStr+'>';
				if(p.label && field.xml.label) 
					rtn+='<label>'+field.GET.label()+'</label>';
				


				if(field.xml.options)
					rtn+=getXMLOptions(field);
				else{

					var value = field.xml.dataValue || DATA.get({type:type,field:p.field});

					if(p.value && field.xml.value) rtn+='<value>'+value+'</value>';
				}
				
				if(field.xml.lookup || field.GET.type() == 'lookup')
					rtn+='<lookup>http://igrp.teste.gov.cv/images/IGRP.bootstrap/app/BOOTSTRAP/xml/lookup.test.xml</lookup>';

				if(field.GET.service && field.GET.service().code)
					rtn+=getFieldServiceMap(field.GET.service());

			rtn += checkRules(field,rtn);

			rtn+='</'+tag+'>';

			

		}

		return rtn;
	}

	var genCalendar = function(container){
		var tag = container.GET.tag(),
			rtn = '<fields>'+
				'<'+tag+'_lang name="p_'+tag+'_lang"  type="text" maxlength="30">'+
					'<label>Linguagem</label>'+
					'<value>pt</value>'+
				'</'+tag+'_lang>'+
				'<'+tag+'_event name="p_'+tag+'_event"  type="link" maxlength="4000">'+
					'<label>Load Eventos</label>'+
					'<value>'+path+'/core/calendar/3.0.1/data/calendar.json</value>'+
				'</'+tag+'_event>'+
				'<'+tag+'_add name="p_'+tag+'_add"  type="link" maxlength="4000">'+
					'<label>add Eventos</label>'+
					'<value></value>'+
				'</'+tag+'_add>'+
				'<'+tag+'_view name="p_'+tag+'_view"  type="text" maxlength="30">'+
					'<label>Default View</label>'+
					'<value></value>'+
				'</'+tag+'_view>'+
				'<'+tag+'_date name="p_'+tag+'_date"  type="date" maxlength="30">'+
					'<label>Default View</label>'+
					'<value></value>'+
				'</'+tag+'_date>'+
			'</fields>';
		
		if(container.contextMenu && container.contextMenu.items[0])
			rtn+=genContextMenu(container);
		
		return rtn;
		//return '<table><label><title>Titulo do Evento</title><start>Inicio do Evento</start><id>ID do Evento</id><end>Fim do Evento</end><bgcolor>Cor de Fundo do Evento simboliza prioridade</bgcolor><description>Descricao do evento</description></label><value><row><title>Long Event</title><start>07-01-2015T10:00:00</start><id>2</id><end>12-01-2015T00:59:59</end><allDay>true</allDay><description>Long Event Long Event Long Event Long Event Long Event Long Event Long Event</description><context-menu><param>p1=linha1</param><param>p2=linha1</param></context-menu></row><row><title>All Day Event</title><start>01-01-2015T10:30:00</start><id>1</id><end>01-01-2015T12:30:00</end><description>All Day Event All Day Event All Day Event All Day Event</description><context-menu><param>p1=linha2</param><param>p2=linha2</param></context-menu></row><row><title>Repeating Event</title><start>09-01-2015T16:00:00</start><description>Repeating Event Repeating Event Repeating Event Repeating Event Repeating Event</description><id>3</id><end>09-01-2015</end><backgroundcolor>#F00000</backgroundcolor><context-menu><param>p1=linha3</param><param>p2=linha3</param></context-menu></row><row><title>Repeating Event</title><start>16-01-2015T16:00:00</start><id>3</id><end>16-01-2015</end><backgroundColor>#F00000</backgroundColor><context-menu><param>p1=linha4</param><param>p2=linha4</param></context-menu></row><row><title>Conferencet</title><start>11-01-2015</start><id>4</id><end>13-01-2015</end><context-menu><param>p1=linha5</param><param>p2=linha5</param><param>ctx_hidden=Relacoes,Anexar Documetos,Efectuar Pagamento,Relacoes Eliminar</param></context-menu></row><row><title>Meeting</title><start>19-01-2015T10:30:00</start><id>5</id><end>19-01-2015T12:30:00</end><context-menu><param>p1=linha6</param><param>p2=linha6</param><param>ctx_hidden=Relacoes</param></context-menu></row><row><title>Meeting</title><start>20-01-2015T10:30:00</start><id>6</id><end>20-01-2015T12:30:00</end><context-menu><param>p1=linha7</param><param>p2=linha7</param><param>ctx_hidden=Relacoes,Efectuar Pagamento</param></context-menu></row></value><context-menu><item type="specific"><title>Relacoes</title><app>HOME</app><page>CRIACAO_EMPRESA</page><link>AD2</link><target>_blank</target><img>edit.png</img><parameter>&amp;p_nome=zegui&amp;p_idade=29</parameter></item><item><img>pagar.png</img><link>http://teste.html?p_id=#P1&amp;p_nr_processo=#P2</link><title>Efectuar Pagamento</title><target>self</target><app>HOME</app><page>CRIACAO_EMPRESA</page></item><item><img>send.png</img><link>http://teste.html?p_id=#P1&amp;p_nr_processo=#P2</link><title>Anexar Documetos</title><target>_blank</target><app>HOME</app><page>CRIACAO_EMPRESA</page></item><item><img>delete.png</img><link>DELETE</link><title>Relacoes Eliminar</title><target>confirm</target><app>HOME</app><page>CRIACAO_EMPRESA</page><parameter>IGRP-calendar.xml?p_data=2</parameter></item></context-menu></table>';
	}

	var genGroup = function(container){
		var rtn = "";
		container.groups.items.forEach(function(g){
			var groupFields = [];
			rtn+='<group title="'+g.name+'" code="" id="'+g.id+'">';
				container.GET.fields().forEach(function(f){
					if(f.GET.group && f.GET.group() == g.id){
						groupFields.push(f);
					}
				});
				rtn+=genItems(groupFields);
			rtn+='</group>';
		});
		
		return rtn;
	}

	var genFields = function(container){
		var rtn      = '<fields>';
		var fields   = container.GET.fields();
		var containerTag = container.GET.tag()

		fields.forEach(function(f){
			rtn+=genFieldStruc({
				field:f,
				label:container.xml.fieldsLabel,
				value:container.xml.fieldsValue
			});

		});

		if(container.xml.table){
			//rtn+='<'+container.GET.tag()+'_id type="hidden" name="p_'+container.GET.tag()+'_id"></'+container.GET.tag()+'_id>'
		}
		
		if(container.GET.filter && container.GET.filter()){
			var filter = $.trim(container.GET.filter());
			if(filter){
				rtn+='<'+containerTag+'_filter name="p_'+containerTag+'_filter" type="text" maxlength="100">'+
						'<value></value>'+
					 '</'+containerTag+'_filter>';

				if(filter == 'filter_num')
					rtn+='<'+containerTag+'_filter_pg name="p_'+containerTag+'_filter_pg" type="text" maxlength="100">'+
						'<value>0|1|2|3|4|5|6|7|8</value>'+
					 '</'+containerTag+'_filter_pg>';
			}
		}

		if(container.onFieldsXMLGenerate && container.onFieldsXMLGenerate(rtn) )
			rtn += container.onFieldsXMLGenerate(rtn);

		for(var p in container.propertiesOptions){
			var pr = container.propertiesOptions[p];
			
			if(pr.isField){
				var tag  = containerTag+'_'+pr.name;
				var persist = pr.valuePersist ? 'persist="true"' : '';
				rtn+='<'+tag+' name="p_'+tag+'" type="text" maxlength="4000" '+persist+'>'+
						'<label>'+pr.label+'</label>'+
						'<value>'+container.GET[pr.name]()+'</value>'+
					 '</'+tag+'>';
			}
		}

		rtn+='</fields>';
		
		return rtn;
	}

	var genTable = function(container){
		var fields = container.GET.fields();
		var atts = "";

		var genTableFields = function(o){
			
			var rtn = '';
			var total = o ? o.total : false;

			fields.forEach(function(f){
				
				var tag     = f.xml.tag ? f.xml.tag : f.GET.tag();
				var type    = f.GET.type();
				

				/*var value   = f.xml.dataValue || DATA.get({type:type, field:f});
				var descVal = value;*/

				var value = '', 
					descVal = '';

				if(total){

					if(f.GET.type() == 'number')
						value = f.xml.dataValue || DATA.get({type:type, field:f});

				}else{

					value   = f.xml.dataValue || DATA.get({type:type, field:f});
					descVal = value;

				}


				switch(type){
					case 'link':
						descVal = f.GET.label()
					break;
				}

				var attributesStr = getAttrsArr(f,['name']);
				
				var visible       = f.GET.visible && !f.GET.visible() ? ' visible="false"' : '';
				
				attributesStr+=visible;

				rtn+='<'+tag+' '+attributesStr+'>'+value+'</'+tag+'>';
				if(f.xml.desc)
					rtn+='<'+tag+'_desc name="'+f.GET.name()+'_desc">'+descVal+'</'+tag+'_desc>';
				
			});

			return rtn;
		}
		
		var rtn ='<table>';
				rtn+='<value>';
				
				if(container.GET.fields()[0]){
					for(var i=0; i<5;i++){
						
						var aux = i+1;

						rtn+='<row>';
						
						if(container.hasFieldType(container.contextMenu.type))
							rtn+='<context-menu>'+
						            '<param>p1=linha1</param>'+
						            '<param>p2=linha1</param>'+
						          '</context-menu>';
					         
					        rtn+=genTableFields();
							
						rtn+='</row>';
					}

					if(container.GET.tableFooter && container.GET.tableFooter()){
						rtn+='<row total="yes">';
							rtn+=genTableFields({total:true});
						rtn+='</row>'
					}



				}
				rtn+='</value>';

			if(container.xml.tableLegend)
				rtn+='<legend_color><title>Legenda</title><item><label>Cor 1</label><value>1</value></item><item><label>Cor 2</label><value>2</value></item><item><label>Cor 3</label><value>3</value></item><item><label>Cor 4</label><value>4</value></item><item><label>Cor 5</label><value>5</value></item><item><label>Cor 6</label><value>6</value></item><item><label>Cor 7</label><value>7</value></item><item><label>Cor 8</label><value>8</value></item><item><label>Cor 9</label><value>9</value></item></legend_color>';
			
			if(container.contextMenu)
				rtn+=genContextMenu(container);


		rtn+='</table>';
		
		return rtn;
	}

	var genMap = function(container){
		return 'http://igrp.teste.gov.cv'+path+'/xml/gis/map.xml';
	}

	var genContextMenu = function(container){
		var rtn = '';
		
		if(container.contextMenu.items[0]){
			var ctx      = container.contextMenu.items;
			var tag      = container.contextMenu.xmlTag ? container.contextMenu.xmlTag : 'context-menu';
			var itemType = container.GET.type() == 'form' ? 'form' : 'specific';
			var rtn      = '<'+tag+'>';
			
			ctx.forEach(function(item){

				var app    = item.action && item.action.app    ? item.action.app    : '',
					page   = item.action && item.action.page   ? item.action.page   : '',
					link   = item.action && item.action.action ? item.action.action : '',
					tran   = item.GET.transaction &&  item.GET.transaction() ? 'flg_transaction="true"'  : '',
					map    = item.GET.service && item.GET.service().code ? getFieldServiceMap(item.GET.service()) : '',
					_class = item.GET.class && item.GET.class() ? item.GET.class() : 'default',
					target = item.GET.target();

				if(item.GET.target_fields && item.GET.target_fields())
					target += '|'+item.GET.target_fields();

				else if(item.GET.closerefresh && item.GET.closerefresh())
					target += '|refresh';


				rtn+='<item type="'+itemType+'" code="" '+tran+' class="'+_class+'" rel="'+item.GET.tag()+'">'+
	                    '<title>'+item.GET.label()+'</title>'+
	                    '<app>'+app+'</app>'+
	                    '<page>'+page+'</page>'+
	                    '<link>'+link+'</link>'+
	                    '<parameter>'+item.action.link+'?</parameter>'+
	                    '<target>'+target+'</target>'+
	                    '<img>'+_class+'|'+item.GET.img()+'</img>'+
	                     map+
	                 '</item>';
			});

			rtn+='</'+tag+'>';
		}

		return rtn;
	}

	var genItems = function(fields,rel){
		var rtn    = "";
		
		fields.forEach(function(f){
			var tag    = f.GET.tag(),
				title  = f.GET.label(),
				target = f.GET.target ? f.GET.target() : "",
				img    = f.GET.img    ? f.GET.img()    : "",
				imgTag = img ? '<img>'+img+'</img>' : '',
				app    = f.action && f.action.app    ? f.action.app    : '',
				page   = f.action && f.action.page   ? f.action.page   : '',
				link   = f.action && f.action.action ? f.action.action : '',
				tran   = f.GET.transaction &&  f.GET.transaction() ? 'flg_transaction="true"'  : '',
				map    = f.GET.service && f.GET.service().code ? getFieldServiceMap(f.GET.service()) : '',
				_class = f.GET.class && f.GET.class() ? f.GET.class()+'|' : '',
				parent = f.GET.parent && f.GET.parent() ? 'parent="'+f.GET.parent()+'"':'',
				params = '',
				actionLINK = f.action ? f.action.link : '';

			if(f.GET.target_fields && f.GET.target_fields())
				target += '|'+f.GET.target_fields();

			else if(f.GET.closerefresh && f.GET.closerefresh())
				target += '|refresh';

				//console.log(params);
			rtn+='<item type="specific" code="" rel="'+tag+'" '+tran+' '+parent+'>'+
		            '<title>'+title+'</title>'+
		            '<app>'+app+'</app>'+
		            '<page>'+page+'</page>'+
		            '<link>'+link+'</link>'+
		            '<target>'+target+'</target>'+
		            '<img>'+_class+img+'</img>'+
		            '<parameter>'+actionLINK+'</parameter>'+
		            map+
		        '</item>';
		});

		return rtn;
	}

	var genTabs = function(contents){
		
		var rtn = '<fields>';
		
		contents.forEach(function(c){

			rtn+='<'+c.GET.tag()+' '+getAttrsArr(c)+' maxlength="50" >'+
				 	'<label>'+c.GET.label()+'</label>'+
				 	'<value/>'+
				 '</'+c.GET.tag()+'>';
			
		});

		rtn+='</fields>';
		return rtn;
	}

	var genGraphic = function(container){
		var colors = '<colors>'+
						'<col>'+DATA.get({ type:'color',format:'hex' })+'</col>'+
						'<col>'+DATA.get({ type:'color',format:'hex' })+'</col>'+
						'<col>'+DATA.get({ type:'color',format:'hex' })+'</col>'+
						'<col>'+DATA.get({ type:'color',format:'hex' })+'</col>'+
					'</colors>';
		var chartType = container.GET.chart_type ? container.GET.chart_type() : 'line';

		return '<caption></caption> <chart_type>'+chartType+'</chart_type> <xaxys>Eixo de X</xaxys><yaxys>Eixo de Y</yaxys><url>#</url><label><col>Ano</col><col>X1</col><col>X2</col><col>X3</col><col>X4</col></label><value><row><col>2010</col><col>265</col><col>658</col><col>498</col><col>698</col></row><row><col>2009</col><col>784</col><col>258</col><col>594</col><col>498</col></row><row><col>2015</col><col>1010</col><col>698</col><col>366</col><col>498</col></row></value>'+colors;
	};

	var genFingerPrint = function(container){
		var rtn = '<fields>',
			tag = container.GET.tag();
		rtn += '<'+tag+'_link name="p_'+tag+'_link" type="link" maxlength="4000"><label>link</label><value></value></'+tag+'_link>';
		rtn += '<'+tag+'_photo name="p_'+tag+'_photo" type="link" maxlength="4000"><label>Photo</label><value></value></'+tag+'_photo>';
		rtn += '<'+tag+'_fingerright name="p_'+tag+'_fingerright" type="link" maxlength="4000"><label>Finger Right</label><value></value></'+tag+'_fingerright>';
		rtn += '<'+tag+'_fingerleft name="p_'+tag+'_fingerleft"  type="link" maxlength="4000"><label>Finger Left</label><value></value></'+tag+'_fingerleft>';
		rtn += '<'+tag+'_signature name="p_'+tag+'_signature"  type="link" maxlength="4000"><label>Signature</label><value></value></'+tag+'_signature>';
		rtn += '<'+tag+'_self name="p_'+tag+'_self"  type="link" maxlength="4000"><label>Self Service</label><value></value></'+tag+'_self>';
		rtn += '<'+tag+'_start name="p_'+tag+'_start"  type="link" maxlength="4000"><label>Process Start</label><value></value></'+tag+'_start>';
		rtn += '<'+tag+'_process type="number" maxlength="30" name="p_number_process"><label>Number Process</label><value></value></'+tag+'_process>';
		rtn += '</fields>';

		return rtn;
	};

	var genTreeMenu = function(container){
		var rtn = '<fields>',
			row = '',
			tag = container.GET.tag();
		rtn += '<'+tag+'_link type="link" target="_self" desc="true"><label>Link</label></'+tag+'_link>';
		rtn += '<'+tag+'_tmid type="number"><label>ID</label></'+tag+'_tmid>';
		rtn += '<'+tag+'_parent type="number"><label>Parent ID</label></'+tag+'_parent>';
		rtn += '<'+tag+'_icon type="text"><label>Icon</label></'+tag+'_icon>';
		rtn += '<'+tag+'_child><label>Has child value(0/X)</label></'+tag+'_child>';
		rtn += '<'+tag+'_active><label>Is Active value(true/false)</label></'+tag+'_active>';
		rtn += '</fields><table><value>';
		for (var i = 0; i < 8; i++) {
			var aux = i+1,
			parent  = aux % 2 == 0 ? aux - 1 : aux % 3 == 0 ? aux - 2 : '';  

			row += '<row>';
			row += '<'+tag+'_link>#</'+tag+'_link>';
			row += '<'+tag+'_link_desc> Menu '+aux+'</'+tag+'_link_desc>';
			row += '<'+tag+'_tmid>'+aux+'</'+tag+'_tmid>';
			row += '<'+tag+'_parent>'+parent+'</'+tag+'_parent>';
			row += '<'+tag+'_icon></'+tag+'_icon>';
			row += '<'+tag+'_child></'+tag+'_child>';
			row += '<'+tag+'_active></'+tag+'_active>';
			row += '</row>';
		}
		rtn +=row+'</value></table>';

		return rtn;
	};

	var getFieldsService = function(service,point){
		var rtn 	= '';
		service.forEach(function(c){
			if(c[0]){
				var type = c[0].type ? 'type="'+c[0].type+'"' : '';
				rtn+='<item '+type+' structure="list" rel="'+c[0].to+'">';
					c.forEach(function(e,i){
						if (i > 0) {
							if(e.from)
								rtn+='<row '+point+'="'+e.from+'">'+e.to+'</row>';
							else
								rtn+='<row>'+e.to+'</row>';
						}
					});
				rtn+='</item>';
			}else{
				if(c.from)
					rtn+='<item '+point+'="'+c.from+'">'+c.to+'</item>';
				else
					rtn+='<item>'+c.to+'</item>';
			}
		});
		return rtn;
	};

	var getFieldServiceMap = function(service){
		var GEN = VARS.getGen();
		var rtn = '<service code="'+service.code+'" proc="'+service.proc+'">';
			rtn+='<request>';
			var serviceReq = service.fieldsReq[0] ? service.fieldsReq : service.fieldsRes;
				rtn+=getFieldsService(serviceReq,'from');
			rtn+='</request>';

			rtn+='<response>';
			var serviceRes = service.fieldsRes[0] ? service.fieldsRes : service.fieldsReq;
				rtn+=getFieldsService(serviceRes,'to');
			rtn+='</response>';

		rtn+='</service>';

		return rtn;
	}

	var returnAttr = function(field,name){
		var arr = '';
		if(field.GET[name]){
			
			var value = field.GET[name]();
			
			if(field.xml.attrs[name])
				arr+=' '+field.xml.attrs[name]()
			else
				arr+=' '+name+'="'+value+'"';

		}

		return arr;
	}
	var validAttrArr = function(name){
		
		var rtn = false;
		
		if( name != 'label' && name !='tag' && name !='size' && name !='domain_value' )
			rtn = true;
		
		return rtn;
	}
	var getAttrsArr = function(field,which){
		var arr = "";
		if(which && which[0])
			which.forEach(function(name){
				if(validAttrArr(name)){
					if(field.propertiesOptions[name] && field.propertiesOptions[name].valuePersist)
						arr+='persist="true"';

					if(name == 'visible'){

						if(!field.GET.visible())
							arr+=' visible="false"';
					}
					else
						arr+=returnAttr(field,name);
				}
			});
		else
			for(var name in field.proprieties){
				if(validAttrArr(name)){

					if(field.propertiesOptions[name] && field.propertiesOptions[name].valuePersist)
						arr+=' persist="true" ';
					
					if(name == 'visible'){

						if(!field.GET.visible())
							arr+=' visible="false"';
					}
					else
						arr+=returnAttr(field,name);

					
					
				}
			}

		if(field.type=='hidden')
			arr+=' tag="'+field.GET.tag()+'"';

		if(field.xml.check)
			arr+=' check="true"';

		if(field.xml.desc)
			arr+=' desc="true"';

		return arr;
	}

	var getXMLOptions = function(f){

		var rtn = '<list>';
		
		if(f.type == 'select')
			rtn+='<option></option>';

		if(f.GET.domain && f.GET.domain() && f.proprieties.domain_value && f.proprieties.domain_value[0]){

			f.proprieties.domain_value.forEach(function(d){
				var value = $.IGRP.utils.htmlEncode(d.value),
					text  = $.IGRP.utils.htmlEncode(d.text);
				rtn+='<option><text>'+text+'</text><value>'+value+'</value></option>';
			});
			
		}else{

			for(var i = 1; i <= 4 ; i++){
				var text = 'Option '+i;
					rtn +='<option><text>'+text+'</text><value>'+i+'</value></option>';
			}
		}

		rtn+= '</list>';

		return rtn;
	}

	var checkRules = function(f,sxml){
		
		var rtn   = '',

			rules = f.rules;

		if(rules && rules[0]){
			
			rtn +=	'<rules>';
			
			rules.forEach(function(r){
				
				var actions = r.actions ? JSON.parse(r.actions.replace(/'/g,'"')) : [];
				
				actions.forEach(function(a){

					var action = a.gen_rule_action;

					switch(action){
						
						case 'remote_combobox':
						case 'remote':
						
							var proc = a.gen_rule_procedure;

							rtn+='<rule>';

								rtn+= '<proc>'+proc+'</proc>';

							rtn+='</rule>';
								 	
						break;
					}

				});

			});

			rtn+= '</rules>';

		}

		return rtn;
	}	

}