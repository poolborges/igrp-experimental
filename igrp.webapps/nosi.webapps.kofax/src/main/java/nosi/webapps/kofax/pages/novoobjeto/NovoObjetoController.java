/*-------------------------*/

/*Create Controller*/

package nosi.webapps.kofax.pages.novoobjeto;
/*---- Import your packages here... ----*/

import nosi.core.gui.components.IGRPSeparatorList.Pair;
import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import nosi.core.webapp.Response;
import nosi.core.webapp.helpers.IgrpHelper;
import nosi.core.webapp.helpers.Permission;
import nosi.webapps.igrp.dao.Organization;
import nosi.webapps.kofax.dao.Campos;
import nosi.webapps.kofax.dao.Objeto;

/*---- End ----*/

public class NovoObjetoController extends Controller {		


	public Response actionIndex() throws IOException{
		/*---- Insert your code here... ----*/				
		NovoObjeto model = new NovoObjeto();		
		String id = Igrp.getInstance().getRequest().getParameter("id");
		Objeto obj = new Objeto();
		if(id != null) {
			obj = obj.findOne(id);
			if(obj != null) {
				model.setP_estado(obj.getEstado());
				model.setFormato_output(obj.getFormato_output());
				model.setObjeto(obj.getObjeto());
				model.setOrganica(""+obj.getId_organica());
			}
		}
		NovoObjetoView view = new NovoObjetoView(model);
		view.organica.setValue(IgrpHelper.toMap(new Organization().find().andWhere("application.dad", "=",Permission.getCurrentEnv()).all(), "id", "name"));
		HashMap<String,String> formato = new HashMap<>();
		formato.put("xml", "XML");
		formato.put("pdf", "PDF");
		formato.put("png", "PNG");
		view.formato_output.setValue(formato);
		
		if(id != null && obj!=null) {
			view.btn_gravar.setLink("gravar&p_id="+id);
			List<NovoObjeto.Separatorlist_1> list = new ArrayList<>();
			Collection<Campos> campos = new Campos().find().andWhere("id_objeto", "=",obj.getId()).all();
			if(campos!=null && campos.size() > 0){
				for(Campos c:campos){
					if(c.getEstado().equalsIgnoreCase("ATIVO")){
						NovoObjeto.Separatorlist_1 t = new NovoObjeto.Separatorlist_1();
						t.setCampo(new Pair(c.getCampo(),c.getCampo()));
						t.setP_estado(new Pair(c.getEstado(),c.getEstado()));
						list.add(t);
					}
				}
				model.setSeparatorlist_1(list);
			}
			view.separatorlist_1.addData(model.getseparatorlist_1());
		}
		return this.renderView(view);
				/*---- End ----*/
	}


	public Response actionGravar() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*---- Insert your code here... ----*/				
		NovoObjeto model = new NovoObjeto();
		model.load();
		Organization o = new Organization().findOne(model.getOrganica());
		Objeto obj = new Objeto(o.getId(), model.getObjeto(), model.getFormato_output(),model.getP_estado(),null);
		obj.setEstado("ATIVO");
		Collection<Campos> campos = new LinkedHashSet<>();
		for(NovoObjeto.Separatorlist_1 s:model.getseparatorlist_1()){
			Campos campo = new Campos(obj, s.getCampo().getValue(),s.getP_estado().getValue());
			campo.setEstado("ATIVO");
			campos.add(campo);
		}
		obj.setCampos(campos);
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		boolean isNewRecord = true;
		if(id != null && !id.equals("")){
			obj.setId(Integer.parseInt(id));
			for(Campos c:new Campos().find().andWhere("id_objeto", "=", Integer.parseInt(id)).all()){
				c.delete();
			}
			obj = obj.update();
			isNewRecord = false;
		}else{
			obj = obj.insert();
		}
		if(obj != null) {
			Igrp.getInstance().getFlashMessage().addMessage("success", "Operação efetuada com sucesso");
		}else {
			Igrp.getInstance().getFlashMessage().addMessage("error", "Operação falhada");
		}
		
		if(!isNewRecord) {
			return this.redirect("kofax","NovoObjeto","index", new String[] {"id"}, new String[] {obj.getId().intValue() + ""});
		}
		
		return this.redirect("kofax","NovoObjeto","index");
				/*---- End ----*/
	}
	
	/*---- Insert your actions here... ----*//*---- End ----*/
}
