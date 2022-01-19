package nosi.webapps.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import nosi.webapps.agenda.helper.RestRequestHelper;

/**
 * @author: Emanuel Pereira
 * 4 Aug 2017
 */
public class Agenda {
	@Expose(serialize = false, deserialize = true)
	private Integer id;
	private int id_serv_balcao;
	private int nr_atendimentos;
	private int nr_atend_disponiveis;
	private int nr_atendedores;
	private float tempo_medio;
	private int antecede_edit;
	private int anteced_agenda;
	private String dias_atendimento;
	private String estado;
	private String periodo;
	private String hora_inicio;
	private String hora_fim;
	@Expose(serialize = false, deserialize = true)
	private String nome_balcao;
	@Expose(serialize = false, deserialize = true)
	private String nome_servico;
	
	public int getId_serv_balcao() {
		return id_serv_balcao;
	}
	public void setId_serv_balcao(int id_serv_balcao) {
		this.id_serv_balcao = id_serv_balcao;
	}
	public String getDias_atendimento() {
		return dias_atendimento;
	}
	public void setDias_atendimento(String dias_atendimento) {
		this.dias_atendimento = dias_atendimento;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public String getHora_fim() {
		return hora_fim;
	}
	public void setHora_fim(String hora_fim) {
		this.hora_fim = hora_fim;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNr_atendimentos() {
		return nr_atendimentos;
	}
	public void setNr_atendimentos(int nr_atendimentos) {
		this.nr_atendimentos = nr_atendimentos;
	}
	public int getNr_atend_disponiveis() {
		return nr_atend_disponiveis;
	}
	public void setNr_atend_disponiveis(int nr_atend_disponiveis) {
		this.nr_atend_disponiveis = nr_atend_disponiveis;
	}
	public int getNr_atendedores() {
		return nr_atendedores;
	}
	public void setNr_atendedores(int nr_atendedores) {
		this.nr_atendedores = nr_atendedores;
	}
	public float getTempo_medio() {
		return tempo_medio;
	}
	public void setTempo_medio(float tempo_medio) {
		this.tempo_medio = tempo_medio;
	}
	public int getAntecede_edit() {
		return antecede_edit;
	}
	public void setAntecede_edit(int antecede_edit) {
		this.antecede_edit = antecede_edit;
	}
	public int getAnteced_agenda() {
		return anteced_agenda;
	}
	public void setAnteced_agenda(int anteced_agenda) {
		this.anteced_agenda = anteced_agenda;
	}
	public String getDias_atendiemnto() {
		return dias_atendimento;
	}
	public void setDias_atendiemnto(String dias_atendimento) {
		this.dias_atendimento = dias_atendimento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	public String getNome_balcao() {
		return nome_balcao;
	}
	public void setNome_balcao(String nome_balcao) {
		this.nome_balcao = nome_balcao;
	}
	public String getNome_servico() {
		return nome_servico;
	}
	public void setNome_servico(String nome_servico) {
		this.nome_servico = nome_servico;
	}
	public static List<Agenda> getAllAgenda() {
		
		List<Agenda> aux = null;
		
		try {
			ClientConfig config = new DefaultClientConfig();
			 
	        Client client = Client.create(RestRequestHelper.applySslSecurity(config));
	        
	        String url = RestRequestHelper.baseUrl_ + "/list_agendas";
	        
	        WebResource resource = client.resource(url);
	        
	        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
	        
	   	 	String jsonResult = response.getEntity(String.class);
	   	 	
	        if(response.getStatus() == 200) {
	            aux = (List<Agenda>) RestRequestHelper.convertJsonToDaoColl(jsonResult, "Agendas", "Agenda", new TypeToken<List<Agenda>>(){}.getType());
		    }
	        else {
	       	 System.out.println("Error");
	       	 //System.out.println(RestRequestHelper.convertToDefaultFault(jsonResult));
	        }
	       client.destroy();
		}catch(Exception e){
			e.printStackTrace();
		}
		return aux != null ? aux : new ArrayList<Agenda>();
	}
	
}
