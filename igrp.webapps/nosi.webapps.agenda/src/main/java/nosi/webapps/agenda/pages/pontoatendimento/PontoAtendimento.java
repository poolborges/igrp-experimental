/*---------------------- Create Model ----------------------*/
package nosi.webapps.agenda.pages.pontoatendimento;
import nosi.core.config.Config;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.SeparatorList;
import java.util.ArrayList;
import java.util.List;

public class PontoAtendimento extends Model{		
	@RParam(rParamName = "p_sectionheader_1_text")
	private String sectionheader_1_text;
	@RParam(rParamName = "p_entidade")
	private String entidade;
	@RParam(rParamName = "p_ponto")
	private String ponto;
	@RParam(rParamName = "p_n_de_servicos")
	private String n_de_servicos;
	@RParam(rParamName = "p_localizacao")
	private String localizacao;
	@RParam(rParamName = "p_fuso_horario")
	private String fuso_horario;
	@RParam(rParamName = "p_confirmacao_automatica")
	private String confirmacao_automatica;
	@RParam(rParamName = "p_horario_de_atendimento")
	private String horario_de_atendimento;
	@RParam(rParamName = "p_hora_inicio")
	private String hora_inicio;
	@RParam(rParamName = "p_hora_fim")
	private String hora_fim;
	@RParam(rParamName = "p_id_ponto_atendimento")
	private String p_id_ponto_atendimento;
	@RParam(rParamName = "p_estado")
	private String p_estado;

	@SeparatorList(name = Table_1.class)
	private List<Table_1> table_1 = new ArrayList<>();
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> gettable_1(){
		return this.table_1;
	}

	@SeparatorList(name = Table_2.class)
	private List<Table_2> table_2 = new ArrayList<>();
	public void setTable_2(List<Table_2> table_2){
		this.table_2 = table_2;
	}
	public List<Table_2> gettable_2(){
		return this.table_2;
	}
	
	public void setSectionheader_1_text(String sectionheader_1_text){
		this.sectionheader_1_text = sectionheader_1_text;
	}
	public String getSectionheader_1_text(){
		return this.sectionheader_1_text;
	}
	
	public void setEntidade(String entidade){
		this.entidade = entidade;
	}
	public String getEntidade(){
		return this.entidade;
	}
	
	public void setPonto(String ponto){
		this.ponto = ponto;
	}
	public String getPonto(){
		return this.ponto;
	}
	
	public void setN_de_servicos(String n_de_servicos){
		this.n_de_servicos = n_de_servicos;
	}
	public String getN_de_servicos(){
		return this.n_de_servicos;
	}
	
	public void setLocalizacao(String localizacao){
		this.localizacao = localizacao;
	}
	public String getLocalizacao(){
		return this.localizacao;
	}
	
	public void setFuso_horario(String fuso_horario){
		this.fuso_horario = fuso_horario;
	}
	public String getFuso_horario(){
		return this.fuso_horario;
	}
	
	public void setConfirmacao_automatica(String confirmacao_automatica){
		this.confirmacao_automatica = confirmacao_automatica;
	}
	public String getConfirmacao_automatica(){
		return this.confirmacao_automatica;
	}
	
	public void setHorario_de_atendimento(String horario_de_atendimento){
		this.horario_de_atendimento = horario_de_atendimento;
	}
	public String getHorario_de_atendimento(){
		return this.horario_de_atendimento;
	}
	
	public void setHora_inicio(String hora_inicio){
		this.hora_inicio = hora_inicio;
	}
	public String getHora_inicio(){
		return this.hora_inicio;
	}
	
	public void setHora_fim(String hora_fim){
		this.hora_fim = hora_fim;
	}
	public String getHora_fim(){
		return this.hora_fim;
	}
	
	public void setP_id_ponto_atendimento(String p_id_ponto_atendimento){
		this.p_id_ponto_atendimento = p_id_ponto_atendimento;
	}
	public String getP_id_ponto_atendimento(){
		return this.p_id_ponto_atendimento;
	}
	
	public void setP_estado(String p_estado){
		this.p_estado = p_estado;
	}
	public String getP_estado(){
		return this.p_estado;
	}


	public static class Table_1{
		private String ponto_atendimento;
		private String ponto_atendimento_desc;
		private String estado_list;
		private String p_id_balcao;
		public void setPonto_atendimento(String app,String page,String action){
			this.ponto_atendimento = Config.getResolveUrl(app, page, action);
		}
		public String getPonto_atendimento(){
			return this.ponto_atendimento;
		}
		public void setPonto_atendimento_desc(String ponto_atendimento_check){
			this.ponto_atendimento_desc = ponto_atendimento_check;
		}
		public String getPonto_atendimento_desc(){
			return this.ponto_atendimento_desc;
		}

		public void setEstado_list(String estado_list){
			this.estado_list = estado_list;
		}
		public String getEstado_list(){
			return this.estado_list;
		}

		public void setP_id_balcao(String p_id_balcao){
			this.p_id_balcao = p_id_balcao;
		}
		public String getP_id_balcao(){
			return this.p_id_balcao;
		}

	}
	public static class Table_2{
		private String servicos;
		private int id_servico_check;
		private int id_servico_check_check;
		private String p_id_servico;
		public void setServicos(String servicos){
			this.servicos = servicos;
		}
		public String getServicos(){
			return this.servicos;
		}

		public void setId_servico_check(int id_servico_check){
			this.id_servico_check = id_servico_check;
		}
		public int getId_servico_check(){
			return this.id_servico_check;
		}
		public void setId_servico_check_check(int id_servico_check_check){
			this.id_servico_check_check = id_servico_check_check;
		}
		public int getId_servico_check_check(){
			return this.id_servico_check_check;
		}

		public void setP_id_servico(String p_id_servico){
			this.p_id_servico = p_id_servico;
		}
		public String getP_id_servico(){
			return this.p_id_servico;
		}

	}
	@Override
	public String toString() {
		return "PontoAtendimento [entidade=" + entidade + ", ponto=" + ponto + ", n_de_servicos=" + n_de_servicos
				+ ", localizacao=" + localizacao + ", fuso_horario=" + fuso_horario + ", confirmacao_automatica="
				+ confirmacao_automatica + ", horario_de_atendimento=" + horario_de_atendimento + ", hora_inicio="
				+ hora_inicio + ", hora_fim=" + hora_fim + ", p_id_ponto_atendimento=" + p_id_ponto_atendimento
				+ ", p_estado=" + p_estado + "]";
	}
	
	
}
/*-------------------------*/