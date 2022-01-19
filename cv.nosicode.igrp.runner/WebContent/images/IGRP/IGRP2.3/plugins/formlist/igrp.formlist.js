// JavaScript Document Form Lista @NOSi 2016
(function($){
	$.fn.IGRP_formlist = function(options){
        if(this[0]){
            var defaults = {
              btnDelete     : true,
              btnAdd        : true,
              btnObjAdd     : '.formlist-row-add',
              btnObjDelete  : '.formlist-row-remove',
              //imgAdd        : path_tmpl+"/img/icon/tools-bar/add.png",
              //imgDelete     : path_tmpl+"/img/icon/tools-bar/delete.png",
              onAdd         : null,
              beforeAdd     : null,
              afterAdd      : null,
              onDelete      : null,
              beforeDelete  : null,
              afterDelete   : null
            };

            var _this = this;
            var settings    = $.extend( {}, defaults, options);
            var TABLE       = $(this);
            var vRel        = TABLE.attr('rel'),
                type        = TABLE.attr('type') ? TABLE.attr('type') : 'table',
                obj         = type == 'table' ? 'tbody tr' : '.fl-box';

            
            try{
                vRel        = vRel.substring(2,vRel.length);
            }catch(e){null}
            
            _this.getLength = function(){

                return $(obj,TABLE).length;
            }

            _this.getRow = function(i){
                return i?$(obj+"[row='"+i+"']",TABLE):$(obj,TABLE);
            }
            
            function resetFildsRow(vObjTr){
                $("*",vObjTr).each(function(idx,element){
                    
                    if($(element).attr('type')){
                        var type = $(element).attr('type');
                        switch(type){
                            case'radio':
                            case 'checkbox':
                                $(element).removeAttr("checked").prop("checked",false);
                            break;
                            default:
                                $(element).val('');
                                $(element).attr('value','');
                        }
                    }
                    if($(element).prop('tagName').toLowerCase() == 'textarea')
                        $(element).text('');

                    if(($(element).prop('tagName')).toLowerCase() == "select")
                        $(element).find("option").removeAttr("selected");
                });

                console.log(TABLE)

                TABLE[0].events.execute('row-reset',vObjTr);
            }

            function hideShowItem(){
                (settings.btnDelete)?$("tbody tr:first-child",TABLE).removeClass("noDelete"):
                $("tbody tr:first-child",TABLE).addClass("noDelete");
            }

            function getCloneRow(){
                var vTrObj = $(obj+":last",TABLE) ,vTrRow = (vTrObj.attr("row")*1)+1,
                    vObjTr = !settings.btnDelete?vTrObj.clone(!1):vTrObj.removeClass('noDelete').clone(!1);
                    vObjTr = vObjTr.attr("row",vTrRow);
                    //console.log(vObjTr.find('*[id]').attr('id'))
                return vObjTr;
            }

            function ROW_id(pRel,pObj){
                return  $("input[name='p_"+pRel+"_id']", pObj)[0]?$("input[name='p_"+pRel+"_id']", pObj).val():null;
            }

            function IGRP_delRowForm(pObj){ 
                var vVal = ROW_id(pObj.pRel,pObj.pObjt);
                
                if(vVal != null && vVal != "")//{
                    $("form").append('<input type="hidden" name="p_'+pObj.pRel+'_del" value="'+vVal+'"/>');
                    
                if(_this.getLength() > 1 && settings.btnDelete){
                    $(pObj.pObjt,"*[rel='T_"+pObj.pRel+"']").remove();

                    TABLE[0].events.execute("row-remove",$(pObj.pObjt,"*[rel='T_"+pObj.pRel+"']"));
                     //_this.events.execute('row-remove',$(pObj.pObjt,"table[rel='T_"+pObj.pRel+"']"));
                }
                else{
                    resetFildsRow(pObj.pObjt);
                }


              /*}else
                    IGRP_message({pObjt:$("table[rel='T_"+pObj.pRel+"']").parents(".box-table:first"),pType:"warning",
                        pMessage:JSMSG_WARNING_DELETE_ITEM_TABLE,pPos:"before"});*/
            }


            var getObjTable = function(rel){
                return $('*[rel="T_'+rel+'"]');
            }

            var addRowForm = function(t){
                var vObjTr  = getCloneRow(TABLE);
                resetFildsRow(vObjTr);
                type == 'table' ? $("tbody",TABLE).append(vObjTr) : vObjTr.insertAfter(t);

                TABLE[0].events.execute("row-add",vObjTr);

            };

            var _delete = function(pObj){
                IGRP_delRowForm({pRel:vRel,pObjt:pObj});
            };

            var onAdd = function(t){
                if(settings.beforeAdd)
                    settings.beforeAdd(TABLE,_this.getLength(),getCloneRow(),t);

                settings.onAdd?settings.onAdd(TABLE,_this.getLength(),getCloneRow(),t):addRowForm(t);
                
                if(settings.afterAdd)
                    settings.afterAdd(TABLE,_this.getLength(),getCloneRow(),t);
            };

            this.on('click',settings.btnObjAdd,function(){
                TABLE = getObjTable($(this).attr('rel'));
                vRel  = TABLE.attr('rel');
                var element = type == 'table' ? "tr:first" : '.fl-box';
                onAdd($(this).parents(element));
            });

            var onDelete = function(pObj){
                if(settings.beforeDelete)
                    settings.beforeDelete(TABLE,_this.getLength(),pObj);

                settings.onDelete?settings.onDelete(TABLE,_this.getLength(),pObj):_delete(pObj);

                if(settings.afterDelete)
                    settings.afterDelete(TABLE,_this.getLength(),pObj);
            };

            this.on('click',settings.btnObjDelete,function(){
                TABLE = getObjTable($(this).attr('rel'));
                vRel  = TABLE.attr('rel');
                var element = type == 'table' ? "tr:first" : '.fl-box';
                onDelete($(this).parents(element));
            });

            this.each(function(i,e){
                
                e.events    = new $.EVENTS(["row-add","row-remove","row-reset"]);

                var onInit = function(){
                    $(obj,e).each(function(i,tr){
                        if(!$(tr).attr('row')){
                            $(tr).attr('row',i);
                            if (type == 'table') {
                                var idx = i;
                                $('td',$(tr)).each(function(itd,td){
                                    if (!$(td).attr('data-row'))
                                        $(td).attr('data-row',idx);
                                });
                            }
                        }
                    });
   

                    hideShowItem();

                    if(!settings.btnAdd)
                        $(settings.btnObjAdd,e).hide();

                    if(!settings.btnAdd && !settings.btnDelete){
                        if (type=='table') {
                            $("thead tr th:last-child",e).hide();
                            $("tbody tr",e).each(function(i,tr){
                                $("td:last-child",tr).hide();
                            });
                        }else{
                            $(settings.btnObjDelete,e).hide();
                        }
                    }
                };

                onInit();
            });
            return _this;
        }
		
	};
})(jQuery);

$(function(){
    $('.IGRP_formlist').IGRP_formlist();
});