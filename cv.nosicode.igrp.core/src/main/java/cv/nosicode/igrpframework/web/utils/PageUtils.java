/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.nosicode.igrpframework.web.utils;

import nosi.core.config.Config;
import nosi.core.gui.components.IGRPMessage;
import nosi.core.gui.page.Page;
import nosi.core.webapp.Igrp;
import nosi.core.xml.XMLWritter;

import nosi.webapps.igrp.dao.Action;
import nosi.webapps.igrp.dao.Application;

/**
 *
 * @author pauloborges
 */
public class PageUtils {

    public static String createTemplate() {
        String path_xsl = "teste";
        String app = Igrp.getInstance().getCurrentAppName();
        String page = Igrp.getInstance().getCurrentPageName();
        String action = Igrp.getInstance().getCurrentActionName();
        if (!app.equals("") && !page.equals("") && !action.equals("")) {
            Application appl = new Application();
            appl = appl.findOne(appl.getCriteria().where(
                    appl.getBuilder().equal(appl.getRoot().get("dad"), app)));
            Action ac = new Action();
            ac = ac.findOne(ac.getCriteria().where(
                    ac.getBuilder().equal(ac.getRoot().get("application"), appl),
                    //ac.getBuilder().equal(ac.getRoot().get("action"), action),
                    ac.getBuilder().equal(ac.getRoot().get("page"), Page.resolvePageName(page))));
            path_xsl = "images/IGRP/IGRP" + Config.getPageVersion() + "/app/" + ac.getXsl_src();
        }

        XMLWritter xml = new XMLWritter("rows", path_xsl, "utf-8");

        xml.addXml(Config.getHeader());
        xml.startElement("content");
        xml.writeAttribute("type", "");
        xml.setElement("title", "");
        xml.text(":_content");
        IGRPMessage msg = new IGRPMessage();
        String m = msg.toString();
        if (m != null) {
            xml.addXml(m);
        }
        xml.endElement();
        return xml + "";
    }
}
