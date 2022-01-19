/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.nosi.igrp.osgi;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.context.ServletContextHelper;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

/**
 *
 * @author pauloborges
 */
/*
@Component(
        service = ServletContextHelper.class,
        property = {
                HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME + "=" + OsgiIgrpServletContextHelper.NAME,
                HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_PATH + "=/",
                HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_INIT_PARAM_PREFIX + "workspace=./",
                HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_INIT_PARAM_PREFIX + "images=/"
        }
)
*/
public class OsgiIgrpServletContextHelper extends ServletContextHelper {

    public final static String NAME = "IGRP_CONTEXT";
}
