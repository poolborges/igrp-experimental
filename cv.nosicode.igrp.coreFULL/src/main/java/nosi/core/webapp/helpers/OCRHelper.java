package nosi.core.webapp.helpers;

import java.io.File;

import com.asprise.ocr.Ocr;

/**
 * Yma
 * 11 Sep 2017
 */
public class OCRHelper {
	private Ocr ocr;
	private String fileName;
	
	public OCRHelper(String fileName) {
		this.open();
		this.ocr = new Ocr();
    	this.ocr.startEngine("eng", Ocr.SPEED_FASTEST); 
    	this.fileName = fileName;
	}
	
	public String outputText() {
    	String s = ocr.recognize(new File[] {new File(this.fileName)}, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);
    	return s;
	} 
	
	public String outputXml() {
    	String s = ocr.recognize(new File[] {new File(this.fileName)}, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_XML);
    	return s;
	} 
	

	public void outputPDF(String fileNamePDF) {
		this.ocr.recognize(new File[] {new File(this.fileName)},
			  Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PDF,
			  new Ocr.PropertyBuilder()
			    .setPdfOutputFile(fileNamePDF+".pdf")
			    .setPdfTextVisible(false)
			);
	} 
	
	public void open(){
		Ocr.setUp(); // one time setup
	}
	
	public void close(){
		this.ocr.stopEngine();
	}
}
