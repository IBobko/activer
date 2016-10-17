package ru.todo100.activer.qualifier;

import java.util.HashMap;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class Qualifier {
    private Order order;
    private String orderName;
    private Integer start;
    private Integer count;
    private HashMap<String, Object> params;

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public enum Order {
        asc, desc
    }
}
