/*-------------------------*/

/*Create Controller*/

package nosi.webapps.agenda.pages.detalhesmarcacao;
/*---- Import your packages here... ----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;
import nosi.core.config.Config;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import nosi.core.webapp.Response;
import nosi.webapps.agenda.dao.Marcacao;

/*---- End ----*/

public class DetalhesMarcacaoController extends Controller {		


	public Response actionIndex() throws IOException{
		/*---- Insert your code here... ----*/
		DetalhesMarcacao model = new DetalhesMarcacao();
		String id = Igrp.getInstance().getRequest().getParameter("p_event_id");
		String estado = "";
		if(id!=null && !id.equals("")){
			String filter = " AND ag_t_marcacao.id="+id;
			List<Marcacao> marcacoes = Marcacao.getAllMarcacao(filter);
			for(Marcacao m:marcacoes){
				model.setBalcao(m.getNome_balcao());
				model.setData(Marcacao.convertDate(m.getData_marcacao(),"yyyy-MM-dd","dd-MM-yyyy"));
				model.setEmail(m.getEmail());
				model.setHora(m.getHr_marcacao());
				model.setN_documento(m.getNr_documento());
				model.setNome(m.getNome());
				model.setRequerente(m.getRequerente());
				model.setServico(m.getNome_servico());
				model.setTelefone(m.getTelefone());
				model.setTelemovel(m.getTelemovel());
				model.setTipo_documento(m.getTipo_documento());
				estado = m.getEstado();
			}
		}
		DetalhesMarcacaoView view = new DetalhesMarcacaoView(model);
		Config.target = "_blank";
		if(estado.equalsIgnoreCase("ATIVO")){
			view.btn_cancelar_agenda.setLink("cancelarAgenda&p_id="+id);
			view.btn_confirmar_agenda.setLink("confirmarAgenda&p_id="+id);
			view.toolsbar_1.addButton(view.btn_confirmar_agenda);
			view.toolsbar_1.addButton(view.btn_cancelar_agenda);
		}else if(estado.equalsIgnoreCase("CONFIRMADO")){
			view.btn_cancelar_agenda.setLink("cancelarAgenda&p_id="+id);
			view.btn_confirmar_presenca.setLink("confirmarPresenca&p_id="+id);
			view.toolsbar_1.addButton(view.btn_confirmar_presenca);
			view.toolsbar_1.addButton(view.btn_cancelar_agenda);
		}
		view.servico.setLabel("Serviço");
		view.n_documento.setLabel("Nº Documento");
		return this.renderView(view);
		/*---- End ----*/
	}


	public Response actionConfirmarAgenda() throws IOException{
		/*---- Insert your code here... ----*/		
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		if(id!=null){
			Marcacao m = Marcacao.getMarcacao(Integer.parseInt(id));
			m.setEstado("CONFIRMADO"); 
			String filter = " AND ag_t_marcacao.id="+m.getId();
			List<Marcacao> marcacoes = Marcacao.getAllMarcacao(filter);
			Marcacao obj = marcacoes.get(0);
			int s = Marcacao.update(m);
			if(s==200 || s==204){
				Igrp.getInstance().getFlashMessage().addMessage("success", "Operação Realizada com sucesso");
				// Send Email here ... 
				String subject = "Confirmação de Agenda - IGRP Agenda Electrónica";
				String msg = "<h2>Detalhes da Agenda</h2><b>Serviço:</b> " + obj.getNome_servico() + " <br/><b>Balcão:</b> " + obj.getNome_balcao() 
				+ " <br/><b>Data:</b> " + m.getData_marcacao() + " <br/><b>Hora:</b> " + m.getHr_marcacao();
				if(sendEmail(m.getEmail(), subject, msg))
					Igrp.getInstance().getFlashMessage().addMessage("success", "Email enviado com sucesso ( To: " + m.getEmail() + " ).");
				else
					Igrp.getInstance().getFlashMessage().addMessage("error", "Ocorreu um erro ao enviar email ( To: " +  m.getEmail() + " ).");
			}else{
				Igrp.getInstance().getFlashMessage().addMessage("error", "Operação Falhada");
			}
		}
		return this.redirect("agenda","DetalhesMarcacao","index");
		/*---- End ----*/
	}
	
	private static boolean sendEmail(String destinationEmail, String subject, String msg) {
		System.setProperty("java.net.preferIPv4Stack", "true");
		String to = destinationEmail; 
		// Sender's email ID needs to be mentioned
		String from = "igrpframeworkjava@gmail.com";
		// Assuming you are sending email from localhost
		String host = "smtp.gmail.com";
		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("igrpframeworkjava@gmail.com","Pa$$w0rd10");
					}
				});
		// Set response content type
		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session); 
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,
			new InternetAddress(to));
			// Set Subject: header field
			message.setSubject(subject);
			// Now set the actual message 
			message.setText(msg, "utf-8", "html");	
			// Send message
			Transport.send(message);
		}catch (MessagingException mex) {
			System.out.println("Error ... sending email ...");
			mex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Response actionConfirmarPresenca() throws IOException{
		/*---- Insert your code here... ----*/		
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		if(id!=null){
			Marcacao m = Marcacao.getMarcacao(Integer.parseInt(id));
			m.setEstado("REALIZADO");
			int s = Marcacao.update(m);
			if(s==200 || s==204){
				Igrp.getInstance().getFlashMessage().addMessage("success", "Operação Realizada com sucesso");
			}else{
				Igrp.getInstance().getFlashMessage().addMessage("error", "Operação Falhada");
			}
		}
		return this.redirect("agenda","DetalhesMarcacao","index");
		/*---- End ----*/
	}
	
	public Response actionCancelarAgenda() throws IOException{
		/*---- Insert your code here... ----*/		
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		if(id!=null){
			Marcacao m = Marcacao.getMarcacao(Integer.parseInt(id));
			m.setEstado("INATIVO");
			int s = Marcacao.update(m);
			if(s==200 || s==204){
				Igrp.getInstance().getFlashMessage().addMessage("success", "Operação Realizada com sucesso");
			}else{
				Igrp.getInstance().getFlashMessage().addMessage("error", "Operação Falhada");
			}
		}
		return this.redirect("agenda","DetalhesMarcacao","index");
		/*---- End ----*/
	}
	
	
	/*---- Insert your actions here... ----*//*---- End ----*/
}
