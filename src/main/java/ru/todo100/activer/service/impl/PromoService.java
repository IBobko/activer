package ru.todo100.activer.service.impl;

import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.PromoCodeItem;

import java.util.List;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
public interface PromoService {
    /*
        Возвращает все промо принадлежающие этому аккаунту
     */
    List<PromoCodeItem> getAccountPromos(AccountItem accountItem);

    /*
        Возвращает новый промо аккаунта
    */
    PromoCodeItem generateNewPromo(AccountItem accountItem);

    /*
       Возвращает новый промо аккаунта c таким-то кодом
   */
    PromoCodeItem removePromo(String code);

    /*
      Возвращает владельца промо
    */
    AccountItem getPromoOwner(String code);

    /*
     Возвращает использовавшего промо
   */
    AccountItem getPromoUsed(String code);

}
