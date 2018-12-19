package schoolofnet.com.keep.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import schoolofnet.com.keep.R;
import schoolofnet.com.keep.models.Note;

public class RegisterNoteActivity extends AppCompatActivity {

    @BindView(R.id.title_note_txt)
    EditText editTitleNote;
    @BindView(R.id.body_note_txt)
    EditText editBodyNote;

    FirebaseFirestore firebaseFirestore;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_note);
        setTitle("New Note");

        ButterKnife.bind(this);

        this.firebaseFirestore = FirebaseFirestore.getInstance();
        this.awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.title_note_txt, ".{2,30}", R.string.error_title);
        awesomeValidation.addValidation(this, R.id.body_note_txt, ".{5,255}", R.string.error_body);

    }

    @OnClick(R.id.btn_create_note)
    public void onSaveNote(View view) {
        if (awesomeValidation.validate()) {
            Note note = new Note(UUID.randomUUID().toString(), editTitleNote.getText().toString(), editBodyNote.getText().toString());

            this.firebaseFirestore
                    .collection("notes")
                    .add(note)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Created success", Toast.LENGTH_SHORT).show();
                            clearForm();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Error while creating", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Falied to create. Check fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearForm() {
        editBodyNote.setText("");
        editTitleNote.setText("");
    }
}
