package ru.todo100.activer.payeer.service;

import ru.todo100.activer.payeer.domain.PayeerForm;

import java.util.List;

public interface PayeerService {
    String getShop();
    String getKey();
    String getDefaultCurr();
    String getSign(final PayeerForm form);
    String getHash(final List<String> arHash);
}
