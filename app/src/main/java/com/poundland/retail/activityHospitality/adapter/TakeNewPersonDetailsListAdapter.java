package com.poundland.retail.activityHospitality.adapter;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.TakeNewPersonDetailsItemViewBinding;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.interfaces.EditTextChangedAction;
import com.poundland.retail.interfaces.EditTextChangedListener;
import com.poundland.retail.model.requestModel.ReservationGuestReqModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.poundland.retail.interfaces.Constants.CONTACT_NO;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.LAST_NAME;


/* for setting item for the recycler view for */
public class TakeNewPersonDetailsListAdapter extends RecyclerView.Adapter<TakeNewPersonDetailsListAdapter.ViewResource> {

    private Context mContext;
    private List<ReservationGuestReqModel.GuestBean> mItems;
    private LayoutInflater mInflater;
    private PrefManager prefManager;
    private LinkedHashMap<Integer, ReservationGuestReqModel.GuestBean> adapterItemViewModel;

    public TakeNewPersonDetailsListAdapter(Context mContext) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mItems = new ArrayList<>();
        prefManager = PrefManager.getInstance(mContext);
        this.adapterItemViewModel = new LinkedHashMap<>();
    }

    public LinkedHashMap<Integer, ReservationGuestReqModel.GuestBean> getAdapterItems() {
        return adapterItemViewModel;
    }

    public void clear() {
        this.mItems.clear();
        notifyDataSetChanged();
    }

    public void addItem(ReservationGuestReqModel.GuestBean mItem) {
        this.mItems.add(mItem);
        notifyItemChanged(this.mItems.size());
    }

    public void addItem(List<ReservationGuestReqModel.GuestBean> mItem) {
        this.mItems.addAll(mItem);
        notifyItemChanged(this.mItems.size());
    }


    @NonNull
    @Override
    public TakeNewPersonDetailsListAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.take_new_person_details_item_view, parent, false);

        return new TakeNewPersonDetailsListAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TakeNewPersonDetailsListAdapter.ViewResource holder, int position) {

        ReservationGuestReqModel.GuestBean model = new ReservationGuestReqModel.GuestBean();

        String name = null;
        if (mItems.get(position).getGuestfname() != null) {
            name = mItems.get(position).getGuestfname();
        }
        if (mItems.get(position).getGuestlname() != null) {
            name += " " + mItems.get(position).getGuestlname();
        }
        holder.binding.labelAvailableTable.setText(position + 1 + " Guest Details");
        if (adapterItemViewModel.containsKey(position)) {
            ReservationGuestReqModel.GuestBean data = adapterItemViewModel.get(position);
            model.setGuestfname(data.getGuestfname());
            model.setGuestlname(data.getGuestlname());
            model.setGuestmobile(data.getGuestmobile());
            model.setSame_house_hold(data.getSame_house_hold());
            model.setGuestEmail(data.getGuestEmail());
        } else {
            model.setGuestfname(name);
            model.setGuestlname(mItems.get(position).getGuestlname());
            model.setGuestmobile(mItems.get(position).getGuestmobile());
            model.setSame_house_hold(mItems.get(position).getSame_house_hold());
            model.setGuestEmail(mItems.get(position).getGuestEmail());
        }
        adapterItemViewModel.put(position, model);
        if (position == 0) {
            holder.binding.ivImportant.setVisibility(View.VISIBLE);
            holder.binding.etName.setText(prefManager.getPreference(FIRST_NAME, ""));
            holder.binding.etLastName.setText(prefManager.getPreference(LAST_NAME, ""));
            holder.binding.etPhone.setText(prefManager.getPreference(CONTACT_NO, ""));
            holder.binding.etEmail.setText(prefManager.getPreference(EMAIL, ""));

            holder.binding.cbSameHouseHold.setChecked(true);
            holder.binding.tvSameHouseHold.setText("Main Household");
            holder.binding.tvSameHouseHold.setTextColor(ContextCompat.getColor(mContext,R.color.app_header_color));

            model.setGuestfname(prefManager.getPreference(FIRST_NAME, ""));
            model.setGuestlname(prefManager.getPreference(LAST_NAME, ""));
            model.setGuestmobile(prefManager.getPreference(CONTACT_NO, ""));
            model.setGuestEmail(prefManager.getPreference(EMAIL, ""));
        }
    }


    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder implements EditTextChangedAction {
        public TakeNewPersonDetailsItemViewBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.etName.addTextChangedListener(new EditTextChangedListener(mContext, binding.etName, Constants.ACTION_NO, this));
            binding.etLastName.addTextChangedListener(new EditTextChangedListener(mContext, binding.etLastName, Constants.ACTION_NO, this));
            binding.etPhone.addTextChangedListener(new EditTextChangedListener(mContext, binding.etPhone, Constants.ACTION_NO, this));
            binding.etEmail.addTextChangedListener(new EditTextChangedListener(mContext, binding.etEmail, Constants.ACTION_NO, this));

            binding.cbSameHouseHold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ReservationGuestReqModel.GuestBean model = adapterItemViewModel.get(getLayoutPosition());
                    if(((CompoundButton) view).isChecked()){
                        model.setSame_house_hold(1);
                        adapterItemViewModel.put(getLayoutPosition(), model);
                    } else {
                        model.setSame_house_hold(0);
                        adapterItemViewModel.put(getLayoutPosition(), model);
                    }
                }
            });

        }

        @Override
        public void onAfterTextChanged(EditText editText, int action, Editable s) {
            ReservationGuestReqModel.GuestBean model = adapterItemViewModel.get(getLayoutPosition());
            switch (editText.getId()) {
                case R.id.etName:
                    model.setGuestfname(editText.getText().toString());
                    adapterItemViewModel.put(getLayoutPosition(), model);
                    break;
                case R.id.etLastName:
                    model.setGuestlname(editText.getText().toString());
                    adapterItemViewModel.put(getLayoutPosition(), model);
                    break;
                case R.id.etPhone:
                    model.setGuestmobile(editText.getText().toString());
                    adapterItemViewModel.put(getLayoutPosition(), model);
                    break;
                case R.id.etEmail:
                    model.setGuestEmail(editText.getText().toString());
                    adapterItemViewModel.put(getLayoutPosition(), model);
                    break;
            }
        }
    }

}
