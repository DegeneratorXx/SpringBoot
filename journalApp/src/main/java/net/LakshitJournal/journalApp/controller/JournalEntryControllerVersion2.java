package net.LakshitJournal.journalApp.controller;

import net.LakshitJournal.journalApp.Services.JournalEntryService;
import net.LakshitJournal.journalApp.Services.UserService;
import net.LakshitJournal.journalApp.entity.JournalEntry;
import net.LakshitJournal.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerVersion2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @GetMapping()
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        List<JournalEntry> all= userInDb.getJournalEntries();

        if(all!=null && !all.isEmpty())
            return  new ResponseEntity<>(all,HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry mynewEntry) {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(mynewEntry,userName);
            return new ResponseEntity<>(mynewEntry, HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()){
            Optional<JournalEntry> journalEntry=journalEntryService.findbyId(myId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public String deleteEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed=journalEntryService.deletebyId(myId,userName);
        if(removed)
        return "Entry of Id:" + myId + " has been deleted";
        else
            return "Error while deleting the entry with Id:"+ myId+ ". Please check the Id Again.";
    }


    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myId,
                                             @RequestBody JournalEntry myEntry
                                           )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()){
           Optional<JournalEntry> entry=journalEntryService.findbyId(myId);
            if(entry!=null) {
                JournalEntry oldentry = entry.get();
                oldentry.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : oldentry.getTitle());
                oldentry.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : oldentry.getContent());
                journalEntryService.saveEntry(oldentry);
                return new ResponseEntity<>(oldentry, HttpStatus.OK);
            }

        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
