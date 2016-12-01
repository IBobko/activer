package ru.todo100.activer.dao;

import org.hibernate.Query;
import org.hibernate.transform.ResultTransformer;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.TopLineItem;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class TopLineDao extends AbstractDao<TopLineItem> {
    private final static Integer MAX_TOP_LINE = 20;
    class Transformer implements ResultTransformer {
        @Override
        public Object transformTuple(Object[] tuple, String[] aliases) {
            final TopLineItem topLineItem = new TopLineItem();
            final AccountItem accountItem = new AccountItem();
            topLineItem.setAccount(accountItem);
            for (int i = 0; i < aliases.length; i++) {
                switch (aliases[i]) {
                    case "tl_id": topLineItem.setId((Integer)tuple[i]); break;
                    case "a_id": accountItem.setId((Integer)tuple[i]); break;
                    case "a_last_name": accountItem.setLastName((String)tuple[i]); break;
                    case "a_first_name": accountItem.setFirstName((String)tuple[i]); break;
                    case "tl_created_date": topLineItem.setCreatedDate((Calendar)tuple[i]); break;
                    case "tl_message": topLineItem.setMessage((String)tuple[i]); break;

                }
            }
            return topLineItem;
        }

        @Override
        public List transformList(List collection) {
            return collection;
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<TopLineItem> getTopList() {
        final Query query = getSession().createQuery("SELECT distinct account.id as a_id, account.firstName as a_first_name ,account.lastName as a_last_name, t.id as tl_id, t.createdDate as tl_created_date,t.message as tl_message FROM TopLineItem t JOIN t.account as account order by t.createdDate desc");
        query.setMaxResults(MAX_TOP_LINE);
        query.setResultTransformer(new TopLineDao.Transformer());
        return query.list();
    }

    @Transactional
    public void addTopLine(final Integer account_id,final String message) {
        final TopLineItem topLineItem = new TopLineItem();
        final AccountItem account = getSession().load(AccountItem.class, account_id);
        topLineItem.setMessage(message);
        topLineItem.setAccount(account);
        topLineItem.setCreatedDate(new GregorianCalendar());
        getSession().save(topLineItem);
    }

    @Override
    public Class getItemClass() {
        return TopLineItem.class;
    }
}
