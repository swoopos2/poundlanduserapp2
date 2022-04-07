package com.poundland.retail.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
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
import com.poundland.retail.databinding.LayoutMyCartItemBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.UpdateCartListner;
import com.poundland.retail.model.responseModel.GetCartWithSummaryResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.STORE;
import static com.poundland.retail.interfaces.Constants.UNIVERSAL_CODE;


public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewResource> {

    private Activity activity;
    //private List<MyCartModel.DataBean.CartBean> myCartList;
    private List<GetCartWithSummaryResponseModel.CartsBean> myCartList;
    private UpdateCartListner cartListner;
    private DrawerListner drawerListner;
    private int count;
    private double price;
    private String totalprice;
    Context mContext;

    public MyCartAdapter(Context mContext, List<GetCartWithSummaryResponseModel.CartsBean> myCartList,
                         UpdateCartListner cartListner, DrawerListner drawerListner) {
        this.mContext = mContext;
        this.myCartList = myCartList;
        this.cartListner = cartListner;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_my_cart_item, parent, false);
        return new MyCartAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCartAdapter.ViewResource holder, int position) {

        if (position == 0) {
            holder.binding.tvVenueName.setVisibility(View.VISIBLE);
            holder.binding.ivVenueImage.setVisibility(View.VISIBLE);
            holder.binding.tvAddMore.setVisibility(View.GONE);
        } else {
            holder.binding.tvVenueName.setVisibility(View.GONE);
            holder.binding.ivVenueImage.setVisibility(View.GONE);
            holder.binding.tvAddMore.setVisibility(View.GONE);
        }
        holder.binding.tvVenueName.setText(myCartList.get(position).getVenue_name());


        if (myCartList.get(position).getAvl_quantity() < 1) {
            holder.binding.ivItemOutOfStock.setVisibility(View.VISIBLE);
            holder.binding.rlUpdate.setVisibility(View.GONE);
            holder.binding.tvNewPrice.setVisibility(View.GONE);
        } else {
            holder.binding.ivItemOutOfStock.setVisibility(View.GONE);
            holder.binding.rlUpdate.setVisibility(View.VISIBLE);
            holder.binding.tvNewPrice.setVisibility(View.VISIBLE);
        }


        if (myCartList.get(position).getCaseqty() > 0) {

            String modifName = myCartList.get(position).getModifier_name();
            if (modifName != null && modifName.length() > 0) {
                holder.binding.tvProductName.setText(myCartList.get(position).getProduct_name() + " [" + modifName + "]" + " (Pack of " + myCartList.get(position).getQuantities() + ")");
            } else
                holder.binding.tvProductName.setText(myCartList.get(position).getProduct_name() + " (Pack of " + myCartList.get(position).getQuantities() + ")");
            holder.binding.tvItemCount.setText(String.valueOf(myCartList.get(position).getCaseqty()));
            holder.binding.tvModifier.setText(TextUtils.isEmpty(modifName) ? "" : "(" + modifName + ")");


        } else {
            String modifName = myCartList.get(position).getModifier_name();
            if (modifName != null && modifName.length() > 0) {
                holder.binding.tvProductName.setText(myCartList.get(position).getProduct_name() + " (" + modifName + ")");
            } else holder.binding.tvProductName.setText(myCartList.get(position).getProduct_name());
            holder.binding.tvItemCount.setText(String.valueOf(myCartList.get(position).getQuantities()));
            holder.binding.tvModifier.setText(TextUtils.isEmpty(modifName) ? "" : "(" + modifName + ")");
        }


        holder.binding.tvNewPrice.setText(TextUtils.isEmpty(myCartList.get(position).getNew_price()) ? "" : mContext.getString(R.string.pound) + myCartList.get(position).getNew_price());
        if (myCartList.get(position).getOffer_id() != 0) {
            holder.binding.tvDiscPrice.setPaintFlags(holder.binding.tvDiscPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.binding.tvDiscPrice.setText(TextUtils.isEmpty(myCartList.get(position).getSelling_price()) ? "" : mContext.getString(R.string.pound) + myCartList.get(position).getSelling_price());
        } else {
            holder.binding.tvDiscPrice.setVisibility(View.GONE);
        }

        Glide.with(mContext).load(TextUtils.isEmpty(myCartList.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + myCartList.get(position).getProduct_image() : ApiRequestUrl.BASE_URL + myCartList.get(position).getModifier_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivProductImg);

        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_VENUE + myCartList.get(position).getVenue_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivVenueImage);


        holder.binding.tvAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myCartList.get(holder.getAdapterPosition()).getAvl_quantity() < 1) {
                    return;
                }

                if (myCartList.size() > 0) {
                    count = Integer.parseInt(holder.binding.tvItemCount.getText().toString());
                    ++count;
                    // holder.binding.tvItemCount.setText(String.valueOf(count));
                    cartListner.onIncrease(holder.getAdapterPosition(), String.valueOf(count));
                }


            }
        });

        holder.binding.tvSubtractItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myCartList.get(holder.getAdapterPosition()).getAvl_quantity() < 1) {
                    return;
                }


                    if (myCartList.size() > 0) {
                        count = Integer.parseInt(holder.binding.tvItemCount.getText().toString());
                        --count;
                        // holder.binding.tvItemCount.setText(String.valueOf(count));
                        cartListner.onDecrease(holder.getAdapterPosition(), String.valueOf(count));
                    }



                /* if (count == 0) {
                    myCartList.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                }*/


            }
        });

        holder.binding.ivProductRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartListner.onDelete(holder.getAdapterPosition());

                // myCartList.remove(holder.getAdapterPosition());
                // notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        holder.binding.ivProductImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), UNIVERSAL_CODE);
            }
        });

        holder.binding.rlTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), STORE);
            }
        });
        holder.binding.tvAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), STORE);
            }
        });
       /* holder.binding.tvModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), UNIVERSAL_CODE);
            }
        });*/
        /*holder.binding.ivEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), UNIVERSAL_CODE);
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return myCartList == null ? 0 : myCartList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        //public LayoutMyCartItemBinding binding;
        public LayoutMyCartItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
