package tn.ensit.engagementservice.like.services;

import tn.ensit.engagementservice.like.entities.Like;
import tn.ensit.engagementservice.like.repositories.LikeRepository;
import tn.ensit.engagementservice.post.entities.Post;
import org.springframework.stereotype.Service;
import tn.ensit.engagementservice.post.services.PostService;

import java.util.List;

@Service
public class LikeService {

    private final LikeRepository repository;

    private final PostService postService;

    public LikeService(LikeRepository repository, PostService postService) {
        this.repository = repository;
        this.postService = postService;
    }

    public Like likePost(Long userId, Long postId) {
        Post post = postService.findById(postId);
        return repository.save(
            new Like(userId, post)
        );
    }

    public List<Like> getLikesByPost(Long postId) {
        Post post = postService.findById(postId);
        return repository.findByPost(post);
    }
}
