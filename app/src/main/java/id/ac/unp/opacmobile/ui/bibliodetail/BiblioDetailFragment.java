package id.ac.unp.opacmobile.ui.bibliodetail;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import id.ac.unp.opacmobile.R;
import id.ac.unp.opacmobile.model.Biblio;
import id.ac.unp.opacmobile.model.BiblioDetail;

public class BiblioDetailFragment extends Fragment {

    private BiblioDetailViewModel mViewModel;
    TextView txtTitle, txtIsbn,txtAuthors;
    ImageView imgFoto;
    CardView cardView;

    public static BiblioDetailFragment newInstance() {
        return new BiblioDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.biblio_detail_fragment, container, false);
        txtTitle = (TextView) root.findViewById(R.id.txt_title);
        txtIsbn = (TextView) root.findViewById(R.id.txt_isbn);
        txtAuthors = (TextView) root.findViewById(R.id.txt_authors);
        imgFoto = (ImageView) root.findViewById(R.id.img_foto);
        cardView = (CardView) root.findViewById(R.id.card);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BiblioDetailViewModel.class);
        // TODO: Use the ViewModel
        Bundle params = getArguments();
        String biblioId = params.getString("biblio_id");
        mViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading){
                    //message("loading...");
                }
            }
        });
        mViewModel.message.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                message(s);
            }
        });
        getData(biblioId);
    }

    private void message(String message){
        Toast.makeText(getActivity(),message,
                Toast.LENGTH_SHORT).show();
    }

    private void getData(String biblioId){
        mViewModel.getDetail(biblioId).observe(this, new Observer<BiblioDetail>() {
            @Override
            public void onChanged(BiblioDetail biblioDetail) {
                txtTitle.setText(biblioDetail.title);
                txtIsbn.setText(biblioDetail.isbnIssn);
                txtAuthors.setText(biblioDetail.author);
                Glide.with(getActivity()).load(biblioDetail.image).into(imgFoto);
            }
        });
    }
}
