package com.blog.model.post.controller;

import com.blog.common.apiresult.ApiResponse;
import com.blog.common.apiresult.AppExceptionCodeMsg;
import com.blog.common.utils.TokenUtil;
import com.blog.model.post.entity.Posts;
import com.blog.model.post.service.PostsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    PostsService postsService;

    /**
     * 添加文章
     *
     * @param posts
     * @param session
     * @return
     */
    @PostMapping
    public ApiResponse addPost(@RequestBody Posts posts, HttpSession session) {
        String loginUserToken = (String) session.getAttribute("loginUserToken");
        String claimsByToken = TokenUtil.getClaimsByToken(loginUserToken);
        posts.setUserId(Integer.valueOf(claimsByToken));
        postsService.addPost(posts);
        return ApiResponse.success("添加成功");
    }

    /**
     * 查询所有文章通过文章ID
     *
     * @param uid
     * @return
     */
    @GetMapping("")
    public ApiResponse findAllPostByUserId(Integer uid) {
        List<Posts> allPostByUserId = postsService.findAllPostByUserId(uid);
        return ApiResponse.success(allPostByUserId);
    }

    /**
     * 查找单个文章通过文章ID
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResponse findPostByPostId(@PathVariable Integer id) {
        Posts postByPostId = postsService.findPostByPostId(id);
        return ApiResponse.success(postByPostId);
    }

    /**
     * 更新文章通过文章ID
     *
     * @param id
     * @param session
     * @param posts
     * @return
     */
    @PutMapping("/{id}")
    public ApiResponse updatePostByPostId(@PathVariable Integer id, HttpSession session,
                                          @RequestBody Posts posts) {
        Integer userId = getUserIdBySessionToken(session);
        if (userId == 0) {
            return ApiResponse.error(AppExceptionCodeMsg.LOGIN_STATUS_OVERDUE);
        }
        Posts postByPostId = postsService.findPostByPostId(id);
        if (postByPostId == null) {
            return ApiResponse.error(AppExceptionCodeMsg.POST_NOT_EXIST);
        }
        if (isLoginUser(userId, postByPostId.getUserId())) {
            String title = posts.getTitle();
            String content = posts.getContent();
            postsService.updatePostByPostId(title, content, id);
            return ApiResponse.success("更新成功");
        }
        return ApiResponse.error(AppExceptionCodeMsg.NOT_POWER_UPDATE);
    }

    /**
     * 删除文章通过文章ID
     *
     * @param id
     * @param session
     * @return
     */
    @DeleteMapping("/{id}")
    public ApiResponse deletePostByPostId(@PathVariable Integer id, HttpSession session) {
        Integer userId = getUserIdBySessionToken(session);
        if (userId == 0) {
            return ApiResponse.error(AppExceptionCodeMsg.LOGIN_STATUS_OVERDUE);
        }
        Posts postByPostId = postsService.findPostByPostId(id);
        if (postByPostId == null) {
            return ApiResponse.error(AppExceptionCodeMsg.POST_NOT_EXIST);
        }
        if (isLoginUser(userId, postByPostId.getUserId())) {
            postsService.deletePostByPostId(id);
            return ApiResponse.success("删除成功");
        }
        return ApiResponse.error(AppExceptionCodeMsg.NOT_POWER_DELETE);
    }

    /**
     * 从session里面把token拿出来解析用户ID
     *
     * @param session
     * @return
     */
    private Integer getUserIdBySessionToken(HttpSession session) {
        String loginUserToken = (String) session.getAttribute("loginUserToken");
        String claimsByToken = TokenUtil.getClaimsByToken(loginUserToken);
        if ("".equals(claimsByToken)) {
            return 0;
        }
        return Integer.parseInt(claimsByToken);
    }

    /**
     * 判断当前登录用户是不是发布文章的用户
     *
     * @param loginUserId
     * @param postUserId
     * @return
     */
    private boolean isLoginUser(Integer loginUserId, Integer postUserId) {
        return Objects.equals(loginUserId, postUserId);
    }
}
