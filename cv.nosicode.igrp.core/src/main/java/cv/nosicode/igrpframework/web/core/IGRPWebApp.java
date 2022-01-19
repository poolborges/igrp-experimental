/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.nosicode.igrpframework.web.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import nosi.core.webapp.Response;

/**
 *
 * @author pauloborges
 */
public interface IGRPWebApp {

    List<ControllerAction> getControllers();

    public void execute(String controllerPath, String actionPath);

    interface Controller {
    }

    @FunctionalInterface
    public interface ActionResponseFunction<T, Response> {

        /**
         * Gets a Response for the {@code item}.
         *
         * @param item the item to process action
         * @return the response of the item;
         * @throws java.lang.Exception
         */
        Response apply(T item) throws Exception;
    }

    public class ControllerAction<T> {

        private final T instance;

        String controllerPath;

        Map<String, ActionResponseFunction<T, Response>> mapping = new HashMap<>();

        public ControllerAction(T instance) {
            this.instance = instance;
        }

        public void addAction(String path, ActionResponseFunction<T, Response> actionMethod) {
            mapping.put(path, actionMethod);
        }

        public Response executeAction(String path) {
            return getAction(path);
        }

        /**
         * TODO MUST provide better implementation. SHOULD return Optional or
         * throw an exception
         *
         * @param path
         * @return
         */
        public Response getAction(String path) {
            Response res = null;
            if (mapping.containsKey(path)) {
                try {
                    res = mapping.get(path).apply(instance);
                } catch (Exception ex) {
                    Logger.getLogger(IGRPWebApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //NOT FOUND - TODO MUST provide better implementation return Optional or throw exception
            }
            return res;
        }
    }

}
