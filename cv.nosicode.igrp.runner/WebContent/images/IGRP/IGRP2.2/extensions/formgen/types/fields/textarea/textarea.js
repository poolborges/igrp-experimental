var TEXTAREAFIELD = function(type,proprieties){
	Field.call(this,type,proprieties);
}

this[VARS.name].declareField({
	type:'textarea',
	field:TEXTAREAFIELD
});