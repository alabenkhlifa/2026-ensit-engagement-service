package tn.ensit.engagementservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.ensit.engagementservice.comment.entities.Comment;
import tn.ensit.engagementservice.comment.repositories.CommentRepository;
import tn.ensit.engagementservice.like.entities.Like;
import tn.ensit.engagementservice.like.repositories.LikeRepository;
import tn.ensit.engagementservice.post.entities.Post;
import tn.ensit.engagementservice.post.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public DataSeeder(PostRepository postRepository,
                      CommentRepository commentRepository,
                      LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public void run(String... args) {
        if (postRepository.count() > 0) return;

        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Post post = new Post((long) i, "Post #" + i + " by user " + i);
            posts.add(postRepository.save(post));
        }

        for (int i = 0; i < 10; i++) {
            long commenterId = (i % 10) + 1;
            Post post = posts.get(i % 10);
            commentRepository.save(new Comment(commenterId, post, "Comment #" + (i + 1) + " on post " + post.getId()));
        }

        for (int i = 0; i < 10; i++) {
            long userId = (i % 10) + 1;
            Post post = posts.get((i + 1) % 10);
            likeRepository.save(new Like(userId, post));
        }
    }
}
