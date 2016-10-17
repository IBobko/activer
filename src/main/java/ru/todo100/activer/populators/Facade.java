package ru.todo100.activer.populators;

import java.text.SimpleDateFormat;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface Facade {
    SimpleDateFormat FORMAT_DD_MM_yyyy_HH_mm_ss = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
    SimpleDateFormat FORMAT_DD_MM_yyyy = new SimpleDateFormat("dd.MM.yyyy");
}
