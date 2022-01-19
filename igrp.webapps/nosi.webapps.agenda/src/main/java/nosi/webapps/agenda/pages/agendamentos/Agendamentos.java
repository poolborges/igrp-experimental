/*---------------------- Create Model ----------------------*/
package nosi.webapps.agenda.pages.agendamentos;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import java.util.ArrayList;

public class Agendamentos extends Model{		
	@RParam(rParamName = "p_entidade")
	private String entidade;
	@RParam(rParamName = "p_balcao")
	private String balcao;
	@RParam(rParamName = "p_servico")
	private String servico;
	@RParam(rParamName = "p_date_de")
	private String date_de;
	@RParam(rParamName = "p_data_ate")
	private String data_ate;

	private ArrayList<Table_1> table_1 = new ArrayList<>();
	public void setTable_1(ArrayList<Table_1> table_1){
		this.table_1 = table_1;
	}
	public ArrayList<Table_1> gettable_1(){
		return this.table_1;
	}
	
	public void setEntidade(String entidade){
		this.entidade = entidade;
	}
	public String getEntidade(){
		return this.entidade;
	}
	
	public void setBalcao(String balcao){
		this.balcao = balcao;
	}
	public String getBalcao(){
		return this.balcao;
	}
	
	public void setServico(String servico){
		this.servico = servico;
	}
	public String getServico(){
		return this.servico;
	}
	
	public void setDate_de(String date_de){
		this.date_de = date_de;
	}
	public String getDate_de(){
		return this.date_de;
	}
	
	public void setData_ate(String data_ate){
		this.data_ate = data_ate;
	}
	public String getData_ate(){
		return this.data_ate;
	}


	public static class Table_1{
		private String dia;
		private String hora;
		private String nome;
		private String balcao;
		private String servico;
		private String estado;
		public void setDia(String dia){
			this.dia = dia;
		}
		public String getDia(){
			return this.dia;
		}

		public void setHora(String hora){
			this.hora = hora;
		}
		public String getHora(){
			return this.hora;
		}

		public void setNome(String nome){
			this.nome = nome;
		}
		public String getNome(){
			return this.nome;
		}

		public void setBalcao(String balcao){
			this.balcao = balcao;
		}
		public String getBalcao(){
			return this.balcao;
		}

		public void setServico(String servico){
			this.servico = servico;
		}
		public String getServico(){
			return this.servico;
		}

		public void setEstado(String estado){
			this.estado = estado;
		}
		public String getEstado(){
			return this.estado;
		}

	}
}
/*-------------------------*/