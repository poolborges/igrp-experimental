/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.nosi.igrp.osgi;

import javax.servlet.Servlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

/**
 *
 * @author pauloborges
 */
/*
@Component(
        service = Servlet.class,
        property = {
            HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX + "authentication_type=db",
            HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX + "env=dev",
            HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_NAME+ "=IGRP",
            HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN + "=/app/*",
            HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT + "=(" + HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME + "=" + OsgiIgrpServletContextHelper.NAME + ")"})
*/
public class OsgiIgrpServlet extends nosi.core.servlet.IgrpServlet {}
