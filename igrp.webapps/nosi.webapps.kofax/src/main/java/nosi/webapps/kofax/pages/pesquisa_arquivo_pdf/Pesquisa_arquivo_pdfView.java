/*-------------------------*/

/*Create View*/

package nosi.webapps.kofax.pages.pesquisa_arquivo_pdf;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;

public class Pesquisa_arquivo_pdfView extends View {
	
	
	public Field conteudo;
	public Field caminho_do_arquivo;
	public Field tamanho_do_arquivo;
	public Field arquivo;
	public Field sectionheader_1_text;
	public IGRPForm form_1;
	public IGRPTable table_1;
	public IGRPSectionHeader sectionheader_1;

	public IGRPButton btn_pesquisar;
	public Pesquisa_arquivo_pdfView(Pesquisa_arquivo_pdf model){
		this.setPageTitle("Pesquisar os arquivos pdf de acordo com um determinado input");
			
		form_1 = new IGRPForm("form_1","");
		table_1 = new IGRPTable("table_1","");
		sectionheader_1 = new IGRPSectionHeader("sectionheader_1","");
		conteudo = new TextField(model,"conteudo");
		conteudo.setLabel("Conteudo");
		conteudo.propertie().add("name","p_conteudo").add("type","text").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","Digite o conteudo que quer pesquisar").add("right","false");
		caminho_do_arquivo = new TextField(model,"caminho_do_arquivo");
		caminho_do_arquivo.setLabel("Caminho do Arquivo");
		caminho_do_arquivo.setValue("");
		caminho_do_arquivo.propertie().add("name","p_caminho_do_arquivo").add("type","text").add("maxlength","30").add("align","left").add("lookup_parser","false").add("iskey","false");
		tamanho_do_arquivo = new TextField(model,"tamanho_do_arquivo");
		tamanho_do_arquivo.setLabel("Tamanho do Arquivo");
		tamanho_do_arquivo.setValue("");
		tamanho_do_arquivo.propertie().add("name","p_tamanho_do_arquivo").add("type","text").add("maxlength","30").add("align","left").add("lookup_parser","false").add("iskey","false");
		arquivo = new LinkField(model,"arquivo");
		arquivo.setLabel("Arquivo");
		arquivo.setValue("");
		arquivo.propertie().add("name","p_arquivo").add("type","link").add("target","_blank").add("target_fields","").add("action","index").add("page","Pesquisa_arquivo_pdf").add("app","kofax").add("class","primary").add("btnSize","").add("iconColor","#333").add("iconClass","").add("img","fa-link").add("maxlength","30").add("align","left").add("lookup_parser","false").add("iskey","false").add("desc","true");
		sectionheader_1_text = new TextField(model,"sectionheader_1_text");
		sectionheader_1_text.setLabel("");
		sectionheader_1_text.setValue("Pesquisar nos Arquivos PDF");
		sectionheader_1_text.propertie().add("type","text").add("name","p_sectionheader_1_text").add("persist","true").add("maxlength","4000");

		btn_pesquisar = new IGRPButton("Pesquisar","kofax","Pesquisa_arquivo_pdf","index","submit","default|fa-search","","");
		btn_pesquisar.propertie.add("type","form").add("code","").add("class","default").add("rel","pesquisar");
		
	}
		
	@Override
	public void render(){
		
		form_1.addField(conteudo);

		table_1.addField(caminho_do_arquivo);
		table_1.addField(tamanho_do_arquivo);
		table_1.addField(arquivo);

		sectionheader_1.addField(sectionheader_1_text);

		form_1.addButton(btn_pesquisar);
		this.addToPage(form_1);
		this.addToPage(table_1);
		this.addToPage(sectionheader_1);
	}
}
/*-------------------------*/