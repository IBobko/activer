package ru.todo100.activer.populators;

import ru.todo100.activer.data.VideoData;
import ru.todo100.activer.model.VideoItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class VideoPopulator implements Populator<VideoItem, VideoData>  {
    @Override
    public VideoData populate(VideoItem videoItem) {
        if (videoItem == null) return null;
        final VideoData data = new VideoData();
        data.setBody(videoItem.getBody());
        data.setDescription(videoItem.getDescription());
        data.setId(videoItem.getId());
        return data;
    }
}
