package com.wesley.link;

public class cardImage {
    private  String name;
    private  int imageResourceId;

    public static final cardImage[] images={
            new cardImage("Shop",R.drawable.shop),
            new cardImage("Transport",R.drawable.transport),
            new cardImage("Maps",R.drawable.maps)

    };

    private  cardImage(String name, int imageResourceId){
        this.name=name;
        this.imageResourceId=imageResourceId;
    }

    public String getName (){
        return name;
    }

    public int getImageResourceId(){
        return imageResourceId;
    }
}
