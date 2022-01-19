/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.nosicode.igrpframework.osgi;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.http.context.ServletContextHelper;

/**
 *
 * @author pauloborges
 */
@Component(service = ServletContextHelper.class,
        scope = ServiceScope.BUNDLE,
        property = {
            "osgi.http.whiteboard.context.name=igrp-context",
            "osgi.http.whiteboard.context.path=/"})
public class IGRPServletContextHelper extends ServletContextHelper {

    /*
    @Override
    public URL getResource(String name) {
        try {
            return new URL("http://acmecdn.com/assets-vendor/" + name);
        } catch (MalformedURLException e) {
            return null;
        }
    }
     */
}
