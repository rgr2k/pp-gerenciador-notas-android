package schoolofnet.com.keep.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import schoolofnet.com.keep.R;
import schoolofnet.com.keep.models.Note;
import schoolofnet.com.keep.utils.NotesVAdapter;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

//    @BindView(R.id.txt_user)
//    TextView txtUser;
    @BindView(R.id.list_notes_recycler)
    RecyclerView listNotesRecycler;

    @BindView(R.id.btn)
    private NotesVAdapter notesVAdapter;

    private ArrayList listData = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notes");
        ButterKnife.bind(this);

        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseFirestore = FirebaseFirestore.getInstance();

        this.firebaseFirestore
                .collection("notes")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        listData = (ArrayList) queryDocumentSnapshots.toObjects(Note.class);
                        setRecyclerView("list");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error while listing", Toast.LENGTH_SHORT).show();

                    }
                });

//        if (this.firebaseAuth.getCurrentUser() != null) {
//            txtUser.setText(this.firebaseAuth.getCurrentUser().getEmail());
//        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                startActivity(new Intent(getApplicationContext(), RegisterNoteActivity.class));
            }
        });
    }

    private void setRecyclerView(String type) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        this.listNotesRecycler.setLayoutManager(layoutManager);

        this.notesVAdapter = new NotesVAdapter(this.listData);
        this.listNotesRecycler.setAdapter(this.notesVAdapter);

        this.listNotesRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            this.firebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        } else if (id == R.id.action_card_view) {

        } else if (id == R.id.action_list_view) {

        }

        return super.onOptionsItemSelected(item);
    }
}
