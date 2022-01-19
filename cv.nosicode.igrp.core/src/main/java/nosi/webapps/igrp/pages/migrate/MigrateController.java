/*-------------------------*/

 /*Create Controller*/
package nosi.webapps.igrp.pages.migrate;

/*---- Import your packages here... ----*/
import nosi.core.igrp.mingrations.MigrationIGRP;
import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Response;
import nosi.core.webapp.helpers.IgrpHelper;
import nosi.webapps.igrp.dao.Application;
import nosi.core.config.AppConfig;
import nosi.core.config.Config;
import java.util.HashMap;
import java.util.Map;
import nosi.core.webapp.Igrp;

/*---- End ----*/
public class MigrateController extends Controller {

    public Response actionIndex() throws IOException {
        /*---- Insert your code here... ----*/
        Migrate model = new Migrate();
        MigrateView view = new MigrateView(model);
        tipos.put("mysql", "MySql");
        tipos.put("postgresql", "Postgresql");
        tipos.put("h2", "H2");
        tipos.put("oracle", "Oracle");
        view.tipo_base_dados.setValue(tipos);
        view.aplicacao.setValue(IgrpHelper.toMap(new Application().find().andWhere("id", "=", 1).all(), "id", "name", "--- Selecionar Aplicação ---"));
        return this.renderView(view);
        /*---- End ----*/
    }

    public Response actionMigrar() throws IOException, IllegalArgumentException, IllegalAccessException {
        /*---- Insert your code here... ----*/
        Migrate model = new Migrate();
        if (Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST")) {
            model.load();
            if (model.getAplicacao().equals("1")) {
                AppConfig appC = new AppConfig();
                appC.setAuthenticationType(Config.getAutenticationType());
                appC.setProject_loc(Config.getProject_loc());
                MigrationIGRP.start(appC, model);
            }
            Igrp.getInstance().getFlashMessage().addMessage("success", "Migração Efetuada com sucesso");
        }
        return this.redirect("igrp", "Migrate", "index");
        /*---- End ----*/
    }

    /*---- Insert your actions here... ----*/
    private static Map<String, String> tipos = new HashMap<>();
    /*---- End ----*/
}
