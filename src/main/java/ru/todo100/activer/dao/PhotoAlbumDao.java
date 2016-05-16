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

    /**
     * It retrieves album by its id and account id
     *
     * @param accountId Id of account
     * @param albumId   id of album
     * @return Model of Photo Album item
     */
    public PhotoAlbumItem getAlbum(final Integer accountId, final Integer albumId) {
        return (PhotoAlbumItem) getCriteria().add(Restrictions.eq("accountId", accountId)).
                add(Restrictions.eq("id", albumId)).uniqueResult();
    }
}
