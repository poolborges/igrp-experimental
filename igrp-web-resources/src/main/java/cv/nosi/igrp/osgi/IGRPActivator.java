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
package cv.nosi.igrp.osgi;

import java.util.Dictionary;
import java.util.Hashtable;
import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestListener;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.context.ServletContextHelper;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

/**
 *
 * @author pauloborges
 */
public class IGRPActivator implements BundleActivator {
    
    public final static String IGRP_CONTEXT_NAME = "IGRP_CONTEXT";

    private static final int NUM_SERVICES = 2;
    private ServiceRegistration<Servlet> serviceRef, serviceRef1;
    private ServiceRegistration<Filter> filterRef;
    private ServiceRegistration<ServletContextHelper> ctxhRef;
    private ServiceRegistration<IGRPResourceService> resourceRef;
    private ServiceRegistration<ServletContextListener> ctxRef;
    private ServiceRegistration<ServletRequestListener> reqRef;

    @Override
    public void start(BundleContext context) throws Exception {

        //CONTEXT_HELPER
        ctxhRef = context.registerService(ServletContextHelper.class, new IGRPServletContextHelper(), getServletContextHelperParams());

        //WHITEBOARD RESOURCE
        resourceRef = context.registerService(IGRPResourceService.class, new IGRPResourceService(), getIgrpResourcesParams());

        //LISTENER 
        ctxRef = context.registerService(ServletContextListener.class, new nosi.core.listener.BasicListener(), null);
        reqRef = context.registerService(ServletRequestListener.class, new nosi.core.listener.ThreadLocalListener(), null);

        //SERVLET
        serviceRef = context.registerService(javax.servlet.Servlet.class, new nosi.core.servlet.IgrpServlet(), getIGRPServletParams());
        serviceRef1 = context.registerService(javax.servlet.Servlet.class, new nosi.core.servlet.IgrpOAuth2SSO(), getIgrpOAuth2SSOServletParams());

        //FILTER
        filterRef = context.registerService(Filter.class, new nosi.core.filter.ModifyParams(), getModifyParamsFilterParams());

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        
        //SERVLET
        serviceRef.unregister();
        serviceRef1.unregister();
        
        // FILTER
        filterRef.unregister();
        
        //LISTENER
        ctxRef.unregister();
        reqRef.unregister();
        
        //RESOURCE
        resourceRef.unregister();
        
        //CONTEXT_HELPER
        ctxhRef.unregister();
    }

    private Dictionary<String, String> getIgrpResourcesParams() {
        Dictionary<String, String> params = new Hashtable<>();
        params.put("osgi.http.whiteboard.resource.pattern", "/files");
        params.put("osgi.http.whiteboard.resource.prefix", "/images");
        return params;
    }

    private Dictionary<String, String> getServletContextHelperParams() {
        Dictionary<String, String> params = new Hashtable<>();
        params.put("osgi.http.whiteboard.context.path", "/");
        params.put("osgi.http.whiteboard.context.name", IGRP_CONTEXT_NAME);
        params.put("context.init.workspace", "");
        params.put("context.init.images", "/"); //PARA LIMPAR CONFIGURATION - PROVAVELMENT DEVIDO AO PROXY
        return params;
    }

    private Dictionary<String, String> getIGRPServletParams() {
        Dictionary<String, String> params = new Hashtable<>();
        params.put("osgi.http.whiteboard.servlet.name", "IGRP");
        params.put("osgi.http.whiteboard.servlet.pattern", "/app/*");
        params.put("servlet.init.env", "dev");
        params.put("servlet.init.authentication_type", "dev");
        params.put(HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT , "(" + HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME + "=" + IGRP_CONTEXT_NAME + ")");
        return params;
    }

    private Dictionary<String, String> getIgrpOAuth2SSOServletParams() {
        Dictionary<String, String> params = new Hashtable<>();
        params.put("osgi.http.whiteboard.servlet.name", "igrpsso");
        // params.put("osgi.http.whiteboard.servlet.pattern", "/app/*");
        //params.put("servlet.init.env", "dev");
        //params.put("servlet.init.authentication_type", "dev");
        return params;
    }

    private Dictionary<String, String> getModifyParamsFilterParams() {
        Dictionary<String, String> params = new Hashtable<>();
        params.put("osgi.http.whiteboard.filter.servlet", "IGRP");
        params.put("osgi.http.whiteboard.filter.dispatcher", "REQUEST,FORWARD,ERROR");
        params.put("osgi.http.whiteboard.filter.name", "ModifyParamsFilter");
        params.put("filter.init.encoding", "UTF-8");
        return params;
    }

    public class IGRPResourceService {
    }
    
    public class IGRPServletContextHelper extends ServletContextHelper {}
}
