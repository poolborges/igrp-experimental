/*-------------------------*/

/*Create Controller*/

package nosi.webapps.kofax.pages.detalhesarquivo;

/*---- Import your packages here... ----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;
import nosi.core.config.Config;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletOutputStream;
import nosi.core.webapp.Response;
import nosi.webapps.kofax.dao.Dados;
/*---- End ----*/

public class DetalhesArquivoController extends Controller {		


	public Response actionIndex() throws IOException{
		/*---- Insert your code here... ----*/
		DetalhesArquivo model = new DetalhesArquivo();
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		if(id!=null && !id.equals("")){
			Dados d = new Dados().findOne(id);
			if(d!=null){
				model.setIframe_1_src("/IGRP/webapps?r=kofax/DetalhesArquivo/getFile&p_id="+id);
			}
		}
		DetalhesArquivoView view = new DetalhesArquivoView(model);
		Config.target = "_blank";
		return this.renderView(view);
		/*---- End ----*/
	}

	/*---- Insert your actions here... ----*/
	
	public ServletOutputStream actionGetFile() throws FileNotFoundException, IOException{
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		if(id!=null && !id.equals("")){
			Dados d = new Dados().findOne(id);
			if(d!=null){
				Igrp.getInstance().getResponse().setContentType(d.getMime_type());
				String aux[] = d.getFile_name().split("\\.");
				String result = aux.length == 2 ? aux[0] : d.getFile_name();
				String pathImg = Config.getBasePathXsl()+"images/IGRP/IGRP2.3/app/kofax/recuperacao/images/" + result + "/" +d.getFile_name();
				pathImg = pathImg.replace("\\", "/");
				ServletOutputStream outStream = Igrp.getInstance().getResponse().getOutputStream();
		        FileInputStream fin = new FileInputStream(pathImg);

		        BufferedInputStream bin = new BufferedInputStream(fin);
		        BufferedOutputStream bout = new BufferedOutputStream(outStream);
		        int ch =0; ;
		        while((ch=bin.read())!=-1)
		            bout.write(ch);

		        bin.close();
		        fin.close();
		        bout.close();
		        outStream.close();
				return outStream;
			}
		}
		return null;
	}
	/*---- End ----*/
}
