package com.poundland.retail.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.poundland.retail.R;
import com.poundland.retail.adapter.CategoryDetailsAdapter;
import com.poundland.retail.databinding.DialogShowCategoryListBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.ProductByCategoryResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.SELECT_CATEGORY;

public class DialogShowCategory extends Dialog implements DrawerListner {
    private Context context;
    private LayoutInflater inflater;
    private DrawerListner drawerListner;
    private DialogShowCategoryListBinding binding;
    private List<ProductByCategoryResponseModel.CategoryBean> list;
    private String search;
    public DialogShowCategory(Context context, List<ProductByCategoryResponseModel.CategoryBean> list, String search, DrawerListner drawerListner) {
        super(context);
        this.context = context;
        this.drawerListner = drawerListner;
        this.list = list;
        this.search = search;
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().getAttributes().windowAnimations = R.style.AnimationCenterPopUp;
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_show_category_list, null, false);
        setContentView(binding.getRoot());
        setCancelable(false);
//        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        Window window = getWindow();
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams wmlp = getWindow().getAttributes();
//        wmlp.x = (int) context.getResources().getDimension(R.dimen._2sdp);
//        wmlp.y = (int) context.getResources().getDimension(R.dimen._2sdp);
       /* if (TAG.equals(PRICE)){
            wmlp.gravity = Gravity.CENTER | Gravity.START;
        }else if (TAG.equals(LOCATION)){
            wmlp.gravity = Gravity.CENTER *//*| Gravity.END*//*;
        }else if (TAG.equals(REVIEW)){
            wmlp.gravity = Gravity.CENTER | Gravity.END;
        }*/
        wmlp.gravity = Gravity.CENTER;
        window.setAttributes(wmlp);


        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        setCategoryAdapter();
        binding.ivBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setCategoryAdapter() {
        final GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        CategoryDetailsAdapter homeCategoryAdapter = new CategoryDetailsAdapter(context, list, this, false,search);
        binding.rvCategoryList.setLayoutManager(layoutManager);
        binding.rvCategoryList.setAdapter(homeCategoryAdapter);
    }

    @Override
    public void onDrawerSelect(int position, int clickId) {
        drawerListner.onDrawerSelect(position, SELECT_CATEGORY);
        dismiss();
    }
}
