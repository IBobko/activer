package ru.todo100.activer.form;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PagedForm {
    private Integer page = 0;
    private String orderType;
    private String orderField;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
