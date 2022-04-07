package com.poundland.retail.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellInnerComboOfferAddCartBinding;
import com.poundland.retail.databinding.LayoutCellInnerComboOfferBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.ComboOfferListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.responseModel.SingleProductDetailResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;


/*for setting item for the recycler view for   */
public class InnerComboOfferAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    private LayoutInflater mInflater;
    private ComboOfferListner comboOfferListner;
    private List<SingleProductDetailResponseModel.ComboOfferBean> comboOfferBeans;
    private PrefManager prefManager;

    public InnerComboOfferAdapter(Context mContext, List<SingleProductDetailResponseModel.ComboOfferBean> comboOfferBeans, ComboOfferListner comboOfferListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.comboOfferBeans = comboOfferBeans;
        this.comboOfferListner = comboOfferListner;
        prefManager = PrefManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ViewResource(mInflater.inflate(R.layout.layout_cell_inner_combo_offer, parent, false));
            case 1:
                return new ViewResourceAddCart(mInflater.inflate(R.layout.layout_cell_inner_combo_offer_add_cart_, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case 0:
                ViewResource viewResource = (ViewResource) viewHolder;
                viewResource.bindData(position);
                break;
            case 1:
                ViewResourceAddCart viewHolder2 = (ViewResourceAddCart) viewHolder;
                viewHolder2.bindData(position - 1);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return comboOfferBeans == null ? 0 : comboOfferBeans.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == comboOfferBeans.size()) {
            return 1;
        } else {
            return 0;
        }


    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellInnerComboOfferBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bindData(int position) {
            if (position < comboOfferBeans.size()) {


                if (position == 0)
                    binding.tvPlush.setVisibility(View.GONE);
                Glide.with(mContext).load(ApiRequestUrl.BASE_URL + comboOfferBeans.get(position).getImages()).apply(new RequestOptions()
                        .placeholder(R.drawable.ic_app_icon))
                        .into(binding.ivProductImg);
                binding.tvProductName.setText(comboOfferBeans.get(position).getProduct_name());
                binding.rbRating.setRating(Float.parseFloat(comboOfferBeans.get(position).getRating().getRating_value()));
                binding.tvNewPrice.setText(mContext.getString(R.string.pound) + comboOfferBeans.get(position).getNew_price());
                binding.tvOldPrice.setPaintFlags(binding.tvOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                binding.tvOldPrice.setText(mContext.getString(R.string.pound) + comboOfferBeans.get(position).getSelling_price());
                if (comboOfferBeans.get(position).getDiscount_type() == 1) {
                    binding.tvSave.setText(comboOfferBeans.get(position).getDiscount_amount() + mContext.getString(R.string.off));
                } else {
                    binding.tvSave.setText(mContext.getString(R.string.save) + mContext.getString(R.string.pound) + comboOfferBeans.get(position).getDiscount_amount());
                }

            }
        }
    }

    public class ViewResourceAddCart extends RecyclerView.ViewHolder {
        public LayoutCellInnerComboOfferAddCartBinding bindingAddCart;
        public double totalPrice = 0.0;

        ViewResourceAddCart(View itemView) {
            super(itemView);
            bindingAddCart = DataBindingUtil.bind(itemView);

        }

        public void bindData(int position) {

            for (int i = 0; i < comboOfferBeans.size(); i++) {
                // String costEach =comboOfferBeans.get(i).getNew_price();
                totalPrice += Double.parseDouble(comboOfferBeans.get(i).getNew_price());
            }
            bindingAddCart.tvTotalAmount.setText(mContext.getString(R.string.pound) + String.format("%.2f", totalPrice));

            //getString(R.string.pound) + String.format("%.2f", Double.parseDouble(carts_summary.getGrand_total())));

            bindingAddCart.tvAddToCart.setOnClickListener(v -> {

                if (bindingAddCart.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.add_to_cart)) || bindingAddCart.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.go_to_bag))) {

                    if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(comboOfferBeans.get(position).getVenue_id())
                            || prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {

                        prefManager.savePreference(VENUE_ID_IN_CART, comboOfferBeans.get(position).getVenue_id());
                        bindingAddCart.tvAddToCart.setText(mContext.getString(R.string.go_to_bag));
                       // bindingAddCart.tvAddToCart.setBackgroundResource(R.drawable.saffron_filled_rounded_rect);
                        bindingAddCart.tvAddToCart.setTextColor(mContext.getResources().getColor(R.color.color_white));
                        comboOfferListner.onComboOfferListner(-1);

                    } else {
                        DialogUtils.showAlertDialog(mContext, mContext.getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {
                                prefManager.savePreference(VENUE_ID_IN_CART, comboOfferBeans.get(position).getVenue_id());
                                bindingAddCart.tvAddToCart.setText(mContext.getString(R.string.go_to_bag));
                              //  bindingAddCart.tvAddToCart.setBackgroundResource(R.drawable.saffron_filled_rounded_rect);
                                bindingAddCart.tvAddToCart.setTextColor(mContext.getResources().getColor(R.color.color_white));
                                comboOfferListner.onComboOfferListner(-1);
                            }

                            @Override
                            public void onNegativeClick() {

                            }
                        });
                    }

                }/* else if (bindingAddCart.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.go_to_bag))) {
                    Intent goToCart = new Intent(mContext, MyCartActivity.class);
                    mContext.startActivity(goToCart);
                }*/

            });
        }
    }
}
