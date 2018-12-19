package schoolofnet.com.keep.models;

import java.util.Date;

import lombok.Data;

@Data
public class Note {
    private String id;
    private String title;
    private String body;
    private Date created_at = new Date();

    public Note() {

    }

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Note(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
