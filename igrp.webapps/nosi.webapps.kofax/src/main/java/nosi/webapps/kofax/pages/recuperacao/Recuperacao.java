/*---------------------- Create Model ----------------------*/
package nosi.webapps.kofax.pages.recuperacao;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.gui.components.IGRPSeparatorList.Pair;
import nosi.core.webapp.SeparatorList;
import java.util.ArrayList;
import java.util.List;

public class Recuperacao extends Model{		
	@RParam(rParamName = "p_identificacao")
	private String identificacao;
	@RParam(rParamName = "p_tipo_objeto")
	private String tipo_objeto;
	@RParam(rParamName = "p_data_de_registo")
	private String data_de_registo;
	@RParam(rParamName = "p_descricao")
	private String descricao;
	@RParam(rParamName = "p_localizacao_fisica")
	private String localizacao_fisica;
	@RParam(rParamName = "p_estante")
	private String estante;
	@RParam(rParamName = "p_pasta")
	private String pasta;
	@RParam(rParamName = "p_livro")
	private String livro;
	@RParam(rParamName = "p_folha")
	private String folha;
	@RParam(rParamName = "p_adicionar_ficheiro")
	private String adicionar_ficheiro;
	@RParam(rParamName = "p_imagem")
	private String imagem;
	@RParam(rParamName = "p_id")
	private String p_id;

	@SeparatorList(name = Formlist_1.class)
	private List<Formlist_1> formlist_1 = new ArrayList<>();
	public void setFormlist_1(List<Formlist_1> formlist_1){
		this.formlist_1 = formlist_1;
	}
	public List<Formlist_1> getformlist_1(){
		return this.formlist_1;
	}
	
	public void setIdentificacao(String identificacao){
		this.identificacao = identificacao;
	}
	public String getIdentificacao(){
		return this.identificacao;
	}
	
	public void setTipo_objeto(String tipo_objeto){
		this.tipo_objeto = tipo_objeto;
	}
	public String getTipo_objeto(){
		return this.tipo_objeto;
	}
	
	public void setData_de_registo(String data_de_registo){
		this.data_de_registo = data_de_registo;
	}
	public String getData_de_registo(){
		return this.data_de_registo;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao(){
		return this.descricao;
	}
	
	public void setLocalizacao_fisica(String localizacao_fisica){
		this.localizacao_fisica = localizacao_fisica;
	}
	public String getLocalizacao_fisica(){
		return this.localizacao_fisica;
	}
	
	public void setEstante(String estante){
		this.estante = estante;
	}
	public String getEstante(){
		return this.estante;
	}
	
	public void setPasta(String pasta){
		this.pasta = pasta;
	}
	public String getPasta(){
		return this.pasta;
	}
	
	public void setLivro(String livro){
		this.livro = livro;
	}
	public String getLivro(){
		return this.livro;
	}
	
	public void setFolha(String folha){
		this.folha = folha;
	}
	public String getFolha(){
		return this.folha;
	}
	
	public void setAdicionar_ficheiro(String adicionar_ficheiro){
		this.adicionar_ficheiro = adicionar_ficheiro;
	}
	public String getAdicionar_ficheiro(){
		return this.adicionar_ficheiro;
	}
	
	public void setImagem(String imagem){
		this.imagem = imagem;
	}
	public String getImagem(){
		return this.imagem;
	}
	
	public void setP_id(String p_id){
		this.p_id = p_id;
	}
	public String getP_id(){
		return this.p_id;
	}


	public static class Formlist_1{
		private Pair campo;
		private Pair valor;
		public void setCampo(Pair campo){
			this.campo = campo;
		}
		public Pair getCampo(){
			return this.campo;
		}

		public void setValor(Pair valor){
			this.valor = valor;
		}
		public Pair getValor(){
			return this.valor;
		}

	}
}
/*-------------------------*/