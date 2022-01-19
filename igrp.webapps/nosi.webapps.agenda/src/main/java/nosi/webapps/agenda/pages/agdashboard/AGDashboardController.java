/*-------------------------*/

/*Create Controller*/

package nosi.webapps.agenda.pages.agdashboard;
/*---- Import your packages here... ----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import nosi.core.webapp.Response;
import nosi.core.webapp.helpers.DateHelper;
import nosi.core.webapp.helpers.IgrpHelper;
import nosi.webapps.agenda.dao.DashBoard;
import nosi.webapps.agenda.dao.Entidade;

/*---- End ----*/

public class AGDashboardController extends Controller {		


	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*---- Insert your code here... ----*/						
		AGDashboard model = new AGDashboard();
		model.setData_de_(DateHelper.getCurrentDate("dd-MM-yyyy"));
		String filter = "";
		if(Igrp.getInstance().getRequest().getMethod().equalsIgnoreCase("POST")){
			model.load();
			filter += (model.getEntidade()!=null && !model.getEntidade().equals(""))?" AND ag_t_balcoes.id_entidade="+model.getEntidade():"";
			filter += (model.getData_de_()!=null && !model.getData_de_().equals("") && (model.getData_ate()==null || model.getData_ate().equals("")))?" AND ag_t_marcacao.data_marcacao='"+DateHelper.convertDate(model.getData_de_(), "dd-MM-yyyy", "yyyy-MM-dd")+"'":"";
			filter += (model.getData_ate()!=null && !model.getData_ate().equals("") && (model.getData_de_()==null || model.getData_de_().equals("")))?" AND ag_t_marcacao.data_marcacao='"+DateHelper.convertDate(model.getData_ate(), "dd-MM-yyyy", "yyyy-MM-dd")+"'":"";
			filter += (model.getData_ate()!=null && model.getData_de_()!=null && !model.getData_de_().equals("") && !model.getData_ate().equals(""))?" AND ag_t_marcacao.data_marcacao>='"+DateHelper.convertDate(model.getData_de_(), "dd-MM-yyyy", "yyyy-MM-dd")+"' AND ag_t_marcacao.data_marcacao<='"+DateHelper.convertDate(model.getData_ate(), "dd-MM-yyyy", "yyyy-MM-dd")+"'":"";
		}
		
		if(filter.equals("")){
			filter +=" AND ag_t_marcacao.data_marcacao='"+DateHelper.getCurrentDate()+"'";
			model.setData_de_(DateHelper.getCurrentDate("dd-MM-yyyy"));
		}
		
		List<DashBoard> balcoes_chart = DashBoard.getChartBalcao(filter);
		List<DashBoard> servicos_chart = DashBoard.getChartServico(filter);
		List<DashBoard> estado_chart = DashBoard.getChartEstado(filter);
		List<DashBoard> assunto_chart = DashBoard.getChartAssunto(filter);
		
		List<DashBoard> agenda_dia_chart_1 = DashBoard.getChartEstado(filter+" AND ag_t_marcacao.estado='ATIVO'");
		List<DashBoard> agenda_dia_chart_2 = DashBoard.getChartEstado(filter+" AND ag_t_marcacao.estado='CONFIRMADO'");
		List<DashBoard> agenda_dia_chart_3 = DashBoard.getChartEstado(filter+" AND ag_t_marcacao.estado='REALIZADO'");
		
		List<AGDashboard.Chart> chart1 = new ArrayList<>();
		List<AGDashboard.Chart> chart2 = new ArrayList<>();
		List<AGDashboard.Chart> chart3 = new ArrayList<>();
		List<AGDashboard.Chart> chart4 = new ArrayList<>();
		
		for(DashBoard d:balcoes_chart){
			AGDashboard.Chart c = new AGDashboard.Chart();
			c.setDescricao(d.getDescricao());
			c.setQuantidade(""+d.getCount());
			chart1.add(c);
		}
		for(DashBoard d:servicos_chart){
			AGDashboard.Chart c = new AGDashboard.Chart();
			c.setDescricao(d.getDescricao());
			c.setQuantidade(""+d.getCount());
			chart2.add(c);
		}
		for(DashBoard d:estado_chart){
			AGDashboard.Chart c = new AGDashboard.Chart();
			c.setDescricao(d.getDescricao());
			c.setQuantidade(""+d.getCount());
			chart3.add(c);
		}
		for(DashBoard d:assunto_chart){
			AGDashboard.Chart c = new AGDashboard.Chart();
			c.setDescricao(d.getDescricao());
			c.setQuantidade(""+d.getCount());
			chart4.add(c);
		}
		
		AGDashboardView view = new AGDashboardView(model);
		view.btn_pesquisar.setLink("index");
		view.entidade.setValue(IgrpHelper.toMap(Entidade.getAllEntidade(), "id", "nome_entidade","--- Filtrar por Entidade ---"));
		view.chart_1.addData(chart1);
		view.chart_2.addData(chart4);
		view.chart_3.addData(chart3);
		view.chart_5.addData(chart2);
		if(agenda_dia_chart_1!=null && agenda_dia_chart_1.size()>0){
			view.agenda_solicitada_val.setValue(agenda_dia_chart_1.get(0).getCount());
		}if(agenda_dia_chart_2!=null && agenda_dia_chart_2.size()>0){
			view.agendas_confirmadas_val.setValue(agenda_dia_chart_2.get(0).getCount());
		}if(agenda_dia_chart_3!=null && agenda_dia_chart_3.size()>0){
			view.agendas_realizadas_val.setValue(agenda_dia_chart_3.get(0).getCount());
		}
		return this.renderView(view);
					/*---- End ----*/
	}


	public Response actionPesquisar() throws IOException{
		/*---- Insert your code here... ----*/						
		return this.redirect("agenda","AGDashboard","index");
					/*---- End ----*/
	}
	
	/*---- Insert your actions here... ----*//*---- End ----*/
}
