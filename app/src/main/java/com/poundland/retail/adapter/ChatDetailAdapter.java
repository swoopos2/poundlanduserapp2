package com.poundland.retail.adapter;

import android.app.Activity;
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
import com.poundland.retail.databinding.LayoutCellMineChatBinding;
import com.poundland.retail.databinding.LayoutCellUserChatBinding;
import com.poundland.retail.model.responseModel.MessageDetailResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.CUST;
import static com.poundland.retail.interfaces.Constants.PROFILE_IMAGE;

public class ChatDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private static final int MY_MESSAGE = 0, OTHER_MESSAGE = 1;
    private List<MessageDetailResponseModel.MessagesBean.DataBean> chatList;
    private String image;
    private PrefManager prefManager;

    public ChatDetailAdapter(Activity activity, List<MessageDetailResponseModel.MessagesBean.DataBean> chatList, String image) {
        this.activity = activity;
        this.chatList = chatList;
        this.image = image;
        prefManager = PrefManager.getInstance(activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MY_MESSAGE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_mine_chat, parent, false);
            return new ChatDetailAdapter.ViewResource(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_user_chat, parent, false);
            return new ChatDetailAdapter.ViewResource1(view);
        }
    }

    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == MY_MESSAGE) {
            ViewResource viewResource = (ViewResource) holder;
            viewResource.mineChatBinding.tvMessage.setText(chatList.get(position).getMessage());
            viewResource.mineChatBinding.tvTime.setText(chatList.get(position).getCreated_at());
            if (prefManager.getPreference(PROFILE_IMAGE, "").contains("uploaded/profile/")) {
              /*  Glide.with(activity).load(ApiRequestUrl.BASE_URL + prefManager.getPreference(PROFILE_IMAGE, "")).apply(new RequestOptions()
                        .placeholder(R.drawable.app_icon))
                        .into(((ViewResource) holder).mineChatBinding.ivProfile);*/

                Glide.with(activity).load(ApiRequestUrl.BASE_URL + prefManager.getPreference(PROFILE_IMAGE, ""))
                        .apply(RequestOptions.circleCropTransform()).into(((ViewResource) holder).mineChatBinding.ivProfile);


            } else {
               /* Glide.with(activity).load(prefManager.getPreference(PROFILE_IMAGE, ""))
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.profile_placeholder))
                        .into(((ViewResource) holder).mineChatBinding.ivProfile);*/



                Glide.with(activity).load(prefManager.getPreference(PROFILE_IMAGE, ""))
                        .apply(RequestOptions.circleCropTransform()).into(((ViewResource) holder).mineChatBinding.ivProfile);


            }


        } else {
            final ViewResource1 viewResource = (ViewResource1) holder;
           /* Glide.with(activity).load(image).apply(new RequestOptions()
                    .placeholder(R.drawable.profile_placeholder))
                    .into(((ViewResource1) holder).userChatBinding.ivProfile);*/



            Glide.with(activity).load(image)
                    .apply(RequestOptions.circleCropTransform()).into(((ViewResource1) holder).userChatBinding.ivProfile);


            // viewResource.userChatBinding.tvTitle.setText(chatList.get(position).getVenue_name());
            viewResource.userChatBinding.tvTitle.setText(chatList.get(position).getCreated_at());
            viewResource.userChatBinding.tvMessage.setText(chatList.get(position).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return chatList == null ? 0 : chatList.size();
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        private LayoutCellMineChatBinding mineChatBinding;

        public ViewResource(View itemView) {
            super(itemView);
            mineChatBinding = DataBindingUtil.bind(itemView);
        }
    }

    public class ViewResource1 extends RecyclerView.ViewHolder {
        private LayoutCellUserChatBinding userChatBinding;

        ViewResource1(View itemView) {
            super(itemView);
            userChatBinding = DataBindingUtil.bind(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getFrom().equals(CUST)) {
            return MY_MESSAGE;
        } else {
            return OTHER_MESSAGE;
        }
    }
}