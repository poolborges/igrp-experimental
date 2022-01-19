/*-------------------------*/

/*Create View*/

package nosi.webapps.kofax.pages.dashboard;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;

public class DashBoardView extends View {
	
	
	public Field tipo_objeto;
	public Field data_de;
	public Field data_ate;
	public Field statbox_1_tit;
	public Field statbox_1_val;
	public Field statbox_1_txt;
	public Field statbox_1_url;
	public Field statbox_1_bg;
	public Field statbox_1_icn;
	public IGRPForm form_1;
	public IGRPStartBox statbox_1;
	public IGRPChart chart_1;

	public IGRPButton btn_pesquisar;
	public DashBoardView(DashBoard model){
		this.setPageTitle("DashBoard");
			
		form_1 = new IGRPForm("form_1","");
		statbox_1 = new IGRPStartBox("statbox_1","");
		chart_1 = new IGRPChart("chart_1","Arquivos Recuperados Por Tipos");
		tipo_objeto = new ListField(model,"tipo_objeto");
		tipo_objeto.setLabel("Tipo Objeto");
		tipo_objeto.propertie().add("name","p_tipo_objeto").add("type","select").add("multiple","false").add("domain","").add("maxlength","30").add("required","false").add("change","false").add("disabled","false").add("right","false");
		data_de = new DateField(model,"data_de");
		data_de.setLabel("Data de");
		data_de.propertie().add("name","p_data_de").add("type","date").add("format","IGRP_datePicker").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false").add("class","default");
		data_ate = new DateField(model,"data_ate");
		data_ate.setLabel("Data ate");
		data_ate.propertie().add("name","p_data_ate").add("type","date").add("format","IGRP_datePicker").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false").add("class","default");
		statbox_1_tit = new TextField(model,"statbox_1_tit");
		statbox_1_tit.setLabel("Total de Arquivos Recuperados");
		statbox_1_tit.setValue("Total de Arquivos Recuperados");
		statbox_1_tit.propertie().add("name","p_statbox_1_tit").add("type","text").add("maxlength","4000").add("persist","true");
		statbox_1_val = new TextField(model,"statbox_1_val");
		statbox_1_val.setLabel("Value");
		statbox_1_val.setValue("0");
		statbox_1_val.propertie().add("name","p_statbox_1_val").add("type","text").add("maxlength","4000").add("persist","true");
		statbox_1_txt = new TextField(model,"statbox_1_txt");
		statbox_1_txt.setLabel("Url Text");
		statbox_1_txt.propertie().add("name","p_statbox_1_txt").add("type","text").add("maxlength","4000").add("persist","true");
		statbox_1_url = new TextField(model,"statbox_1_url");
		statbox_1_url.setLabel("Url");
		statbox_1_url.propertie().add("name","p_statbox_1_url").add("type","text").add("maxlength","4000").add("persist","true");
		statbox_1_bg = new TextField(model,"statbox_1_bg");
		statbox_1_bg.setLabel("Background");
		statbox_1_bg.setValue("cp-emerald");
		statbox_1_bg.propertie().add("name","p_statbox_1_bg").add("type","text").add("maxlength","4000").add("persist","true");
		statbox_1_icn = new TextField(model,"statbox_1_icn");
		statbox_1_icn.setLabel("Icon");
		statbox_1_icn.setValue("fa-check");
		statbox_1_icn.propertie().add("name","p_statbox_1_icn").add("type","text").add("maxlength","4000").add("persist","true");

		btn_pesquisar = new IGRPButton("Pesquisar","kofax","DashBoard","pesquisar","submit","primary|fa-search","","");
		btn_pesquisar.propertie.add("type","form").add("code","").add("class","primary").add("rel","pesquisar");
		
		chart_1.setCaption("");
		chart_1.setChart_type("column");
		chart_1.setXaxys("Eixo de X");
		chart_1.setYaxys("Eixo de Y");
		chart_1.setUrl("#");
		chart_1.addColor("#501bad").addColor("#ce910e").addColor("#6399d9").addColor("#19a4e5");

	}
		
	@Override
	public void render(){
		
		form_1.addField(tipo_objeto);
		form_1.addField(data_de);
		form_1.addField(data_ate);

		statbox_1.addField(statbox_1_tit);
		statbox_1.addField(statbox_1_val);
		statbox_1.addField(statbox_1_txt);
		statbox_1.addField(statbox_1_url);
		statbox_1.addField(statbox_1_bg);
		statbox_1.addField(statbox_1_icn);


		form_1.addButton(btn_pesquisar);
		this.addToPage(form_1);
		this.addToPage(statbox_1);
		this.addToPage(chart_1);
	}
}
/*-------------------------*/