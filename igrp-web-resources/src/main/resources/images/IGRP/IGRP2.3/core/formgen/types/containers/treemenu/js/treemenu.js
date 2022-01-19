var TREEMENU = function(name,params){
	CONTAINER.call(this,name,params);
	
	var GEN = VARS.getGen();

	var container = this;

	var proprieties = params && params.properties;
	
	container.fields 		= true;

	container.xml.type 		= 'treemenu';

	container.xml.structure = 'table';

	container.xml.genGroup 	= true;

	container.xml.genRemote = false;

	container.includes = {
		xsl : ['treemenu'],
		css : [ { path : '/plugins/treemenu/treemenu.css' } ], 
		js  : [ { path : '/plugins/treemenu/treemenu.js' }]
	}

	var showPackageName = function(v){
		var action  = v ? 'show' : 'hide'; 
		$('.gen-propreties-checkers-holder div[rel="onLoad"]')[action]();
	}

	container.ready = function(){

		container.setPropriety({
			name:'style',
			label:'Style',
			value:{
				value:'default',
				options:[
					{value:'default',label:'Default'},
					{value:'customimg',label:'Custom Images'}
				]
			}
		});

		GEN.setTargetAttr(container,{value:'_blank'});

		container.setPropriety({
			name 	: 'remote',
			label	: 'Remote',
			value 	: false,
			xslValue: '<xsl:with-param name="toggle" select="\'remote\'"/>',
			onChange : function(v){
				if (v)
					container.xml.genRemote = true;	
				else
					container.xml.genRemote = false;
			},
			onEditionStart : function(v){
				showPackageName(v.value);
				container.SET.packageName(v.value);
				$('input',v.input).on('change',function(){
					container.SET.packageName($(this).is(':checked'));
					showPackageName($(this).is(':checked'));
				});
			}
		});

		container.setPropriety({
			name 	: 'tooltip',
			label	: 'Has Tooltip',
			value 	: false,
			xslValue: '<xsl:with-param name="tooltip" select="\'true\'"/>',
			onChange : function(v){
				
			},
			onEditionStart : function(v){
				
			}
		});

		container.setPropriety({
			name:'packageName',
			value:false,
			editable:false,
			xslValue:function(){
				var app     = GEN.SETTINGS.table ? GEN.SETTINGS.table.split('.')[0] : GEN.DETAILS.app,
	            	package = GEN.SETTINGS.package;
				
				return '<xsl:with-param name="app" select="\''+app+'\'"/>'+
					   '<xsl:with-param name="package" select="\''+package+'\'"/>';
			}
		});

		container.setPropriety({
			name 	: 'onLoad',
			label	: 'Call onLoad',
			value 	: false,
			xslValue: '<xsl:with-param name="onload" select="\'true\'"/>',
			onEditionStart : function(o){
				if(container.GET.remote && container.GET.remote())
					o.input.show();
				else
					o.input.hide();
			}
		});
	}
};

this[VARS.name].declareContainer({
	name:'treemenu',
	container:TREEMENU
});