package ru.todo100.activer.data;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class DialogData {
    private MessageAccountData owner;
    private MessageData lastMessage;

    public Long getCountNotRed() {
        return countNotRed;
    }

    public void setCountNotRed(Long countNotRed) {
        this.countNotRed = countNotRed;
    }

    private Long countNotRed;

    public MessageData getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageData lastMessage) {
        this.lastMessage = lastMessage;
    }

    public MessageAccountData getOwner() {
        return owner;
    }

    public void setOwner(MessageAccountData owner) {
        this.owner = owner;
    }
}
