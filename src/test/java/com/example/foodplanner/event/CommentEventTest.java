package com.example.foodplanner.event;


import com.example.foodplanner.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class CommentEventTest {

    private CommentsEvent commentsEvent;
    @Mock
    CommentService commentService;

    @BeforeEach
    public void setUp(){
        commentsEvent=new CommentsEvent(commentService);
    }

    @Test
    public void testCleanComments(){
        commentsEvent.cleanOldComments();

        Mockito.verify(commentService).deletePastComments();
    }
}
