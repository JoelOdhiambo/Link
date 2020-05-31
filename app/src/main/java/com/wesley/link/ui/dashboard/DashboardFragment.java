package com.wesley.link.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wesley.link.CaptionedImagesAdapter;
import com.wesley.link.R;
import com.wesley.link.cardImage;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        RecyclerView cardRecycler= (RecyclerView)inflater.inflate(R.layout.fragment_dashboard,container,false);

        String[] cardNames= new String[cardImage.images.length];
        for (int i = 0; i<cardNames.length;i++){
            cardNames[i]=cardImage.images[i].getName();

        }

        int[] cardImages= new int[cardImage.images.length];
        for (int i = 0; i<cardImages.length;i++){
            cardImages[i]=cardImage.images[i].getImageResourceId();
        }

        CaptionedImagesAdapter adapter= new CaptionedImagesAdapter(cardNames, cardImages);
        cardRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        cardRecycler.setLayoutManager(layoutManager);
        return cardRecycler;
    }


}
