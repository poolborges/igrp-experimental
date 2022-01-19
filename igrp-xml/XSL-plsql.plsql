-- SET DEFINE OFF;
-- /

CREATE  OR  REPLACE
PACKAGE  C_CIDADAO_LAB2_PLSQL_DB
IS
            


--------------- RED
            
--------------- IGRP FrameWork
            
--------------- Data:
            
--------------- User:
            
--------------- XSL-Generator
        



		------Label variables

	TYPE  R_LABEL  IS  RECORD(
		MN_gen_form_toolsbar_ctx_1  VARCHAR2(100):='Fechar',
		MN_gen_form_toolsbar_ctx_0  VARCHAR2(100):='Gravar'
	);

	TP_LABEL  R_LABEL;



		------Permission variables

	TYPE  R_PERMISSION  IS  RECORD(
		MN_gen_form_toolsbar_ctx_1  BOOLEAN:=TRUE,
		MN_gen_form_toolsbar_ctx_0  BOOLEAN:=TRUE
	);

	TP_PERMISSION  R_PERMISSION;



		------Parameter variables

	TYPE  R_PARAMETER  IS  RECORD(
		MN_gen_form_toolsbar_ctx_1  VARCHAR2(1000),
		MN_gen_form_toolsbar_ctx_0  VARCHAR2(1000)
	);

	TP_PARAMETER  R_PARAMETER;



		------Record variables

	TYPE  R_RECORD  IS  RECORD(
	);

	TP_RECORD  R_RECORD;


		---#START-DB_TYPES##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=DB_TYPES,)/#
		---#END-DB_TYPES#

	PROCEDURE  dml_select;

	PROCEDURE  dml_select_all;

	PROCEDURE  dml_insert;

	PROCEDURE  dml_update;

	PROCEDURE  dml_delete;

	PROCEDURE  dml_menu;

	PROCEDURE  dml_(p_page  OUT  VARCHAR2,  p_action  OUT  VARCHAR2,  p_app  OUT  VARCHAR2);

	PROCEDURE  dml_(p_page  OUT  VARCHAR2,  p_action  OUT  VARCHAR2,  p_app  OUT  VARCHAR2);

	PROCEDURE  TOOLBAR_gen_form_toolsbar(p	OUT  REDGLOBAL.GLB_TYPE.t_toolbar);

	K_LOCAL_USERDATA  varchar2(255);

		---#START-DB_SPEC##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=DB_SPEC,)/#
		---#END-DB_SPEC#

END;
/

CREATE  OR  REPLACE
PACKAGE  BODY  C_CIDADAO_LAB2_PLSQL_DB
IS
            


--------------- RED
            
--------------- IGRP FrameWork
            
--------------- Data:
            
--------------- User:
            
--------------- XSL-Generator
        


		---#START-BIZTALK##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=BIZTALK,)/#
		---#END-BIZTALK#

	PROCEDURE  dml_select
	IS
                        

	BEGIN


		------dml_select

		NULL;

		---#START-DML_SELECT##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=DML_SELECT,)/#
		---#END-DML_SELECT#


		---#START-RESPDML_SELECT##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=RESPDML_SELECT,)/#
		---#END-RESPDML_SELECT#


	END  dml_select;

	PROCEDURE  dml_select_all
	IS
                        

	BEGIN


		------dml_select_all

		NULL;


		---#START-DML_SELECT_ALL##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=DML_SELECT_ALL,)/#
		---#END-DML_SELECT_ALL#


		---#START-RESPDML_SELECT_ALL##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=RESPDML_SELECT_ALL,)/#
		---#END-RESPDML_SELECT_ALL#


	END  dml_select_all;

	PROCEDURE  dml_insert
	IS
                        

	BEGIN


		------dml_insert

		NULL;

		---#START-DML_INSERT##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=DML_INSERT,)/#
		---#END-DML_INSERT#


	END  dml_insert;

	PROCEDURE  dml_update
	IS
                        

	BEGIN


		------dml_update

		NULL;

		---#START-DML_UPDATE##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=DML_UPDATE,)/#
		---#END-DML_UPDATE#


	END  dml_update;

	PROCEDURE  dml_delete
	IS
                        

	BEGIN


		------dml_delete

		NULL;

		---#START-DML_DELETE##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=DML_DELETE,)/#
		---#END-DML_DELETE#


	END  dml_delete;

	PROCEDURE  dml_menu
	IS
                        

	BEGIN


		------dml_menu

		NULL;

		---#START-DML_MENU##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=DML_MENU,)/#
		---#END-DML_MENU#


	END  dml_menu;



	-----dml_:gen_form_toolsbar-ctx-1(C_CIDADAO - LAB - PLSQL)
	PROCEDURE  dml_(p_page  OUT  VARCHAR2,  p_action  OUT  VARCHAR2,  p_app  OUT  VARCHAR2)
	IS
                        

	BEGIN


		------TODO: put your code here

		REDGLOBAL.GLB_CORE.set_action(p_action=>'Fechar');

		p_app:='C_CIDADAO';

		p_page:='LAB';

		p_action:='PLSQL';

		---#START-##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=,gen_form__plsqlplsqlab)/#
		---#END-#


	END  dml_;

	--- SERVICE  GEN_FORM_TOOLSBAR-CTX-0
	
	FUNCTION  GEN_FORM_TOOLSBAR-CTX-0_xml
		RETURN  CLOB
	IS
		v_xml  CLOB:='';
			v_blob_content  BLOB;
			v_mime_type  VARCHAR2(100);

		BEGIN
		v_xml:=v_xml||'<rows>';
		
		v_xml:=v_xml||'<row>';
		
		v_xml:=v_xml||'<mimetype>xml</mimetype>';
		
		v_xml:=v_xml||'</row>';
		
		v_xml:=v_xml||'</rows>';

		---#START-GEN_FORM_TOOLSBAR-CTX-0_XML##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=GEN_FORM_TOOLSBAR-CTX-0_XML,)/#
		---#END-GEN_FORM_TOOLSBAR-CTX-0_XML#

		
		return v_xml;
	END;
	
	
	
	PROCEDURE  GEN_FORM_TOOLSBAR-CTX-0_parsing(p_xml_response  CLOB)
	IS
		myxml  RED.NOSI_XML_API;
	
	BEGIN
		myxml := NEW  RED.NOSI_XML_API(p_xml_response/*DBMS_XMLGEN.CONVERT(p_xml_response,  DBMS_XMLGEN.entity_decode)*/);
		
		IF  LOWER(myxml.getitempath('//row/status'))='false'  THEN

		---#START-GEN_FORM_TOOLSBAR-CTX-0_ERROR##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=GEN_FORM_TOOLSBAR-CTX-0_ERROR,)/#
		---#END-GEN_FORM_TOOLSBAR-CTX-0_ERROR#

		
		REDGLOBAL.GLB_CORE.messageerror(DBMS_XMLGEN.CONVERT(myxml.getitempath ('//row/status_text'),  DBMS_XMLGEN.entity_decode));
		
			RETURN;
		
		END  IF;

		---#START-GEN_FORM_TOOLSBAR-CTX-0_PARSING_OK##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=GEN_FORM_TOOLSBAR-CTX-0_PARSING_OK,)/#
		---#END-GEN_FORM_TOOLSBAR-CTX-0_PARSING_OK#

		
		

		---#START-GEN_FORM_TOOLSBAR-CTX-0_PARSING##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=GEN_FORM_TOOLSBAR-CTX-0_PARSING,)/#
		---#END-GEN_FORM_TOOLSBAR-CTX-0_PARSING#

		
		myxml:=NULL;
	END;
	
	


	-----dml_:gen_form_toolsbar-ctx-0(C_CIDADAO - LAB - PLSQL)
	PROCEDURE  dml_(p_page  OUT  VARCHAR2,  p_action  OUT  VARCHAR2,  p_app  OUT  VARCHAR2)
	IS
                        

	BEGIN


		------TODO: put your code here

		REDGLOBAL.GLB_CORE.set_action(p_action=>'Gravar');

		p_app:='C_CIDADAO';

		p_page:='LAB';

		p_action:='PLSQL';

		---#START-##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=,gen_form__plsqlplsqlab)/#
		---#END-#

		
		GEN_FORM_TOOLSBAR-CTX-0_parsing(REDGLOBAL.GLB_CORE.invoke_biztalk(
		
			p_code_client=>REDGLOBAL.GLB_CORE.get_biztalk_client('5003353'),
		
			p_code_contract=>REDGLOBAL.GLB_CORE.get_biztalk_contract('5003353'),
		
			p_user_data=>NVL(K_LOCAL_USERDATA,'C_CIDADAO'),
		
			p_code_service=>'5003353',
		
			p_session_id=>REDGLOBAL.GLB_CORE.get_session_id,
		
			p_function_name=>'',
		
			p_xml_request=>GEN_FORM_TOOLSBAR-CTX-0_xml
		
		));

		---#START-RESP##gen(preserve_code,https://nosiappsdev.gov.cv/redglobal_lab/red.form_designer_db.preserve_your_code?p_owner=C_CIDADAO&p_pkg_name=C_CIDADAO_LAB2_PLSQL_DB&p_instance=findev&p_tag=RESP,)/#
		---#END-RESP#


	END  dml_;



	-----TOOLBAR_gen_form_toolsbar: group of button for gen_form_toolsbar (Dynamic Menu)
	PROCEDURE  TOOLBAR_gen_form_toolsbar(p	OUT  REDGLOBAL.GLB_TYPE.t_toolbar)
	IS
                        

		t	REDGLOBAL.GLB_TYPE.t_toolbar;
	BEGIN

	
            /*
            


		-----------------------------------------------------------------------

		-----TOOLBAR:gen_form_toolsbar-ctx-1

		t(t.count+1):=REDGLOBAL.GLB_TYPE.add_toolbar(
									p_title=>REDGLOBAL.GLB_CORE.get_lang_label(p_code=>'',p_text=>NVL(C_CIDADAO_LAB2_PLSQL_DB.tp_label.MN_gen_form_toolsbar_ctx_1,'Fechar')),
									p_app=>L_APP,
									p_page=>'LAB',
									p_action=>'$C_CIDADAO.C_CIDADAO_LAB2_PLSQL_HTML.form_$',
									p_type=>'specific',
									p_target=>'_close',
									p_permission=>C_CIDADAO_LAB2_PLSQL_DB.tp_permission.MN_gen_form_toolsbar_ctx_1,
									p_parameter=>C_CIDADAO_LAB2_PLSQL_DB.tp_parameter.MN_gen_form_toolsbar_ctx_1,
									p_img=>'PDF_F.png'
								);

		-----------------------------------------------------------------------

		-----TOOLBAR:gen_form_toolsbar-ctx-0

		t(t.count+1):=REDGLOBAL.GLB_TYPE.add_toolbar(
									p_title=>REDGLOBAL.GLB_CORE.get_lang_label(p_code=>'',p_text=>NVL(C_CIDADAO_LAB2_PLSQL_DB.tp_label.MN_gen_form_toolsbar_ctx_0,'Gravar')),
									p_app=>L_APP,
									p_page=>'LAB',
									p_action=>'$C_CIDADAO.C_CIDADAO_LAB2_PLSQL_HTML.form_$',
									p_type=>'specific',
									p_target=>'submit',
									p_permission=>C_CIDADAO_LAB2_PLSQL_DB.tp_permission.MN_gen_form_toolsbar_ctx_0,
									p_parameter=>C_CIDADAO_LAB2_PLSQL_DB.tp_parameter.MN_gen_form_toolsbar_ctx_0,
									p_img=>'save.png'
								);

	 
            */
            

            --- #START-MENU#
            --- #END-MENU#
        

		p:=t;

	END  TOOLBAR_gen_form_toolsbar;

END;
/

CREATE  OR  REPLACE
PACKAGE  C_CIDADAO_LAB2_PLSQL_HTML
IS
            


--------------- RED
            
--------------- IGRP FrameWork
            
--------------- Data:
            
--------------- User:
            
--------------- XSL-Generator
        

	PROCEDURE  list;

	PROCEDURE  form_;

	PROCEDURE  form_;

	PROCEDURE  TOOLBAR_gen_form_toolsbar(p	OUT  REDGLOBAL.GLB_TYPE.t_toolbar);

END;
/

CREATE  OR  REPLACE
PACKAGE  BODY  C_CIDADAO_LAB2_PLSQL_HTML
IS
            


--------------- RED
            
--------------- IGRP FrameWork
            
--------------- Data:
            
--------------- User:
            
--------------- XSL-Generator
        

	--CÓDIGO PÁGINA: LAB

	L_APP  VARCHAR2(150):=  'C_CIDADAO';

	L_PAGE  VARCHAR2(150):=  'LAB';

	V_APP  VARCHAR2(150);

	V_PAGE  VARCHAR2(150);

	V_ACTION  VARCHAR2(150);
/*


		------Label variables

	TYPE  R_LABEL  IS  RECORD(
		MN_gen_form_toolsbar_ctx_1  VARCHAR2(100):='Fechar',
		MN_gen_form_toolsbar_ctx_0  VARCHAR2(100):='Gravar'
	);

	TP_LABEL  R_LABEL;



		------Permission variables

	TYPE  R_PERMISSION  IS  RECORD(
		MN_gen_form_toolsbar_ctx_1  BOOLEAN:=TRUE,
		MN_gen_form_toolsbar_ctx_0  BOOLEAN:=TRUE
	);

	TP_PERMISSION  R_PERMISSION;



		------Parameter variables

	TYPE  R_PARAMETER  IS  RECORD(
		MN_gen_form_toolsbar_ctx_1  VARCHAR2(1000),
		MN_gen_form_toolsbar_ctx_0  VARCHAR2(1000)
	);

	TP_PARAMETER  R_PARAMETER;



		------Record variables

	TYPE  R_RECORD  IS  RECORD(
	);

	TP_RECORD  R_RECORD;

*/



	-----LOAD: load parameters
	PROCEDURE  LOAD
	IS
                        

	BEGIN


		------load simple type


		------------------------------------------------------------------------------------------------------------


		------load complex type

	END  LOAD;



	-----TOOLBAR_gen_form_toolsbar: group of button for gen_form_toolsbar (Dynamic Menu)
	PROCEDURE  TOOLBAR_gen_form_toolsbar(p	OUT  REDGLOBAL.GLB_TYPE.t_toolbar)
	IS
                        

		t	REDGLOBAL.GLB_TYPE.t_toolbar;
	BEGIN

		C_CIDADAO_LAB2_PLSQL_DB.TOOLBAR_gen_form_toolsbar(p=>t);

		p:=t;

	END  TOOLBAR_gen_form_toolsbar;



	-----list: main procedure
	PROCEDURE  list
	IS
                        

	BEGIN


		------load parameters into record

		LOAD;

		REDGLOBAL.GLB_CORE.LOG_DEBUG('ACTION DB:C_CIDADAO_LAB2_PLSQL_DB.DML_SELECT_ALL');

		C_CIDADAO_LAB2_PLSQL_DB.DML_SELECT_ALL;


		------invoke   gen_form_toolsbar  type  


		TOOLBAR_gen_form_toolsbar(REDGLOBAL.GLB_TYPE.TP_PAGE.TBL_TOOLBAR('gen_form_toolsbar'));


		------invoke xml engine for igrp2.2

		REDGLOBAL.GLB_CORE.XML_2DOT2(p_title=>REDGLOBAL.GLB_CORE.get_lang_label(p_code=>'LAB.PLSQL.title',
							p_text=>'Teste Gerador PLSQL'||REDGLOBAL.GLB_TYPE.get_description_dml(REDGLOBAL.GLB_CORE.get_action)));

	END  list;



	-----form_:Fechar(LAB - PLSQL)
	PROCEDURE  form_
	IS
                        

	BEGIN


		------load parameters into record

		LOAD;


		------invoke db dml_

		REDGLOBAL.GLB_CORE.LOG_DEBUG('ACTION DB:C_CIDADAO_LAB2_PLSQL_DB.dml_');

		C_CIDADAO_LAB2_PLSQL_DB.dml_(p_page=>V_PAGE,p_action=>V_ACTION,p_app=>V_APP);


		------invoke xml engine for igrp2.2

		REDGLOBAL.GLB_CORE.GO_TO_PAGE(p_page=>NVL(V_PAGE,L_PAGE),p_action=>NVL(V_ACTION,'PLSQL'),p_app=>NVL(V_APP,L_APP));


		------catch error exception

		EXCEPTION


		------catch user validation error

		WHEN  REDGLOBAL.GLB_CORE.L_EX_VALIDATION  THEN

			ROLLBACK;

			REDGLOBAL.GLB_CORE.LOG_DEBUG('L_EX_VALIDATION  '||SQLERRM||DBMS_UTILITY.FORMAT_ERROR_BACKTRACE);

			REDGLOBAL.GLB_CORE.load_message;

			REDGLOBAL.GLB_CORE.GO_TO_PAGE(p_page=>L_PAGE,p_action=>'PLSQL',p_app=>L_APP);


		------catch others error

		WHEN  OTHERS  THEN

			ROLLBACK;

			REDGLOBAL.GLB_CORE.LOG_DEBUG('SEVERE:   '||SQLERRM||DBMS_UTILITY.FORMAT_ERROR_BACKTRACE);

			REDGLOBAL.GLB_CORE.messagedmlerror;

			REDGLOBAL.GLB_CORE.GO_TO_PAGE(p_page=>L_PAGE,p_action=>'PLSQL',p_app=>L_APP);

	END  form_;



	-----form_:Gravar(LAB - PLSQL)
	PROCEDURE  form_
	IS
                        

	BEGIN


		------load parameters into record

		LOAD;


		------invoke db dml_

		REDGLOBAL.GLB_CORE.LOG_DEBUG('ACTION DB:C_CIDADAO_LAB2_PLSQL_DB.dml_');

		C_CIDADAO_LAB2_PLSQL_DB.dml_(p_page=>V_PAGE,p_action=>V_ACTION,p_app=>V_APP);


		------invoke xml engine for igrp2.2

		REDGLOBAL.GLB_CORE.GO_TO_PAGE(p_page=>NVL(V_PAGE,L_PAGE),p_action=>NVL(V_ACTION,'PLSQL'),p_app=>NVL(V_APP,L_APP));


		------catch error exception

		EXCEPTION


		------catch user validation error

		WHEN  REDGLOBAL.GLB_CORE.L_EX_VALIDATION  THEN

			ROLLBACK;

			REDGLOBAL.GLB_CORE.LOG_DEBUG('L_EX_VALIDATION  '||SQLERRM||DBMS_UTILITY.FORMAT_ERROR_BACKTRACE);

			REDGLOBAL.GLB_CORE.load_message;

			REDGLOBAL.GLB_CORE.GO_TO_PAGE(p_page=>L_PAGE,p_action=>'PLSQL',p_app=>L_APP);


		------catch others error

		WHEN  OTHERS  THEN

			ROLLBACK;

			REDGLOBAL.GLB_CORE.LOG_DEBUG('SEVERE:   '||SQLERRM||DBMS_UTILITY.FORMAT_ERROR_BACKTRACE);

			REDGLOBAL.GLB_CORE.messagedmlerror;

			REDGLOBAL.GLB_CORE.GO_TO_PAGE(p_page=>L_PAGE,p_action=>'PLSQL',p_app=>L_APP);

	END  form_;

END;
/

