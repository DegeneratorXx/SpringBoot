package net.LakshitJournal.journalApp.Repository;

import net.LakshitJournal.journalApp.entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId> {



}
