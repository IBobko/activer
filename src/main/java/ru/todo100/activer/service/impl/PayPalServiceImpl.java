package ru.todo100.activer.service.impl;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import ru.todo100.activer.service.PayPalService;

import javax.servlet.http.HttpServletRequest;
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

    @Override
    public String getServerName(final HttpServletRequest request) {
        String forward = request.getHeader("x-forwarded-host");
        String ret = request.getScheme() + "://";
        if (forward != null) {
            return ret + forward + request.getContextPath();
        }
        return ret + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
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
