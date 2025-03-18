package net.LakshitJournal.journalApp.services;


import net.LakshitJournal.journalApp.Services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){

        emailService.sendEmail("bt22eci027@iiitn.ac.in","Testing java mail sender","check123");
    }

}
