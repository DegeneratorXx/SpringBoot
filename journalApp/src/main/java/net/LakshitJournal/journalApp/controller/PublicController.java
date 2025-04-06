package net.LakshitJournal.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.LakshitJournal.journalApp.Services.UserDetailServiceImpl;
import net.LakshitJournal.journalApp.Services.UserService;
import net.LakshitJournal.journalApp.entity.User;
import net.LakshitJournal.journalApp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {



    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";
    }


    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @PostMapping("/Login")
    public ResponseEntity<String> Login(@RequestBody User user){
        try {
             authenticationManager.
                     authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getUserPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.error("Error occured while authentication");
            return new ResponseEntity<>("incorrect username/password",HttpStatus.BAD_REQUEST);
        }
    }
}
