package schoolofnet.com.keep.utils;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import schoolofnet.com.keep.R;

public class LIneNoteVHolder extends RecyclerView.ViewHolder {

    public TextView titleNoteTxt;
    public TextView dateNoteTxt;
    public ImageButton btnDeleteLine;
    public LIneNoteVHolder(View item) {
        super(item);

        titleNoteTxt = (TextView) item.findViewById(R.id.title_note_line_txt);
        dateNoteTxt = (TextView) item.findViewById(R.id.date_note_line_txt);
        btnDeleteLine = (ImageButton) item.findViewById(R.id.btn_delete_line);
    }
}
