package net.LakshitJournal.journalApp.Repository;

import net.LakshitJournal.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.ObjectInput;

public interface Repo extends MongoRepository<JournalEntry, ObjectId> {

}
