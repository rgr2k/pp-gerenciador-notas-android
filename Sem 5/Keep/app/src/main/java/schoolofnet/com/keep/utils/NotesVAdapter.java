package schoolofnet.com.keep.utils;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import schoolofnet.com.keep.R;
import schoolofnet.com.keep.models.Note;

public class NotesVAdapter extends RecyclerView.Adapter<LIneNoteVHolder> {

    private List<Note> mNotes;
    FirebaseFirestore firebaseFirestore;
    public NotesVAdapter(ArrayList notes) {
        this.mNotes = notes;
        this.firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public LIneNoteVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.line_note_v, parent, false);

        return new LIneNoteVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LIneNoteVHolder holder, int position) {
        String date = new SimpleDateFormat("dd/MM/yyyy").format(this.mNotes.get(position).getCreated_at());

        holder.titleNoteTxt.setText(this.mNotes.get(position).getTitle());
        holder.dateNoteTxt.setText(date);
        holder.btnDeleteLine.setOnClickListener(view -> removeItem(position));
    }

    @Override
    public int getItemCount() {
        return this.mNotes.size();
    }

    private void removeItem(int position) {
        Note note = this.mNotes.get(position);
        this.firebaseFirestore
                .collection("notes")
                .whereEqualTo("id", note.getId())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            firebaseFirestore
                                    .collection("notes")
                                    .document(doc.getId())
                                    .delete()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            mNotes.remove(position);
                                            notifyItemRemoved(position);
                                            notifyItemRangeChanged(position, mNotes.size());
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}
