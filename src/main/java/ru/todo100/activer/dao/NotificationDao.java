package ru.todo100.activer.dao;

import ru.todo100.activer.model.NotificationItem;

public class NotificationDao extends AbstractDao  {
    @Override
    public Class getItemClass() {
        return NotificationItem.class;
    }
}
