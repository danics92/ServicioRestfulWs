package Pojo;

import java.util.Date;

/**
 * Created by dcatalans on 31/01/17.
 */
public class Fitxatge {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public TipusFitxatge getTipus() {
        return tipus;
    }

    public void setTipus(TipusFitxatge tipus) {
        this.tipus = tipus;
    }

    private Date hora;
    private TipusFitxatge tipus;
}
