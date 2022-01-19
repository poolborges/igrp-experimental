/*-------------------------*/

/*Create Controller*/

package nosi.webapps.kofax.pages.dashboard;
/*---- Import your packages here... ----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;
import java.io.IOException;
import nosi.core.webapp.Response;
import nosi.core.webapp.helpers.DateHelper;
import nosi.core.webapp.helpers.IgrpHelper;
import nosi.core.webapp.helpers.Permission;
import nosi.webapps.kofax.dao.Dados;
import nosi.webapps.kofax.dao.Objeto;

/*---- End ----*/

public class DashBoardController extends Controller {		


	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*---- Insert your code here... ----*/
		DashBoard model = new DashBoard();
		String filter = "";
		if(Igrp.getInstance().getRequest().getMethod().equalsIgnoreCase("POST")){
			model.load();
			filter += model.getTipo_objeto()!=null && !model.getTipo_objeto().equals("")? " AND d.id_objeto="+model.getTipo_objeto():"";
			filter += model.getData_de()!=null && !model.getData_de().equals("")? " AND d.dt_registo>='"+DateHelper.convertDate(model.getData_de(), "dd-MM-yyyy", "yyyy-MM-dd")+"'":"";
			filter += model.getData_ate()!=null && !model.getData_ate().equals("")? " AND d.dt_registo<='"+DateHelper.convertDate(model.getData_ate(), "dd-MM-yyyy", "yyyy-MM-dd")+"'":"";
		
		}
		
		DashBoardView view = new DashBoardView(model);
		view.tipo_objeto.setValue(IgrpHelper.toMap(new Objeto().find().andWhere("id_organica","=",Permission.getCurrentOrganization()).all(), "id", "objeto","--- Selecionar Objeto ---"));

		Long total = new Dados().find()
				.andWhere("objeto", "=",(model.getTipo_objeto()!=null && !model.getTipo_objeto().equals(""))?Integer.parseInt(model.getTipo_objeto()):null)
				.andWhere("dt_registo", ">=",(model.getData_de()!=null && !model.getData_de().equals(""))?DateHelper.formatDate(model.getData_de(), "dd-MM-yyyy", "yyyy-MM-dd"):null)
				.andWhere("dt_registo", "<=",(model.getData_ate()!=null && !model.getData_ate().equals(""))?DateHelper.formatDate(model.getData_ate(), "dd-MM-yyyy", "yyyy-MM-dd"):null)
				.getCount();
		view.btn_pesquisar.setLink("index");
		view.statbox_1_val.setValue(total);
		view.chart_1.addData(new Dados().getChart1(filter));
		return this.renderView(view);
		/*---- End ----*/
	}


	public Response actionPesquisar() throws IOException{
		/*---- Insert your code here... ----*/
		return this.redirect("kofax","DashBoard","index");
		/*---- End ----*/
	}
	
	/*---- Insert your actions here... ----*//*---- End ----*/
}
