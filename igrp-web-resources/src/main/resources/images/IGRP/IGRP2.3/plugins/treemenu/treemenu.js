(function () {
	var com;
	$.IGRP.component('tm',{// tm = tree menu
		toolTip   : function(){
			$('body').tooltip({
			    selector: '[data-toggle="tooltip"]'
			});	
		},
		transform : function(p){
			var parent  = p.holder,
			xslParams 	= {},
			name		= parent.attr('name'),
			package 	= parent.attr('package-db') ? parent.attr('package-db').toUpperCase() : null,
			app	 		= parent.attr('app') ? parent.attr('app').toUpperCase() : '',
			url 		= package ? app+'.'+package+'.REMOTE_'+name.toUpperCase() : $.IGRP.utils.getPageUrl(),
			params 		= package ? 'p_id='+p.id : 'p_remote_tmid='+p.id+'&p_remote_tm='+name;

			xslParams.name 		= name;
			xslParams.target  	= parent.attr('target');

			if (parent.attr('tooltip') && parent.attr('tooltip') != undefined)
				xslParams.tooltip = parent.attr('tooltip');

			if (p.id != '')
				xslParams.id 	= p.id;

			if (p.class)
				xslParams.class = p.class;

			var active = $.IGRP.store.get(name+'_active') || $('#p_fwl_'+name+'_tmid').val()*1;

			if (active)
				params += '&p_active='+active;
			
			$.ajax({
				url : $.IGRP.utils.getUrl(url)+params,
				dataType:'xml',
				error : function(e,status,ex){
					p.error();
					$.IGRP.notify({
						message : ex,
						type	: 'danger'
					});
				},
				success: function(data){
					var xmlData = package ? $.parseXML(data) : data,
					 	xmlPath = package ? 'table' : 'rows content '+name;

					data = xmlData ? xmlData : data;

					data = $(data).find(xmlPath).getXMLDocument();
					
					if (p.id == '')
						xslParams.id = $(data).find('table value row '+name+'_parent').eq(0).text();
					
					p.transform.XMLTransform({
				    	xml 	  : data,
				    	xsl 	  : path+'/xsl/tmpl/IGRP-treemenu.tmpl.xsl',
				    	xslParams : xslParams,
				    	complete  : p.complete,
				    	error     : p.error
				    });
				}
			}).always(function(e){
				p.error();
			})
		},
		onLoad : function(){
			$('.box-tm[data-toggle="remote"][onload="true"]').each(function(){
				var holder 		= $(this),
					name 		= holder.attr('name'),
					onComplete  = function(){
						$.IGRP.utils.loading.hide(holder);
						var active = $.IGRP.store.get(name+'_active') || $('#p_fwl_'+name+'_tmid').val() *1;
						if (active){
							$('ul.tree li',holder).removeClass('active');
							$('li#'+active,holder).addClass('active');
							$.IGRP.store.unset(name+'_active');
						}
						com.toolTip();
					},
					onError = function(){
						$.IGRP.utils.loading.hide(holder);
					};
				
				$.IGRP.utils.loading.show(holder);

				com.transform({
					holder  	: holder,
					id 			: '',
					transform 	: holder,
					complete 	: onComplete,
					error  		: onError,
					class   	: 'holder'
				});
			});
		},
		toggle: {
			remote : function(f){
				var id = f.attr('rel');

				if (!f.hasClass('set')) {
					f.addClass('load');
					com.transform({
						holder  	: f.parents('.box-tm'),
						id  		: id.split('-')[1],
						transform 	: $('#'+id),
						class   	: 'tree',
						complete 	: function(){
							f.addClass('set').removeClass('load');
							com.toggle.live(f);
							com.toolTip();
						},
						error  		: function(){
							f.removeClass('load').removeAttr('type');
						}
					});
			    } else 
			    	com.toggle.live(f);
			},
			live : function(f){
				var rel = f.attr('rel');
				com.toggle.isActive(f);
				com.toggle.isActive($('#'+f.attr('rel')));

				$('#'+rel).children('ul.tree[rel="'+rel+'"]').toggle(300);
			},
			isActive : function(f){
				f.attr('type') == 'active' ? f.removeAttr('type') : f.attr('type','active');
			}
		},
		init:function(){
			com = this;
			com.toolTip();
			com.onLoad();

			$('body').on('click','.tree-toggler',function(){
				var toggle 	= $(this).parents('.box-tm:first').attr('data-toggle');
				com.toggle[toggle]($(this));
			});

			$('.box-tm').on('click','a[tree-target]',function(e){
				e.preventDefault();
				var target 		= $(this).attr('tree-target'),
					id 			= $(this).parents('li:first').attr('id'),
					holderName 	= $(this).parents('.box-tm:first').attr('name'),
					name 		= 'p_fwl_'+holderName+'_tmid',
					itarget     = $.IGRP.targets[target] || $.IGRP.targets._self;

				$.IGRP.store.set({
					name : holderName+'_active',
					value: id
				});

				if($('input[name="'+name+'"]')[0])
					$('input[name="'+name+'"]').val(id);
				else
					$.IGRP.utils.createHidden({name : name, value : id, class : 'submittable'});
				
				itarget.action({

					url 	: $(this).attr('href'),
					target 	: target,
					clicked : $(this)
					
				});
			});
		}
	},true);
})();