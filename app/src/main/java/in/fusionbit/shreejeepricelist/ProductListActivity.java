package in.fusionbit.shreejeepricelist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import in.fusionbit.shreejeepricelist.adapters.ProductListAdapter;
import in.fusionbit.shreejeepricelist.api.Api;
import in.fusionbit.shreejeepricelist.apimodels.ProductList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends BaseActivity {

    @BindView(R.id.rv_productList)
    RecyclerView rvProductList;
    @BindView(R.id.srl_refreshProductList)
    SwipeRefreshLayout srlRefreshProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Product List");

        rvProductList.setLayoutManager(new LinearLayoutManager(this));
        rvProductList.setHasFixedSize(true);


        getProductList(getIntent().getStringExtra("admin_id"));

        srlRefreshProductList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProductList(getIntent().getStringExtra("admin_id"));
            }
        });

        srlRefreshProductList.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

    }

    private void getProductList(final String adminId) {

        if (adminId == null) {
            Toast.makeText(this, "admin_id not found", Toast.LENGTH_SHORT).show();
            finish();
        }

        showProgressDialog("Please wait", "Getting products...");

        Api.User.getProductsByUserId(adminId, new Callback<List<ProductList>>() {
            @Override
            public void onResponse(Call<List<ProductList>> call, Response<List<ProductList>> response) {
                srlRefreshProductList.setRefreshing(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().size() > 0) {
                            final ProductListAdapter adapter = new ProductListAdapter(ProductListActivity.this);
                            rvProductList.setAdapter(adapter);
                            for (ProductList productList : response.body()) {
                                adapter.addProductList(productList);
                            }
                        } else {
                            Toast.makeText(ProductListActivity.this, "No Products to show", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ProductListActivity.this, "No Products found!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ProductListActivity.this, "No Response", Toast.LENGTH_SHORT).show();
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<List<ProductList>> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                hideProgressDialog();
                srlRefreshProductList.setRefreshing(false);
            }
        });

    }


    @Override
    int getLayoutResourceId() {
        return R.layout.activity_product_list;
    }

    public static void start(final Context context, final String adminId) {
        Intent i = new Intent(context, ProductListActivity.class);
        i.putExtra("admin_id", adminId);
        //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    @Override
    public void onBackPressed() {
        confirmLogout();
    }

    private void confirmLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ProductListActivity.this.finish();
                    }
                }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.search){
            Toast.makeText(this, "Search Products", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
