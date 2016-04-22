package ru.todo100.activer.service;

import ru.todo100.activer.data.PartnerData;
import ru.todo100.activer.data.PartnerInfo;
import ru.todo100.activer.data.PartnerQualifier;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface PartnerService {
    void synchronize(Integer accountId);
    List<PartnerData> getPartners(PartnerQualifier qualifier);
    Long getPartnersCount(PartnerQualifier qualifier);
    Integer getPartnerLevel(Integer accountID);
    Integer getNetworkCount(Integer accountID);
    BigDecimal getEarned(Integer accountID);
    BigDecimal getProfit(Integer accountID);

    List<PartnerInfo> recursive(String referCode, Integer level);
}
