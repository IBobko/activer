package ru.todo100.activer.service.impl;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import ru.todo100.activer.service.PayPalService;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PayPalServiceImpl implements PayPalService {

    public APIContext getApiContext() {
        final String accessToken = getAccessToken();
        if (accessToken == null) return null;
        return new APIContext(accessToken);
    }

    public String getAccessToken(){
        final InputStream is = getClass().getResourceAsStream("/sdk_config.properties");

        final Properties prop = new Properties();
        try {
            prop.load(is);
            OAuthTokenCredential tokenCredential = Payment.initConfig(prop);
            return tokenCredential.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
