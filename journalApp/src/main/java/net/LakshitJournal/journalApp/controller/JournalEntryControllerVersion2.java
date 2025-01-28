package net.LakshitJournal.journalApp.controller;

import net.LakshitJournal.journalApp.Services.JournalEntryService;
import net.LakshitJournal.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerVersion2 {
    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all= journalEntryService.getAll();
        if(all!=null && !all.isEmpty())
            return  new ResponseEntity<>(all,HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry mynewEntry) {

        try {

        mynewEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(mynewEntry);
        return new ResponseEntity<>(mynewEntry, HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry=journalEntryService.findbyId(myId);
        if(journalEntry.isPresent())
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public String deleteEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deletebyId(myId);
        return "Entry of Id:" + myId + " has been deleted";
    }


    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry) {
        JournalEntry oldentry=journalEntryService.findbyId(myId).orElse(null);

        if(oldentry!=null) {
            oldentry.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : oldentry.getTitle());
            oldentry.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : oldentry.getContent());
            journalEntryService.saveEntry(oldentry);
            return new ResponseEntity<>(oldentry, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
