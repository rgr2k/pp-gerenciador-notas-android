package schoolofnet.com.keep.utils;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import schoolofnet.com.keep.R;
import schoolofnet.com.keep.models.Note;

public class NotesVAdapter extends RecyclerView.Adapter<LIneNoteVHolder> {

    private List<Note> mNotes;

    public NotesVAdapter(ArrayList notes) {
        this.mNotes = notes;
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
    }

    @Override
    public int getItemCount() {
        return this.mNotes.size();
    }
}
