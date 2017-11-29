package ru.todo100.activer.payeer.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import ru.todo100.activer.payeer.domain.PayeerForm;
import ru.todo100.activer.payeer.service.PayeerService;

import java.util.ArrayList;
import java.util.List;

public class PayeerServiceImpl implements PayeerService, ApplicationContextAware {
    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String getShop() {
        return getApplicationContext().getEnvironment().getProperty("payeer.shop");
    }

    public String getKey() {
        return getApplicationContext().getEnvironment().getProperty("payeer.key");
    }

    public String getDefaultCurr() {
        return getApplicationContext().getEnvironment().getProperty("payeer.default_curr");
    }

    public String getSign(final PayeerForm form) {
        final List<String> arHash = new ArrayList<>();
        arHash.add(form.getShop());
        arHash.add(form.getOrder().toString());
        arHash.add(form.getAmount());
        arHash.add(form.getCurr());
        arHash.add(form.getDesc());
        // Возможно нужны еще поля.
        arHash.add(form.getKey());
        return getHash(arHash);
    }

    public String getHash(final List<String> arHash) {
        if (arHash == null) return null;
        return DigestUtils.sha256Hex(StringUtils.join(arHash,":")).toUpperCase();
    }
}
