package net.LakshitJournal.journalApp.controller;

import net.LakshitJournal.journalApp.Services.UserService;
import net.LakshitJournal.journalApp.cache.AppCache;
import net.LakshitJournal.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;


    @GetMapping("/all-users")
    public ResponseEntity<?> getALLUsers(){
        List<User> all=userService.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<String> createAdmin(@RequestBody User user) {
        userService.saveAdmin(user);
        return new ResponseEntity<>("Admin user created successfully", HttpStatus.CREATED);
    }


    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
