package com.wanted.teamV.service.impl;

import com.wanted.teamV.dto.req.PostCreateReqDto;
import com.wanted.teamV.entity.Post;
import com.wanted.teamV.entity.PostHashtag;
import com.wanted.teamV.repository.PostHashtagRepository;
import com.wanted.teamV.repository.PostRepository;
import com.wanted.teamV.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostHashtagRepository postHashtagRepository;

    // 게시물 생성 메서드
    @Override
    @Transactional
    public void createPost(PostCreateReqDto request) {
        Post post = Post.builder()
                .contentId(request.getContentId())
                .title(request.getTitle())
                .content(request.getContent())
                .type(request.getType())
                .viewCount(request.getViewCount())
                .likeCount(request.getLikeCount())
                .shareCount(request.getShareCount())
                .build();

        postRepository.save(post);

        for (String hashtag : request.getHashtags()) {
            PostHashtag postHashtag = PostHashtag.builder()
                    .post(post)
                    .hashtag(hashtag)
                    .build();
            postHashtagRepository.save(postHashtag);
        }
    }
}
