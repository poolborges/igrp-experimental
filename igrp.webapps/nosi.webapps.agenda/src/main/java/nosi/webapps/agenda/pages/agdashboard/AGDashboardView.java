/*-------------------------*/

/*Create View*/

package nosi.webapps.agenda.pages.agdashboard;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;

public class AGDashboardView extends View {
	
	
	public Field sectionheader_1_text;
	public Field entidade;
	public Field data_de_;
	public Field data_ate;
	public Field agenda_solicitada_tit;
	public Field agenda_solicitada_val;
	public Field agenda_solicitada_txt;
	public Field agenda_solicitada_url;
	public Field agenda_solicitada_bg;
	public Field agenda_solicitada_icn;
	public Field agendas_confirmadas_tit;
	public Field agendas_confirmadas_val;
	public Field agendas_confirmadas_txt;
	public Field agendas_confirmadas_url;
	public Field agendas_confirmadas_bg;
	public Field agendas_confirmadas_icn;
	public Field agendas_realizadas_tit;
	public Field agendas_realizadas_val;
	public Field agendas_realizadas_txt;
	public Field agendas_realizadas_url;
	public Field agendas_realizadas_bg;
	public Field agendas_realizadas_icn;
	public IGRPSectionHeader sectionheader_1;
	public IGRPForm form_1;
	public IGRPBox box_1;
	public IGRPStartBox agenda_solicitada;
	public IGRPChart chart_1;
	public IGRPStartBox agendas_confirmadas;
	public IGRPBox box_2;
	public IGRPStartBox agendas_realizadas;
	public IGRPChart chart_2;
	public IGRPChart chart_5;
	public IGRPChart chart_3;

	public IGRPButton btn_pesquisar;
	public AGDashboardView(AGDashboard model){
		this.setPageTitle("Dashboard Agenda Eletronica");
			
		sectionheader_1 = new IGRPSectionHeader("sectionheader_1","");
		form_1 = new IGRPForm("form_1","");
		box_1 = new IGRPBox("box_1","Agendamento do Dia");
		agenda_solicitada = new IGRPStartBox("agenda_solicitada","");
		chart_1 = new IGRPChart("chart_1","Agendamentos por balcao");
		agendas_confirmadas = new IGRPStartBox("agendas_confirmadas","");
		box_2 = new IGRPBox("box_2","");
		agendas_realizadas = new IGRPStartBox("agendas_realizadas","");
		chart_2 = new IGRPChart("chart_2","Agendamentos por assunto");
		chart_5 = new IGRPChart("chart_5","Agendamento por Serviço");
		chart_3 = new IGRPChart("chart_3","Agendamentos por estado");
		sectionheader_1_text = new TextField(model,"sectionheader_1_text");
		sectionheader_1_text.setLabel("");
		sectionheader_1_text.setValue("Dashboard Agendamento");
		sectionheader_1_text.propertie().add("type","text").add("name","p_sectionheader_1_text").add("persist","true").add("maxlength","4000");
		entidade = new ListField(model,"entidade");
		entidade.setLabel("Entidade");
		entidade.propertie().add("name","p_entidade").add("type","select").add("multiple","false").add("domain","").add("maxlength","30").add("required","false").add("change","false").add("disabled","false").add("right","false");
		data_de_ = new DateField(model,"data_de_");
		data_de_.setLabel("Data de ");
		data_de_.propertie().add("name","p_data_de_").add("type","date").add("format","IGRP_datePicker").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false").add("class","primary");
		data_ate = new DateField(model,"data_ate");
		data_ate.setLabel("Data até");
		data_ate.propertie().add("name","p_data_ate").add("type","date").add("format","IGRP_datePicker").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false").add("class","primary");
		agenda_solicitada_tit = new TextField(model,"agenda_solicitada_tit");
		agenda_solicitada_tit.setLabel("Box Title");
		agenda_solicitada_tit.setValue("Agendas Solicitadas");
		agenda_solicitada_tit.propertie().add("name","p_agenda_solicitada_tit").add("type","text").add("maxlength","4000").add("persist","true");
		agenda_solicitada_val = new TextField(model,"agenda_solicitada_val");
		agenda_solicitada_val.setLabel("Value");
		agenda_solicitada_val.setValue("0");
		agenda_solicitada_val.propertie().add("name","p_agenda_solicitada_val").add("type","text").add("maxlength","4000").add("persist","true");
		agenda_solicitada_txt = new TextField(model,"agenda_solicitada_txt");
		agenda_solicitada_txt.setLabel("Url Text");
		agenda_solicitada_txt.propertie().add("name","p_agenda_solicitada_txt").add("type","text").add("maxlength","4000").add("persist","true");
		agenda_solicitada_url = new TextField(model,"agenda_solicitada_url");
		agenda_solicitada_url.setLabel("Url");
		agenda_solicitada_url.setValue("http://www.example.com");
		agenda_solicitada_url.propertie().add("name","p_agenda_solicitada_url").add("type","text").add("maxlength","4000").add("persist","true");
		agenda_solicitada_bg = new TextField(model,"agenda_solicitada_bg");
		agenda_solicitada_bg.setLabel("Background");
		agenda_solicitada_bg.setValue("cp-yellow");
		agenda_solicitada_bg.propertie().add("name","p_agenda_solicitada_bg").add("type","text").add("maxlength","4000").add("persist","true");
		agenda_solicitada_icn = new TextField(model,"agenda_solicitada_icn");
		agenda_solicitada_icn.setLabel("Icon");
		agenda_solicitada_icn.setValue("fa-check");
		agenda_solicitada_icn.propertie().add("name","p_agenda_solicitada_icn").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_confirmadas_tit = new TextField(model,"agendas_confirmadas_tit");
		agendas_confirmadas_tit.setLabel("Box Title");
		agendas_confirmadas_tit.setValue("Agendas Confirmadas");
		agendas_confirmadas_tit.propertie().add("name","p_agendas_confirmadas_tit").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_confirmadas_val = new TextField(model,"agendas_confirmadas_val");
		agendas_confirmadas_val.setLabel("Value");
		agendas_confirmadas_val.setValue("0");
		agendas_confirmadas_val.propertie().add("name","p_agendas_confirmadas_val").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_confirmadas_txt = new TextField(model,"agendas_confirmadas_txt");
		agendas_confirmadas_txt.setLabel("Url Text");
		agendas_confirmadas_txt.propertie().add("name","p_agendas_confirmadas_txt").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_confirmadas_url = new TextField(model,"agendas_confirmadas_url");
		agendas_confirmadas_url.setLabel("Url");
		agendas_confirmadas_url.setValue("http://www.example.com");
		agendas_confirmadas_url.propertie().add("name","p_agendas_confirmadas_url").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_confirmadas_bg = new TextField(model,"agendas_confirmadas_bg");
		agendas_confirmadas_bg.setLabel("Background");
		agendas_confirmadas_bg.setValue("cp-cyan");
		agendas_confirmadas_bg.propertie().add("name","p_agendas_confirmadas_bg").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_confirmadas_icn = new TextField(model,"agendas_confirmadas_icn");
		agendas_confirmadas_icn.setLabel("Icon");
		agendas_confirmadas_icn.setValue("fa-check");
		agendas_confirmadas_icn.propertie().add("name","p_agendas_confirmadas_icn").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_realizadas_tit = new TextField(model,"agendas_realizadas_tit");
		agendas_realizadas_tit.setLabel("Box Title");
		agendas_realizadas_tit.setValue("Agendas Realizadas");
		agendas_realizadas_tit.propertie().add("name","p_agendas_realizadas_tit").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_realizadas_val = new TextField(model,"agendas_realizadas_val");
		agendas_realizadas_val.setLabel("Value");
		agendas_realizadas_val.setValue("0");
		agendas_realizadas_val.propertie().add("name","p_agendas_realizadas_val").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_realizadas_txt = new TextField(model,"agendas_realizadas_txt");
		agendas_realizadas_txt.setLabel("");
		agendas_realizadas_txt.setValue("");
		agendas_realizadas_txt.propertie().add("name","p_agendas_realizadas_txt").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_realizadas_url = new TextField(model,"agendas_realizadas_url");
		agendas_realizadas_url.setLabel("Url");
		agendas_realizadas_url.setValue("http://www.example.com");
		agendas_realizadas_url.propertie().add("name","p_agendas_realizadas_url").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_realizadas_bg = new TextField(model,"agendas_realizadas_bg");
		agendas_realizadas_bg.setLabel("Background");
		agendas_realizadas_bg.setValue("cp-emerald");
		agendas_realizadas_bg.propertie().add("name","p_agendas_realizadas_bg").add("type","text").add("maxlength","4000").add("persist","true");
		agendas_realizadas_icn = new TextField(model,"agendas_realizadas_icn");
		agendas_realizadas_icn.setLabel("Icon");
		agendas_realizadas_icn.setValue("fa-check");
		agendas_realizadas_icn.propertie().add("name","p_agendas_realizadas_icn").add("type","text").add("maxlength","4000").add("persist","true");

		btn_pesquisar = new IGRPButton("Pesquisar","agenda","AGDashboard","index","submit","primary|fa-search","","");
		btn_pesquisar.propertie.add("type","form").add("code","").add("class","primary").add("rel","pesquisar");
		
		chart_1.setCaption("");
		chart_1.setChart_type("column");
		chart_1.setXaxys("Eixo de X");
		chart_1.setYaxys("Eixo de Y");
		chart_1.setUrl("#");
		chart_1.addColor("#98de3b").addColor("#01e842").addColor("#67f37b").addColor("#1df6a0");

		chart_2.setCaption("");
		chart_2.setChart_type("bar");
		chart_2.setXaxys("Eixo de X");
		chart_2.setYaxys("Eixo de Y");
		chart_2.setUrl("#");
		chart_2.addColor("#3032e1").addColor("#e6127c").addColor("#8bd3e0").addColor("#216cf3");

		chart_5.setCaption("");
		chart_5.setChart_type("column");
		chart_5.setXaxys("Eixo de X");
		chart_5.setYaxys("Eixo de Y");
		chart_5.setUrl("#");
		chart_5.addColor("#813cc0").addColor("#112496").addColor("#7a9a7a").addColor("#44e544");

		chart_3.setCaption("");
		chart_3.setChart_type("column");
		chart_3.setXaxys("Eixo de X");
		chart_3.setYaxys("Eixo de Y");
		chart_3.setUrl("#");
		chart_3.addColor("#FFEB3B").addColor("#0091EA").addColor("#f44336").addColor("#00E676");

	}
		
	@Override
	public void render(){
		
		sectionheader_1.addField(sectionheader_1_text);

		form_1.addField(entidade);
		form_1.addField(data_de_);
		form_1.addField(data_ate);


		agenda_solicitada.addField(agenda_solicitada_tit);
		agenda_solicitada.addField(agenda_solicitada_val);
		agenda_solicitada.addField(agenda_solicitada_txt);
		agenda_solicitada.addField(agenda_solicitada_url);
		agenda_solicitada.addField(agenda_solicitada_bg);
		agenda_solicitada.addField(agenda_solicitada_icn);


		agendas_confirmadas.addField(agendas_confirmadas_tit);
		agendas_confirmadas.addField(agendas_confirmadas_val);
		agendas_confirmadas.addField(agendas_confirmadas_txt);
		agendas_confirmadas.addField(agendas_confirmadas_url);
		agendas_confirmadas.addField(agendas_confirmadas_bg);
		agendas_confirmadas.addField(agendas_confirmadas_icn);


		agendas_realizadas.addField(agendas_realizadas_tit);
		agendas_realizadas.addField(agendas_realizadas_val);
		agendas_realizadas.addField(agendas_realizadas_txt);
		agendas_realizadas.addField(agendas_realizadas_url);
		agendas_realizadas.addField(agendas_realizadas_bg);
		agendas_realizadas.addField(agendas_realizadas_icn);




		form_1.addButton(btn_pesquisar);
		this.addToPage(sectionheader_1);
		this.addToPage(form_1);
		this.addToPage(box_1);
		this.addToPage(agenda_solicitada);
		this.addToPage(chart_1);
		this.addToPage(agendas_confirmadas);
		this.addToPage(box_2);
		this.addToPage(agendas_realizadas);
		this.addToPage(chart_2);
		this.addToPage(chart_5);
		this.addToPage(chart_3);
	}
}
/*-------------------------*/