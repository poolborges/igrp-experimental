(function($){
	if($ && $.IGRP && !$.IGRP.targets){
		
		var form ;

		var confirmText = '';

		var ev = $.IGRP.events;

		ev.declare( ['target-click','submit-ajax'] );

		$.IGRP.events.declare(['submit']);

		//confirm
		var confirm = function(p){
			
			setConfirmModal(function(){

				window.location.replace(p.url);

			});

			return false;	
		};
		//close
		var close = function(){

			window.opener = top;

			window.close();

			return false;
		};
 		//back
 		var back = function(){

 			window.history.back();

 			return false;
 		};
 	
 		//alert_submit
 		var alert_submit = function(p){
 			
 			setConfirmModal(function(){
				submit(p);
			});

			return false;
 		};
 		//submit page
 		var submit = function(p){
 			
 			$.IGRP.utils.clearSubmittables();

 			var sform     = $.IGRP.utils.getForm(),

 				fields    = $.IGRP.utils.getFieldsValidate(sform),//$('input[required],select[required],textarea[required]',form).not('.no-validation'),
 				
 				validate  = p.validate === false ? false : true,
 				
 				action    = $.IGRP.utils.getSubmitParams(p.url,p.form,p.scrollTo);

 			fields.addClass('submittable');

 			try{

 				if(p.clicked && p.clicked.parents('li.operationTable')[0])
 					validate = false;

 				form.attr('validateCtrl',validate);

				form.attr('action',action).submit();

 			}catch(e){
 				console.log(e);
 			}
 			
			return false;
 		};
 		//submit form
 		var submit_form = function(p){

 			$.IGRP.utils.clearSubmittables();
 			
 			var parent 	  = p.clicked && p.clicked[0] ? $(p.clicked.parents('.gen-container-item')[0]) : false,

 				actionURL = p.url || $("input[name='p_env_frm_url']").val() || window.location.href,

 				sform     = parent && parent.find('[role="form"]')[0] ? parent.find('[role="form"]') : $.IGRP.utils.getForm(),

 				fields    = $.IGRP.utils.getFieldsValidate(sform),//$('input[required],select[required],textarea[required]',form).not('.no-validation'),
 				
 				validate  = p.validate === false ? false : true,
 				
 				action    = $.IGRP.utils.getSubmitParams(actionURL,p.form,p.scrollTo);

 				console.log(fields);

 				fields.addClass('submittable');

 				if(parent.parents('.gen-tab-holder')[0]){
 					var tab     = parent.parents('.gen-tab-holder').first(),
 						tabname = tab.attr('item-name'),
 						tabCtrl = $('.'+tabname+'Ctrl');
 						tabCtrl.addClass('submittable');
 				}

 			try{
 				
 				form.attr('validateCtrl',validate);

				form.attr('action',action).submit();

 			}catch(e){
 				console.log(e);
 			}
 			
			return false;
 		};
 		//submit_popup
 		var submit_popup = function(p){

 			var fields = $.IGRP.utils.getFieldsValidate(); //$('input,select,textarea',form).not('.no-validation');
 	
 			if(fields.valid())
 				$.IGRP.utils.openWin({
 					url    : setTargetParameter($.IGRP.utils.getUrl(p.url))+form.serialize(),
	 				width  : 980,
	 				height : 520,
	 				win    : 'IGRP'
 				});
 			
			return false;
		};
		//submit ajax
		var submit_ajax = function(p){
			var sform     	= $.IGRP.utils.getForm(),
				fields    	= $.IGRP.utils.getFieldsValidate(sform),
				action    	= $.IGRP.utils.getSubmitParams(p.url,sform,p.scrollTo);
				arrayFiles 	= $.IGRP.utils.submitPage2File.getFiles(),
				pArrayItem  = sform.find('*').not(".notForm").serializeArray();
				
			if (fields.valid()) {
				
				$.IGRP.utils.loading.show();
				//console.log(p)
				ev.execute('submit-ajax',{
					pArrayItem : pArrayItem,
					clicked    : p.clicked,
					url  	   : action
				});
				
				$.IGRP.utils.submitStringAsFile({
					pParam 		: {
						pArrayFiles : arrayFiles,
						pArrayItem 	: pArrayItem
					},
					pUrl   		: action,
					pNotify 	: false,
					pComplete 	: function(resp){

						$.IGRP.utils.loading.hide();

						try{

							var xml = resp.responseXML || $($.parseXML(resp.response)),
							
							alert = '',

							debug = '';

							$.each($(xml).find('messages message'),function(i,row){

								var type = $(row).attr('type');

								if (type != 'debug' && type != 'confirm') {

									type = type == 'error' ? 'danger' : type;

									alert += $.IGRP.utils.message.alert({
										type : type,
										text : $(row).text()
									});

								}else if(type == 'debug'){
									debug += '<li value="'+$(row).text()+'">'+
									$.IGRP.utils.htmlDecode($(row).text())+
									'</li>';
								}
							});

						}catch(e){
							var str = resp.response;
							if(str.indexOf('<html') != -1){
								str = str.substring(str.indexOf('<html'),str.length);
							}
							alert = $.IGRP.utils.message.alert({
								type : 'danger',
								text : $.IGRP.utils.htmlDecode(str)
							});
						}

						$('.igrp-msg-wrapper').html(alert);

						$('#igrp-debugger .igrp-debug-list').html(debug);

						if(p.clicked[0].events)
							p.clicked[0].events.execute('submit-ajax-complete',{
								xml : xml
							});
					}
				});
			}
		};
		//filter
		var filter       = function(p){

			var filterLetter = p.url.split("=");

			var actionURL	 = $("input[name='p_env_frm_url']").val() || window.location.href;

			var action   	 = $.IGRP.utils.getSubmitParams(actionURL,form,p.scrollTo); 	

			var table  	     = $(p.clicked.parents('.gen-container-item')[0]);

			var tableName	 = table.attr('item-name');

			/*if($('input[name="'+filterLetter[0]+'"]')[0])
				$('input[name="'+filterLetter[0]+'"]').remove();

			$.IGRP.utils.createHidden({
				name  : filterLetter[0],
				value : filterLetter[1]
			});*/

			$.IGRP.utils.transformXMLNodes({
				
				nodes : [tableName],

				url   : $.IGRP.utils.getUrl(action)+filterLetter[0]+'='+filterLetter[1],

				data  : form.serialize(),

				success:function(c){
		
					var table = $('.gen-container-item[item-name="'+tableName+'"]'),

						fitem = $('.igrp-table-filter [filter-item="'+filterLetter[1]+'"]',table);
						
					fitem.addClass('active');
					
					if($.IGRP.components.contextMenu)

						$.IGRP.components.contextMenu.set( table );

					if(table.find('table').hasClass('igrp-data-table')){
						
						var id = table.find('table').attr('id');

						$.IGRP.components.tableCtrl.dataTable({

							selector : 'table#'+id+'.igrp-data-table'

						});
						
					}
						
				},

				error:function(){
					console.log('filter error line 272 IGRP.targets.js')
				}

			});

			/*submit({
				url      : actionURL,
				validate : false
			});*/

			return false;
		};
		
		//self
		var _self        = function(p){
			$.IGRP.utils.loading.show();

			var url 	= p.url,
				input 	= $('input.menuCtrl');

			if (input[0]) 
				url = $.IGRP.utils.getUrl(url)+input.attr('name')+'='+input.val();

			window.location.href = url;
			return false;
		};
		//self
		var _link       = _self;
		//specific
		var specific     = function(p){
			_self(p);
			return true;
		};
		//new tab
		var _newtab      = function(p){
			window.open(p.url,'_blank');
			return false;
		};
		//blank (popup)
		var _blank       = function(p){
			var d = new Date();
			$.IGRP.utils.openWin({
				url    : setTargetParameter(p.url),
				width  : 980,
				height : 520,
				win    : 'IGRP-'+d.getMilliseconds()
			});
			return false;
		};
		//export all - table
		var exportAll       = function(p){
			console.log('EXPORT ALL')
			return false;
		};

		//blank (popup)
		var mWindow = null;
		
		var modal       = function(p){
			
			if (p.clicked && p.clicked.attr('close') && p.clicked.attr('close') == 'refresh')
				
				mWindow = window;
		
			var url = setTargetParameter(p.url);
			
			$.IGRP.components.iframeNav.set({
				url    :url,
				clicked:p.clicked
			});
			return false;
		};

		var right_panel       = function(p){

			if (p.clicked && p.clicked.attr('close') && p.clicked.attr('close') == 'refresh')
				mWindow = window;
			
			p.url = setTargetParameter(p.url);

			$.IGRP.components.rightPanel.set(p);
			
			return false;
		};
		
		var right_panel_submit       = function(p){

			if (p.clicked && p.clicked.attr('close') && p.clicked.attr('close') == 'refresh')
				mWindow = window;
			
			p.url = setTargetParameter($.IGRP.utils.getUrl(p.url)+form.serialize());

			$.IGRP.components.rightPanel.set(p);
			
			return false;
		};

		var mpsubmit  = function(p){
			
			var formData = p.clicked.parents('table tbody tr')[0] ? '' : form.serialize();
			
			if (p.clicked && p.clicked.attr('close') && p.clicked.attr('close') == 'refresh')				
				mWindow = window;
			
			$.IGRP.components.iframeNav.set({
				url    : setTargetParameter($.IGRP.utils.getUrl(p.url)+formData),
				clicked:p.clicked
			});
			
			return false;
		};

		var modalpopup = modal;

		var setConfirmModal = function(onClick){
			$.IGRP.components.globalModal.set({
				rel    : 'confirm-target',
				content: confirmText,
				buttons: [
					{
						class   :'success',
						icon    :'check',
						text    :'Confirmar',
						onClick :function(e){
							try{ onClick(); }catch(err){ console.log(err); }
							$.IGRP.components.globalModal.hide();
							return false;
						}
					},
					{
						class :'danger',
						icon  :'times',
						text  :'Cancelar',
						attr  :{ 
							"data-dismiss":"modal" 
						}
					}
				]
			});
		};

		var changesrc = function(p){

			var targetFields = p.clicked.attr('target-fields');

			if(targetFields){

				var tfieldsArr = targetFields.split(',');

				tfieldsArr.forEach(function(f,i){

					var holder = $('[item-name="'+f+'"]');

					var field  = $('[src]',holder);

					field.attr('src','');

					var fclone = field.clone();

					fclone.attr('src',p.url);

					field.replaceWith(fclone);

					$.IGRP.components.iframes.init( holder );

				});
			}
		};

		var closerefresh = function(p){
			try{

				var popup 	= window.opener || false,

					_window = popup || window.parent,

					_window = _window.frames['head_filho'] || _window;

				if (mWindow) {
					_window = mWindow;
					popup 	= false;
					mWindow = null;
				}
				
				if(popup)
				
					close();
					
				_window.location.reload();
				


			}catch(e){null;}
		};

		changesrc.showContents = function(holder){

			setTimeout(function(){

				$.IGRP.utils.loading.hide(holder);

			},300);
		};

		var configTargetsEvents = function(){
			//submit ajax
			$.each($('a[target], button[target]'),function(i,e){
				e.events = $.EVENTS(['submit-ajax-complete','submit-ajax-error']);
			});
		};

		var remoteList = function(p){

		};
		
		var setTargetParameter = function(url){
			
			if(url){
				if(url.indexOf('target=_blank') == -1){
					var symb = getParameterSymbol(url);
					url+=symb+'target=_blank';
				}
			}
			return url;
		}
		
		var getParameterSymbol = function(url){
		
			var symb = "&";
			
			if(url.indexOf('?') == -1)
				symb = '?';
			
			return symb;
		}
		

		$.IGRP.targets = {
			
			confirm      : {

				label  : 'Confirmar',

				action : confirm 

			},
			
			submit       : {

				label : 'Submit',

				action : submit

			},
			modal 	     : {

				label : 'Modal',

				action : modal

			},
			
			mpsubmit   : {

				label : 'Submit Modal',

				action : mpsubmit

			},
			right_panel 	     : {

				label : 'Right Panel',

				action : right_panel

			},
			right_panel_submit   : {

				label : 'Submit Right Panel',

				action : right_panel_submit

			},
			_blank       : {

				label : 'Popup',

				action : _blank

			},

			
			submit_popup : {

				label : 'Submit Popup',

				action : submit_popup

			},
			submit_form  : {

				label : 'Submit Form',

				action : submit_form

			},

			submit_ajax  : {

				label : 'Submit Ajax',

				action : submit_ajax

			},

			alert_submit : {

				label : 'Alert Submit',

				action : alert_submit

			},	
			_link       : {

				label : 'Link',

				action : _link

			},		

			
			filter       : {

				label : 'Filter',

				action : filter

			},

			_self        : {

				label : 'Self',

				action : _self

			},

			specific     : {

				label : 'Specific',

				action : specific

			},

			_newtab      : {

				label : 'New Tab',

				action : _newtab

			},

			

			exportall    : {

				label : 'Export All',

				action : exportAll

			},

			

			
			_close       : {

				label  : 'Close',

				action : close

			},

			closerefresh : {

				label  : 'Close and Refresh Parent',

				action : closerefresh

			},

			_back        : {

				label  : 'Back',

				action : back

			},


			changesrc    : {

				label : 'Change Src',

				action : changesrc

			}/*,

			remotelist    : {

				label : 'Remote Lista',

				action : remoteList

			}*/
		};


		$.IGRP.on('init',function(){
			
			form        = $.IGRP.utils.getForm(),

			doc 	    = $(document),

			confirmText = $('#confirm-text').text();

			configTargetsEvents();

			/* target clicks controller */
			doc.on('click','a[target], button[target]',function(e){
				
				e.preventDefault();
				
				var target       = $(this).attr('target')  ? $(this).attr('target'): '_blank';
				
				var url          = $(this).attr('fw_href') ? $(this).attr('fw_href') : $(this).attr('href');			
				
				var targetAction = $.IGRP.targets[target] && $.IGRP.targets[target].action ? $.IGRP.targets[target].action : _blank;

				ev.execute('target-click',{
					target  : target,
					url     : url,
					clicked : this
				});

				$.IGRP.store.set({
					name : 'target-clicked',
					value: target
				});
				
				return targetAction({
					url     :url,
					target  :target,
					clicked : $(this)
				});

			});

			/*form submit controller */
			form.on('submit',function(e){ 

				var validate  = form.attr('validateCtrl'),
					fields    = $.IGRP.utils.getFieldsValidate(),
					vfields   = fields.filter('.submittable'),//form.find('.submittable'),//$.IGRP.utils.getFieldsValidate(),
					canSubmit = true;

				if(validate != 'false')
 					canSubmit = vfields.valid({
 						exclude : '.no-validation, .IGRP_checkall' //hack for separator list on submit fields from form. 
 					}) == 1 ? true : false;

 				
 				var eventCB  = $.IGRP.events.execute('submit',{
 					valid  : canSubmit,
 					fields :  fields
 				});

 				canSubmit = eventCB == false ? false : canSubmit;

 				if (canSubmit)
 					$.IGRP.utils.loading.show();

 				//return false;
				return canSubmit;		

			});

			//set targets on IGRP defaults
			var r = [];

			for(var t in $.IGRP.targets){

				r.push({
					value : t,
					label : $.IGRP.targets[t].label
				});
				
			}

			$.IGRP.defaults.buttons.targets = r;

			setTimeout(function(){
				
				$.IGRP.store.unset('target-clicked')
			
			},200);

			

		});
	}
})($);
