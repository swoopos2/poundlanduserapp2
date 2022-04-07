package com.poundland.retail.activityHospitality.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.databinding.LayoutCelllMyCartHospitalityBinding;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.EditTextChangedAction;
import com.poundland.retail.interfaces.EditTextChangedListener;
import com.poundland.retail.interfaces.UpdateCartListner;
import com.poundland.retail.model.responseModel.GetCartSummaryHospResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.STORE;


public class MyCartHospitalityAdapter extends RecyclerView.Adapter<MyCartHospitalityAdapter.ViewResource> {

    private Activity activity;
    private List<GetCartSummaryHospResponseModel.CartsBean> myCartList;
    private UpdateCartListner cartListner;
    private DrawerListner drawerListner;
    private int count;
    private double price;
    private String totalprice;
    Context mContext;

    public MyCartHospitalityAdapter(Context mContext, List<GetCartSummaryHospResponseModel.CartsBean> myCartList,
                                    UpdateCartListner cartListner, DrawerListner drawerListner) {
        this.mContext = mContext;
        this.myCartList = myCartList;
        this.cartListner = cartListner;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public MyCartHospitalityAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_celll_my_cart_hospitality, parent, false);
        return new MyCartHospitalityAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCartHospitalityAdapter.ViewResource holder, int position) {
//        holder.binding.ivEditProduct.setVisibility(View.INVISIBLE);
        if (position == 0) {
            holder.binding.tvShopName.setVisibility(View.VISIBLE);
            holder.binding.ivVenueImage.setVisibility(View.VISIBLE);
        } else {
            holder.binding.tvShopName.setVisibility(View.GONE);
            holder.binding.ivVenueImage.setVisibility(View.GONE);
        }


        if (myCartList.get(position).getAvl_quantity() < 1) {
            holder.binding.ivItemOutOfStock.setVisibility(View.VISIBLE);
            holder.binding.rlUpdate.setVisibility(View.GONE);
            holder.binding.tvNewPrice.setVisibility(View.GONE);
        } else {
            holder.binding.ivItemOutOfStock.setVisibility(View.GONE);
            holder.binding.rlUpdate.setVisibility(View.VISIBLE);
            holder.binding.tvNewPrice.setVisibility(View.VISIBLE);
        }

        StringBuilder sb = new StringBuilder();
        if (myCartList.get(position).getAdd_on() != null) {
            for (int i = 0; i < myCartList.get(position).getAdd_on().size(); i++) {
                sb.append(myCartList.get(position).getAdd_on().get(i).getName() + " X" + myCartList.get(position).getAdd_on().get(i).getQty());
                if (i != myCartList.get(position).getAdd_on().size() - 1) {
                    sb.append(", ");
                }
            }
            holder.binding.tvModifier.setText(TextUtils.isEmpty(sb) ? "" : "(" + sb + ")");
        }

        holder.binding.tvShopName.setText(myCartList.get(position).getVenue_name());


        String modifName="";
        if (!TextUtils.isEmpty(myCartList.get(position).getModifier_name())) {
            modifName = myCartList.get(position).getModifier_name().equals("S") ? "" : myCartList.get(position).getModifier_name();
        }

        if (modifName != null && modifName.length() > 0) {
            holder.binding.tvProductName.setText(myCartList.get(position).getProduct_name() + " (" + modifName + ")");
        } else holder.binding.tvProductName.setText(myCartList.get(position).getProduct_name());


        if (myCartList.get(position).getAllergens() != null && myCartList.get(position).getAllergens().size() > 0) {
            holder.binding.llAllergy.setVisibility(View.VISIBLE);
            holder.binding.tvAllergyName.setText(String.join(", ", myCartList.get(position).getAllergens()));
        }

        if (myCartList.get(position).getCaseqty() > 0) {
            holder.binding.tvItemCount.setText(String.valueOf(myCartList.get(position).getCaseqty()));
        } else
            holder.binding.tvItemCount.setText(String.valueOf(myCartList.get(position).getQuantities()));

        holder.binding.tvNewPrice.setText(TextUtils.isEmpty(myCartList.get(position).getNew_price()) ? "" : mContext.getString(R.string.pound) + myCartList.get(position).getNew_price());

        Glide.with(mContext).load(TextUtils.isEmpty(myCartList.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + myCartList.get(position).getProduct_image() : ApiRequestUrl.BASE_URL + myCartList.get(position).getModifier_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivProductImg);

        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_VENUE + myCartList.get(position).getVenue_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivVenueImage);


        holder.binding.tvAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myCartList.get(position).getAvl_quantity() < 1) {
                    return;
                }

                    if (myCartList.size() > 0) {
                        count = Integer.parseInt(holder.binding.tvItemCount.getText().toString());
                        cartListner.onIncrease(holder.getAdapterPosition(), String.valueOf(count));
                    }


            }
        });
        holder.binding.tvSubtractItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myCartList.get(position).getAvl_quantity() < 1) {
                    return;
                }

                    if (myCartList.size() > 0) {
                        count = Integer.parseInt(holder.binding.tvItemCount.getText().toString());
                        cartListner.onDecrease(holder.getAdapterPosition(), String.valueOf(count));
                    }

            }
        });

        holder.binding.tvProductRemove.setOnClickListener(new View.OnClickListener() {
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
                //  drawerListner.onDrawerSelect(holder.getAdapterPosition(), UNIVERSAL_CODE);
            }
        });

        holder.binding.rlInputMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (holder.binding.llInputMessage.getVisibility() == View.GONE) {
                    holder.binding.llInputMessage.setVisibility(View.VISIBLE);
                    holder.binding.ivItemNote.setImageResource(R.drawable.ic_minus);
                } else {
                    holder.binding.llInputMessage.setVisibility(View.GONE);
                    holder.binding.ivItemNote.setImageResource(R.drawable.ic_add);
                }


            }
        });

        holder.binding.etMessage.addTextChangedListener(new EditTextChangedListener(mContext, holder.binding.etMessage, Constants.ACTION_NO, new EditTextChangedAction() {
            @Override
            public void onAfterTextChanged(EditText editText, int action, Editable s) {
                myCartList.get(holder.getAdapterPosition()).setmItem_message(editText.getText().toString());
            }
        }));

        holder.binding.rlTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), STORE);
            }
        });

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
        public LayoutCelllMyCartHospitalityBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
