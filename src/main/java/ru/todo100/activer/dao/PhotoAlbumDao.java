package ru.todo100.activer.dao;

import ru.todo100.activer.model.PhotoAlbumItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PhotoAlbumDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return PhotoAlbumItem.class;
    }


}
