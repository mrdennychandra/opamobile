package id.ac.unp.opacmobile.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.unp.opacmobile.http.RetrofitClient;
import id.ac.unp.opacmobile.model.Biblio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    MutableLiveData<List<Biblio>> biblios;
    MutableLiveData<Boolean> isLoading ;
    MutableLiveData<String> message;

    public HomeViewModel() {
        biblios = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        message = new MutableLiveData<>();
    }

    public MutableLiveData<List<Biblio>> getBiblios() {
        isLoading.postValue(true);
        RetrofitClient
                .getInstance()
                .getApi()
                .biblio()
                .enqueue(new Callback<List<Biblio>>() {
                    @Override
                    public void onResponse(Call<List<Biblio>> call, Response<List<Biblio>> response) {
                        isLoading.postValue(false);
                        if(response != null){
                            if(response.isSuccessful()){
                                biblios.postValue(response.body());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Biblio>> call, Throwable t) {
                        isLoading.postValue(false);
                        message.postValue(t.getMessage());
                    }
                });
        return biblios;
    }
}