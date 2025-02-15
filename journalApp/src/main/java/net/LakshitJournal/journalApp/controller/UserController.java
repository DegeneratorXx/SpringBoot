package net.LakshitJournal.journalApp.controller;

import net.LakshitJournal.journalApp.Services.UserService;
import net.LakshitJournal.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
    userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateById(@RequestBody User user,@PathVariable String userName){

        User userInDb = userService.findByUserName(userName); //finding user from the username inside the path variable
        if(userInDb!=null){
            userInDb.setUserPassword(user.getUserPassword());//updated password
            userInDb.setUserName(user.getUserName());//updated username which we are providing in Querry (json)
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

    }




