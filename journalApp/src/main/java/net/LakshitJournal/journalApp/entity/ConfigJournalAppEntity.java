package net.LakshitJournal.journalApp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app")
//@Getter
//@Setter
@Data //generates getters , setters, hashcode , to string , equals ..etc
@NoArgsConstructor
public class ConfigJournalAppEntity {

    private String key;
    private String value;
}
