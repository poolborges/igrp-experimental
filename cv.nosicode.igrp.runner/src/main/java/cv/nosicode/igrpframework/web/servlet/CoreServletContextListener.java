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
package cv.nosicode.igrpframework.web.servlet;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import org.jolokia.http.AgentServlet;

/**
 *
 * @author pauloborges
 */
@WebListener
public class CoreServletContextListener implements ServletContextListener {

    public static final Logger LOGGER = Logger
            .getLogger(CoreServletContextListener.class.getName());

    private String urlPatterns = "/jolokia/*";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.log(Level.INFO, "ContextListner Onstarting...");
        ServletRegistration servletRegistration = servletContextEvent
                .getServletContext().addServlet(urlPatterns, AgentServlet.class);
        servletRegistration.addMapping(urlPatterns);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.log(Level.INFO, "ContextListner Onstopping...");
    }
}
