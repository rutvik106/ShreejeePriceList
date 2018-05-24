package in.fusionbit.shreejeepricelist.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.fusionbit.shreejeepricelist.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit=null;

    static Retrofit getClient() {
        if (retrofit == null) {

            //Create Logging interceptor
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //Create OkHttp Client
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    //Set Logging interceptor
                    .addInterceptor(loggingInterceptor)
                    .build();

            //Create GSON  for JSON parsing
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();

            //Create Retrofit instance
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API_BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }

        return retrofit;
    }

}
