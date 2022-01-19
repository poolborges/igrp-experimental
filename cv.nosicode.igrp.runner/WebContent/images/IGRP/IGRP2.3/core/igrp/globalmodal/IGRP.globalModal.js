(function($){
	if($ && $.IGRP && !$.IGRP.components.globalModal){
		
		$.IGRP.components.globalModal = {

			modalid:'#igrp-global-modal',

			beforeHide:null,

			get:function(){
				return $($.IGRP.components.globalModal.modalid);
			},
			set:function(o){
				/*
					size: lg, sm, xs
					rel : attr to control

				*/
				var modal = $.IGRP.components.globalModal.get();
				
				$('.modal-dialog',modal).removeClass('modal-lg modal-sm');

				if(o.size)
					$('.modal-dialog',modal).addClass('modal-'+o.size);
				
				if(o.beforeHide) $.IGRP.components.globalModal.beforeHide = o.beforeHide;
				
				if(o.rel) modal.attr('parent-rel',o.rel);
				
				$('.modal-header .title',modal).append(o.title);

				switch(o.operation){
					case 'appendTo':
						o.content.appendTo( $('.modal-body',modal) )
					break;
					default:
						$('.modal-body',modal).append(o.content);
				}

				if(o.buttons && o.buttons[0])
					o.buttons.forEach(function(b){
						/*
							class:
							icon :
							text :
							attr : {
								attr1: 'value'
							},
							onClick : fnc
						*/
						var _class = b.class ? b.class : 'default';
						var icon   = b.icon ? '<i class="fa fa-'+b.icon+'"></i>' : '';
						var text   = b.text ? b.text : '';
						var btn = $('<button class="custom btn btn-'+_class+'">'+icon+text+'</button>');
						
						

						if(b.attr)
							for(attr in b.attr){
								btn.attr(attr, b.attr[attr])
							}

						if(b.onClick)
							btn.on('click',b.onClick);

						$('.modal-footer',modal).append(btn);
					});
				
				if(o.beforeShow) o.beforeShow();

				$.IGRP.components.globalModal.show();
			},
			show:function(){
				var modal = $.IGRP.components.globalModal.get();
				modal.modal('show');
			},
			hide:function(){
				var modal = $.IGRP.components.globalModal.get();
				modal.modal('hide');
			},
			onhide:function(){
				var modal = $.IGRP.components.globalModal.get();

				if($.IGRP.components.globalModal.beforeHide) $.IGRP.components.globalModal.beforeHide();

				$('.custom',modal).remove();
				$('.modal-body',modal).html('');
				$('.modal-header .title',modal).html('');
				$.IGRP.components.globalModal.beforeHide =null;
			}
		}

		$.IGRP.on('init',function(){
			$($.IGRP.components.globalModal.modalid).on("hidden.bs.modal hidden", $.IGRP.components.globalModal.onhide);
		});
	}
}($));
