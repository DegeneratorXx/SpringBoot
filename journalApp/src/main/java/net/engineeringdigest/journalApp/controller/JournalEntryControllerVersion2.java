package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerVersion2 {
    @GetMapping
    public List<JournalEntry> getAll() {
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry mynewEntry) {
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntryById(@PathVariable Long myId) {
        return null;
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable Long myId) {
        return null;

    }

    @PutMapping("id/{myId}")
    public JournalEntry updateEntryById(@PathVariable Long myId, @RequestBody JournalEntry myEntry) {
        return null;

    }
}
