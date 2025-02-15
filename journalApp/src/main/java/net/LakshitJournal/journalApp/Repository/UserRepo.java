package net.LakshitJournal.journalApp.Repository;

import net.LakshitJournal.journalApp.entity.JournalEntry;
import net.LakshitJournal.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
}
