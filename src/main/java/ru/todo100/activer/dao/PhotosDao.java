package ru.todo100.activer.dao;

import org.hibernate.criterion.Restrictions;
import ru.todo100.activer.model.PhotoItem;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

public class PhotosDao extends AbstractDao {

    @Override
    public Class getItemClass() {
        return PhotoItem.class;
    }
    @SuppressWarnings("unchecked")
    @Transactional
    public List<PhotoItem> getByAccountAndAlbum(Integer accountId,Integer albumId) {
        return getCriteria().add(Restrictions.and(Restrictions.eq("accountId",accountId),Restrictions.eq("albumId",albumId))).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<PhotoItem> getByAccount(Integer accountId) {
        return getCriteria().add(Restrictions.eq("accountId",accountId)).list();
    }

}
