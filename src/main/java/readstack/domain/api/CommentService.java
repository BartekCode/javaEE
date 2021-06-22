package readstack.domain.api;

import readstack.domain.comments.Comment;
import readstack.domain.comments.CommentDao;
import readstack.domain.user.UserDao;
import readstack.domain.vote.VoteDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentService {

    private final CommentDao commentDao = new CommentDao();
    private final CommentMapper commentMapper= new CommentMapper();

    public void addComment(DiscoveryComment comment) {
        Comment comment1 = commentMapper.map1(comment);
        commentDao.save(comment1);

    }

    public List<CommentInfo> allDiscComments(Integer discId){
        return commentDao.findCommentsByDiscoveryId(discId)
                .stream().map(commentMapper::map)
                .collect(Collectors.toList());

    }


    private static class CommentMapper {
        private final static CommentDao commentDao = new CommentDao();
        private final static UserDao userDao = new UserDao();
        private final static VoteDao voteDao = new VoteDao();

        CommentInfo map(Comment comment){
            List<Comment> commentsByDiscoveryId = commentDao.findCommentsByDiscoveryId(comment.getDiscoveryId());
            return new CommentInfo(
                    userDao.findById(comment.getUserId()).orElseThrow().getUsername(),
                    comment.getDescription(),
                    comment.getDiscoveryId(),
                    comment.getDateAdded());
        }

        Comment map1 (DiscoveryComment commentSave){
            return new Comment(userDao.findByUsername(commentSave.getAuthor()).orElseThrow().getId(),commentSave.getDiscoveryId(),
                    LocalDateTime.now(),commentSave.getDescription());

        }



        }

    }




