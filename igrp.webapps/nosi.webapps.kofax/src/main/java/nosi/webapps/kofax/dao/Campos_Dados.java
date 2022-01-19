package nosi.webapps.kofax.dao;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import nosi.base.ActiveRecord.BaseActiveRecord;

/**
 * @author: Emanuel Pereira
 * 11 Sep 2017
 */
@Entity
 @Table(name="tbl_campo_dados")
public class Campos_Dados extends BaseActiveRecord<Campos_Dados> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="dados_fk",foreignKey=@ForeignKey(name="tbl_campos_dados_dados_fk"),nullable=false)
	private Dados dados;
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="campo_fk",foreignKey=@ForeignKey(name="tbl_campos_dados_campo_fk"),nullable=false)
	private Campos campo;
	@Column(nullable=false)
	private String valor;
	
	public Campos_Dados() {
	}
	
	public Campos_Dados(Dados dados, Campos campo, String valor) {
		super();
		this.dados = dados;
		this.campo = campo;
		this.valor = valor;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Dados getDados() {
		return dados;
	}
	public void setDados(Dados dados) {
		this.dados = dados;
	}
	public Campos getCampo() {
		return campo;
	}
	public void setCampo(Campos campo) {
		this.campo = campo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Campos_Dados [id=" + id + ", dados=" + dados + ", campo=" + campo + ", valor=" + valor + "]";
	}
	
	@Override
	public String getConnectionName() {
		return "hibernate-kofax";
	}
}
