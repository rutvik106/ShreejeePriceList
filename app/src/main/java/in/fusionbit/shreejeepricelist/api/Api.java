package in.fusionbit.shreejeepricelist.api;

import java.util.List;

import in.fusionbit.shreejeepricelist.apimodels.ProductList;
import in.fusionbit.shreejeepricelist.apimodels.UserModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class Api {

    public static class User {
        private static ApiInterface.User user =
                ApiClient.getClient().create(ApiInterface.User.class);

        public static Call<UserModel> tryLogin(final String usrename, final String password, final Callback<UserModel> callback) {
            Call<UserModel> call = user.tryLogin("try_login", usrename, password);
            call.enqueue(callback);
            return call;
        }

        public static Call<List<ProductList>> getProductsByUserId(final String adminId, final Callback<List<ProductList>> callback) {
            Call<List<ProductList>> call = user.getProductsByUserId("getProductsByAdminId", adminId);
            call.enqueue(callback);
            return call;
        }
    }


}
