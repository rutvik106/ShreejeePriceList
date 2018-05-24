package in.fusionbit.shreejeepricelist.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.fusionbit.shreejeepricelist.R;
import in.fusionbit.shreejeepricelist.apimodels.ProductList;
import in.fusionbit.shreejeepricelist.views.CategoryView;

public class VhProductListItem extends RecyclerView.ViewHolder {

    private final Context context;

    @BindView(R.id.ll_categories)
    LinearLayout llCategories;
    @BindView(R.id.tv_companyName)
    TextView tvCompanyName;

    private VhProductListItem(View itemView, Context context) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    public static VhProductListItem create(Context context, ViewGroup parent) {
        return new VhProductListItem(LayoutInflater.from(context)
                .inflate(R.layout.product_list_item_row, parent, false), context);
    }

    public static void bind(final VhProductListItem vh, final ProductList productList) {

        vh.tvCompanyName.setText(productList.getCompany_name());

        for (ProductList.CategoriesBean category : productList.getCategories()) {
            vh.llCategories
                    .addView(new CategoryView( vh.context,
                            null, category).getView());
        }

    }

}
