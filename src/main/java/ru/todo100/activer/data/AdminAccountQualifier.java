package ru.todo100.activer.data;

import ru.todo100.activer.qualifier.Qualifier;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AdminAccountQualifier extends Qualifier {
    private Boolean onOffline;

    public Boolean getOnOffline() {
        return onOffline;
    }

    public void setOnOffline(Boolean onOffline) {
        this.onOffline = onOffline;
    }
}
