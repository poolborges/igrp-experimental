/*-------------------------*/

/*Create View*/

package nosi.webapps.kofax.pages.pesquisa_arquivo;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;

public class Pesquisa_arquivoView extends View {
	
	
	public Field sectionheader_1_text;
	public Field tipo_de_objecto;
	public Field campo;
	public Field valor;
	public Field descricao;
	public Field data_registo;
	public Field link_1;
	public IGRPSectionHeader sectionheader_1;
	public IGRPForm form_1;
	public IGRPTable table_1;

	public IGRPButton btn_pesquisar;
	public Pesquisa_arquivoView(Pesquisa_arquivo model){
		this.setPageTitle("Pesquisa arquivo");
			
		sectionheader_1 = new IGRPSectionHeader("sectionheader_1","");
		form_1 = new IGRPForm("form_1","");
		table_1 = new IGRPTable("table_1","");
		sectionheader_1_text = new TextField(model,"sectionheader_1_text");
		sectionheader_1_text.setLabel("");
		sectionheader_1_text.propertie().add("type","text").add("name","p_sectionheader_1_text").add("persist","true").add("maxlength","4000");
		tipo_de_objecto = new ListField(model,"tipo_de_objecto");
		tipo_de_objecto.setLabel("Tipo de Objecto");
		tipo_de_objecto.propertie().add("name","p_tipo_de_objecto").add("type","select").add("multiple","false").add("domain","").add("maxlength","30").add("required","false").add("change","true").add("disabled","false").add("right","false");
		campo = new ListField(model,"campo");
		campo.setLabel("Campo");
		campo.propertie().add("name","p_campo").add("type","select").add("multiple","false").add("domain","").add("maxlength","30").add("required","false").add("change","false").add("disabled","false").add("right","false");
		valor = new TextAreaField(model,"valor");
		valor.setLabel("Valor");
		valor.propertie().add("name","p_valor").add("type","textarea").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		descricao = new TextField(model,"descricao");
		descricao.setLabel("Descrição");
		descricao.propertie().add("name","p_descricao").add("type","text").add("maxlength","30").add("align","left").add("lookup_parser","false").add("iskey","false");
		data_registo = new TextField(model,"data_registo");
		data_registo.setLabel("Data Registo");
		data_registo.propertie().add("name","p_data_registo").add("type","text").add("maxlength","30").add("align","left").add("lookup_parser","false").add("iskey","false");
		link_1 = new LinkField(model,"link_1");
		link_1.setLabel("Link");
		link_1.propertie().add("name","p_link_1").add("type","link").add("target","_blank").add("target_fields","").add("action","index").add("page","Pesquisa_arquivo").add("app","kofax").add("class","primary").add("btnSize","").add("iconColor","#333").add("iconClass","").add("img","fa-link").add("maxlength","30").add("align","left").add("lookup_parser","false").add("iskey","false").add("desc","true");

		btn_pesquisar = new IGRPButton("Pesquisar","kofax","Pesquisa_arquivo","index","submit","default|fa-search","","");
		btn_pesquisar.propertie.add("type","form").add("code","").add("class","default").add("rel","pesquisar");
		
	}
		
	@Override
	public void render(){
		
		sectionheader_1.addField(sectionheader_1_text);

		form_1.addField(tipo_de_objecto);
		form_1.addField(campo);
		form_1.addField(valor);

		table_1.addField(tipo_de_objecto);
		table_1.addField(descricao);
		table_1.addField(data_registo);
		table_1.addField(link_1);

		form_1.addButton(btn_pesquisar);
		this.addToPage(sectionheader_1);
		this.addToPage(form_1);
		this.addToPage(table_1);
	}
}
/*-------------------------*/