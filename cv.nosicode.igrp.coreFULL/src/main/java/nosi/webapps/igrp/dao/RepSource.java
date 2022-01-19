package nosi.webapps.igrp.dao;
/**
 * @author: Emanuel Pereira
 * 29 Jun 2017
 */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import nosi.base.ActiveRecord.BaseActiveRecord;
import nosi.core.gui.components.IGRPForm;
import nosi.core.gui.components.IGRPTable;
import nosi.core.gui.fields.Field;
import nosi.core.gui.fields.TextField;
import nosi.core.xml.XMLWritter;

@Entity
@Table(name="tbl_rep_source")
public class RepSource extends BaseActiveRecord<RepSource> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7036375790328828424L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(nullable=false)
	private String type;
	private Integer type_fk;
	private String type_name;
	private String type_query;
	private int status;
	@Temporal(TemporalType.DATE)
	private Date dt_created;
	@Temporal(TemporalType.DATE)
	private Date dt_updated;

	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="env_fk",foreignKey=@ForeignKey(name="REP_SOURCE_ENV_FK"),nullable=false)
	private Application application;

	@ManyToOne
	@JoinColumn(name="env_source_fk",foreignKey=@ForeignKey(name="REP_SOURCE_ENV_SOURCE_FK"))
	private Application application_source;
	
	@ManyToOne
	@JoinColumn(name="user_created_fk",foreignKey=@ForeignKey(name="REP_SOURCE_USER_CREATED_FK"),nullable=false)
	private User user_created;
	
	@ManyToOne
	@JoinColumn(name="user_updated_fk",foreignKey=@ForeignKey(name="REP_SOURCE_USER_UPDATED_FK"),nullable=false)
	private User user_updated;
	@Transient
	private XMLWritter xmlRows = new XMLWritter();
	public RepSource(){}
	
	public RepSource(String name, String type, Integer type_fk, String type_name, String type_query,
			int status, Date dt_created, Date dt_updated, Application application, Application application_source,
			User user_created, User user_updated) {
		super();
		this.name = name;
		this.type = type;
		this.type_fk = type_fk;
		this.type_name = type_name;
		this.type_query = type_query;
		this.status = status;
		this.dt_created = dt_created;
		this.dt_updated = dt_updated;
		this.application = application;
		this.application_source = application_source;
		this.user_created = user_created;
		this.user_updated = user_updated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getType_fk() {
		return type_fk;
	}

	public void setType_fk(Integer type_fk) {
		this.type_fk = type_fk;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getType_query() {
		return type_query;
	}

	public void setType_query(String type_query) {
		this.type_query = type_query;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDt_created() {
		return dt_created;
	}

	public void setDt_created(Date dt_created) {
		this.dt_created = dt_created;
	}

	public Date getDt_updated() {
		return dt_updated;
	}

	public void setDt_updated(Date dt_updated) {
		this.dt_updated = dt_updated;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Application getApplication_source() {
		return application_source;
	}

	public void setApplication_source(Application application_source) {
		this.application_source = application_source;
	}

	public User getUser_created() {
		return user_created;
	}

	public void setUser_created(User user_created) {
		this.user_created = user_created;
	}

	public User getUser_updated() {
		return user_updated;
	}

	public void setUser_updated(User user_updated) {
		this.user_updated = user_updated;
	}

	public HashMap<Integer,String> getListSources(Integer id){
		HashMap<Integer,String> lista = new HashMap<>();
		//lista.put(null, "--- Selecionar Aplica��o ---");
		for(RepSource rep:this.find().andWhere("application", "=",id).all()){
			lista.put(rep.getId(), rep.getName());
		}
		return lista;
	}
	
	//Validate sql query
	public boolean validateQuery(String query) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		EntityTransaction t =  em.getTransaction();
		t.begin();
		boolean x = false;
		try{
			Query q = em.createNativeQuery(query);
			for(Parameter<?> param:q.getParameters()){
				q.setParameter(param.getName(), null);
			}
			q.getResultList();
			x = true;
			t.commit();
		}catch(Exception e){
			x = false;
		}finally{
			em.close();
		}
		return x;
	}

	/*Extract columns in Query Select and Check if the column is a parameter
	 * 
	 * Example: SELECT ID, NAME, EMAIL from tbl_user
	 * 
	 * The columns extracted is: id, name, email
	 * 
	 */
	public Set<Properties> getColumns(int template_id,String query) {
		Set<Properties> columns = new LinkedHashSet<>();
		Connection con = nosi.core.config.Connection.getConnection(this.getConnectionName());
		try {
			Statement s = con.createStatement();
			Set<String> keys = getParamsQuery(template_id,query);
			query =query.replaceAll(":\\w+", "null");
			ResultSetMetaData rsd =s.executeQuery(query).getMetaData();
			for(int i=1;i<=rsd.getColumnCount();i++){
				Properties p = new Properties();
				//Set propertie true if column is a parameter
				p.put("key", keys.contains(rsd.getColumnName(i).toLowerCase())?"true":"false");
				p.put("name","p_"+rsd.getColumnName(i).toLowerCase());
				p.put("tag",rsd.getColumnName(i).toLowerCase());
				columns.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(con!=null){con.close();}
			} catch (SQLException e) {
			}
		}
		return columns;
	}
	
	
	/*Extract parameters in query select
	 * Example: SELECT * FROM tbl_user WHERE id=:p_id
	 * 
	 * The "p_id" is parameter
	 */
	public Set<String> getParamsQuery(int template_id,String query){
		Set<String> params = new HashSet<String>();
		EntityManager em = this.entityManagerFactory.createEntityManager();
		EntityTransaction t =  em.getTransaction();
		t.begin();
		try{
			Query q = em.createNativeQuery(query);
			for(Parameter<?> param:q.getParameters()){
				params.add(param.getName().contains("p_")?param.getName().substring("p_".length()):param.getName());
			}
			t.commit();
		}catch(Exception e){
		}finally{
			em.close();
		}
		if(template_id!=0){
			RepTemplateParam rtp = new RepTemplateParam();
			for(RepTemplateParam r:rtp.findAll(rtp.getCriteria().where(rtp.getBuilder().equal(rtp.getRoot().get("reptemplate"),template_id)))){
				params.add(r.getParameter().toLowerCase());
			}
		}
		return params;
	}
	
	/*Transform sql query to xml
	 * 
	 * Example: SELECT ID, NAME, EMAIL from tbl_user
	 * The return value is:
	 * 	<form>
	 * 		<fields>
        		<number_1 name="p_number_1" type="number" maxlength="30" required="false" change="false" readonly="false" disabled="false" placeholder="" right="false">
	                <label>Number</label>
	                <value>606</value>
	            </number_1>
	            ...
	        </fields>
	 *  </form>
	 *  <table>
	        <value>
	            <row>
	                <number_1>527</number_1>
	                <number_1_desc>527</number_1_desc>
	                <text_1>Magna dolor labore ipsum totam</text_1>
	                <text_1_desc>Magna dolor labore ipsum totam</text_1_desc>
	            </row>
	            ...
	        </value>
	 *  <table>
	 */
	public String getSqlQueryToXml(String query,String[]name_array,String[]value_array,RepTemplate rt,RepSource rs){
		
		Set<Properties> columns = this.getColumns(rt.getId(), query);
		if(value_array==null || value_array.length<=0){
			query =rs.getType().equals("query")?query.replaceAll("\\w+=:\\w+", "1=1"):query;
		}
		String xml = null;
		Map<String, String> paramsUrl = (value_array!=null && value_array.length > 0)?(Map<String, String>) IntStream.range(0, name_array.length).boxed().collect(Collectors.toMap(i -> /*name_array[i].contains("p_")?name_array[i].substring("p_".length()):*/name_array[i], i -> value_array[i])):null;
		EntityManager em = this.entityManagerFactory.createEntityManager();
		EntityTransaction t =  em.getTransaction();
		t.begin();
		try{
			Query q = em.createNativeQuery(query);
			if(value_array!=null && value_array.length>0){
				for(Parameter<?> param:q.getParameters()){
					q.setParameter(param.getName(), paramsUrl.get(param.getName()));
				}
			}	
			@SuppressWarnings("unchecked")
			List<Object[]> list = q.getResultList();
			xml = this.getSqlQueryToXml(columns, list);
			t.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			em.close();	
		}
		return xml;
	}
	
	
	private String getSqlQueryToXml(Set<Properties> columns,List<Object[]> data){
		XMLWritter xml = new XMLWritter();
		IGRPForm form = new IGRPForm("form",(float)2.1);
		IGRPTable table = new IGRPTable("table",(float)2.1);
		Map<Properties,String> mappData = this.mappingColumnValue(columns, data);		
		Set<Entry<Properties, String>> setData = mappData.entrySet();
		for(Entry<Properties,String> entry:setData){
				Field f = new TextField(null,entry.getKey().getProperty("tag"));
				f.propertie().clear();
				f.propertie().add("name",entry.getKey().getProperty("name"));
				f.setValue(entry.getValue());
				form.addField(f);
				table.addField(f);
		}
		table.addRowsXMl(this.xmlRows.toString());
		xml.addXml(form.toString());
		xml.addXml(table.toString());
		return xml.toString();
	}
	
	/*Mapping columns with value properties
	 * 
	 */
	private Map<Properties,String> mappingColumnValue(Set<Properties> columns,List<Object[]> data){
		Map<Properties,String> mapping = new HashMap<>();
		for(Object[] obj:data){
			if(obj instanceof Object[]){
				xmlRows.startElement("row");
				int i=0;
				Iterator<Properties> listColumns = columns.iterator();
				while(listColumns.hasNext()){
					Properties p = listColumns.next();
					mapping.put(p, obj[i]!=null?obj[i].toString():"");
					xmlRows.startElement(p.getProperty("tag"));
					xmlRows.writeAttribute("name",p.getProperty("name"));
					xmlRows.text(obj[i]!=null?obj[i].toString():"");
					xmlRows.endElement();
					i++;
				}
				xmlRows.endElement();
			}
		}
		return mapping;
	}

	
}
