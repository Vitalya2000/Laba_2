package com.example.laba2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Technologies.Technology> mValues;
    private final MyListFragment.OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Technologies.Technology> items, MyListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        Log.d("MyTag", "Я в MyItem, получил инфу:"+mValues.get(0).getName());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText("" + (position+1)+".");
            holder.mContentView.setText( mValues.get(position).getName());
            holder.mImageView.setImageBitmap(mValues.get(position).getImage());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Подучаем номер View текстом
                    TextView text = (TextView)v.findViewById(R.id.item_number);
                    String numStr = (String)text.getText();
                    numStr = numStr.substring(0, numStr.length()-1);

                    // Переводим в интеджер
                    int num = Integer.parseInt(numStr);

                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(holder.mItem, num-1);
                    }
                }
            });
        }catch (Exception e){
            Log.d("MyTag", "Я в MyItem, получил ошибку:"+ e);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mImageView;
        public Technologies.Technology mItem;



        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.name);
            mImageView = (ImageView) view.findViewById(R.id.imageView2);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
