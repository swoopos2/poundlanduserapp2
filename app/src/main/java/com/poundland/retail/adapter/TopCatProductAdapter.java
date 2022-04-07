package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.LayoutCellTopCateProductTitleItemBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.responseModel.LandingPageBottomResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.TOP_CATEGORY_PRODUCTS;

public class TopCatProductAdapter extends RecyclerView.Adapter<TopCatProductAdapter.ViewResource> {

    private Context context;
    private List<LandingPageBottomResponseModel.TopCatProductsBean> list;
    private SuccessActionListner successActionListner;
    private DrawerListner drawerListner;
    private ModifierSelectionListner modifierSelectionListner;

    public TopCatProductAdapter(Context context, List<LandingPageBottomResponseModel.TopCatProductsBean> list, DrawerListner drawerListner, SuccessActionListner successActionListner, ModifierSelectionListner modifierSelectionListner) {
        this.context = context;
        this.list = list;
        this.successActionListner = successActionListner;
        this.drawerListner = drawerListner;
        this.modifierSelectionListner = modifierSelectionListner;
    }

    @NonNull
    @Override
    public TopCatProductAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_top_cate_product_title_item, parent, false);

        return new TopCatProductAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopCatProductAdapter.ViewResource holder, int position) {


        holder.binding.tvHeadingText.setText(list.get(position).getCat_name());

        TopCategoryProductInnerAdapter myOrderNewAdapter = new TopCategoryProductInnerAdapter(context, list.get(position).getProducts(), new ModifierSelectionListner() {
            @Override
            public void onModifierSelect(int parentPosition, int childPosition) {
                modifierSelectionListner.onModifierSelect(holder.getAdapterPosition(),childPosition);
            }

            @Override
            public void onModifierDeselect(int pos_1st, int pos_2nd) {

            }
        },new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {

            }
        }, new SuccessActionListner() {
            @Override
            public void onSuccessActionListner() {
                successActionListner.onSuccessActionListner();
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        //  holder.binding.rvRecycle.setLayoutManager(layoutManager);
        holder.binding.rvTopCatProductsInner.setLayoutManager(new WrapContentLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.binding.rvTopCatProductsInner.setAdapter(myOrderNewAdapter);

        holder.binding.tvHeadingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerListner.onDrawerSelect(holder.getLayoutPosition(),TOP_CATEGORY_PRODUCTS);
            }
        });
        holder.binding.tvShowAllTopVenues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerListner.onDrawerSelect(holder.getLayoutPosition(),TOP_CATEGORY_PRODUCTS);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellTopCateProductTitleItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
