package ru.todo100.activer.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.model.SettingItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class SettingDao extends AbstractDao {

    public void setAccountSetting(Integer accountId, String name,String value) {
        SettingItem settingItem = isAccountSetting(accountId,name);
        if (settingItem == null) {
            settingItem = new SettingItem();
            settingItem.setKey(name);
            settingItem.setAccountId(accountId);
        }
        settingItem.setValue(value);
        getSession().save(settingItem);
    }

    public SettingItem isAccountSetting(Integer accountId, String name) {
        return (SettingItem)getCriteria()
                .add(Restrictions.eq("accountId",accountId))
                .add(Restrictions.eq("key",name))
                .uniqueResult();

    }

    public String getAccountSetting(Integer accountId, String name) {
        final SettingItem result = (SettingItem)getCriteria()
                .add(Restrictions.eq("accountId",accountId))
                .add(Restrictions.eq("key",name))
                .uniqueResult();
        if (result == null) return null;
        return result.getValue();
    }

    @Override
    public Class getItemClass() {
        return SettingItem.class;
    }
}
