package in.fusionbit.shreejeepricelist.api;

import java.util.List;

import in.fusionbit.shreejeepricelist.apimodels.ProductList;
import in.fusionbit.shreejeepricelist.apimodels.UserModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    interface User {
        @FormUrlEncoded
        @POST("webservice/webservice.php")
        Call<UserModel> tryLogin(@Field("method") String method, @Field("username") String username,
                                    @Field("password") String password);

        @FormUrlEncoded
        @POST("webservice/webservice.php")
        Call<List<ProductList>> getProductsByUserId(@Field("method") String method, @Field("admin_id") String adminId);

    }

}
