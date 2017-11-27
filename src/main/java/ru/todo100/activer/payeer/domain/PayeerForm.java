package ru.todo100.activer.payeer.domain;

public class PayeerForm {
    private String shop;
    private String key;
    private Integer order;
    private Integer amount;
    private String desc;
    private String curr;

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(final Integer order) {
        this.order = order;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(final Integer amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(final String desc) {
        this.desc = desc;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(final String shop) {
        this.shop = shop;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }
}
