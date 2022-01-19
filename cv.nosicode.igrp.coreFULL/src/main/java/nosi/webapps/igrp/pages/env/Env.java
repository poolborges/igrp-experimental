/*---------------------- Create Model ----------------------*/
package nosi.webapps.igrp.pages.env;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

public class Env extends Model{		
	@RParam(rParamName = "p_id")
	private int id;
	@RParam(rParamName = "p_sectionheader_1_text")
	private String sectionheader_1_text;
	@RParam(rParamName = "p_dad")
	private String dad;
	@RParam(rParamName = "p_name")
	private String name;
	@RParam(rParamName = "p_description")
	private String description;
	@RParam(rParamName = "p_img_src")
	private String img_src;
	@RParam(rParamName = "p_action_fk")
	private int action_fk;
	@RParam(rParamName = "p_host")
	private String host;
	@RParam(rParamName = "p_apache_dad")
	private String apache_dad;
	@RParam(rParamName = "p_link_menu")
	private String link_menu;
	@RParam(rParamName = "p_link_center")
	private String link_center;
	@RParam(rParamName = "p_templates")
	private String templates;
	@RParam(rParamName = "p_flg_old")
	private int flg_old;
	@RParam(rParamName = "p_flg_external")
	private int flg_external;
	@RParam(rParamName = "p_status")
	private int status;
	
	public void setSectionheader_1_text(String sectionheader_1_text){
		this.sectionheader_1_text = sectionheader_1_text;
	}
	public String getSectionheader_1_text(){
		return this.sectionheader_1_text;
	}
	
	public void setDad(String dad){
		this.dad = dad;
	}
	public String getDad(){
		return this.dad;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setImg_src(String img_src){
		this.img_src = img_src;
	}
	public String getImg_src(){
		return this.img_src;
	}
	
	public void setAction_fk(int action_fk){
		this.action_fk = action_fk;
	}
	public int getAction_fk(){
		return this.action_fk;
	}
	
	public void setHost(String host){
		this.host = host;
	}
	public String getHost(){
		return this.host;
	}
	
	public void setApache_dad(String apache_dad){
		this.apache_dad = apache_dad;
	}
	public String getApache_dad(){
		return this.apache_dad;
	}
	
	public void setLink_menu(String link_menu){
		this.link_menu = link_menu;
	}
	public String getLink_menu(){
		return this.link_menu;
	}
	
	public void setLink_center(String link_center){
		this.link_center = link_center;
	}
	public String getLink_center(){
		return this.link_center;
	}
	
	public void setTemplates(String templates){
		this.templates = templates;
	}
	public String getTemplates(){
		return this.templates;
	}
	
	public void setFlg_old(int flg_old){
		this.flg_old = flg_old;
	}
	public int getFlg_old(){
		return this.flg_old;
	}
	
	public void setFlg_external(int flg_external){
		this.flg_external = flg_external;
	}
	public int getFlg_external(){
		return this.flg_external;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


}
/*-------------------------*/