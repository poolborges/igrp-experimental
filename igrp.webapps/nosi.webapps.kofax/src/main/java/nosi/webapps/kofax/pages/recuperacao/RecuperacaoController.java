/*-------------------------*/
/*Create Controller*/
package nosi.webapps.kofax.pages.recuperacao;

/*---- Import your packages here... ----*/
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import nosi.core.config.Config;
import nosi.core.gui.components.IGRPSeparatorList.Pair;
import nosi.core.webapp.Controller;
import java.sql.Date;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import nosi.core.webapp.Response;
import nosi.core.webapp.Igrp;
import nosi.webapps.kofax.dao.Campos;
import nosi.webapps.kofax.dao.Campos_Dados;
import nosi.webapps.kofax.dao.Dados;
import nosi.webapps.kofax.dao.Objeto;
import nosi.webapps.kofax.pages.recuperacao.Recuperacao.Formlist_1;
import nosi.core.webapp.helpers.DateHelper;
import nosi.core.webapp.helpers.FileHelper;
import nosi.core.webapp.helpers.IgrpHelper;
import nosi.core.webapp.helpers.OCRHelper;
import nosi.core.webapp.helpers.Permission;
/*---- End ----*/

public class RecuperacaoController extends Controller {		

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*---- Insert your code here... ----*/														
		Recuperacao model = new Recuperacao();
		String ichange = Igrp.getInstance().getRequest().getParameter("ichange");
		
		if(Igrp.getInstance().getRequest().getMethod().equalsIgnoreCase("POST")){
			model.load();
		}
		List<Recuperacao.Formlist_1> campos = new ArrayList<>();
		if(ichange!=null && model.getTipo_objeto()!=null && !model.getTipo_objeto().equals("")){
			for(Campos c:new Campos().find().andWhere("id_objeto", "=", Integer.parseInt(model.getTipo_objeto())).all()){
				Formlist_1 e = new Formlist_1();
				e.setCampo(new Pair(c.getCampo(), ""+c.getId()));
				e.setValor(new Pair("", " "));
				campos.add(e);
			}
		}
		RecuperacaoView view = new RecuperacaoView(model);
		view.tipo_objeto.setValue(IgrpHelper.toMap(new Objeto().find().andWhere("id_organica", "=", Permission.getCurrentOrganization()).all(), "id", "objeto", "--- Escolher Tipo Objecto ---"));
		view.data_de_registo.setValue(DateHelper.convertDate(new Date(System.currentTimeMillis()).toString(),"yyyy-MM-dd","dd-MM-yyyy"));
		view.formlist_1.addData(campos);
		return this.renderView(view);
									/*---- End ----*/
	}
	
	public PrintWriter actionGravar() throws IOException, IllegalArgumentException, IllegalAccessException, ServletException{
		/*---- Insert your code here... ----*/
		Igrp.getInstance().getResponse().setContentType("application/xml");															
		Recuperacao model = new Recuperacao();
		if(Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST")){
			model.load();
			String pathImg = Config.getBasePathXsl()+"images/IGRP/IGRP2.3/app/kofax/recuperacao/images";
			Part img = Igrp.getInstance().getRequest().getPart("p_imagem");
			String fileName = img.getName()+"_"+System.currentTimeMillis();
			Dados d = new Dados();
			d.setDescricao(model.getDescricao());
			d.setDt_registo(model.getData_de_registo());
			d.setFile_name(fileName);
			d.setObjeto(new Objeto().findOne(model.getTipo_objeto()));
			d.setConteudo(" ");
			if(img!=null){
				d.setMime_type(img.getContentType());
			}
			d = d.insert();
			if(d!=null){
				fileName +="_"+d.getId();
				d.setFile_name(fileName+"."+FileHelper.getFileExtension(img));
				pathImg += "/" + fileName;
				FileHelper.saveFile(pathImg, d.getFile_name(), img);
				OCRHelper ocr = new OCRHelper(pathImg+File.separator+d.getFile_name());
				ocr.open();
				d.setConteudo(ocr.outputText());
				ocr.outputPDF(pathImg+File.separator+fileName);
				ocr.close();
				
				applyIndex(pathImg, fileName); // For Apache Lucene ...
				
				d = d.update();
				if(model.getformlist_1().size() > 0 ){
					for(Recuperacao.Formlist_1 formList:model.getformlist_1()){
						Campos c = new Campos().findOne(formList.getCampo().getValue());
						Campos_Dados cd = new Campos_Dados(d, c, formList.getValor().getKey());
						cd.insert();
					}
				}	
				return Igrp.getInstance().getResponse().getWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><messages><message type=\"success\">Operação efectuada com sucesso</message></messages>");
			}
		}	
		return Igrp.getInstance().getResponse().getWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><messages><message type=\"error\">Operação falhada</message></messages>");
		/*---- End ----*/
	}
	
	
	
	/*---- Insert your actions here... ----*/
	private static void applyIndex(String fileUrl, String fileName) throws IOException {
		
        File file = new File(fileUrl + "/" + fileName + ".pdf");
        
      //this directory will contain the indexes
        Directory indexDirectory = FSDirectory.open(file.getParentFile());
      //create the indexer
        IndexWriter writer = new IndexWriter(indexDirectory, new StandardAnalyzer(Version.LUCENE_36),true, IndexWriter.MaxFieldLength.UNLIMITED);

        Document document = new Document();
        
        PDDocument pdf = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setLineSeparator("\n");
        stripper.setStartPage(1);
       // BufferedInputStream cin = new BufferedInputStream(new FileInputStream(file));
        
        //index file contents
        Field contentField = new Field(file.getName() + "_contents", stripper.getText(pdf), Field.Store.NO, Field.Index.ANALYZED);
        		//new Field("contents", new FileReader(file));
        //index file name
        Field fileNameField = new Field(file.getName() + "_my_index",
           file.getName(),Field.Store.YES,Field.Index.NOT_ANALYZED);
        //index file path
        Field filePathField = new Field(file.getName() + "_res",file.getCanonicalPath(),Field.Store.YES,Field.Index.NOT_ANALYZED);

        document.add(contentField);
        document.add(fileNameField);
        document.add(filePathField);
        
        writer.addDocument(document);
        
        writer.close();
        pdf.close();
	}
	/*---- End ----*/
}