package ru.todo100.activer.qualifier;

import ru.todo100.activer.data.Qualifier;
import ru.todo100.activer.form.FriendSearchForm;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AccountQualifier extends Qualifier {
    private FriendSearchForm friendSearchForm;

    public FriendSearchForm getFriendSearchForm() {
        return friendSearchForm;
    }

    public void setFriendSearchForm(FriendSearchForm friendSearchForm) {
        this.friendSearchForm = friendSearchForm;
    }
}
