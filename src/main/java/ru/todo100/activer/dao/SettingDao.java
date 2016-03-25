package ru.todo100.activer.dao;

import org.hibernate.criterion.Restrictions;
import ru.todo100.activer.model.SettingItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class SettingDao extends AbstractDao {
    public void setAccountSetting(Integer accountId, String name,String value) {

    }

    public void isAccountSetting(Integer accountId, String name) {
        Object result = getCriteria()
                .add(Restrictions.eq("accountId",accountId))
                .add(Restrictions.eq("key",name))
                .uniqueResult();
    }


    public String getAccountSetting(Integer accountId, String name) {

        return null;
    }

    @Override
    public Class getItemClass() {
        return SettingItem.class;
    }
}
