package in.fusionbit.shreejeepricelist.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.fusionbit.shreejeepricelist.R;
import in.fusionbit.shreejeepricelist.apimodels.ProductList;

public class CategoryView {

    public View getView() {
        return view;
    }

    private View view;

    public CategoryView(final Context context, ViewGroup parent, final ProductList.CategoriesBean category) {
        view = LayoutInflater.from(context).inflate(R.layout.category_view, parent, false);

        ((TextView) view.findViewById(R.id.tv_categoryName)).setText(category.getCategory_name());

        final LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll_products);

        for (ProductList.CategoriesBean.ProductsBean product : category.getProducts()) {
            ll.addView(new ProductView(context, null, product).getView());
        }

    }

}
