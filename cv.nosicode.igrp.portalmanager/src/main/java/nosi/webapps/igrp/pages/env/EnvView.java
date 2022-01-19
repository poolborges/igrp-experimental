/*-------------------------*/

 /*Create View*/
package nosi.webapps.igrp.pages.env;

import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;

public class EnvView extends View {

    public Field sectionheader_1_text;
    public Field dad;
    public Field name;
    public Field description;
    public Field img_src;
    public Field action_fk;
    public Field host;
    public Field apache_dad;
    public Field link_menu;
    public Field link_center;
    public Field templates;
    public Field flg_old;
    public Field flg_external;
    public Field status;
    public Field id;
    public IGRPSectionHeader sectionheader_1;
    public IGRPForm form_1;

    public IGRPToolsBar toolsbar_1;
    public IGRPButton btn_gravar;
    public IGRPButton btn_voltar;

    public EnvView(Env model) {
        sectionheader_1 = new IGRPSectionHeader("sectionheader_1");
        form_1 = new IGRPForm("form_1");
        sectionheader_1_text = new TextField(model, "sectionheader_1_text");
        sectionheader_1_text.setLabel("");
        sectionheader_1_text.setValue("Gest�o de Aplica��o - Novo");
        sectionheader_1_text.propertie().add("type", "text").add("name", "p_sectionheader_1_text").add("persist", "true").add("maxlength", "4000");
        dad = new TextField(model, "dad");
        dad.setLabel("Schema");
        dad.propertie().add("name", "p_dad").add("type", "text").add("maxlength", "30").add("required", "true").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false");
        name = new TextField(model, "name");
        name.setLabel("Nome");
        name.propertie().add("name", "p_name").add("type", "text").add("maxlength", "50").add("required", "true").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false");
        description = new TextAreaField(model, "description");
        description.setLabel("Descri��o");
        description.propertie().add("name", "p_description").add("type", "textarea").add("maxlength", "500").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false");
        img_src = new TextField(model, "img_src");
        img_src.setLabel("Logotipo");
        img_src.propertie().add("name", "p_img_src").add("type", "text").add("maxlength", "50").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false");
        action_fk = new ListField(model, "action_fk");
        action_fk.setLabel("Primeira P�gina");
        action_fk.propertie().add("name", "p_action_fk").add("type", "select").add("multiple", "false").add("maxlength", "30").add("required", "false").add("change", "false").add("disabled", "false").add("right", "false");
        host = new TextField(model, "host");
        host.setLabel("Host");
        host.propertie().add("name", "p_host").add("type", "text").add("maxlength", "30").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false");
        apache_dad = new TextField(model, "apache_dad");
        apache_dad.setLabel("DAD");
        apache_dad.propertie().add("name", "p_apache_dad").add("type", "text").add("maxlength", "30").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false");
        link_menu = new TextField(model, "link_menu");
        link_menu.setLabel("Link Menu (Antigo)");
        link_menu.propertie().add("name", "p_link_menu").add("type", "text").add("maxlength", "2000").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false");
        link_center = new TextField(model, "link_center");
        link_center.setLabel("Link Centro (Antigo)");
        link_center.propertie().add("name", "p_link_center").add("type", "text").add("maxlength", "2000").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false");
        templates = new TextField(model, "templates");
        templates.setLabel("Template");
        templates.propertie().add("name", "p_templates").add("type", "text").add("maxlength", "30").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false");
        flg_old = new CheckBoxField(model, "flg_old");
        flg_old.setLabel("Antigo?");
        flg_old.propertie().add("name", "p_flg_old").add("type", "checkbox").add("maxlength", "30").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("right", "false").add("check", "true");
        flg_external = new CheckBoxField(model, "flg_external");
        flg_external.setLabel("Externo?");
        flg_external.propertie().add("name", "p_flg_external").add("type", "checkbox").add("maxlength", "30").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("right", "false").add("check", "true");
        status = new CheckBoxField(model, "status");
        status.setLabel("Ativo?");
        status.propertie().add("name", "p_status").add("type", "checkbox").add("maxlength", "30").add("required", "false").add("change", "false").add("readonly", "false").add("disabled", "false").add("right", "false").add("check", "true");
        id = new HiddenField(model, "id");

        toolsbar_1 = new IGRPToolsBar("toolsbar_1");
        btn_gravar = new IGRPButton("Gravar", "igrp", "env", "gravar", "submit", "default|fa-floppy-o", "", "");
        btn_gravar.propertie.add("type", "specific").add("code", "").add("rel", "gravar");
        btn_voltar = new IGRPButton("Voltar", "igrp", "env", "voltar", "_self", "default|fa-arrow-left", "", "");
        btn_voltar.propertie.add("type", "specific").add("code", "").add("rel", "voltar");

    }

    @Override
    public void render() {
        sectionheader_1.addField(sectionheader_1_text);

        form_1.addField(dad);
        form_1.addField(name);
        form_1.addField(description);
        form_1.addField(img_src);
        form_1.addField(action_fk);
        form_1.addField(host);
        form_1.addField(apache_dad);
        form_1.addField(link_menu);
        form_1.addField(link_center);
        form_1.addField(templates);
        form_1.addField(flg_old);
        form_1.addField(flg_external);
        form_1.addField(status);
        form_1.addField(id);

        toolsbar_1.addButton(btn_gravar);
        toolsbar_1.addButton(btn_voltar);
        this.addToPage(sectionheader_1);
        this.addToPage(form_1);
        this.addToPage(toolsbar_1);
    }
}
/*-------------------------*/
