package net.LakshitJournal.journalApp.Services;

import lombok.extern.slf4j.Slf4j;
import net.LakshitJournal.journalApp.Repository.UserRepo;
import net.LakshitJournal.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

//    private static final Logger logger= LoggerFactory.getLogger(UserService.class); instead of this we can directly use @SLF4J annotation

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public  void saveNewUser(User user)
    {
        try{

            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
        }
        catch (Exception e){
            log.error("Error Occured for {} :",user.getUserName());
        }
    }
    public  void saveAdmin(User user)
    {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }
    public  void saveUser(User user)
    {
        userRepo.save(user);
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
