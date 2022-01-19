/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.nosicode.igrpframework.osgi;

import org.osgi.service.component.annotations.Component;

/**
 *
 * @author pauloborges
 */
@Component(service = StaticResourceService.class,
        property = {
            "osgi.http.whiteboard.resource.pattern=/images/*",
            "osgi.http.whiteboard.resource.prefix=images/"})
public class StaticResourceService {
}
