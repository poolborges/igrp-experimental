(function(){
   var com,
      bpmn = {};

   bpmn.id   = '';
   bpmn.title= $('#igrp-app-title').text();
   bpmn.app  = $('#form_1_env_fk').val();
   bpmn.new  = path+'/core/bpmnjs/resources/initial.bpmn';

   var bpmLookup     = $('[input-rel="form_1_formkey"]'),
      lookupHref     = bpmLookup.attr('href'),
      hrefAfterApp   = lookupHref,
      pdid           = null;

   $.IGRP.component('bpmn',{
      importXML : function(l){
         $.get(l, function(xml){

            xml = $($.parseXML(xml)).activiti2Io();

            BPMN.importXML(xml, function(err) {
               if (err) {
                  $('body').removeClass('shown');
                  console.error(err);
               } else{
                  pdid = $('#camunda-id').val();
                  $('body').addClass('shown');
               }
            });
         },'text');
      },
      saveDiagram : function(){
         $("body").on("click","#save",function(e) {
            e.preventDefault();
            var url = $('.btns-holder a[target="submit"]').attr('href');
            
            BPMN.saveXML({ format: true }, function(err, xml) {
               if (err) {
                  console.error('diagram save failed', err);
               } else{
                  var arrayItem = [];

                  if(url.indexOf("?")>-1){
                     var param = url.substring(url.indexOf("?")+1),
                        p      = param.split("&");

                     for(var i = 0; i < p.length; i++){
                        var v = p[i].split("=");
                        if(v[0].toLowerCase() != "p_env_frm_url"){
                           arrayItem.push({name:v[0],value:v[1]});
                        }
                     }
                  }

                  xml = $($.parseXML(xml)).io2Activiti();
                  
                  arrayItem.push({name:'p_id',value:bpmn.id});
                  arrayItem.push({name:'p_env_fk',value:bpmn.app});
                  
                  $.IGRP.utils.submitStringAsFile({
                     pUrl    : url,
                     pParam  : {
                        pArrayFiles : [{name:'p_data',value:xml}],
                        pContentType: 'text/xml',
                        pFormat     : 'xml',
                        pArrayItem  : arrayItem
                     }
                  });
               } 
            });
            return false;
         });
      },
      processClick : function(){
         $("body").on("click",".treeview a[type='line'].treeClick",function(e) {
            e.preventDefault();
            var url = $(this).attr('href');

            if(url){
               $('#igrp-app-title').html(bpmn.title+' [ '+$(this).text()+'  ]');

               bpmn.id = $(this).attr('idObj');
               
               if(!$(e.target).hasClass("btn")){
                  com.importXML(url);
               }
            }
         });
      },
      appChange : function(){
         $("#form_1_env_fk").on("change",function(){
            bpmn.app = $(this).val();
            var name = $(this).attr('name'),
               param = name+'='+bpmn.app;

            if (bpmn.app != null) {
               $('.addArea').addClass('active');
               $.ajax({
                  url  : $.IGRP.utils.getPageUrl(),
                  data : param,
                  success : function(data){
                     $('#tabprocessos').XMLTransform({
                        xsl       : path+"/core/bpmnjs/xsl/bpmn-tree-menu.tmpl.xsl",
                        xslParams : {template : 'tree-menu'},
                        xml       : $(data).find('rows content gen_table table').getXMLDocument(),
                        complete  : function(){
                           try{
                              com.importXML(bpmn.new);
                           }catch(e){null;}

                           com.lookupSetHref(param);
                        }
                     });
                  },
                  error : function(e){
                     $.IGRP.notify({
                        message : 'Not Found',
                        type    : 'danger'
                     });
                  } 
               });
            }else
               $('.addArea').removeClass('active');
         });
      },
      btnAddEditClick : function(){
         $("body").on("click",".treeview .btn, .addArea",function(e){
            e.preventDefault();
            var url       = '',
            parent      = $(this).parents('a:first'),
            parentNode  = parent.parents('li[type="node"]:first');

            if($(this).hasClass('btn')){
               url = $(this).hasClass('addItem') ? parent.attr('linkAdd') : parent.attr('linkEdit');

               parent.hasClass('active') ? parent.addClass('active') : parent.removeClass('active');
               parentNode.hasClass('active') ? parentNode.addClass('active') : parentNode.removeClass('active');
            }else{
               url = $.IGRP.utils.getUrl($('#p_link_add_area').val())+'p_env_fk='+bpmn.app;
            }

            if(url){
               $.IGRP.components.iframeNav.set({
                  url       : url,
                  beforeLoad: function(c){
                     var modal = $($.IGRP.components.iframeNav.modal);
                     $('.iframe-nav-close',modal).click(function(){
                        $("#form_1_env_fk").change();
                     });
                  }
               });
            }
         });
      },
      lookupSetHref : function(param){
         if(lookupHref){
            hrefAfterApp = $.IGRP.utils.getUrl(lookupHref)+param;
            bpmLookup.attr('href',hrefAfterApp);
         }
      },
      utils : function(){
         $('#js-properties-panel').on('click','button',function(e){
            e.preventDefault();
            return false;
         });

         if ($('#form_1_formkey')[0]) {
            $('#form_1_formkey')[0].lookupCallback = function(v){
              $("#camunda-form-key").attr('rel','igrp').trigger('change');
            };
         }

         if (bpmn.app)
            com.lookupSetHref('p_env_fk='+bpmn.app);

            //bpmLookup.attr('href',$.IGRP.utils.getUrl(lookupHref)+'p_env_fk='+bpmn.app);

         bpmLookup.on('click',function(e){
            var href =  $.IGRP.utils.getUrl(hrefAfterApp),
               gid   = $('#camunda-id').val();

            if (gid){
               href  += 'p_general_id='+gid;

               if (pdid)
                  href += '&p_process_id='+pdid;

               $(this).attr('href',href);
            }else{
               $.IGRP.notify({
                  message : 'Campo id do Separador General é Obrigatorio.',
                  type    : 'danger'
               });
               return false;
            }
         });

         $.IGRP.targets['modal'].action = function(p){
            var url = $.IGRP.utils.getUrl(p.url)+'p_task_id='+$('#camunda-id').val()+'&p_env_fk='+bpmn.app;
            $.IGRP.components.iframeNav.set({
               url     : url,
               clicked : p.clicked
            });
            return false;
         };
      },
      panelAttr : function(){
         $('.panelAttr').on('click',function(e){
            e.preventDefault();
            if ($(this).hasClass('jsc')) {
               $('.jscAttr').removeClass('jsc');
               $('#jsleftpanel').alterClass('col-md-9','col-md-12');
               $('#jsrightpanel').removeClass('col-md-3');
            }else{
               $('.jscAttr').addClass('jsc');
               $('#jsleftpanel').alterClass('col-md-12','col-md-9');
               $('#jsrightpanel').addClass('col-md-3');               
            }
            return false;
         });
      },
      init:function(){
         com = this;
         com.processClick();
         com.appChange();
         com.btnAddEditClick();
         com.utils();
         com.saveDiagram();
         //com.panelAttr();
      }
   },true);
})();