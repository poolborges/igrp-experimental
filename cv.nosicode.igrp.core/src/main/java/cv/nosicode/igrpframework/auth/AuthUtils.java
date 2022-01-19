/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.nosicode.igrpframework.auth;

import nosi.core.webapp.Identity;
import nosi.core.webapp.Igrp;

/**
 *
 * @author pauloborges
 */
public final class AuthUtils {

    //TODO 
    public static boolean isAuthenticated(Identity identity) {
        /*
        String aux = (String) Igrp.getInstance().getRequest().getSession().getAttribute("_identity");
        int identityId = Integer.parseInt(aux != null && !aux.equals("") ? aux : "0");
        nosi.webapps.igrp.dao.User user = new nosi.webapps.igrp.dao.User();
        user = user.findIdentityById(identityId);
        try {
            if (user != null && user.getId() != 0) {
                identity = (Identity) user;
                return true;
            }
        } catch (NullPointerException e) {
        }
        return false;
         */
        return false;
    }

    public static boolean isAuthorized(Identity identity) {

        return false;
    }

    public static void checkPermission()/*TODO throws UnathorizedException */ {
    }
}
