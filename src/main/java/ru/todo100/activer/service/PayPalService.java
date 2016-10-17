package ru.todo100.activer.service;

import com.paypal.base.rest.APIContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface PayPalService {
    String getAccessToken();
    APIContext getApiContext();
    String getServerName(final HttpServletRequest request);
}
