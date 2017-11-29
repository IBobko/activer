package ru.todo100.activer.payeer.domain;

public class PayeerForm {
    /**
     * Идентификатор мерчанта зарегистрированного в системе Payeer на который будет совершен платеж.
     */
    private String shop;
    /**
     * Секретный ключ из настроек мерчанта.
     */
    private String key;
    /**
     * В этом поле продавец задает идентификатор покупки в соответствии со своей системой учета. Желательно использовать уникальный номер для каждого платежа.
     * Идентификатор должен представлять собой любую строку длиной не больше 32 символов из символов: "A-z", "_", "0-9".
     */
    private Integer order;
    /**
     * Сумма платежа, которую продавец желает получить от покупателя.
     * Сумма должна быть больше нуля, дробная часть отделяется точкой, количество знаков после точки - два знака
     */
    private String amount;
    /**
     * Описание товара или услуги. Формируется продавцом. Строка добавляется в назначение платежа.
     * Кодируется алгоритмом base64.
     * Пример: dGVzdA==
     */
    private String desc;
    /**
     * Валюта платежа
     * Возможные валюты: USD, EUR, RUB
     */
    private String curr;
    /**
     * Контрольная подпись, которая используется для проверки целостности полученной информации и однозначной идентификации отправителя
     */
    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(final String amount) {
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
