package net.LakshitJournal.journalApp.Scheduler;

import net.LakshitJournal.journalApp.Repository.UserRepo;
import net.LakshitJournal.journalApp.Repository.UserRepoImpl;
import net.LakshitJournal.journalApp.Services.EmailService;
import net.LakshitJournal.journalApp.Services.SentimentAnalysisService;
import net.LakshitJournal.journalApp.cache.AppCache;
import net.LakshitJournal.journalApp.entity.JournalEntry;
import net.LakshitJournal.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class userScheduler {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepo;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

//    @Scheduled(cron = "0 * * ? * *")
    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendEmail(){
        List<User> userForSA = userRepo.getUserForSA();
        for(User user:userForSA){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.sentimentAnalysis(entry);
            emailService.sendEmail(user.getEmail(),"Sentiment Analysis for last 7 days",sentiment);
        }
    }

    //reloading appcache every5 min for detecting cahnges in config_app collection in db
    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }

}
