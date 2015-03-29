package com.webacademy.march.api.model;

import java.io.Serializable;

/**
 * Created by collos on 29.03.15.
 */
public class Exchange implements Serializable {

    //https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5

    /**
     * Код валюты (справочник кодов валют: https://ru.wikipedia.org/wiki/Коды_валют)
     */
    private String ccy;
    /**
     * Код национальной валюты
     */
    private String base_ccy;
    /**
     * Курс покупки
     */
    private Float buy;
    /**
     * Курс продажи
     */
    private Float sale;


    public String getCcy() {
        return ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public Float getBuy() {
        return buy;
    }

    public Float getSale() {
        return sale;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Exchange{");
        sb.append("ccy='").append(ccy).append('\'');
        sb.append(", base_ccy='").append(base_ccy).append('\'');
        sb.append(", buy=").append(buy);
        sb.append(", sale=").append(sale);
        sb.append('}');
        return sb.toString();
    }
}
