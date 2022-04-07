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
import com.poundland.retail.activity.TutorialActivity;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.FragmentTutorialOneBinding;

import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;

public class TutorialOneFragment extends Fragment {
    private FragmentTutorialOneBinding binding;
    private Context context;
    private PrefManager prefManager;


    public TutorialOneFragment() {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tutorial_one, container, false);
        Glide.with(this).load(R.drawable.ic_click_n_collect_paper_bag4x)
                .apply(new RequestOptions().placeholder(R.drawable.ic_click_n_collect_paper_bag4x)).
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
        binding.tvClickNCollect.setText(" Click & Collect From "+"\n"+ "Local Retailers ");
        binding.text.setText(" Save time waiting in line, skip the "+"\n"+"queue with Click & Collect ");

        binding.skip.setOnClickListener(new View.OnClickListener() {
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
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TutorialActivity) getContext()).getToNext();
            }
        });
    }

}
