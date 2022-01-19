/*---------------------- Create Model ----------------------*/
package nosi.webapps.kofax.pages.detalhesarquivo;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

public class DetalhesArquivo extends Model{		
	@RParam(rParamName = "p_iframe_1_src")
	private String iframe_1_src;
	
	public void setIframe_1_src(String iframe_1_src){
		this.iframe_1_src = iframe_1_src;
	}
	public String getIframe_1_src(){
		return this.iframe_1_src;
	}


}
/*-------------------------*/