/*---------------------- Create Model ----------------------*/
package nosi.webapps.kofax.pages.pesquisa_arquivo_pdf;
import nosi.core.config.Config;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.SeparatorList;
import java.util.ArrayList;
import java.util.List;

public class Pesquisa_arquivo_pdf extends Model{		
	@RParam(rParamName = "p_conteudo")
	private String conteudo;
	@RParam(rParamName = "p_sectionheader_1_text")
	private String sectionheader_1_text;

	@SeparatorList(name = Table_1.class)
	private List<Table_1> table_1 = new ArrayList<>();
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> gettable_1(){
		return this.table_1;
	}
	
	public void setConteudo(String conteudo){
		this.conteudo = conteudo;
	}
	public String getConteudo(){
		return this.conteudo;
	}
	
	public void setSectionheader_1_text(String sectionheader_1_text){
		this.sectionheader_1_text = sectionheader_1_text;
	}
	public String getSectionheader_1_text(){
		return this.sectionheader_1_text;
	}


	public static class Table_1{
		private String caminho_do_arquivo;
		private String tamanho_do_arquivo;
		private String arquivo;
		private String arquivo_desc;
		public void setCaminho_do_arquivo(String caminho_do_arquivo){
			this.caminho_do_arquivo = caminho_do_arquivo;
		}
		public String getCaminho_do_arquivo(){
			return this.caminho_do_arquivo;
		}

		public void setTamanho_do_arquivo(String tamanho_do_arquivo){
			this.tamanho_do_arquivo = tamanho_do_arquivo;
		}
		public String getTamanho_do_arquivo(){
			return this.tamanho_do_arquivo;
		}

		public void setArquivo(String app,String page,String action){
			this.arquivo = Config.getResolveUrl(app, page, action);
		}
		public String getArquivo(){
			return this.arquivo;
		}
		public void setArquivo_desc(String arquivo_desc){
			this.arquivo_desc = arquivo_desc;
		}
		public String getArquivo_desc(){
			return this.arquivo_desc;
		}

	}
}
/*-------------------------*/