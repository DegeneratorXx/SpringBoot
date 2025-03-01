package net.LakshitJournal.journalApp.services;

import net.LakshitJournal.journalApp.Repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepo userRepo;

    @Disabled
    @Test
    public void testfindByUserName(){
        assertNotNull(userRepo.findByUserName("Lakshit"));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "Lakshit",
            "ADMIN"

    })
    public void testfindByUserName2(String userName){
        assertNotNull(userRepo.findByUserName(userName));
    }
}
