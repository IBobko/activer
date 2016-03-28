package ru.todo100.activer.service.impl;

import ru.todo100.activer.data.OnlineActivityData;
import ru.todo100.activer.service.OnlineActivityService;

import java.util.HashMap;

/**
 * Service is stored current online users.
 *
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

public class OnlineActivityServiceImpl implements OnlineActivityService {

    private HashMap<Integer, OnlineActivityData> activities = new HashMap<>();

    public HashMap<Integer, OnlineActivityData> getActivities() {
        return activities;
    }
}
