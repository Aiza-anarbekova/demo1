package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Lesson;
import peaksoft.model.Video;
import peaksoft.repository.LessonRepository;
import peaksoft.repository.VideoRepository;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final LessonRepository lessonRepository;

    public VideoService(VideoRepository videoRepository, LessonRepository lessonRepository) {
        this.videoRepository = videoRepository;
        this.lessonRepository = lessonRepository;
    }


    public List<Video> getAllVideo() {
        return videoRepository.findAll();
    }


    public void addVideo(Long id, Video video) {
        Lesson lesson = lessonRepository.findLessonById(id);
        lesson.setVideo(video);
        video.setLesson(lesson);
        videoRepository.save(video);

    }


    public Video getById(Long id) {
        return videoRepository.findById(id).get();
    }


    public List<Video> getVideoByLessonId(Long id) {
        return videoRepository.findVideoByLessonId(id);
    }


    public void updateVideo(Long id, Video video) {
        Video video1 = videoRepository.findVideoById(id);
        video1.setVideoName(video.getVideoName());
        video1.setId(video.getId());
        video1.setLink(video.getLink());
        videoRepository.save(video1);
    }


    public void deleteVideo(Long id) {
        Video video = videoRepository.findVideoById(id);
        video.setLesson(null);
        videoRepository.deleteById(id);

    }
}
