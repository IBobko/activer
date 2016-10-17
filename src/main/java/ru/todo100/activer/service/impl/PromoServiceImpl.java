package ru.todo100.activer.service.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.dao.AbstractDao;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.PromoCodeItem;
import ru.todo100.activer.service.PromoService;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PromoServiceImpl extends AbstractDao implements PromoService {

    final static int MAX_PROMO = 9999;

    @Transactional
    @Override
    public PromoCodeItem getPromo(String code) {
        return (PromoCodeItem) getCriteria().add(Restrictions.eq("code",code)).uniqueResult();
    }


    @Transactional
    @Override
    public List<PromoCodeItem> getAccountPromos(AccountItem accountItem) {

        return getCriteria().add(Restrictions.eq("owner",accountItem)).list();
    }

    @Transactional
    @Override
    public PromoCodeItem generateNewPromo(final AccountItem accountItem) {
        final PromoCodeItem promoCodeItem = new PromoCodeItem();
        final Random rand = ThreadLocalRandom.current();
        promoCodeItem.setOwner(accountItem);
        promoCodeItem.setCode(Integer.toString(rand.nextInt(MAX_PROMO)));
        getSession().persist(promoCodeItem);
        return promoCodeItem;
    }

    @Override
    public PromoCodeItem removePromo(String code) {
        return null;
    }

    @Override
    public AccountItem getPromoOwner(String code) {
        return null;
    }

    @Override
    public AccountItem getPromoUsed(String code) {
        return null;
    }

    @Override
    public Class<? extends Item> getItemClass() {
        return PromoCodeItem.class;
    }

}
