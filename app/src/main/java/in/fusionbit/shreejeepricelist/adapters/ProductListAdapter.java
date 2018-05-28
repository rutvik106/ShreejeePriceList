package in.fusionbit.shreejeepricelist.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.fusionbit.shreejeepricelist.ProductListActivity;
import in.fusionbit.shreejeepricelist.apimodels.ProductList;
import in.fusionbit.shreejeepricelist.viewholders.VhProductListItem;

public class ProductListAdapter extends RecyclerView.Adapter {

    final Context context;

    List<ProductList> productLists;

    public ProductListAdapter(Context context) {
        this.context = context;
        productLists = new ArrayList<>();
    }

    public ProductList addProductList(ProductList list) {
        productLists.add(list);
        notifyItemInserted(productLists.size());
        return list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return VhProductListItem.create(context, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VhProductListItem.bind((VhProductListItem) holder, productLists.get(position));
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public void setData(List<ProductList> productList) {
        this.productLists = productList;
        notifyDataSetChanged();
    }

}
