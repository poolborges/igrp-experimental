package nosi.core.config;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import nosi.base.ActiveRecord.PersistenceUtils;
import nosi.core.gui.components.IGRPButton;
import nosi.core.gui.components.IGRPToolsBar;
import nosi.core.gui.page.Page;
import nosi.core.igrp.mingrations.MigrationIGRPInitConfig;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.helpers.Permission;
import nosi.core.xml.XMLWritter;
import nosi.webapps.igrp.dao.Action;
import nosi.webapps.igrp.dao.Application;
import nosi.webapps.igrp.dao.User;

public class Config {
	
	public static String TITLE = "";
	public static String target = "";
	public static String type_header = "normal";
	public static final String RESERVE_CODE_IMPORP_PACKAGE_CONTROLLER = "/*---- Import your packages here... ----*/";
	public static final String RESERVE_CODE_ACTIONS_CONTROLLER = "/*---- Insert your actions here... ----*/";
	public static final String RESERCE_CODE_ON_ACTIONS_CONTROLLER = "/*---- Insert your code here... ----*/";
	public static final String RESERVE_CODE_END = "/*---- End ----*/";
	
	public static String getHeader(){
		XMLWritter xml = new XMLWritter();
		xml.setElement("tamplate", "");
		xml.setElement("title", TITLE);
		xml.setElement("version",getVersion());
		xml.setElement("link",getLink());
		xml.setElement("link_img",getLinkImg());
		if(!target.equals("")){
			xml.setElement("target", target);
		}
		xml.startElement("site");
			xml.setElement("welcome_note",getWelcomeNote());
			xml.setElement("footer_note", getFooterName());
			xml.setElement("user_name", getUserName());
			IGRPToolsBar button = new IGRPToolsBar("button");
			button.addButton(new IGRPButton("Sair", "webapps?r=igrp", "login", "logout", "_self", "exit.png","",""));
			xml.addXml(button.toXmlButton());
		xml.endElement();
		xml.setElement("app", "igrp");
		xml.setElement("page", "form");
		xml.startElement("plsql");
			xml.setElement("action", "1");
			xml.setElement("package_db", "FORM_DESIGNER_DB");
			xml.setElement("package_html", "FORM_DESIGNER_HTML");
			xml.setElement("package_instance", "");
			xml.setElement("with_replace", "false");
			xml.setElement("with_label", "false");
			xml.setElement("with_biztalk", "false");
			xml.setElement("dynamic_menu", "false");
			xml.setElement("copy_menu", "false");
			xml.setElement("package_copy_db", "");
			xml.setElement("package_copy_html", "");
		xml.endElement();
		xml.startElement("navigation");
		xml.writeAttribute("url", "webapps?");
		xml.writeAttribute("prm_app", "prm_app");
		xml.writeAttribute("prm_page", "prm_page");
		xml.writeAttribute("prm_action", "r");
		xml.endElement();
		xml.startElement("slide-menu");
		xml.writeAttribute("file",getLinkSileMenu());
		xml.endElement();
		if(type_header.equals("home")){
			xml.startElement("applications");
			xml.writeAttribute("file","webapps?r=igrp/env/myApps");
			xml.endElement();
		}
		target = "";
		TITLE = "";
		type_header = "normal";
		return xml.toString();
	}
	
	private static String getUserName() {
		User u = (User) Igrp.getInstance().getUser().getIdentity();
		return (u!=null)?u.getName():"red-igrp";
	}

	public static Properties getConfig(){
		Properties configs = new Properties();
		for(nosi.webapps.igrp.dao.Config c: new nosi.webapps.igrp.dao.Config().findAll()){
			configs.put(c.getName(),c.getValue());
		}
		return configs;
	}
	
	public static String getBasePathConfig(){
		return Igrp.getInstance().getServlet().getServletContext().getRealPath("/WEB-INF/config/");
	}
	
	public static String getPathLib(){
		return Igrp.getInstance().getServlet().getServletContext().getRealPath("/WEB-INF/lib/");
	}
	
	public static String getBasePathClass(){
		return Igrp.getInstance().getServlet().getServletContext().getRealPath("/WEB-INF/classes/");
	}
	
	public static String getProject_loc(){
		return Igrp.getInstance().getAppConfig().getProject_loc();
	}
	
	public static String getDbType(){
		String name = "h2";
		DbConfig dbC = Igrp.getInstance().getDbConfig();
		List<DbInfo> dbinfo = dbC.getDbInfo();
		if(!dbinfo.isEmpty()){
			for(DbInfo db:dbinfo){
				if(db.getDefault_db().equals("true")){
					name = db.getConnectionName();
					break;
				}
			}
		}
		return name;
	}
	
	public static String getAutenticationType(){
		return Igrp.getInstance().getAppConfig().getAuthenticationType();
	}
	
	public static String getBasePathXsl(){
		return Igrp.getInstance().getServlet().getServletContext().getRealPath("/");
	}
	public static String getLinkImg(){
		return getRootPaht()+(getConfig().get("link_img")!=null? getConfig().get("link_img").toString()+getPageVersion():"images/IGRP/IGRP"+getPageVersion());
	}
	public static String getLinkImg2_2(){
		return getRootPaht()+(getConfig().get("link_img")!=null? getConfig().get("link_img").toString()+getPageVersion():"images/IGRP/IGRP2.2/");
	}
	
	public static String getLink(){
		return getConfig().get("link")!=null? getConfig().get("link").toString():"webapps?r=igrp/home/index";
	}
	public static String getVersion(){
		return getConfig().get("version")!=null? getConfig().get("version").toString():"1.0";
	}
	public static String getLinkSileMenu(){
		return "webapps?r=igrp/pesquisar-menu/myMenu&amp;dad="+Permission.getCurrentEnv();
	}
	public static String getLinkTopMenu(){
		return getConfig().get("link_top_menu")!=null? getConfig().get("link_top_menu").toString():"";
	}
	public static String getFooterName(){
		return getConfig().get("footer_name")!=null? getConfig().get("footer_name").toString():"2017 - Copyright NOSI";
	}
	public static String getWelcomeNote(){
		return getConfig().get("welcome_note")!=null? getConfig().get("welcome_note").toString():"Ola";
	}
	
	public static String getPageVersion(){
		String app = Igrp.getInstance().getCurrentAppName();
		String page = Igrp.getInstance().getCurrentPageName();
		String action = Igrp.getInstance().getCurrentActionName();
		if(!app.equals("") && !page.equals("") && !action.equals("")){
			Application appl = new Application();
			appl = appl.findOne(appl.getCriteria().where(
					appl.getBuilder().equal(appl.getRoot().get("dad"), app)));
			Action ac = new Action();
			ac = ac.findOne(ac.getCriteria().where(
					ac.getBuilder().equal(ac.getRoot().get("application"), appl),
//					ac.getBuilder().equal(ac.getRoot().get("action"), action),
					ac.getBuilder().equal(ac.getRoot().get("page"), Page.resolvePageName(page))));
			return ac!=null?ac.getVersion():"2.3";		
		}
		return "2.3";
	}
	
	public static String getResolveUrl(String app,String page,String action){
		HttpServletRequest req = Igrp.getInstance().getRequest();
		String url = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/IGRP/webapps?r="+app+"/"+page+"/"+action+"&amp;dad="+Permission.getCurrentEnv();
		return url;
	}
	public static String getRootPaht(){
		return Igrp.getInstance().getBasePath()+"/";
	}

	public static HashMap<String,String> getVersions() {
		HashMap<String,String> versions = new HashMap<>();
		versions.put("", "--- Version ---");
		versions.put("2.2", "2.2");
		versions.put("2.3", "2.3");
		return versions;
	}
	
	public static String getResolvePathXsl(String app,String page,String version){
		return "images"+"/"+"IGRP"+"/"+"IGRP"+version+"/"+"app"+"/"+app.toLowerCase()+"/"+page.toLowerCase();
	}
	
	public static String getResolvePathClass(String app,String page,String version){
		return "images"+"/"+"IGRP"+"/"+"IGRP"+version+"/"+"app"+"/"+app.toLowerCase()+"/"+page.toLowerCase();
	}
	
	public static String getDefaultPageController(String app,String title){
		return "package nosi.webapps."+app.toLowerCase()+".pages.defaultpage;\n"
				 + "import nosi.webapps.igrp.pages.home.HomeAppView;\n"
				 + "import java.io.IOException;\n"
				 + "import nosi.core.webapp.Response;\n"
				 + "import nosi.core.webapp.Controller;\n"
				 + "public class DefaultPageController extends Controller {	\n"
						+ "public Response actionIndex() throws IOException{\n"
							+ "HomeAppView view = new HomeAppView();\n"
							+ "view.title = \""+title+"\";\n"
							+ "return this.renderView(view,true);\n"
						+ "}\n"
				  + "}";
	}
	
	public static void main(String[]args){
		//new nosi.webapps.igrp.dao.Config().findAll();
	}

	public static String getPackage(String app, String page,String action) {
		String basePackage = "nosi.webapps." + app.toLowerCase() + ".pages." + page.toLowerCase() + "." + page + "Controller";
		if(!app.equals("") && !page.equals("") && !action.equals("")){
			Application appl = new Application();
			appl = appl.findOne(appl.getCriteria().where(
					appl.getBuilder().equal(appl.getRoot().get("dad"), app)));
			Action ac = new Action();
			ac = ac.findOne(ac.getCriteria().where(
					ac.getBuilder().equal(ac.getRoot().get("application"), appl),
					ac.getBuilder().equal(ac.getRoot().get("action"), action),
					ac.getBuilder().equal(ac.getRoot().get("page"), Page.resolvePageName(page))));
			return (ac!=null && ac.getPackage_name()!=null)?ac.getPackage_name().toLowerCase():basePackage;		
		}
		return basePackage;
	}
	
	public static void configurationApp(){	
		if(!isInstall()){
			MigrationIGRPInitConfig.start();
			configSetInstall();
		}
	}
	
	private static boolean isInstall() {
		nosi.webapps.igrp.dao.Config config = null;
		try{
			config = new nosi.webapps.igrp.dao.Config();
			config = config.find().andWhere("name", "=", "install").one();
			return config!=null;
		}catch(Exception e){
			return false;
		}
	}
	
	private static void  configSetInstall(){
		nosi.webapps.igrp.dao.Config config = new nosi.webapps.igrp.dao.Config("install", "ok");
		if(config.insert()!=null){
			PersistenceUtils.confiOtherConnections();
			System.out.println("IGRP foi instalado com sucesso!");
		}else{
			System.err.println("Nao foi possivel concluir a instacao do IGRP!");
		}
	}
}