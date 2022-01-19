/*-------------------------*/

 /*Create Controller*/
package nosi.webapps.igrp.pages.novomenu;

/*---- Import your packages here... ----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.FlashMessage;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.RParam;
import nosi.core.webapp.Response;
import nosi.webapps.igrp.dao.Action;
import nosi.webapps.igrp.dao.Application;
import nosi.webapps.igrp.dao.Menu;

import java.io.IOException;
import java.util.HashMap;

/*---- End ----*/
public class NovoMenuController extends Controller {

    public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
        NovoMenu model = new NovoMenu();
        NovoMenuView view = new NovoMenuView(model);
        HashMap<String, String> targets = new HashMap<>();
        targets.put(null, "--- Selecionar Target ---");
        targets.put("_self", "Mesma p�gina");
        targets.put("target", "Popup");
        targets.put("submit", "Submit");
        targets.put("confirm", "Confirm");
        view.env_fk.setValue(new Application().getListApps()); // Prompt
        view.action_fk.setValue(new Action().getListActions());
        view.self_id.setValue(new Menu().getListPrincipalMenus());
        view.target.setValue(targets); // prompt

        return this.renderView(view);
    }

    public Response actionGravar() throws IOException, IllegalArgumentException, IllegalAccessException {
        NovoMenu model = new NovoMenu();
        if (Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST")) {
            model.load();
            Menu menu = new Menu();
            if (model.getAction_fk() != 0) {
                menu.setAction(new Action().findOne(model.getAction_fk()));
            }
//			menu.setArea(model.getP_area());
//			menu.setCode(model.getCode());
            menu.setDescr(model.getDescr());
            menu.setApplication(new Application().findOne(model.getEnv_fk()));
            menu.setFlg_base(model.getFlg_base());
//			menu.setImg_src(model.getP_img_src());
//			menu.setLink(model.getLink());
            menu.setOrderby(model.getOrderby());
            if (model.getSelf_id() != 0) {
                menu.setMenu(new Menu().findOne(model.getSelf_id()));
            }
            menu.setStatus(model.getStatus());
            menu.setTarget(model.getTarget());
            //menu.setAction_fk(model.getPagina());
            menu = menu.insert();
            if (menu != null) {
                Igrp.getInstance().getFlashMessage().addMessage("success", "Operação efetuada com sucesso");
            } else {
                Igrp.getInstance().getFlashMessage().addMessage("error", "Falha ao tentar efetuar esta opera��o");
            }
        }
        return this.redirect("igrp", "novo-menu", "index");
    }

    public Response actionEditar(@RParam(rParamName = "p_id") String id_menu) throws IOException, IllegalArgumentException, IllegalAccessException {

        Menu menu = new Menu().findOne(Integer.parseInt(id_menu));
        NovoMenu model = new NovoMenu();
//			model.setCode(menu_db.getCode());
        if (menu.getMenu() != null) {
            model.setSelf_id(menu.getMenu().getId());
        }
        model.setStatus(menu.getStatus());
        model.setFlg_base(menu.getFlg_base());
        model.setEnv_fk(menu.getApplication().getId());
        model.setTarget(menu.getTarget());
        if (menu.getAction() != null) {
            model.setAction_fk(menu.getAction().getId());
        }
        model.setOrderby(menu.getOrderby());
        model.setDescr(menu.getDescr());

        if (Igrp.getInstance().getRequest().getMethod().equals("POST")) {
            model.load();
            System.out.println(id_menu);
            if (model.getAction_fk() != 0) {
                menu.setAction(new Action().findOne(model.getAction_fk()));
            }
//				menu.setArea(model.getP_area());
//				menu.setCode(model.getCode());
            menu.setDescr(model.getDescr());
            menu.setApplication(new Application().findOne(model.getEnv_fk()));
            menu.setFlg_base(model.getFlg_base());
//				menu.setImg_src(model.getP_img_src());
//				menu.setLink(model.getLink());
            menu.setOrderby(model.getOrderby());
            if (model.getSelf_id() != 0) {
                menu.setMenu(new Menu().findOne(model.getSelf_id()));
            }
            menu.setStatus(model.getStatus());
            menu.setTarget(model.getTarget());
            //menu.setAction_fk(model.getPagina());
            menu = menu.update();
            if (menu != null) {
                Igrp.getInstance().getFlashMessage().addMessage(FlashMessage.SUCCESS, "Menu atualizado com sucesso.");
                return this.redirect("igrp", "novo-menu", "editar", new String[]{"p_id"}, new String[]{menu.getId() + ""});
            } else {
                Igrp.getInstance().getFlashMessage().addMessage(FlashMessage.ERROR, "Erro ao atualizar menu.");
            }

        }

        NovoMenuView view = new NovoMenuView(model);
        HashMap<String, String> targets = new HashMap<>();
        targets.put(null, "--- Selecionar Target ---");
        targets.put("_self", "Mesma p�gina");
        targets.put("target", "Popup");
        targets.put("submit", "Submit");
        targets.put("confirm", "Confirm");
        view.target.setValue(targets);

        //view.env_fk.setValue(new Application().getListAppsOne(Integer.parseInt(id_menu)));
        view.env_fk.setValue(new Application().getListApps());
        view.action_fk.setValue(new Action().getListActions());
        view.self_id.setValue(new Menu().getListPrincipalMenus());
        view.btn_gravar.setLink("editar&p_id=" + id_menu);
        view.sectionheader_1_text.setValue("Gest�o Menu - Atualizar");

        return this.renderView(view);

    }

    public Response actionVoltar() throws IOException {
        return this.redirect("igrp", "pesquisar-menu", "index");
    }

}
