package readstack.domain.api;


import readstack.domain.user.User;
import readstack.domain.user.UserDao;
import readstack.domain.vote.Vote;
import readstack.domain.vote.VoteDao;

import java.time.LocalDateTime;
import java.util.Optional;

//No i klasa warstwy usług, w której definiujemy metodę do dodawania głosów
public class DiscoveryVoteService {
    private VoteDao voteDao = new VoteDao();
    private DiscoveryVoteMapper voteMapper = new DiscoveryVoteMapper();

    public void addVote(DiscoveryVote vote) {
        Vote voteToSave = voteMapper.map(vote);
        voteDao.save(voteToSave);
    }
    private class DiscoveryVoteMapper{ //mapper
        private final UserDao userDao = new UserDao();


        Vote map(DiscoveryVote vote){
            Optional<User> user = userDao.findByUsername(vote.getUsername());
            return new Vote(
                    user.orElseThrow().getId(),
                    vote.getDiscoveryId(),
                    Vote.Type.valueOf(vote.getType()),
                    LocalDateTime.now()
            );
        }


//        Vote mapper (Vote vote){
//            List<Vote> commentsByDiscoveryId = voteDao.findByUserId(vote.getDiscoveryId());
//            return new DiscoveryVote(
//                    userDao.findById(vote.getUserId()).orElseThrow().getUsername(),
//                    vote.getDiscoveryId(),
//                    vote.getType());
//
//        }
    }

}
