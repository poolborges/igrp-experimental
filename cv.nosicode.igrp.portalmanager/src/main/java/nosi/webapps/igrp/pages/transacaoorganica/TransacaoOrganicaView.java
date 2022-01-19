/*-------------------------*/

 /*Create View*/
package nosi.webapps.igrp.pages.transacaoorganica;

import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import nosi.core.config.Config;

public class TransacaoOrganicaView extends View {

    public String title = "Permissao sobre transacoes";

    public Field transacao;
    public Field transacao_check;
    public Field descricao;
    public IGRPTable table_1;

    public IGRPToolsBar toolsbar_1;
    public IGRPButton btn_gravar;
    public IGRPButton btn_voltar;

    public TransacaoOrganicaView(TransacaoOrganica model) {
        table_1 = new IGRPTable("table_1");
        transacao = new CheckBoxField(model, "transacao");
        transacao.setLabel("Transacao");
        transacao.propertie().add("name", "p_transacao").add("type", "checkbox").add("maxlength", "30").add("align", "left").add("lookup_parser", "false").add("check", "true").add("desc", "true");
        transacao_check = new CheckBoxField(model, "transacao_check");
        transacao_check.propertie().add("name", "p_transacao").add("type", "checkbox").add("maxlength", "30").add("align", "left").add("lookup_parser", "false").add("check", "true").add("desc", "true");
        descricao = new TextField(model, "descricao");
        descricao.setLabel("Descricao");
        descricao.propertie().add("name", "p_descricao").add("type", "text").add("maxlength", "30").add("align", "left").add("lookup_parser", "false");

        toolsbar_1 = new IGRPToolsBar("toolsbar_1");
        btn_gravar = new IGRPButton("Gravar", "igrp", "TransacaoOrganica", "gravar&amp;id=" + model.getId() + "&amp;type=" + model.getType(), "submit", "default|fa-save", "", "");
        btn_gravar.propertie.add("type", "specific").add("code", "").add("rel", "gravar");
        btn_voltar = new IGRPButton("Voltar", "igrp", "TransacaoOrganica", "voltar", "_back", "default|fa-angle-right", "", "");
        btn_voltar.propertie.add("type", "specific").add("code", "").add("rel", "voltar");

    }

    @Override
    public void render() {
        Config.TITLE = this.title;

        table_1.addField(transacao);
        table_1.addField(transacao_check);
        table_1.addField(descricao);

        toolsbar_1.addButton(btn_gravar);
        toolsbar_1.addButton(btn_voltar);
        this.addToPage(table_1);
        this.addToPage(toolsbar_1);
    }
}
/*-------------------------*/
