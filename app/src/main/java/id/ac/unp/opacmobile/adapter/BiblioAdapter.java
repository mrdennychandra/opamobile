package id.ac.unp.opacmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import id.ac.unp.opacmobile.R;
import id.ac.unp.opacmobile.model.Biblio;

public class BiblioAdapter
        extends ListAdapter<Biblio, BiblioAdapter.ViewHolder> {

    Context context;
    BiblioSelected callBack;

    public static final DiffUtil.ItemCallback<Biblio> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Biblio>() {

                @Override
                public boolean areItemsTheSame(
                        @NonNull Biblio oldItem, @NonNull Biblio newItem) {
                    return oldItem.biblioId == newItem.biblioId;
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull Biblio oldItem, @NonNull Biblio newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public BiblioAdapter(Context context,BiblioSelected callBack) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.row_biblio, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Biblio biblio = getItem(position);
        holder.txtTitle.setText(biblio.title);
        holder.txtIsbn.setText(biblio.isbnIssn);
        Glide.with(context).load(biblio.image).into(holder.imgFoto);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onBiblioSelected(biblio);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtIsbn;
        ImageView imgFoto;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtIsbn = (TextView) itemView.findViewById(R.id.txt_isbn);
            imgFoto = (ImageView) itemView.findViewById(R.id.img_foto);
            cardView = (CardView) itemView.findViewById(R.id.card);
        }
    }

    public interface BiblioSelected{
        public void onBiblioSelected(Biblio biblio);
    }


}