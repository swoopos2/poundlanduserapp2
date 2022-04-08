package com.poundland.retail.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.activity.LoginActivity;
import com.poundland.retail.activity.MainActivity;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.FragmentTutorialFourBinding;

import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;

public class TutorialFourFragment extends Fragment {
    private FragmentTutorialFourBinding binding;
    private Context context;
    private PrefManager prefManager;


    public TutorialFourFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tutorial_four, container, false);

                Glide.with(this).load(R.drawable.poundland_banner)
                .apply(new RequestOptions().placeholder(R.drawable.poundland_banner)).
                into(binding.ivTopImage);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefManager = PrefManager.getInstance(getActivity());
        init();
    }

    private void init() {
        binding.tvClickNCollect.setText(" Loyalty Rewards ");
        binding.text.setText(" Get loyalty Rewards and"+"\n"+" exchange vouchers from" + "\n" + " local retailers ");
        binding.next.setText(" Get Started ");
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!prefManager.isPreferenceExists(AUTH_TOKEN)) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
    }


}
