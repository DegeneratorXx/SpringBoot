package net.LakshitJournal.journalApp.SchedulerTests;

import net.LakshitJournal.journalApp.Scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTests{

    @Autowired
    private UserScheduler userScheduler;


    @Test
    public void testFetchUsersAndSendSaMail(){
        userScheduler.fetchUserAndSendEmail();
    }
}
