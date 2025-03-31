package net.LakshitJournal.journalApp.controller;

import net.LakshitJournal.journalApp.Repository.UserRepo;
import net.LakshitJournal.journalApp.ResponseAPI.WeatherResponse;
import net.LakshitJournal.journalApp.Services.UserService;
import net.LakshitJournal.journalApp.Services.WeatherService;
import net.LakshitJournal.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public List<User> getAllUsers(){
//        return userService.getAll();
//    }


    @PutMapping
    public ResponseEntity<?> updateById(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName); //finding user from the username inside the path variable

        userInDb.setUserPassword(user.getUserPassword());//updated password
        userInDb.setUserName(user.getUserName());//updated username which we are providing in Querry (json)
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepo.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse res = weatherService.getWeather("Ajmer");
        String greet="";
        if(res!=null)
        {
            greet = "\nWeather Feels Like: " + res.getCurrent().getFeelslike() +
                    "\nCurrent Temperature is: " + res.getCurrent().getTemperature() +
                    "\nWeather Description is: " + res.getCurrent().getWeatherDescriptions();
            System.out.println(greet);
        }
        return new ResponseEntity<>("Hi "+ authentication.getName()+greet, HttpStatus.OK);
    }




}




