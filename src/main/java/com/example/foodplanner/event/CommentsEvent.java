package com.example.foodplanner.event;


import com.example.foodplanner.service.CommentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;




@Component
public class CommentsEvent {

    private final Logger LOGGER = LoggerFactory.getLogger(CommentsEvent.class);
    private final CommentService commentService;

    public CommentsEvent(CommentService commentService) {
        this.commentService = commentService;
    }

    @Async
    @Transactional
    @Scheduled(cron = "0 0 0 */1 * *")
    public void cleanOldComments(){
        commentService.deletePastComments();
        LOGGER.info("Comments older than a year cleaned");
    }
}
