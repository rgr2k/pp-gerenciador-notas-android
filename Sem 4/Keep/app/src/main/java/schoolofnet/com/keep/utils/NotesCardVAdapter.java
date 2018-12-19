package schoolofnet.com.keep.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import schoolofnet.com.keep.R;
import schoolofnet.com.keep.models.Note;

public class NotesCardVAdapter extends RecyclerView.Adapter<CardVHolder> {

    private List<Note> mNotes;

    public NotesCardVAdapter(ArrayList notes) {
        this.mNotes = notes;
    }

    @NonNull
    @Override
    public CardVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_note_v, parent, false);

        return new CardVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardVHolder holder, int position) {
        String date = new SimpleDateFormat("dd/MM/yyyy").format(this.mNotes.get(position).getCreated_at());

        holder.titleNoteCardTxt.setText(this.mNotes.get(position).getTitle());
        holder.dateNoteCardTxt.setText(date);
    }

    @Override
    public int getItemCount() {
        return this.mNotes.size();
    }
}