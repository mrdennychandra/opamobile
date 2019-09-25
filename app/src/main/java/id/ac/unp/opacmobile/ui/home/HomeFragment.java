package id.ac.unp.opacmobile.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.preference.SwitchPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import id.ac.unp.opacmobile.R;
import id.ac.unp.opacmobile.adapter.BiblioAdapter;
import id.ac.unp.opacmobile.model.Biblio;

public class HomeFragment extends Fragment implements BiblioAdapter.BiblioSelected {

    private HomeViewModel homeViewModel;
    private SwipeRefreshLayout swipe;
    private RecyclerView list;
    private BiblioAdapter adapter;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        if(root == null){
            root = inflater.inflate(R.layout.fragment_home, container, false);
            swipe = (SwipeRefreshLayout) root.findViewById(R.id.swipe_container);
            list = (RecyclerView) root.findViewById(R.id.list);
            adapter = new BiblioAdapter(getActivity(),this);
            RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity());
            list.setLayoutManager(layout);
            list.setAdapter(adapter);
            getData();
            swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getData();
                            swipe.setRefreshing(false);
                        }
                    },1000);
                }
            });
        }
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading){
                    //message("loading...");
                }
            }
        });
        homeViewModel.message.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                message(s);
            }
        });

    }

    private void message(String message){
        Toast.makeText(getActivity(),message,
                Toast.LENGTH_SHORT).show();
    }

    private void getData(){
        homeViewModel.getBiblios().observe(this, new Observer<List<Biblio>>() {
            @Override
            public void onChanged(List<Biblio> biblioList) {
                adapter.submitList(biblioList);
            }
        });
    }

    @Override
    public void onBiblioSelected(Biblio biblio) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Bundle params = new Bundle();
        params.putString("biblio_id",biblio.biblioId);
        navController.navigate(R.id.action_navigation_home_to_biblioDetailFragment,params);
    }
}