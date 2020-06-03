package com.wesley.link;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {
    MainActivity activity= new MainActivity();
    private String[] captions; //card texts
    private int[] imageIds;//card numbers

    public  static  class  ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView=v;
        }
    }

    public CaptionedImagesAdapter(String[] captions, int[] imageIds){
        this.captions = captions;
        this.imageIds = imageIds;
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }

    @NonNull
    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        CardView cv= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);

        return new ViewHolder(cv);
    }

    //Display Cards with Images and text
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView=holder.cardView;
        ImageView imageView= (ImageView) cardView.findViewById(R.id.card_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(),imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView= (TextView) cardView.findViewById(R.id.text_holder);
        textView.setText(captions[position]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                    //Display Alert Dialog to alert user of functionality to be added
                    AlertDialog.Builder myAboutBuilder = new AlertDialog.Builder(view.getContext());
                    myAboutBuilder.setTitle("Information to user");
                    myAboutBuilder.setMessage("MultiApp™ Integration coming soon!!");

                    myAboutBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(view.getContext(), "WJ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    myAboutBuilder.show();


            }
        });
    }

}
