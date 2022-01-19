/*---------------------- Create Model ----------------------*/
package nosi.webapps.kofax.pages.dashboard;
import nosi.core.config.Config;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.gui.components.IGRPSeparatorList.Pair;
import nosi.core.webapp.SeparatorList;
import java.util.ArrayList;
import java.util.List;

public class DashBoard extends Model{		
	@RParam(rParamName = "p_tipo_objeto")
	private String tipo_objeto;
	@RParam(rParamName = "p_data_de")
	private String data_de;
	@RParam(rParamName = "p_data_ate")
	private String data_ate;
	@RParam(rParamName = "p_statbox_1_tit")
	private String statbox_1_tit;
	@RParam(rParamName = "p_statbox_1_val")
	private String statbox_1_val;
	@RParam(rParamName = "p_statbox_1_txt")
	private String statbox_1_txt;
	@RParam(rParamName = "p_statbox_1_url")
	private String statbox_1_url;
	@RParam(rParamName = "p_statbox_1_bg")
	private String statbox_1_bg;
	@RParam(rParamName = "p_statbox_1_icn")
	private String statbox_1_icn;

	@SeparatorList(name = Chart_1.class)
	private List<Chart_1> chart_1 = new ArrayList<>();
	public void setChart_1(List<Chart_1> chart_1){
		this.chart_1 = chart_1;
	}
	public List<Chart_1> getchart_1(){
		return this.chart_1;
	}
	
	public void setTipo_objeto(String tipo_objeto){
		this.tipo_objeto = tipo_objeto;
	}
	public String getTipo_objeto(){
		return this.tipo_objeto;
	}
	
	public void setData_de(String data_de){
		this.data_de = data_de;
	}
	public String getData_de(){
		return this.data_de;
	}
	
	public void setData_ate(String data_ate){
		this.data_ate = data_ate;
	}
	public String getData_ate(){
		return this.data_ate;
	}
	
	public void setStatbox_1_tit(String statbox_1_tit){
		this.statbox_1_tit = statbox_1_tit;
	}
	public String getStatbox_1_tit(){
		return this.statbox_1_tit;
	}
	
	public void setStatbox_1_val(String statbox_1_val){
		this.statbox_1_val = statbox_1_val;
	}
	public String getStatbox_1_val(){
		return this.statbox_1_val;
	}
	
	public void setStatbox_1_txt(String statbox_1_txt){
		this.statbox_1_txt = statbox_1_txt;
	}
	public String getStatbox_1_txt(){
		return this.statbox_1_txt;
	}
	
	public void setStatbox_1_url(String statbox_1_url){
		this.statbox_1_url = statbox_1_url;
	}
	public String getStatbox_1_url(){
		return this.statbox_1_url;
	}
	
	public void setStatbox_1_bg(String statbox_1_bg){
		this.statbox_1_bg = statbox_1_bg;
	}
	public String getStatbox_1_bg(){
		return this.statbox_1_bg;
	}
	
	public void setStatbox_1_icn(String statbox_1_icn){
		this.statbox_1_icn = statbox_1_icn;
	}
	public String getStatbox_1_icn(){
		return this.statbox_1_icn;
	}


	public static class Chart_1{
		private String Objeto;
		private int Quantidade;

		public String getObjeto() {
			return Objeto;
		}
		public void setObjeto(String objeto) {
			Objeto = objeto;
		}
		
		public int getQuantidade() {
			return Quantidade;
		}
		public void setQuantidade(int quantidade) {
			Quantidade = quantidade;
		}
		@Override
		public String toString() {
			return "Chart_1 [Quantidade=" + Quantidade + ", Objeto=" + Objeto + "]";
		}
		
	}
}
/*-------------------------*/