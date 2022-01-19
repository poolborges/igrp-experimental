/*---------------------- Create Model ----------------------*/
package nosi.webapps.kofax.pages.pesquisa_arquivo;
import nosi.core.config.Config;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.SeparatorList;
import java.util.ArrayList;
import java.util.List;

public class Pesquisa_arquivo extends Model{		
	@RParam(rParamName = "p_sectionheader_1_text")
	private String sectionheader_1_text;
	@RParam(rParamName = "p_tipo_de_objecto")
	private String tipo_de_objecto;
	@RParam(rParamName = "p_campo")
	private String campo;
	@RParam(rParamName = "p_valor")
	private String valor;

	@SeparatorList(name = Table_1.class)
	private List<Table_1> table_1 = new ArrayList<>();
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> gettable_1(){
		return this.table_1;
	}
	
	public void setSectionheader_1_text(String sectionheader_1_text){
		this.sectionheader_1_text = sectionheader_1_text;
	}
	public String getSectionheader_1_text(){
		return this.sectionheader_1_text;
	}
	
	public void setTipo_de_objecto(String tipo_de_objecto){
		this.tipo_de_objecto = tipo_de_objecto;
	}
	public String getTipo_de_objecto(){
		return this.tipo_de_objecto;
	}
	
	public void setCampo(String campo){
		this.campo = campo;
	}
	public String getCampo(){
		return this.campo;
	}
	
	public void setValor(String valor){
		this.valor = valor;
	}
	public String getValor(){
		return this.valor;
	}


	public static class Table_1{
		private String tipo_de_objecto;
		private String descricao;
		private String data_registo;
		private String link_1;
		private String link_1_desc;
		public void setTipo_de_objecto(String tipo_de_objecto){
			this.tipo_de_objecto = tipo_de_objecto;
		}
		public String getTipo_de_objecto(){
			return this.tipo_de_objecto;
		}

		public void setDescricao(String descricao){
			this.descricao = descricao;
		}
		public String getDescricao(){
			return this.descricao;
		}

		public void setData_registo(String data_registo){
			this.data_registo = data_registo;
		}
		public String getData_registo(){
			return this.data_registo;
		}

		public void setLink_1(String app,String page,String action){
			this.link_1 = Config.getResolveUrl(app, page, action);
		}
		public String getLink_1(){
			return this.link_1;
		}
		public void setLink_1_desc(String link_1_desc){
			this.link_1_desc = link_1_desc;
		}
		public String getLink_1_desc(){
			return this.link_1_desc;
		}

	}
}
/*-------------------------*/