var CHECKBOXFIELD = function(type,params){
	Field.call(this,type,params);

	var field = this;
	
	field.xml.check = true;
	
	field.ready = function(){
		
		
		
	}
	
}
this[VARS.name].declareField({
	type:'checkbox',
	field:CHECKBOXFIELD
});