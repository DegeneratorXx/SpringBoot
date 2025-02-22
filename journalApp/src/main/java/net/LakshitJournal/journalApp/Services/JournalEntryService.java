package net.LakshitJournal.journalApp.Services;

import lombok.extern.slf4j.Slf4j;
import net.LakshitJournal.journalApp.Repository.JournalRepo;
import net.LakshitJournal.journalApp.entity.JournalEntry;
import net.LakshitJournal.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JournalEntryService {

@Autowired
    private JournalRepo repo;
@Autowired
private UserService userService;

    @Transactional //if any of querry fail then whole successfull changes would be rolled back
    public  void saveEntry(JournalEntry journalEntry, String userName)
    {
        try {
            User user=userService.findByUserName(userName); //we got the user
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = repo.save(journalEntry);//saved journal entry in a variable
            user.getJournalEntries().add(saved);//pushing that variable in user db also so they get connected>
            userService.saveUser(user);
        }
        catch (Exception e){
            log.error("Exception",e);
            throw new RuntimeException("An error occured while saving the entry",e);
        }
    }
    public  void saveEntry(JournalEntry journalEntry)
    {
       repo.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return repo.findAll();
    }
    public Optional<JournalEntry> findbyId(ObjectId id){
        return repo.findById(id);
    }

    @Transactional
    public boolean deletebyId(ObjectId id, String userName)
    {
        boolean removed=false;
        try {

        User user = userService.findByUserName(userName);
        removed=user.getJournalEntries().removeIf(x->x.getId().equals(id)); //remove that entry on which getId gives same id as
        if (removed){
        userService.saveUser(user);
        repo.deleteById(id);
        }
        }
        catch (Exception e){
            System.out.println(e);
            throw  new RuntimeException("An error occured while deleting the entry.",e);
        }

        return removed;

    }

}
