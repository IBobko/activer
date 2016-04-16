package ru.todo100.activer.tags;

import ru.todo100.activer.data.PartnerData;

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class DefaultPartnerTag extends BodyTagSupport {
    public void setPartner(Object partner) {
        if (!(partner instanceof PartnerData)) {
            final PartnerData partnerData = new PartnerData();
            partnerData.setId("#id");
            partnerData.setName("#name");
            pageContext.setAttribute("partner",partnerData);
        }
    }


}
