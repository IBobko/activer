package ru.todo100.activer.dao;

import org.hibernate.criterion.Restrictions;
import ru.todo100.activer.model.VideoItem;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class VideoDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return VideoItem.class;
    }

    @SuppressWarnings("unchecked")
    public List<VideoItem> getVideosByAccount(Integer id) {
        return getCriteria().add(Restrictions.eq("accountId", id)).list();
    }
}
