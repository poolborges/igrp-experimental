/*-------------------------*/

/*Create Controller*/

package nosi.webapps.agenda.pages.agenda;
/*---- Import your packages here... ----*/

import nosi.core.webapp.Controller;
import nosi.core.webapp.FlashMessage;
import nosi.core.webapp.Igrp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import nosi.core.webapp.Response;
import nosi.webapps.agenda.dao.ODFault;
import nosi.webapps.agenda.dao.ServBalcao;
import nosi.webapps.agenda.helper.RestRequestHelper;
import nosi.webapps.agenda.pages.agenda.Agenda.Separatorlist_1;

/*---- End ----*/

public class AgendaController extends Controller {		


	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*---- Insert your code here... ----*/								
		Agenda model = new Agenda();
		
		List<Separatorlist_1> lista = new ArrayList<Separatorlist_1>();
		
		if(Igrp.getInstance().getRequest().getMethod().equalsIgnoreCase("POST")) {
			model.load();
			List<nosi.webapps.agenda.dao.Agenda> listAgenda = new ArrayList<nosi.webapps.agenda.dao.Agenda>();
			if(model.getP_servico_fk() != null) 
				for(int i = 0; i < model.getP_servico_fk().length; i++) {
					Separatorlist_1 aux = new Separatorlist_1();
					aux.setAntecedencia_alterarcancelar_hr(new Separatorlist_1.Pair(model.getP_antecedencia_alterarcancelar_hr_fk()[i],model.getP_antecedencia_alterarcancelar_hr_fk_desc()[i]));
					aux.setAntecedencia_de_agendamento_hr(new Separatorlist_1.Pair(model.getP_antecedencia_de_agendamento_hr_fk()[i],model.getP_antecedencia_de_agendamento_hr_fk_desc()[i]));
					aux.setDias_de_semana(new Separatorlist_1.Pair(model.getP_dias_de_semana_fk()[i],model.getP_dias_de_semana_fk_desc()[i]));
					aux.setHora_fim(new Separatorlist_1.Pair(model.getP_hora_fim_fk()[i],model.getP_hora_fim_fk_desc()[i]));
					aux.setHora_inicio(new Separatorlist_1.Pair(model.getP_hora_fim_fk()[i],model.getP_hora_inicio_fk_desc()[i]));
					aux.setNumero_de_atendedores(new Separatorlist_1.Pair(model.getP_numero_de_atendedores_fk()[i] + "",model.getP_numero_de_atendedores_fk_desc()[i] + ""));
					aux.setNumero_de_atendimentos(new Separatorlist_1.Pair(model.getP_numero_de_atendimentos_fk()[i] + "",model.getP_numero_de_atendimentos_fk_desc()[i] + ""));
					aux.setServico(new Separatorlist_1.Pair(model.getP_servico_fk()[i],model.getP_servico_fk_desc()[i]));
					aux.setTempo_medio_de_atendimento_mn(new Separatorlist_1.Pair(model.getP_tempo_medio_de_atendimento_mn_fk()[i] + "",model.getP_tempo_medio_de_atendimento_mn_fk_desc()[i] +""));
					aux.setPeriodo(new Separatorlist_1.Pair(model.getP_periodo_fk()[i],model.getP_periodo_fk_desc()[i]));
					lista.add(aux);
					
					nosi.webapps.agenda.dao.Agenda obj = new nosi.webapps.agenda.dao.Agenda();
					obj.setAntecede_edit(Integer.parseInt(model.getP_antecedencia_alterarcancelar_hr_fk()[i]));
					obj.setAnteced_agenda(Integer.parseInt(model.getP_antecedencia_de_agendamento_hr_fk()[i]));
					obj.setDias_atendiemnto(model.getP_dias_de_semana_fk()[i]);
					obj.setTempo_medio(Float.parseFloat(model.getP_tempo_medio_de_atendimento_mn_fk()[i] + ""));
					obj.setNr_atendedores(Integer.parseInt(model.getP_numero_de_atendedores_fk()[i] + ""));
					obj.setNr_atendimentos(Integer.parseInt(model.getP_numero_de_atendimentos_fk()[i] + ""));
					obj.setEstado("ATIVO");
					obj.setId_serv_balcao(Integer.parseInt(model.getP_servico_fk()[i]));
					obj.setHora_inicio(model.getP_hora_inicio_fk()[i]);
					obj.setHora_fim(model.getP_hora_fim_fk()[i]);
					obj.setPeriodo(model.getP_periodo_fk()[i]);
					
					listAgenda.add(obj);
				}
			
			String errorMessage = "Ooops !!! Ocorreu um Erro ... ";
			boolean success = false;
		
			for(nosi.webapps.agenda.dao.Agenda agenda : listAgenda) {
				ClientConfig config = new DefaultClientConfig();
				 
		        Client client = Client.create(RestRequestHelper.applySslSecurity(config));
		        
		        String url = RestRequestHelper.baseUrl + "/ag_t_agenda";
		        
		        WebResource resource = client.resource(url);
		        
				String content = RestRequestHelper.convertDaoToJson(agenda);
			//	System.out.println(content);
		        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type("application/json")
		        		.post(ClientResponse.class, content);
				
		   	 	String jsonResult = response.getEntity(String.class);
		   	 	
		   	 	//	System.out.println(jsonResult);
		   	 	
		        if(response.getStatus() == 200 || response.getStatus() == 201) {
		        	success = true;
		        }
		        else {
		        	success = false;
		        	try {
		        		ODFault fault = RestRequestHelper.convertJsonToDao(jsonResult, ODFault.class);
		        		errorMessage = fault.getError().getMessage();
		        	}catch(Exception e){
		        		e.printStackTrace();
		        	}
		        }
		       client.destroy();
			}
			if(success)
	        	Igrp.getInstance().getFlashMessage().addMessage(FlashMessage.SUCCESS, "Agenda registado com sucesso.");
			else
				Igrp.getInstance().getFlashMessage().addMessage(FlashMessage.ERROR, errorMessage);
			
		}
		
		AgendaView view = new AgendaView(model);
		
		Map<Integer, String> comboBox1 = new HashMap<Integer,String>();
		comboBox1.put(null, "--- Escolher ---");
		for(ServBalcao obj : ServBalcao.getAllServico_por_Balcao())
			comboBox1.put(obj.getId(), obj.getNome_servico() +"/"+ obj.getNome_balcao());
		view.servico.setValue(comboBox1);
		
		Map<String, String> comboBox4 = new HashMap<String,String>();
		for(String value : Arrays.asList(new String[]{"Segunda","Terca","Quarta","Quinta","Sexta","Sabado","Domingo"}))
			comboBox4.put(value, value);
		view.dias_de_semana.setValue(comboBox4);
		
		Map<String, String> comboBox5 = new HashMap<String,String>();
		comboBox5.put(null, "--- Escolher ---");
		for(String value : Arrays.asList(new String[]{"12","10"}))
			comboBox5.put(value, value);
		view.antecedencia_alterarcancelar_hr.setValue(comboBox5);
		
		Map<String, String> comboBox2 = new HashMap<String,String>();
		comboBox2.put(null, "--- Escolher ---"); 
		for(String value : Arrays.asList(new String[]{"12","10"}))
			comboBox2.put(value, value);
		view.antecedencia_de_agendamento_hr.setValue(comboBox2);
		
		view.separatorlist_1.addData(lista);
		
		return this.renderView(view);
		/*---- End ----*/
	}

	public Response actionGravar() throws IOException{
		/*---- Insert your code here... ----*/								
		return this.redirect("agenda","AddServicos","index");
						/*---- End ----*/
	}
	
	/*---- Insert your actions here... ----*//*---- End ----*/
}