package net.LakshitJournal.journalApp.services;

import net.LakshitJournal.journalApp.Repository.UserRepo;
import net.LakshitJournal.journalApp.Services.UserDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @MockBean
    private UserRepo userRepo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Disabled
    @Test
    public void loadUserByUsernameTest(){
        when(userRepo.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn((net.LakshitJournal.journalApp.entity.User)User.builder()
                        .username("ram")
                        .password("inrinrick")
                        .roles(new String[0]) // Empty array instead of ArrayList
                        .build());
        UserDetails user = userDetailService.loadUserByUsername("ram");
    }
}
