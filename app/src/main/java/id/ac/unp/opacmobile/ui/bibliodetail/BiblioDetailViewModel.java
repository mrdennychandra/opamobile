package id.ac.unp.opacmobile.ui.bibliodetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.unp.opacmobile.http.RetrofitClient;
import id.ac.unp.opacmobile.model.Biblio;
import id.ac.unp.opacmobile.model.BiblioDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BiblioDetailViewModel extends ViewModel {

    MutableLiveData<BiblioDetail> detail;
    MutableLiveData<Boolean> isLoading ;
    MutableLiveData<String> message;

    public BiblioDetailViewModel() {
        detail = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        message = new MutableLiveData<>();
    }

    public MutableLiveData<BiblioDetail> getDetail(String biblioId) {
        isLoading.postValue(true);
        RetrofitClient
                .getInstance()
                .getApi()
                .detail(biblioId)
                .enqueue(new Callback<BiblioDetail>() {
                    @Override
                    public void onResponse(Call<BiblioDetail> call, Response<BiblioDetail> response) {
                        isLoading.postValue(false);
                        if(response != null){
                            if(response.isSuccessful()){
                                detail.postValue(response.body());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BiblioDetail> call, Throwable t) {
                        isLoading.postValue(false);
                        message.postValue(t.getMessage());
                    }
                });
        return detail;
    }

}
