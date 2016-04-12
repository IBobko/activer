package ru.todo100.activer.dao;

import org.hibernate.criterion.Restrictions;
import ru.todo100.activer.model.PhotoAlbumItem;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class PhotoAlbumDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return PhotoAlbumItem.class;
    }

    @SuppressWarnings("unchecked")
    public List<PhotoAlbumItem> getAlbumsByAccount(Integer id) {
        return getCriteria().add(Restrictions.eq("accountId", id)).list();
    }
}
