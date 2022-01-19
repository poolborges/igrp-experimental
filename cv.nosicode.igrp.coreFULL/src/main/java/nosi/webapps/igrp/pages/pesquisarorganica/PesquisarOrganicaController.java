/*-------------------------*/

/*Create Controller*/

package nosi.webapps.igrp.pages.pesquisarorganica;
/*---- Import your packages here... ----*/

import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.Response;
import nosi.webapps.igrp.dao.Application;
import nosi.webapps.igrp.dao.Organization;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*---- End ----*/
public class PesquisarOrganicaController extends Controller {		

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		PesquisarOrganica model = new PesquisarOrganica();
		ArrayList<PesquisarOrganica.Table_1> lista = new ArrayList<>();
		
		if(Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST")){
			model.load();
		}		
		//Preenchendo a tabela
		for(Organization org:new Organization().find().andWhere("application", "=",model.getAplicacao()!=0? model.getAplicacao():null).all()){
			PesquisarOrganica.Table_1 table1 = new PesquisarOrganica().new Table_1();
			table1.setDescricao(org.getName());
			table1.setEstado(org.getStatus()==1?"Ativo":"Inativo");
			table1.setP_id(org.getId());
			lista.add(table1);
		}
		//Alimentando o selectorOption (Aplicacao, organica, e menuPrincipal)
		PesquisarOrganicaView view = new PesquisarOrganicaView(model);
		HashMap<Integer,String> applications =  new Application().getListApps();
		view.aplicacao.setValue(applications);
		
		//Para pegar os parametros que queremos enviar para poder editar o menu no view
		view.table_1.addData(lista);
		view.p_id.setParam(true);
		return this.renderView(view);
	}

	
	public void actionEidtar() throws IOException{
		
	}
	
	public void actionEliminar() throws IOException{
		
	}
	
	public Response actionMenu() throws IOException{
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		return this.redirect("igrp", "MenuOrganica", "index","id="+id+"&type=org");
	}
	
	public Response actionTransacao() throws IOException{
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		return this.redirect("igrp", "TransacaoOrganica", "index","id="+id+"&type=org");
	}
	
	public void actionEtapa() throws IOException{
		
	}
}
