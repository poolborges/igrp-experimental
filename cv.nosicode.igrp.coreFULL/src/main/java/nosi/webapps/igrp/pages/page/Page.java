/*---------------------- Create Model ----------------------*/
package nosi.webapps.igrp.pages.page;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

public class Page extends Model{		
	@RParam(rParamName = "p_sectionheader_1_text")
	private String sectionheader_1_text;
	@RParam(rParamName = "p_env_fk")
	private int env_fk;
	@RParam(rParamName = "p_page")
	private String page;
	@RParam(rParamName = "p_action_descr")
	private String action_descr;
	@RParam(rParamName = "p_id")
	private int p_id;
	@RParam(rParamName = "p_table_name")
	private String p_table_name;
	@RParam(rParamName = "p_xsl_src")
	private String p_xsl_src;
	@RParam(rParamName = "p_img_src")
	private String p_img_src;
	@RParam(rParamName = "p_action")
	private String p_action;
	@RParam(rParamName = "p_page_type")
	private String p_page_type;
	@RParam(rParamName = "p_page_descr")
	private String p_page_descr;
	@RParam(rParamName = "p_flg_menu")
	private int p_flg_menu;
	@RParam(rParamName = "p_flg_transaction")
	private int p_flg_transaction;
	@RParam(rParamName = "p_self_id")
	private int p_self_id;
	@RParam(rParamName = "p_self_fw_id")
	private int p_self_fw_id;
	@RParam(rParamName = "p_version")
	private String version;
	@RParam(rParamName = "p_db_connection")
	private String p_db_connection;
	@RParam(rParamName = "p_flg_offline")
	private int p_flg_offline;
	@RParam(rParamName = "p_flg_internet")
	private int p_flg_internet;
	@RParam(rParamName = "p_status")
	private int p_status;
	@RParam(rParamName = "p_proc_name")
	private String p_proc_name;
	
	public void setSectionheader_1_text(String sectionheader_1_text){
		this.sectionheader_1_text = sectionheader_1_text;
	}
	public String getSectionheader_1_text(){
		return this.sectionheader_1_text;
	}
	
	public void setEnv_fk(int env_fk){
		this.env_fk = env_fk;
	}
	public int getEnv_fk(){
		return this.env_fk;
	}
	
	public void setPage(String page){
		this.page = page;
	}
	public String getPage(){
		return this.page;
	}
	
	public void setAction_descr(String action_descr){
		this.action_descr = action_descr;
	}
	public String getAction_descr(){
		return this.action_descr;
	}
	
	public void setP_id(int p_id){
		this.p_id = p_id;
	}
	public int getP_id(){
		return this.p_id;
	}
	
	public void setP_table_name(String p_table_name){
		this.p_table_name = p_table_name;
	}
	public String getP_table_name(){
		return this.p_table_name;
	}
	
	public void setP_xsl_src(String p_xsl_src){
		this.p_xsl_src = p_xsl_src;
	}
	public String getP_xsl_src(){
		return this.p_xsl_src;
	}
	
	public void setP_img_src(String p_img_src){
		this.p_img_src = p_img_src;
	}
	public String getP_img_src(){
		return this.p_img_src;
	}
	
	public void setP_action(String p_action){
		this.p_action = p_action;
	}
	public String getP_action(){
		return this.p_action;
	}
	
	public void setP_page_type(String p_page_type){
		this.p_page_type = p_page_type;
	}
	public String getP_page_type(){
		return this.p_page_type;
	}
	
	public void setP_page_descr(String p_page_descr){
		this.p_page_descr = p_page_descr;
	}
	public String getP_page_descr(){
		return this.p_page_descr;
	}
	
	public void setP_flg_menu(int p_flg_menu){
		this.p_flg_menu = p_flg_menu;
	}
	public int getP_flg_menu(){
		return this.p_flg_menu;
	}
	
	public void setP_flg_transaction(int p_flg_transaction){
		this.p_flg_transaction = p_flg_transaction;
	}
	public int getP_flg_transaction(){
		return this.p_flg_transaction;
	}
	
	public void setP_self_id(int p_self_id){
		this.p_self_id = p_self_id;
	}
	public int getP_self_id(){
		return this.p_self_id;
	}
	
	public void setP_self_fw_id(int p_self_fw_id){
		this.p_self_fw_id = p_self_fw_id;
	}
	public int getP_self_fw_id(){
		return this.p_self_fw_id;
	}
	
	public void setP_version(String p_version){
		this.version = p_version;
	}
	public String getP_version(){
		return this.version;
	}
	
	public void setP_db_connection(String p_db_connection){
		this.p_db_connection = p_db_connection;
	}
	public String getP_db_connection(){
		return this.p_db_connection;
	}
	
	public void setP_flg_offline(int p_flg_offline){
		this.p_flg_offline = p_flg_offline;
	}
	public int getP_flg_offline(){
		return this.p_flg_offline;
	}
	
	public void setP_flg_internet(int p_flg_internet){
		this.p_flg_internet = p_flg_internet;
	}
	public int getP_flg_internet(){
		return this.p_flg_internet;
	}
	
	public void setP_status(int p_status){
		this.p_status = p_status;
	}
	public int getP_status(){
		return this.p_status;
	}
	
	public void setP_proc_name(String p_proc_name){
		this.p_proc_name = p_proc_name;
	}
	public String getP_proc_name(){
		return this.p_proc_name;
	}


}
/*-------------------------*/