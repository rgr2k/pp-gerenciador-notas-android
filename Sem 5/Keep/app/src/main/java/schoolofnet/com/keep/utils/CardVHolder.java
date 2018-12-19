package schoolofnet.com.keep.utils;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import schoolofnet.com.keep.R;

public class CardVHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.date_note_card_txt)
    public TextView dateNoteCardTxt;
    @BindView(R.id.title_note_card_txt)
    public TextView titleNoteCardTxt;
    @BindView(R.id.btn_delete_card)
    public ImageButton btnDeleteCard;

    public CardVHolder(View item) {
        super(item);
        ButterKnife.bind(this, item);
    }
}
