package id.ac.unp.opacmobile.http;

import java.util.List;

import id.ac.unp.opacmobile.model.Biblio;
import id.ac.unp.opacmobile.model.BiblioDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("index.php")
    Call<List<Biblio>> biblio();

    @GET("detail.php")
    Call<BiblioDetail> detail(@Query("biblio_id") String biblioId);
}
