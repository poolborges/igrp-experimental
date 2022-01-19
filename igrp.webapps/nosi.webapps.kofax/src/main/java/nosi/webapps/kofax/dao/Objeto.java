package nosi.webapps.kofax.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import nosi.base.ActiveRecord.BaseActiveRecord;

/**
 * @author: Emanuel Pereira
 * 14 Aug 2017
 */
@Entity
@Table(name="tbl_objeto")
public class Objeto extends BaseActiveRecord<Objeto> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "id_organica",nullable=false)
	private Integer id_organica;
	@Column(nullable=false)
	private String objeto;
	@Column(nullable=false)
	private String formato_output;
	@Column(nullable=false)
	private String estado;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="id_objeto")
	private Collection<Campos> campos;
	
	
	public Objeto(){}

	

	public Objeto(Integer id_organica, String objeto,String formato_output,String estado, Collection<Campos> campos) {
		super();
		this.id_organica = id_organica;
		this.objeto = objeto;
		this.formato_output = formato_output;
		this.estado = estado;
		this.campos = campos;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_organica() {
		return id_organica;
	}

	public void setId_organica(Integer organica) {
		this.id_organica = organica;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	

	public String getFormato_output() {
		return formato_output;
	}

	public void setFormato_output(String formato_output) {
		this.formato_output = formato_output;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Collection<Campos> getCampos() {
		return campos;
	}

	public void setCampos(Collection<Campos> campos) {
		this.campos = campos;
	}

	@Override
	public String toString() {
		return "Objeto [id=" + id + ", organica=" + id_organica + ", objeto=" + objeto + ", formato_output=" + formato_output + ", estado=" + estado  + "]";
	}
	

	@Override
	public String getConnectionName() {
		return "hibernate-kofax";
	}
}
