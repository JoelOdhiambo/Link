package com.wesley.link.ui.dashboard;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wesley.link.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_holder);
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        List<String> list= new ArrayList<>();
        list.add("ONE");
        list.add("TWO");
        list.add("THREE");
//        list.add("FOUR");
//        list.add("FIVE");



        RecyclerView recyclerView= (RecyclerView)inflater.inflate((R.layout.fragment_dashboard,container,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        String[] carNames= new String[]
        return root;
    }


    private static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public RecyclerViewHolder(LayoutInflater inflater, ViewGroup container){
            super(inflater.inflate(R.layout.card_view,container,false));


        }
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
        //private List<String>mList;
        private String[] captions;
        private int[] imageIds;

        public  class  ViewHolder extends RecyclerView.ViewHolder{
            private CardView cardView;
            public ViewHolder(CardView v) {
                super(v);
                cardView=v;
            }
        }

        public RecyclerViewAdapter(String[]captions, int[]imageIds){
            this.captions=captions;
            this.imageIds=imageIds;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         CardView cv =(CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);

         return new RecyclerViewHolder(cv);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            CardView cardView=holder.cardView;
            ImageView imageView=(ImageView)cardView.findViewById(R.id.card_image);
            Drawable drawable = ContextCompat.getDrawable(cardView.getContext(),imageIds[position]);
            imageView.setImageDrawable(drawable);
            imageView.setContentDescription(captions[position]);
            TextView textView=(TextView)cardView.findViewById(R.id.text_holder);
            textView.setText(captions[position]);
        }

        @Override
        public int getItemCount() {
            return captions.length;
        }
    }

}
