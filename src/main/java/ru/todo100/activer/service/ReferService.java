package ru.todo100.activer.service;

import ru.todo100.activer.model.AccountItem;

/**
 * Created by igor on 17.03.16.
 */
public interface ReferService {
    AccountItem getUserByRefer(String referCode);
}
