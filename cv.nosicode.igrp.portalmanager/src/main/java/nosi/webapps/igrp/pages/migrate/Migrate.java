/*---------------------- Create Model ----------------------*/
package nosi.webapps.igrp.pages.migrate;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

public class Migrate extends Model {

    @RParam(rParamName = "p_aplicacao")
    private String aplicacao;
    @RParam(rParamName = "p_tipo_base_dados")
    private String tipo_base_dados;
    @RParam(rParamName = "p_hostname")
    private String hostname;
    @RParam(rParamName = "p_port")
    private int port;
    @RParam(rParamName = "p_nome_de_bade_dados")
    private String nome_de_bade_dados;
    @RParam(rParamName = "p_username")
    private String username;
    @RParam(rParamName = "p_password")
    private String password;

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
    }

    public String getAplicacao() {
        return this.aplicacao;
    }

    public void setTipo_base_dados(String tipo_base_dados) {
        this.tipo_base_dados = tipo_base_dados;
    }

    public String getTipo_base_dados() {
        return this.tipo_base_dados;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public void setNome_de_bade_dados(String nome_de_bade_dados) {
        this.nome_de_bade_dados = nome_de_bade_dados;
    }

    public String getNome_de_bade_dados() {
        return this.nome_de_bade_dados;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

}
/*-------------------------*/
