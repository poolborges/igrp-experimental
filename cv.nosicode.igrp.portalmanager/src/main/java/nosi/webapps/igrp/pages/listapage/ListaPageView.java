/*-------------------------*/

 /*Create View*/
package nosi.webapps.igrp.pages.listapage;

import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import nosi.core.config.Config;

public class ListaPageView extends View {

    public String title = "Lista de P�ginas";

    public Field env_fk;
    public Field page;
    public Field page_descr;
    public Field nome_page;
    public Field descricao_page;
    public Field versao_page;
    public Field status_page;
    public Field status_page_check;
    public Field id;
    public IGRPForm form_1;
    public IGRPTable table_1;

    public IGRPToolsBar toolsbar_1;
    public IGRPButton btn_novo;
    public IGRPButton btn_pesquisar;
    public IGRPButton btn_editar;
    public IGRPButton btn_eliminar;
    public IGRPButton btn_visualizar;
    public IGRPButton btn_gerar;

    public ListaPageView(ListaPage model) {
        form_1 = new IGRPForm("form_1");
        table_1 = new IGRPTable("table_1");
        env_fk = new ListField(model, "env_fk");
        env_fk.setLabel("Aplica��o");
        env_fk.propertie().add("name", "p_env_fk").add("type", "select").add("multiple", "false").add("maxlength", "30").add("required", "false").add("change", "false").add("disabled", "false").add("right", "false");
        page = new TextField(model, "page");
        page.setLabel("Nome P�gina");
        page.propertie().add("name", "p_page").add("type", "text").add("maxlength", "30").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false");
        page_descr = new TextField(model, "page_descr");
        page_descr.setLabel("Descri��o P�gina");
        page_descr.propertie().add("name", "p_page_descr").add("type", "text").add("maxlength", "30").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false");
        nome_page = new TextField(model, "nome_page");
        nome_page.setLabel("Nome P�gina");
        nome_page.propertie().add("name", "p_nome_page").add("type", "text").add("maxlength", "30").add("align", "left").add("lookup_parser", "false");
        descricao_page = new TextField(model, "descricao_page");
        descricao_page.setLabel("Descri��o da P�gina");
        descricao_page.propertie().add("name", "p_descricao_page").add("type", "text").add("maxlength", "30").add("align", "left").add("lookup_parser", "false");
        versao_page = new TextField(model, "versao_page");
        versao_page.setLabel("Vers�o");
        versao_page.propertie().add("name", "p_versao_page").add("type", "text").add("maxlength", "30").add("align", "left").add("lookup_parser", "false");
        status_page = new CheckBoxField(model, "status_page");
        status_page.setLabel("Ativo?");
        status_page.propertie().add("name", "p_status_page").add("type", "checkbox").add("maxlength", "30").add("align", "left").add("lookup_parser", "false").add("check", "true").add("desc", "true");
        status_page_check = new CheckBoxField(model, "status_page_check");
        status_page_check.setLabel("Ativo?");
        status_page_check.propertie().add("name", "p_status_page").add("type", "checkbox").add("maxlength", "30").add("align", "left").add("lookup_parser", "false").add("check", "true").add("desc", "true");
        id = new HiddenField(model, "id");
        id.propertie().add("name", "p_id").add("type", "hidden").add("maxlength", "30").add("tag", "p_id");

        toolsbar_1 = new IGRPToolsBar("toolsbar_1");
        btn_novo = new IGRPButton("Novo", "igrp", "page", "index", "_self", "success|fa-plus-square", "", "");
        btn_novo.propertie.add("type", "specific").add("code", "").add("rel", "novo");
        btn_pesquisar = new IGRPButton("Pesquisar", "igrp", "lista-page", "index", "submit", "default|fa-search", "", "");
        btn_pesquisar.propertie.add("type", "form").add("code", "").add("class", "default").add("rel", "pesquisar");

        btn_editar = new IGRPButton("Editar", "igrp", "page", "editar", "_self", "warning|fa-pencil", "", "");
        btn_editar.propertie.add("type", "specific").add("code", "").add("rel", "editar");

        btn_eliminar = new IGRPButton("Eliminar", "igrp", "page", "eliminar", "confirm", "danger|fa-trash", "", "");
        btn_eliminar.propertie.add("type", "specific").add("code", "").add("rel", "eliminar");

        btn_visualizar = new IGRPButton("Visualizar", "igrp", "page", "visualizar", "_target", "primary|fa-eye", "", "");
        btn_visualizar.propertie.add("type", "specific").add("code", "").add("rel", "visualizar");

        btn_gerar = new IGRPButton("Gerar Pagina", "igrp", "generator", "index", "_target", "link|fa-gears", "", "");
        btn_gerar.propertie.add("type", "specific").add("code", "").add("rel", "gerar");

    }

    @Override
    public void render() {
        Config.TITLE = this.title;

        form_1.addField(env_fk);
        form_1.addField(page);
        form_1.addField(page_descr);

        table_1.addField(nome_page);
        table_1.addField(descricao_page);
        table_1.addField(versao_page);
        table_1.addField(status_page);
        table_1.addField(status_page_check);
        table_1.addField(id);

        toolsbar_1.addButton(btn_novo);
        form_1.addButton(btn_pesquisar);

        table_1.addButton(btn_editar);
        table_1.addButton(btn_eliminar);
        table_1.addButton(btn_visualizar);
        table_1.addButton(btn_gerar);

        this.addToPage(form_1);
        this.addToPage(table_1);
        this.addToPage(toolsbar_1);
    }
}
/*-------------------------*/
