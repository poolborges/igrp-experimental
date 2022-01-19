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
package cv.nosicode.igrpframework.osgi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.servlet.Filter;
import javax.servlet.Servlet;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 *
 * @author pauloborges
 */
public class IGRPActivator implements BundleActivator {

    private static final int NUM_SERVICES = 2;
    private ServiceRegistration<Servlet> serviceRef, serviceRef1;
    private ServiceRegistration<Filter> filre;

    @Override
    public void start(BundleContext context) throws Exception {
        // TODO add activation code here
        serviceRef = context.registerService(javax.servlet.Servlet.class, new nosi.core.servlet.IgrpServlet(), getIGRPServletParams());
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
        serviceRef.unregister();
        serviceRef1.unregister();
    }

    private Dictionary<String, String> getServletContextHelperParams() {
        Dictionary<String, String> params = new Hashtable<>();
        params.put("osgi.http.whiteboard.context.path", "/");
        params.put("osgi.http.whiteboard.context.name", "igrp-context");
        return params;
    }

    private Dictionary<String, String> getIGRPCommonWBParams() {
        Dictionary<String, String> params = new Hashtable<>();
        params.put("osgi.http.whiteboard.context.select", "/");
        params.put("osgi.http.whiteboard.context.select", "igrp-context");
        return params;
    }

    private Dictionary<String, String> getIGRPServletParams() {
        Dictionary<String, String> params = new Hashtable<>();
        params.put("osgi.http.whiteboard.servlet.name", "IGRPServlet");
        params.put("osgi.http.whiteboard.servlet.pattern", "/app/*");
        params.put("servlet.init.authentication_type", "db");
        params.put("servlet.init.env", "dev");
        return params;
    }

    private Dictionary<String, String> getModifyParamsFilterParams() {
        Dictionary<String, String> params = new Hashtable<>();
        params.put("osgi.http.whiteboard.filter.servlet", "IGRPServlet");
        params.put("osgi.http.whiteboard.filter.dispatcher", "REQUEST,FORWARD,ERROR");
        params.put("osgi.http.whiteboard.filter.name", "ModifyParamsFilter");
        params.put("servlet.init.env", "dev");
        return params;
    }

}
