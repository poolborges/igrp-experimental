package nosi.core.gui.components;

/**
 * @author: Emanuel Pereira
 *
 * Apr 17, 2017
 *
 * Description: class to generate xml of tools-bar
 */
import java.util.ArrayList;

import nosi.core.gui.fields.GenXMLField;
import nosi.core.webapp.helpers.Permission;
import nosi.core.xml.XMLWritter;

public class IGRPToolsBar {

    protected String tag_name;
    protected ArrayList<IGRPButton> buttons;
    protected Object class_name = this;
    protected XMLWritter xml;
    protected String type = "toolsbar";

    public IGRPToolsBar(String tag_name) {
        this.tag_name = tag_name;
        this.buttons = new ArrayList<>();
        this.xml = new XMLWritter();
    }

    public void setClassName(Object class_name) {
        this.class_name = class_name;
    }

    public String getClassName() {
        return this.class_name.getClass().getSimpleName().toString();
    }

    public String toString() {
        return this.toXmlTools();
    }

    /*Generate xml item
	 *  <item type="specific" code="" rel="button_1" class="default">
            <title>Button</title>
            <app/>
            <page/>
            <link/>
            <target>_blank</target>
            <img>fa-dot-circle-o</img>
            <params>default|fa-dot-circle-o|www</params>
        </item>
     */
    public String toXmlTools() {
        if (this.buttons.size() > 0) {
            xml.startElement(this.tag_name);
            if (this.getClassName().compareTo("IGRPToolsBar") == 0) {
                xml.writeAttribute("type", this.type);
                xml.writeAttribute("structure", "items");
            }
            for (IGRPButton item : buttons) {
                this.genXmlItem(item, "item");
            }
            xml.endElement();
        }
        this.buttons = null;
        return xml.toString();
    }

    /*Generate xml button
	 * <button		type="specific">
            <title>Sair</title>
            <app>YGRP</app>
            <page />
            <link>#</link>
            <target>_self</target>
            <img>exit.png</img>
        </button>
     */
    public String toXmlButton() {
        if (this.buttons.size() > 0) {
            for (IGRPButton item : buttons) {
                this.genXmlItem(item, this.tag_name);
            }
        }
        this.buttons = null;
        return xml.toString();
    }

    private void genXmlItem(IGRPButton item, String tag) {
        if (item.getProperties().getProperty("flg_transaction") != null && item.getProperties().getProperty("flg_transaction").equals("true")) {
            if (Permission.isPermission(item.getApp().toLowerCase() + "_" + item.getPage() + "_" + item.getProperties().getProperty("rel"))) {
                this.genItem(tag, item);
            }
        } else {
            this.genItem(tag, item);
        }
    }

    private void genItem(String tag, IGRPButton item) {
        xml.startElement(tag);
        GenXMLField.writteAttributes(xml, item.getProperties());
        xml.setElement("title", item.getTitle());
        xml.setElement("app", item.getApp());
        xml.setElement("page", item.getPage());
        xml.setElement("link", item.getLink());
        xml.setElement("target", item.getTarget());
        xml.setElement("img", item.getImg());
        if (item.getParams().compareTo("") != 0) {
            xml.setElement("params", item.getParams());
        }
        if (item.getParams().compareTo("") != 0) {
            xml.setElement("parameter", item.getParameter());
        }
        xml.endElement();
    }

    public void addButton(IGRPButton button) {
        if (this.buttons != null) {
            button.propertie.put("type", "specific");
            this.buttons.add(button);
        }
    }

    public void setButtons(ArrayList<IGRPButton> buttons) {
        if (this.buttons != null) {
            this.buttons = buttons;
        }
    }
}
