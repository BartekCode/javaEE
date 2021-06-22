package readstack.domain.api;

import readstack.domain.comments.CommentDao;
import readstack.domain.discovery.Discovery;
import readstack.domain.discovery.DiscoveryDao;
import readstack.domain.favDiscovery.FavDiscoveriesDao;
import readstack.domain.user.UserDao;
import readstack.domain.vote.VoteDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//W klasie DiscoveryService na razie będzie tylko jedna metoda,
// której zadaniem będzie pobranie wszystkich znalezisk z bazy danych oraz ich przekształcenie z typu Discovery na DiscoveryBasicInfo.
public class DiscoveryService {
   private final   DiscoveryDao discoveryDao = new DiscoveryDao();
    private final   DiscoveryMapper discoveryMapper= new DiscoveryMapper();
    private final CommentDao commentDao = new CommentDao();
    private final FavDiscoveriesDao favDiscoveriesDao=new FavDiscoveriesDao();




    public void add(DiscoverySaveRequest saveRequest){
       Discovery discoveryToSave = discoveryMapper.map(saveRequest);
       discoveryDao.save(discoveryToSave);
    }


//    public void addFavDisc(DiscoverySaveRequest saveRequest){
//        Discovery discoveryToSave= discoveryMapper.map(saveRequest);
//        favDiscoveriesDao.saveFav(discoveryToSave);
//    }

    public List<DiscoveryBasicInfo> findAll() {
        return discoveryDao.findAll()
                .stream().map(discoveryMapper::map)
                .collect(Collectors.toList());
    }

//    public List<DiscoveryBasicInfo> findAllFavsDiscByUserId(int userId){
//        return favDiscoveriesDao.findFavDiscoveryByUserId(userId)
//                .stream().map(discoveryMapper::map)
//                .collect(Collectors.toList());
//    }

    public Optional <DiscoveryBasicInfo> findByDiscByDiscId(int disId){
        return   discoveryDao.findDiscoveryById(disId)
                .map(discoveryMapper::map);
    }

    public List<DiscoveryBasicInfo> findAllbyUserId(int userID){
        return discoveryDao.findDiscoveryByUserId(userID)
                .stream().map(discoveryMapper::map)
                .collect(Collectors.toList());
    }

    public List<DiscoveryBasicInfo> findAllByCategory(int categoryId) {
        return discoveryDao.findByCategory(categoryId)
                .stream().map(discoveryMapper::map)
                .collect(Collectors.toList());
    }


    private static class DiscoveryMapper{
        private final static UserDao userDao = new UserDao();
        private final static VoteDao voteDao = new VoteDao();
        private final static CommentDao commentDao = new CommentDao();
        private final static CommentService commenService = new CommentService();

          DiscoveryBasicInfo map(Discovery discovery){
            return new DiscoveryBasicInfo(discovery.getId(),discovery.getTitle(),discovery.getUrl(),discovery.getDescription(),discovery.getDateAdded(),
                    userDao.findById(discovery.getUserId()).orElseThrow().getUsername(), commenService.allDiscComments(discovery.getId()),
                     voteDao.countByDiscoveryId(discovery.getId()));
        }
        Discovery map(DiscoverySaveRequest discoverySaveRequest){
             return new Discovery(discoverySaveRequest.getTitle(),discoverySaveRequest.getUrl(),discoverySaveRequest.getDescription(),
                     LocalDateTime.now(), discoverySaveRequest.getCategoryId(), userDao.findByUsername(discoverySaveRequest.getAuthor())
                     .orElseThrow()
                     .getId()
             );
        }//Mapper jest w tym przypadku nieco bardziej skomplikowany niż we wcześniejszych przykładach,
        // ponieważ potrzebujemy w nim znaleźć dodatkowo użytkownika i jego identyfikator.


    }
}
