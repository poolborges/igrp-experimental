/**
 * Copyright {{ year }} {{ organization }}
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package cv.nosicode.igrpportal.osgi;

import cv.nosicode.igrpframework.web.core.IGRPWebApp;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import nosi.webapps.igrp.pages.datasource.DataSourceController;
import nosi.webapps.igrp.pages.defaultpage.DefaultPageController;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 *
 * @author pauloborges
 */
public class IGRPActivator implements BundleActivator {

    private static final int NUM_SERVICES = 2;
    private ServiceRegistration<IGRPWebApp> serviceRef;

    @Override
    public void start(BundleContext context) throws Exception {
        //THIS IS NOT FINAL VERSION, SINCE WE ARE REGIST INSTANCE

        IGRPWebApp.ControllerAction<DataSourceController> ctr
                = new IGRPWebApp.ControllerAction(new DataSourceController());

        //ctr.setControllerPath("datasource")
        ctr.addAction("index", DataSourceController::actionIndex);
//       ctr.addAction("datasource", DataSourceController::actionGetDataSource);
        ctr.addAction("gravar", DataSourceController::actionGravar);
        ctr.addAction("fechar", DataSourceController::actionFechar);

        IGRPWebApp igrpWebapp = new IgrpPortalWebApp();
        //webapp.setBasePath("igrp");

        serviceRef = context.registerService(IGRPWebApp.class, igrpWebapp, getWebAppParams());

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRef.unregister();
    }

    private Dictionary<String, String> getWebAppParams() {
        Dictionary<String, String> params = new Hashtable<>();
        params.put("igrp.webapp.id", "nosi.webapps.igrp.portal");
        params.put("igrp.webapp.package", "nosi.webapps.igrp.pages");
        params.put("igrp.webapp.title", "IGRP Portal");
        params.put("igrp.webapp.version", "1.0.0");
        return params;
    }
}

class WebappManager {

    /**
     * Store information of all webapp available on system
     *
     * DBWebappManager
     *
     * XmlWebappManager
     *
     *
     * app_path, app_id, controller_path, action_path
     *
     * app_path - is define by administrator when installer webapp app_id - is a
     * unique identifier defined at development time, shoul be full name package
     * with unique name cv.nosi.igrp.webapps.igrp_portal
     * cv.nosi.igrp.webapps.agenda cv.nosi.igrp.webapps.marcacao_consulta
     *
     * controller_path - default controller path action_path - default action
     * path
     *
     *
     * Since app_path it only know at runtime app should has await of get its
     * base path
     */
}

class IgrpPortalWebApp implements IGRPWebApp {

    //String cv.gov.minfinancas.taxpayment
    //String cv.gov.cmsal.humanrm
    String APP_ID = "cv.nosi.igrp.webapps.igrp_portal";

    //public String getAppID();
    //public String getDefaultController();
    //public String getDefaultAction();
    @Override
    public List<ControllerAction> getControllers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute(String controllerPath, String actionPath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
