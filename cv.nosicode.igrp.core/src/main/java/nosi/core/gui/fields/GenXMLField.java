package nosi.core.gui.fields;

/**
 * @author: Emanuel Pereira
 *
 * Apr 14, 2017
 *
 * Description: class to generate xml of fields
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import nosi.core.xml.XMLWritter;

public class GenXMLField {

    public static String view_img = "";

    /*Generate xml field
	 * <fields>
            <text_1 name="p_text_1" type="text" maxlength="30" required="false" change="false" readonly="false" disabled="false" placeholder="" right="false">
                <label>Text</label>
                <value>Text 1</value>
            </text_1>
        </fields>
     */
    public static void toXml(XMLWritter xml, ArrayList<Field> fields) {
        if (fields.size() > 0) {
            xml.startElement("fields");
            for (Field field : fields) {
                if (field.isVisible()) {
                    if (field instanceof HiddenField) {
                        xml.startElement("hidden");
                        field.propertie().add("value", field.getValue());
                        field.propertie().remove("maxlength");
                    } else {
                        xml.startElement(field.getTagName());
                    }
                    writteAttributes(xml, field.propertie());
                    if (!(field instanceof HiddenField)) {//Hidden field not contain tag label
                        xml.setElement("label", field.getLabel());
                    }
                    if (!(field instanceof SeparatorField) && !(field instanceof HiddenField)) {//Seprator field not contain tag value
                        getXmlValue(xml, field);
                    }
                    if (field instanceof LookupField) {
                        xml.setElement("lookup", field.getLookup());
                        /*
                        <lookup_1 name="p_lookup_1" type="lookup" action="" page="" app="" class="default" required="false" change="false" readonly="false" disabled="false" maxlength="30" placeholder="" right="false">
                        <label>Lookup</label>
                        <value/>
                        <lookup>http://xpto/file.xml</lookup>
                        </lookup_1>
                         */
                    }
                    xml.endElement();
                }
            }
            if (view_img.toString().compareTo("") > 0) {//add tag view_img in case the View
                xml.setElement("view_img", view_img);
                view_img = "";
            }
            xml.endElement();
        }
    }

    /*
	Generate field xml for Generator
	<label>
            <env_fk     name="p_env_fk" type="select"   maxlength="30">Aplicacao</env_fk>
            ...
        </label>
        <value>
        	...
        </value>
         <list>
            <env_fk     name="p_env_fk">
                <option>
                    <text>-- Aplicação --</text>
                    <value />
                </option>
                <option     selected="true">
                    <text>API - Gerador</text>
                    <value>807</value>
                </option>
                ...
            </env_fk>
        </list>
        ...
     */
    public static void toXmlV21(XMLWritter xml, ArrayList<Field> fields) {
        if (fields.size() > 0) {
            xml.startElement("label");
            for (Field field : fields) {
                if (field.isVisible()) {
                    if (field instanceof HiddenField) {
                        field.setTagName(field.propertie().getProperty("tag"));
                    }
                    xml.startElement(field.getTagName());
                    writteAttributes(xml, field.propertie());
                    xml.text(field.getLabel());
                    xml.endElement();
                }
            }
            xml.endElement();
            xml.startElement("value");
            for (Field field : fields) {
                if (field.isVisible()) {
                    if (!(field instanceof ListField) && !(field instanceof RadioListField) && !(field instanceof CheckBoxListField)) {
                        if (field instanceof HiddenField) {
                            xml.startElement("hidden");
                        } else {
                            xml.startElement(field.getTagName());
                        }
                        writteAttributes(xml, field.propertie());
                        xml.text("" + field.getValue());
                        if (field instanceof LookupField) {
                            xml.setElement("lookup", field.getLookup());
                            /*
                            <lookup_1 name="p_lookup_1" type="lookup" action="" page="" app="" class="default" required="false" change="false" readonly="false" disabled="false" maxlength="30" placeholder="" right="false">
                                <label>Lookup</label>
                                <value/>
                                <lookup>http://xpto/file.xml</lookup>
                            </lookup_1>
                             */
                        }
                        xml.endElement();
                    }
                }
            }
            xml.endElement();
            xml.startElement("list");
            for (Field field : fields) {
                if (field.isVisible()) {
                    if (field instanceof ListField || field instanceof RadioListField || field instanceof CheckBoxListField) {
                        xml.startElement(field.getTagName());
                        writteAttributes(xml, field.propertie());
                        if (field.getValue() != null && field.getValue() instanceof HashMap) {
                            HashMap<?, ?> values = (HashMap<?, ?>) field.getValue();
                            for (Entry<?, ?> obj : values.entrySet()) {
                                xml.startElement("option");
                                if (field instanceof ListField && obj.getKey() != null && field.propertie().get("value") != null && field.propertie().get("value").toString().equals(obj.getKey().toString())) {
                                    xml.writeAttribute("selected", "true");
                                } else if ((field instanceof CheckBoxListField || field instanceof RadioListField) && obj.getKey() != null && field.propertie().get("value") != null && field.propertie().get("value").toString().equals(obj.getKey().toString())) {
                                    xml.writeAttribute("checked", "true");
                                }
                                xml.setElement("text", obj.getValue().toString());
                                if (obj.getKey() == null || obj.getKey().toString() == "") {
                                    xml.emptyTag("value");
                                } else {
                                    xml.setElement("value", obj.getKey().toString());
                                }
                                xml.endElement();
                            }
                        }
                        xml.endElement();
                    }
                }
            }
            xml.endElement();
        }
    }

    /*Generate attributes
	 * name="p_text_1" type="text"
     */
    public static void writteAttributes(XMLWritter xml, java.util.Properties properties) {
        for (Entry<Object, Object> p : properties.entrySet()) {
            xml.writeAttribute(p.getKey().toString(), p.getValue().toString());
        }
    }

    /*Generate xml value
	 * <value>Text 1</value>
     */
    private static void getXmlValue(XMLWritter xml, Field field) {
        if (field instanceof ListField || field instanceof RadioListField || field instanceof CheckBoxListField) {
            xml.startElement("list");
            if (field.getValue() != null && field.getValue() instanceof HashMap) {
                HashMap<?, ?> values = (HashMap<?, ?>) field.getValue();
                for (Entry<?, ?> obj : values.entrySet()) {
                    xml.startElement("option");
                    if (field instanceof ListField && obj.getKey() != null && field.propertie().get("value") != null && field.propertie().get("value").toString().equals(obj.getKey().toString())) {
                        xml.writeAttribute("selected", "true");
                    } else if ((field instanceof CheckBoxListField || field instanceof RadioListField) && obj.getKey() != null && field.propertie().get("value") != null && field.propertie().get("value").toString().equals(obj.getKey().toString())) {
                        xml.writeAttribute("checked", "true");
                    }
                    xml.setElement("text", obj.getValue().toString());
                    if (obj.getKey() == null || obj.getKey().toString() == "") {
                        xml.emptyTag("value");
                    } else {
                        xml.setElement("value", obj.getKey().toString());
                    }
                    xml.endElement();
                }
            }
            xml.endElement();
        } else {
            xml.setElement("value", field.getValue().toString());
        }
    }

}
