package net.LakshitJournal.journalApp.Services;

import lombok.extern.slf4j.Slf4j;
import net.LakshitJournal.journalApp.Repository.UserRepo;
import net.LakshitJournal.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public  void saveEntry(User user)
    {
        try {
            userRepo.save(user);
        }
        catch (Exception e){
            log.error("Exception",e);
        }
    }
    public List<User> getAll(){
        return userRepo.findAll();
    }

    public Optional<User> findbyId(ObjectId id){
        return userRepo.findById(id);
    }

    public void deletebyId(ObjectId id)
    {
        userRepo.deleteById(id);

    }

    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }



}
