/*-------------------------*/

/*Create View*/

package nosi.webapps.kofax.pages.detalhesarquivo;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;

public class DetalhesArquivoView extends View {
	
	
	public Field iframe_1_src;
	public IGRPIframe iframe_1;

	public DetalhesArquivoView(DetalhesArquivo model){
		this.setPageTitle("Detalhes de Arquivo");
			
		iframe_1 = new IGRPIframe("iframe_1","");
		iframe_1_src = new TextField(model,"iframe_1_src");
		iframe_1_src.setLabel("Iframe Source");
		iframe_1_src.propertie().add("name","p_iframe_1_src").add("type","text").add("maxlength","4000");

		
	}
		
	@Override
	public void render(){
		
		iframe_1.addField(iframe_1_src);

		this.addToPage(iframe_1);
	}
}
/*-------------------------*/