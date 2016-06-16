package ru.todo100.activer.populators;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.PhotoAvatarSizeData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.service.PhotoService;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class MessageAccountDataPopulator implements Populator<AccountItem,MessageAccountData>  {
    @Autowired
    private PhotoService photoService1;

    @Override
    public MessageAccountData populate(final AccountItem account) {
        final MessageAccountData messageAccountData = new MessageAccountData();
        messageAccountData.setFirstName(account.getFirstName());
        messageAccountData.setLastName(account.getLastName());
        messageAccountData.setId(account.getId());
        final PhotoAvatarSizeData photos = photoService1.getSizedPhoto(account.getId());
        messageAccountData.setPhoto60x60(photos.getPhotoAvatar());
        return messageAccountData;
    }
}
