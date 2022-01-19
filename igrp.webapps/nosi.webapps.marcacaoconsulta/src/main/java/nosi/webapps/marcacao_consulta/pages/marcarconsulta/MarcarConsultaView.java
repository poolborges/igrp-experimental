/*-------------------------*/

 /*Create View*/
package nosi.webapps.marcacao_consulta.pages.marcarconsulta;

import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;

public class MarcarConsultaView extends View {

    public Field utente;
    public Field medico;
    public Field especialidade;
    public Field data_consulta;
    public IGRPForm form_1;

    public IGRPToolsBar toolsbar_1;
    public IGRPButton btn_gravar;
    public IGRPButton btn_listar_medico;

    public MarcarConsultaView(MarcarConsulta model) {
        this.setPageTitle("Marcar Consulta");

        form_1 = new IGRPForm("form_1");
        utente = new ListField(model, "utente");
        utente.setLabel("Utente");
        utente.propertie().add("name", "p_utente").add("type", "select").add("multiple", "false").add("maxlength", "30").add("required", "true").add("change", "false").add("disabled", "false").add("right", "false").add("domain", "");
        medico = new ListField(model, "medico");
        medico.setLabel("Medico");
        medico.propertie().add("name", "p_medico").add("type", "select").add("multiple", "false").add("maxlength", "30").add("required", "true").add("change", "false").add("disabled", "false").add("right", "false").add("domain", "");
        especialidade = new ListField(model, "especialidade");
        especialidade.setLabel("Especialidade");
        especialidade.propertie().add("name", "p_especialidade").add("type", "select").add("multiple", "false").add("domain", "").add("maxlength", "30").add("required", "true").add("change", "false").add("disabled", "false").add("right", "false");
        data_consulta = new DateField(model, "data_consulta");
        data_consulta.setLabel("Data Consulta");
        data_consulta.propertie().add("name", "p_data_consulta").add("type", "date").add("format", "IGRP_dateTimePicker").add("maxlength", "30").add("required", "true").add("change", "false").add("readonly", "false").add("disabled", "false").add("placeholder", "").add("right", "false").add("class", "default");

        toolsbar_1 = new IGRPToolsBar("toolsbar_1");
        btn_gravar = new IGRPButton("Gravar", "marcacao_consulta", "MarcarConsulta", "gravar", "submit", "success|fa-save", "", "");
        btn_gravar.propertie.add("type", "specific").add("code", "").add("rel", "gravar");
        btn_listar_medico = new IGRPButton("Listar Consultas", "marcacao_consulta", "MarcarConsulta", "listar_medico", "_self", "default|fa-list", "", "");
        btn_listar_medico.propertie.add("type", "specific").add("code", "").add("rel", "listar_medico");

    }

    @Override
    public void render() {

        form_1.addField(utente);
        form_1.addField(medico);
        form_1.addField(especialidade);
        form_1.addField(data_consulta);

        toolsbar_1.addButton(btn_gravar);
        toolsbar_1.addButton(btn_listar_medico);
        this.addToPage(form_1);
        this.addToPage(toolsbar_1);
    }
}
/*-------------------------*/
