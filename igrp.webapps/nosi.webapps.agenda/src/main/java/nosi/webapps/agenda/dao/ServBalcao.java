package nosi.webapps.agenda.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import nosi.core.webapp.helpers.UrlHelper;
import nosi.webapps.agenda.helper.RestRequestHelper;
/**
 * Marcel Iekiny
 * Aug 25, 2017
 */
public class ServBalcao {
	
	private Integer id;
	private int id_balcao;
	private int id_servico;
	private String estado;
	private int porton;
	private String nome_balcao;
	private String nome_servico;
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getId_balcao() {
		return id_balcao;
	}
	public void setId_balcao(int id_balcao) {
		this.id_balcao = id_balcao;
	}
	public int getId_servico() {
		return id_servico;
	}
	public void setId_servico(int id_servico) {
		this.id_servico = id_servico;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getPorton() {
		return porton;
	}
	public void setPorton(int porton) {
		this.porton = porton;
	}
	
	public static List<ServBalcao> getAllServBalcao() {
		
		List<ServBalcao> aux = null;
		
		try {
			ClientConfig config = new DefaultClientConfig();
			 
	        Client client = Client.create(RestRequestHelper.applySslSecurity(config));
	        
	        String url = RestRequestHelper.baseUrl + "/ag_t_serv_balcao";
	        
	        WebResource resource = client.resource(url);
	        
	        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
	        
	   	 	String jsonResult = response.getEntity(String.class);
	   	 	
	        if(response.getStatus() == 200) {
		        aux = RestRequestHelper.convertJsonToListDao(jsonResult, new TypeToken<List<ServBalcao>>(){}.getType());
	        }
	        else {
	       	 System.out.println("Error");
	       	 //System.out.println(RestRequestHelper.convertToDefaultFault(jsonResult));
	        }
	       client.destroy();
		}catch(Exception e){
			e.printStackTrace();
		}
		return aux != null ? aux : new ArrayList<ServBalcao>();
}
	public static List<ServBalcao> getAllServico_por_Balcao() {
		
		List<ServBalcao> aux = null;
		
		try {
			ClientConfig config = new DefaultClientConfig();
			 
	        Client client = Client.create(RestRequestHelper.applySslSecurity(config));
	        
	        String url = RestRequestHelper.baseUrl_ + "/_getselect_servico_balcao";
	        
	        WebResource resource = client.resource(url);
	        
	        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
	        
	   	 	String jsonResult = response.getEntity(String.class);
	   	 	
	        if(response.getStatus() == 200) {
		        aux = RestRequestHelper.convertJsonToDaoColl(jsonResult, "Entries", "Entry", new TypeToken<List<ServBalcao>>(){}.getType());
	        }
	        else {
	       	 System.out.println("Error");
	       	 //System.out.println(RestRequestHelper.convertToDefaultFault(jsonResult));
	        }
	       client.destroy();
		}catch(Exception e){
			e.printStackTrace();
		}
		return aux != null ? aux : new ArrayList<ServBalcao>();
}
	@Override
	public String toString() {
		return "ServBalcao [id=" + id + ", id_balcao=" + id_balcao + ", id_servico=" + id_servico + ", estado=" + estado
				+ ", porton=" + porton + "]";
	}
	public static ServBalcao insert(ServBalcao sb) {
		ClientConfig config = new DefaultClientConfig();			 
        Client client = Client.create(RestRequestHelper.applySslSecurity(config));	        
        String url = RestRequestHelper.baseUrl + "/ag_t_serv_balcao";	        
        WebResource resource = client.resource(url);	        
		String content = RestRequestHelper.convertDaoToJson(sb);
        ClientResponse response = resource.header("Prefer", "return=representation").accept(MediaType.APPLICATION_JSON).type("application/json")
        		.post(ClientResponse.class, content);		
        String jsonResult = response.getEntity(String.class);
       client.destroy();
       return response.getStatus()==201?RestRequestHelper.convertJsonToDao(jsonResult, ServBalcao.class):null;
	}
	
	public static ServBalcao update(ServBalcao sb){
	    ClientConfig config = new DefaultClientConfig();			 
        Client client = Client.create(RestRequestHelper.applySslSecurity(config));	 
        int id_s = sb.getId_servico();
        int id_b = sb.getId_balcao();
        String url = RestRequestHelper.baseUrl + "/ag_t_serv_balcao("+sb.getId()+")";	
        sb.setId(null);
        WebResource resource = client.resource(url);	        
		String content = RestRequestHelper.convertDaoToJson(sb);
		ClientResponse response = resource.header("Prefer", "return=representation").accept(MediaType.APPLICATION_JSON).type("application/json")
        		.put(ClientResponse.class, content);		
       client.destroy();
       return response.getStatus()==204?getServBalcao(id_b,id_s):null;
	}
	
	public static int updateStatus(String estado,int id_balcao){
	    ClientConfig config = new DefaultClientConfig();			 
        Client client = Client.create(RestRequestHelper.applySslSecurity(config));	 
        String url = RestRequestHelper.baseUrl_ +"/status_serv_balcoes";
        WebResource resource = client.resource(url);	        
		String content = "{\"_poststatus_serv_balcoes\":{\"estado\":\""+estado+"\",\"id_balcao\":"+id_balcao+"}}";
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type("application/json")
        		.post(ClientResponse.class, content);		
       client.destroy();
       return response.getStatus();
	}
	
	
	public static ServBalcao getServBalcao(int id_balcao,int id_servico){
		ServBalcao aux = null;
		try {
			ClientConfig config = new DefaultClientConfig();			 
	        Client client = Client.create(RestRequestHelper.applySslSecurity(config));	        
	        String url = RestRequestHelper.baseUrl + "/ag_t_serv_balcao?$filter=id_balcao eq "+id_balcao+" and id_servico eq "+id_servico;	        
	        
	        WebResource resource = client.resource(UrlHelper.urlEncoding(url));	        
	        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);	        
	   	 	String jsonResult = response.getEntity(String.class);
	        if(response.getStatus() == 200) {
	        	List<ServBalcao> list = (List<ServBalcao>) RestRequestHelper.convertJsonToListDao(jsonResult,new TypeToken<List<ServBalcao>>(){}.getType());
	        	aux = list.size()>0 && !list.isEmpty()?list.get(0):null;
	        }
	        else {
	       	 	System.err.println("Error");
	        }
	       client.destroy();
		}catch(Exception e){
			e.printStackTrace();
		}
		return aux != null ? aux : new ServBalcao();
	}
	
}
