/*-------------------------*/

/*Create Controller*/

package nosi.webapps.kofax.pages.pesquisa_arquivo_pdf;
import nosi.core.config.Config;
/*---- Import your packages here... ----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.RParam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import nosi.core.webapp.Response;

/*---- End ----*/

public class Pesquisa_arquivo_pdfController extends Controller {		

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*---- Insert your code here... ----*/
		Pesquisa_arquivo_pdf model = new Pesquisa_arquivo_pdf();
		
		List<Pesquisa_arquivo_pdf.Table_1> dataProvider = new ArrayList<Pesquisa_arquivo_pdf.Table_1>();
		
		if(Igrp.getInstance().getRequest().getMethod().equalsIgnoreCase("POST")) {
			model.load();
			
			List<String> fileUrl = new ArrayList<String>();
			List<String> fileSize = new ArrayList<String>();
			
			if(model.getConteudo() != null && !model.getConteudo().isEmpty())
				search(model.getConteudo(), fileUrl, fileSize);
			
			for(int i = 0; i < fileUrl.size(); i++) {
				Pesquisa_arquivo_pdf.Table_1 obj = new Pesquisa_arquivo_pdf.Table_1();
				obj.setArquivo_desc("Abrir");
				String fileName = new File(fileUrl.get(i)).getName();
				String aux[] = fileName.split("\\.");
				String result = aux.length == 2 ? aux[0] : fileName;
				obj.setArquivo("kofax", "Pesquisa_arquivo_pdf", "pdf&amp;filePath=images/IGRP/IGRP2.3/app/kofax/recuperacao/images/" + result + "/" + fileName);
				obj.setCaminho_do_arquivo(".../" + new File(fileUrl.get(i)).getName());
				obj.setTamanho_do_arquivo(fileSize.get(i));
				dataProvider.add(obj); 
			}
		}
		
		Pesquisa_arquivo_pdfView view = new Pesquisa_arquivo_pdfView(model);
		
		view.table_1.addData(dataProvider);
		
		return this.renderView(view);
		/*---- End ----*/
	}
	
	public ServletOutputStream actionPdf(@RParam(rParamName = "filePath") String filePath) throws IOException{
		ServletOutputStream cout = Igrp.getInstance().getResponse().getOutputStream();
		Igrp.getInstance().getResponse().setContentType("application/pdf");
		Igrp.getInstance().getResponse().addHeader("Content-Transfer-Encoding", " binary");
		Igrp.getInstance().getResponse().addHeader("Content-Disposition", " inline; filename=\"the.pdf\"");
		if(filePath != null && !filePath.isEmpty()) {
			File file = new File(Config.getBasePathXsl() + filePath);
			FileInputStream fin = new FileInputStream(file);
			
		    BufferedInputStream bin = new BufferedInputStream(fin);
		    BufferedOutputStream bout = new BufferedOutputStream(cout);
		    int ch = 0; ;
		    while((ch=bin.read())!=-1)
		        bout.write(ch);
		    bin.close();
		    fin.close();
		    bout.close();
		}
		return cout;
	}
	
	private static void search(String search, List<String> fileUrl, List<String> fileSize) throws IOException {
		String pathImg = Config.getBasePathXsl()+ "images/IGRP/IGRP2.3/app/kofax/recuperacao/images";
	   	File parent = new File(pathImg);
	   	File child[] = parent.listFiles();
	   	for(File file : child) {
	   		Directory indexDirectory = FSDirectory.open(file);
		   	IndexSearcher indexSearcher = new IndexSearcher(indexDirectory);
		   	for(File obj : file.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.matches("[a-zA-Z0-9_]*.(PDF|pdf)");
				}
			})) {
		   		QueryParser queryParser = new QueryParser(Version.LUCENE_36, obj.getName() + "_contents", new StandardAnalyzer(Version.LUCENE_36));
			   	try {
				   	Query query = queryParser.parse(search);
				   	
				   	TopDocs hits = indexSearcher.search(query, 1000);
				   	 
				   //	System.out.println("Size -> " + hits.scoreDocs.length);
				   	 
				   	for(ScoreDoc scoreDoc : hits.scoreDocs) {
				   		Document doc = indexSearcher.doc(scoreDoc.doc);
				   		fileUrl.add(doc.get(obj.getName() + "_res"));
				   		fileSize.add(obj.length() + " B");
				       // System.out.println("File: " + doc.get("res"));
				   	 }
			   	}catch(ParseException e) {
				   		e.printStackTrace();
				   	}
		   	}

		   	indexSearcher.close();
		   	indexDirectory.close();
	   }
	}

	public Response actionPesquisar() throws IOException{
		/*---- Insert your code here... ----*/
		return this.redirect("kofax","Pesquisa_arquivo_pdf","index");
		/*---- End ----*/
	}
	
	/*---- Insert your actions here... ----*//*---- End ----*/
}
