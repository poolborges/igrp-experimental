(function(){

	if($.IGRP && !$.IGRP.components.form){

		$.IGRP.events.declare(['before-change','js-change-success']);
  		
		$.IGRP.component('form',{
  			
  			lookup :{

  				fields : $('input,textarea').toArray(),

  				config:function(idx){

  					$.IGRP.components.form.lookup.fields.forEach(function(field,x){

  						$.IGRP.components.form.lookup.setEvents( field );

  					});

  				},

  				setEvents:function(field){

  					if(field.events)

						field.events.declare(['lookup-change']);
					else

						field.events = $.EVENTS(['lookup-change']);

					field.events.on('lookup-change',function(f){
						
						$(f).trigger('change')

					});

  				},

  				parse:function(clicked){

	  				var isTr   = $(clicked).parents('tr')[0] ? true : false;
	  				var object = isTr ?  $(clicked).parents('tr') : $(clicked);
	  				
	  				var paramsCount = 1*object.attr("CTX_PARAM_COUNT")+1;
	  				var formName    = object.attr("CTX_FORM") ? object.attr("CTX_FORM") : 'formular_default';
	  				var formIdx     = object.attr("CTX_FORM_IDX") ? parseInt(object.attr("CTX_FORM_IDX")) : 0;
	  				
	  				var form 		= window.opener ? window.opener.document.forms[formName] : window.parent.document.forms[formName];
					var vFwlNoclose = 0;
					var vFwlAdd     = 0; 
					var forminput   = null,
						isChange 	= false,
						oChange 	= null; 

					if(form){

						for(var i = 1; i < paramsCount; i++){
							
							var vOp = object.attr("CTX_P"+i).split("=");
		
							try{
								
								vFwlNoclose = (vOp[0]=="p_fwl_noclose")?vOp[1]*1:vFwlNoclose;
								
								vFwlAdd		= (vOp[0]=="p_fwl_add")?vOp[1]*1:vFwlAdd;
								
								forminput	= $("*[name='"+vOp[0]+"']", form);

								var holder 	= $('*[item-name="'+vOp[0]+'"]',form);

								if(forminput[0]){

									$(forminput[(formIdx)]).val(vOp[1]).attr('value',vOp[1]);//.trigger('change');

									if($(forminput[(formIdx)])[0].lookupCallback)
										$(forminput[(formIdx)])[0].lookupCallback($(forminput[(formIdx)]));


									if( forminput[(formIdx)].events ){
										forminput[(formIdx)].events.execute('lookup-change')
									}

									if($(forminput[(formIdx)]).prop('tagName').toLowerCase() == 'select'){
										window.parent.$.IGRP.components.select2.init();
						 				//$(forminput[(formIdx)]).trigger('change.select2');
									}

						 			if ($(forminput[(formIdx)]).hasClass('IGRP_change') || $(forminput[(formIdx)]).attr('change') == 'true'){
										isChange = true;
										oChange  = $(forminput[(formIdx)]);
									}

									$(forminput[(formIdx)]).trigger('change');
								}

								if (holder[0]) {
									var holderType = $(holder[(formIdx)]).attr('item-type');
									
									if (holderType == 'link') {
										$('a',holder[(formIdx)]).attr('href',vOp[1]);
									}
								}

							}catch(e){

								console.log(e);

							}
						}
					}
	  				
					$.IGRP.store.set({
						name : 'target-clicked',
						value: 'lookup_value'
					});
	  				
	  				if (isChange){
	  					
	  					try{

	  						if(window.opener){

	  							var url = $("input[name='p_env_frm_url']",form).val();

								url=url?url:window.opener.location.href;

								window.opener.IGRP_submit(url,null,"#id"+oChange.attr("name"));
	  					
	  						}else{

	  							window.parent.$.IGRP.components.form.change({
	  								target : oChange[0],
	  							});
	  							
	  						}
	  						
	  					}catch(err){
	  						
	  						console.log(err);

	  					}
	  
	  				}

					if(!vFwlNoclose){

						setTimeout(function(){

							if(window.parent)
								window.parent.$.IGRP.components.iframeNav.hide();

							
							if(window.opener)
								window.close();

						},150);

					}
	  			}

  			},

  			texteditor:function(o){

  				var v = $.extend({
  					parent  : $('body'),
  					selector: '.gen-texteditor'
  				},o);

  				$.each( $(v.selector,v.parent),function(){
  					var id = $(this).attr('id') ? $(this).attr('id') : 'texteditor'+(new Date().getTime());
  					$(this).attr('id',id);

  					CKEDITOR.replace(id);

  				} );
  			},

  			novalidate:function(){

  				$.IGRP.utils.getForm().unbind('submit');

  			},

  			change:function(o){
  				
  				var target   = o.target,
  				
  					scrollTo = '#id'+$(target).attr('name'),

  					type  	 = $(target).hasClass('js_change') ? 'js' : 'default',

  					url 	 = $.IGRP.utils.getPageUrl(),

  					form    = $.IGRP.utils.getForm();

  				if(type == 'js'){

  					var _url  = $.IGRP.utils.getSubmitParams( url,form ),	

  						data  = form.find('*').not(".notForm").serializeArray(),

  						nodes = [];

  						//nodes = $('.gen-container-item')
  					$('.gen-container-item').each(function(i,c){

  						nodes.push( $(c).attr('item-name') );

  					})


  					$.IGRP.utils.transformXMLNodes({
				
						nodes : nodes,

						url   : _url,

						data  : data,

						success:function(o){

							$.IGRP.events.execute('js-change-success', o );

						},

						error:function(){
							console.log('dsa')
						}

					});

  				}else{

  					$.IGRP.components.form.novalidate();

					$.IGRP.targets.submit.action({
						url 	 : url,
						validate : false,
						scrollTo : scrollTo
					});

					$.IGRP.events.execute('before-change',{
						target : target
					});

  				}

	  				
				
				return false;
  			},

  			init:function(){
  				//TEXTEDITOR
				$.IGRP.components.form.texteditor();
				//LOOKUP
				$.IGRP.components.form.lookup.config();
  				/*FILE INPUT*/
				$(document).on('change', '.file-btn-holder :file', function() {
					var input    = $(this),
					    numFiles = input.get(0).files ? input.get(0).files.length : 1,
					    label    = input.val().replace(/\\/g, '/').replace(/.*\//, ''),
					    txtInput = $(this).parents('.input-group').find(':text'),
					    log      = numFiles > 1 ? numFiles + ' files selected' : label;
				  	
				  	if( txtInput.length ) 
				  		txtInput.val(log);

				  	if (input.hasClass('file2base64')) {
				  		$.IGRP.utils.file2base64({
				  			target: $('[item-name="'+input.attr('target-rend')+'"]'),
				  			field : input
				  		});
				  	}
				});
				/*LOOKUP PARSER*/
				$(document).on('click','.lookup-parser',function(){
					$.IGRP.components.form.lookup.parse( this );
				});

				/* SELECT CHANGE */
				$(document).on('change','.IGRP_change, [change="true"]',$.IGRP.components.form.change);
				
  			}
  		},true);

  		
  	}

})();



