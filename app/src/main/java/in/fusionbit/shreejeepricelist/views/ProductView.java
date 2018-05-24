package in.fusionbit.shreejeepricelist.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.fusionbit.shreejeepricelist.R;
import in.fusionbit.shreejeepricelist.apimodels.ProductList;

public class ProductView {

    public View getView() {
        return view;
    }

    private View view;

    public ProductView(final Context context, ViewGroup parent, final ProductList.CategoriesBean.ProductsBean product) {
        view = LayoutInflater.from(context).inflate(R.layout.product_view, parent, false);

        ((TextView) view.findViewById(R.id.month)).setText(product.getPeriod());
        ((TextView) view.findViewById(R.id.product)).setText(product.getProduct_name());
        ((TextView) view.findViewById(R.id.mrp)).setText(product.getMrp());
        ((TextView) view.findViewById(R.id.srp)).setText(product.getSrp());

    }
}
