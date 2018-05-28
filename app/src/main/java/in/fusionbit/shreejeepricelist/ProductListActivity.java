package in.fusionbit.shreejeepricelist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
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

    private List<ProductList> productList;

    private List<ProductList> searchedProductList;

    private ProductListAdapter adapter = null;

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
                            productList = new ArrayList<>();
                            adapter = new ProductListAdapter(ProductListActivity.this);
                            rvProductList.setAdapter(adapter);
                            for (ProductList _productList : response.body()) {
                                productList.add(adapter.addProductList(_productList));
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

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callSearch(newText);
                return true;
            }

            private void callSearch(String query) {
                if (adapter != null) {
                    if (query.length() > 0) {
                        searchedProductList = new ArrayList<>();

                        for (int i = 0; i < productList.size(); i++) {
                            final ProductList pl = new ProductList();
                            pl.setCompany_name(productList.get(i).getCompany_name());
                            List<ProductList.CategoriesBean> cList = new ArrayList<>();

                            for (int j = 0; j < productList.get(i).getCategories().size(); j++) {
                                final ProductList.CategoriesBean c = new ProductList.CategoriesBean();
                                c.setCategory_name(productList.get(i).getCategories().get(j).getCategory_name());
                                final List<ProductList.CategoriesBean.ProductsBean> pList = new ArrayList<>();

                                for (int k = 0; k < productList.get(i).getCategories().get(j).getProducts().size(); k++) {

                                    if (productList.get(i).getCategories().get(j).getProducts().get(k).getProduct_name().toLowerCase().contains(query.toLowerCase())) {
                                        final ProductList.CategoriesBean.ProductsBean p = new ProductList.CategoriesBean.ProductsBean();
                                        p.setMrp(productList.get(i).getCategories().get(j).getProducts().get(k).getMrp());
                                        p.setPeriod(productList.get(i).getCategories().get(j).getProducts().get(k).getPeriod());
                                        p.setProduct_name(productList.get(i).getCategories().get(j).getProducts().get(k).getProduct_name());
                                        p.setSrp(productList.get(i).getCategories().get(j).getProducts().get(k).getSrp());

                                        pList.add(p);
                                    }
                                }

                                if (pList.size() > 0) {
                                    c.setProducts(pList);
                                    cList.add(c);
                                }
                            }

                            if (cList.size() > 0) {
                                pl.setCategories(cList);
                                searchedProductList.add(pl);
                            }
                        }

                        adapter = new ProductListAdapter(ProductListActivity.this);
                        adapter.setData(searchedProductList);
                        rvProductList.setAdapter(adapter);
                    } else {
                        adapter = new ProductListAdapter(ProductListActivity.this);
                        adapter.setData(productList);
                        rvProductList.setAdapter(adapter);
                    }
                }
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.search) {
            Toast.makeText(this, "Search Products", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
