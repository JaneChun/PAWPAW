package animalsquad.server.domain.post.controller;

import animalsquad.server.domain.post.dto.PostCommentPatchDto;
import animalsquad.server.domain.post.dto.PostCommentPostDto;
import animalsquad.server.domain.post.dto.PostDto;
import animalsquad.server.domain.post.dto.PostPatchDto;
import animalsquad.server.domain.post.entity.Post;
import animalsquad.server.domain.post.entity.PostComment;
import animalsquad.server.domain.post.mapper.PostCommentMapper;
import animalsquad.server.domain.post.mapper.PostMapper;
import animalsquad.server.domain.post.service.PostCommentService;
import animalsquad.server.domain.post.service.PostService;
import animalsquad.server.global.auth.userdetails.PetDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostCommentService postCommentService;

    private final PostMapper mapper;

    private final PostCommentMapper commentMapper;


    @PostMapping
    public ResponseEntity postPost(@Valid PostDto postDto,
                                   @AuthenticationPrincipal PetDetailsService.PetDetails principal) throws IllegalAccessException {

        long petId = principal.getId();
        Post post = postService.createPost(mapper.postDtoToPost(postDto), postDto.getFile(), petId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity patchPost(@PathVariable("post-id") long id, PostPatchDto postPatchDto,
                                    @AuthenticationPrincipal PetDetailsService.PetDetails principal) throws IllegalAccessException {
        postPatchDto.setId(id);

        Post post = mapper.patchDtoToPost(postPatchDto);
        long petId = principal.getId();
        postService.updatePost(post, postPatchDto.getFile(), petId);


        return new ResponseEntity(HttpStatus.OK);
    }

    //
//    @GetMapping("/posts")
//    public ResponseEntity getPost() {
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id") long id,
                                     @AuthenticationPrincipal PetDetailsService.PetDetails principal) {
        long petId = principal.getId();
        postService.deletePost(petId, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //
//    @GetMapping("/{post-id}")
//    public ResponseEntity getdetailPost() {
//        return new ResponseEntity(HttpStatus.OK);
//
//    }
    @PostMapping("/comment")
    public ResponseEntity postComment(@Valid @RequestBody PostCommentPostDto commentPostDto,
                                      @AuthenticationPrincipal PetDetailsService.PetDetails principal) {
        PostComment postComment = commentMapper.postCommentPostDtoToPost(commentPostDto);
        long id = principal.getId();
        PostComment comment = postCommentService.createComment(postComment, id);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/comment/{post-comment-id}")
    public ResponseEntity patchComment(@PathVariable("post-comment-id") long commentId,
                                       @RequestBody PostCommentPatchDto commentPatchDto,
                                       @AuthenticationPrincipal PetDetailsService.PetDetails principal) {
        commentPatchDto.setCommentId(commentId);

        PostComment postComment = commentMapper.postCommentPatchToPost(commentPatchDto);
        long petId = principal.getId();
        PostComment result = postCommentService.updateComment(postComment, petId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/comment/{post-comment-id}")
    public ResponseEntity deleteComment(@PathVariable("post-comment-id") long commentId,
                                        @AuthenticationPrincipal PetDetailsService.PetDetails principal) {
        long petId = principal.getId();
        postCommentService.deleteComment(commentId, petId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
