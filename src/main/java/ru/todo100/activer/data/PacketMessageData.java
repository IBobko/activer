package ru.todo100.activer.data;

import ru.todo100.activer.PopupMessageType;

/**
 * Эта структура посылается пользователю, как уведомление об определенном сигнале.
 *
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PacketMessageData extends MessageData {
    /**
     * Тип сигнала
     */
    private PopupMessageType type;

    public PopupMessageType getType() {
        return type;
    }

    public void setType(PopupMessageType type) {
        this.type = type;
    }
}
