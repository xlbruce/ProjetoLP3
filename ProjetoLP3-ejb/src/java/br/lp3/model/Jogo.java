package br.lp3.model;

import java.io.Serializable;

/**
 * Essa classe representa um jogo registrado na Steam.
 * No contexto da API, cada jogo é um "appid"
 * @author bruce
 */
public class Jogo implements Serializable {
    private int appid;
    private String name;

    public Jogo() {
    }

    public Jogo(int appid, String name) {
        this.appid = appid;
        this.name = name;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Jogo{" + "appid=" + appid + ", name=" + name + '}';
    }
}
