package ru.todo100.activer.service;

import com.paypal.base.rest.APIContext;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface PayPalService {
    String getAccessToken();
    APIContext getApiContext();
}
