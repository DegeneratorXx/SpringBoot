package net.LakshitJournal.journalApp.Services;

import net.LakshitJournal.journalApp.Repository.Repo;
import net.LakshitJournal.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

@Autowired
    private Repo repo;

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
    public void deletebyId(ObjectId id)
    {
        repo.deleteById(id);

    }
}
