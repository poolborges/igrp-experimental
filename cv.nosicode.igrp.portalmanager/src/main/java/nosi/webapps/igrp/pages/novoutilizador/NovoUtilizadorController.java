/*-------------------------*/

 /*Create Controller*/
package nosi.webapps.igrp.pages.novoutilizador;

/*---- Import your packages here... ----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.Response;
import nosi.webapps.igrp.dao.Application;
import nosi.webapps.igrp.dao.Organization;
import nosi.webapps.igrp.dao.Profile;
import nosi.webapps.igrp.dao.ProfileType;
import nosi.webapps.igrp.dao.User;
import java.io.IOException;

/*---- End ----*/
public class NovoUtilizadorController extends Controller {

    public Response actionIndex() throws IOException {
        NovoUtilizador model = new NovoUtilizador();
        NovoUtilizadorView view = new NovoUtilizadorView(model);
        view.aplicacao.setValue(new Application().getListApps());
        view.organica.setValue(new Organization().getListOrganizations());
        view.perfil.setValue(new ProfileType().getListProfiles());
        String id = Igrp.getInstance().getRequest().getParameter("id");
        if (id != null && !id.equals("")) {
            User u = (User) new User().findIdentityById(Integer.parseInt(id));
            view.email.setValue(u.getEmail());
        }
        return this.renderView(view);
    }

    public Response actionGravar() throws IOException, IllegalArgumentException, IllegalAccessException {
        NovoUtilizador model = new NovoUtilizador();
        if (Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST")) {
            model.load();
            Profile p = new Profile();
            p.setOrganization(new Organization().findOne(model.getOrganica()));
            p.setProfileType(new ProfileType().findOne(model.getPerfil()));
            p.setType("PROF");
            User u = new User().find().andWhere("email", "=", model.getEmail()).one();
            if (u != null) {
                p.setUser(u);
                p.setType_fk(model.getPerfil());
                p = p.insert();
                if (p != null) {
                    p = new Profile();
                    p.setUser(u);
                    p.setOrganization(new Organization().findOne(model.getOrganica()));
                    p.setProfileType(new ProfileType().findOne(model.getPerfil()));
                    p.setType("ENV");
                    p.setType_fk(model.getAplicacao());
                    p = p.insert();
                    if (p != null) {
                        Igrp.getInstance().getFlashMessage().addMessage("success", "Operação efetuada com sucesso");
                    } else {
                        Igrp.getInstance().getFlashMessage().addMessage("error", "Falha ao tentar efetuar esta opera��o");
                    }
                } else {
                    Igrp.getInstance().getFlashMessage().addMessage("error", "Falha ao tentar efetuar esta opera��o");
                }
            } else {
                Igrp.getInstance().getFlashMessage().addMessage("error", "Email inv�lido");
            }
        }
        return this.redirect("igrp", "novo-utilizador", "index");
    }

}
