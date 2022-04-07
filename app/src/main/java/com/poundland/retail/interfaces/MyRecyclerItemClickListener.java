package com.poundland.retail.interfaces;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Shakti on 12/19/2017.
 */
public class MyRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    GestureDetector mGestureDetector;
    private OnItemClickListener mListener;

    public MyRecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mListener != null) {
                    mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));


                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {

        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onLongItemClick(View view, int position);
    }
}

/////======+++++++++***************--------- Implement like this ======+++++++++***************---------/////
 /* recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                        recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String V1 = productkList.get(position).getSupplierName().toString();
                        Intent intent =new Intent(getActivity(),ShowDetailctivity.class);
                        intent.putExtra("V5",V5);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );*/