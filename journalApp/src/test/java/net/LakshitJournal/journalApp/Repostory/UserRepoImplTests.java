package net.LakshitJournal.journalApp.Repostory;


import net.LakshitJournal.journalApp.Repository.UserRepoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepoImplTests {

    @Autowired
    private UserRepoImpl userRepo;

    @Test
    public void test(){
        userRepo.getUserForSA();

     }
}
