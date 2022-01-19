package nosi.webapps.agenda.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import nosi.webapps.agenda.helper.RestRequestHelper;

/**
 * Yma
 * 7 Sep 2017
 */
public class DashBoard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricao;
	private Integer count;
	
	public DashBoard() {
		// TODO Auto-generated constructor stub
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	public static List<DashBoard> getData(String url,String operation,String filter){
		List<DashBoard> aux = null;
		try {
			ClientConfig config = new DefaultClientConfig();			 
	        Client client = Client.create(RestRequestHelper.applySslSecurity(config));	        
	        url = RestRequestHelper.baseUrl_ + "/"+ url;        
	        WebResource resource = client.resource(url);
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put(operation,new JSONObject().put("filterQuery", filter));
	        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type("application/json")
	        		.post(ClientResponse.class, jsonObject.toString());	
	        String jsonResult = response.getEntity(String.class);
	      
//	        System.out.println("result:"+jsonResult);
	        if(response.getStatus() == 200) {
		        aux = (List<DashBoard>) RestRequestHelper.convertJsonToDaoColl(jsonResult, "charts", "chart", new TypeToken<List<DashBoard>>(){}.getType());
	        }
	        else {
	        	
	        	  System.out.println(url+":"+jsonObject.toString());
	       	 System.err.println("Error:"+RestRequestHelper.convertToDefaultFault(jsonResult));
	       	 //System.out.println(RestRequestHelper.convertToDefaultFault(jsonResult));
	        }
	       client.destroy();
		}catch(Exception e){
			e.printStackTrace();
		}
		return aux != null ? aux : new ArrayList<DashBoard>();
	}
	
	public static List<DashBoard> getChartBalcao(String filter){
		return getData("balcoes_grafico", "_postbalcoes_grafico", filter);
	}
	
	public static List<DashBoard> getChartServico(String filter){
		return getData("servicos_grafico", "_postservicos_grafico", filter);
	}
	
	public static List<DashBoard> getChartEstado(String filter){
		return getData("estados_grafico", "_postestados_grafico", filter);
	}
	
	public static List<DashBoard> getChartAssunto(String filter){
		return getData("assuntos_grafico", "_postassuntos_grafico", filter);
	}
	
	public static List<DashBoard> getChartAgendaDia(String filter){
		System.out.println(filter);
		return getData("agenda_dias_grafico", "_postagenda_dias_grafico", filter);
	}
}
