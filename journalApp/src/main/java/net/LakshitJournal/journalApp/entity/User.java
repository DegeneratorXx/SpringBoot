package net.LakshitJournal.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


//@Getter
//@Setter
@Document(collection = "users")
@Data //generates getters , setters, hashcode , to string , equals ..etc
public class User {

    @Id
    private  ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private  String userName;
    @NonNull
    private  String userPassword;
    @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();

}
