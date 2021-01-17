package com.example.memory;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EasyActivity extends AppCompatActivity {
    //declarations
    GameLogic gameLogic = new GameLogic();
    int pos = 0;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        ImageButton imageButton1 = findViewById(R.id.imageButton1);
        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        ImageButton imageButton3 = findViewById(R.id.imageButton3);
        ImageButton imageButton4 = findViewById(R.id.imageButton4);
        ImageButton imageButton5 = findViewById(R.id.imageButton5);
        ImageButton imageButton6 = findViewById(R.id.imageButton6);
        ImageButton imageButton7 = findViewById(R.id.imageButton7);
        ImageButton imageButton8 = findViewById(R.id.imageButton8);
        ImageButton imageButton9 = findViewById(R.id.imageButton9);
        ImageButton imageButton10 = findViewById(R.id.imageButton10);
        ImageButton imageButton11 = findViewById(R.id.imageButton11);
        ImageButton imageButton12 = findViewById(R.id.imageButton12);

        ImageButton [] buttonList = {
                imageButton1, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6,
                imageButton7, imageButton8, imageButton9, imageButton10, imageButton11, imageButton12
        };



       /* List<R.drawable> images = new ArrayList()<>(
                List.of(R.drawable)
                );*/
        //final TypedArray images = getResources().obtainTypedArray(R.array.easy_tile_images);
        //String[] hList = getResources().getStringArray(R.array.easy_tile_images);
        //final Random random = new Random();
        //final int randomInt = random.nextInt(images.length());


      /*  List<ImageButton> buttonList = new ArrayList<>(
                List.of(imageButton1, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6,
                        imageButton7, imageButton8, imageButton9, imageButton10, imageButton11, imageButton12)
        );*/

        Integer [] images = {
                R.drawable.ic_leaf, R.drawable.ic_moon, R.drawable.ic_mug, R.drawable.ic_plane,
                R.drawable.ic_rugby, R.drawable.ic_star, R.drawable.ic_leaf, R.drawable.ic_moon,
                R.drawable.ic_mug, R.drawable.ic_plane, R.drawable.ic_rugby, R.drawable.ic_star
        };

        //shuffle the images
        List<Integer> imageList = Arrays.asList(images);
        Collections.shuffle(imageList);
        imageList.toArray(images);

        for(; pos < buttonList.length; pos++){
            buttonList[pos].setImageResource(R.drawable.ic_android);
            //buttonList[pos].setImageResource(R.drawable.ic_android);
            buttonList[pos].setTag(R.drawable.ic_android);
            int finalPos = pos;
            AtomicInteger drawableInt = new AtomicInteger((Integer) buttonList[pos].getTag());
            buttonList[pos].setOnClickListener(v -> {
                int flipped = gameLogic.getNumOfFlippedTiles();
                if(flipped < 2){
                    if(drawableInt.get() == R.drawable.ic_android){ //isFaceDown
                        setFaceUp(finalPos,buttonList,images,drawableInt);
                        gameLogic.setIndexOfPreviousTile(finalPos);
                    }else if(drawableInt.get() == images[finalPos]){ //isFaceUp
                        setFaceDown(finalPos,buttonList,drawableInt);
                        gameLogic.setIndexOfPreviousTile(finalPos);
                    }
                }else if(flipped == 2 && !checkForMatch(gameLogic.getIndexOfPreviousTile(),finalPos,images)){ //if 2 cards are up and they aren't the same, flipped them down
                    resetView(buttonList, drawableInt, finalPos);
                }else{
                    int previousTile = gameLogic.getIndexOfPreviousTile();
                    buttonList[finalPos].setTag(R.drawable.ic_matched);
                    buttonList[previousTile].setTag(R.drawable.ic_matched);
                    drawableInt.set((Integer) buttonList[finalPos].getTag());
                    drawableInt.set((Integer) buttonList[previousTile].getTag());
                    Toast.makeText(this, "Match!", Toast.LENGTH_SHORT).show();
                }
                /*if(flipped == 2){
                    int previousTile = gameLogic.getIndexOfPreviousTile();
                    checkForMatch(previousTile, finalPos, images);

                    resetView(buttonList);
                    Toast toastTryAgain = Toast.makeText(this, "Oops, try again", Toast.LENGTH_SHORT);
                    toastTryAgain.show();
                }*/
            });

        }
/*
        for (ImageButton ib : buttonList) {
            if(loop == positionInImgChoices){
                //int resID = images.getResourceId(imgChoices[positionInImgChoices], -1);
                ib.setImageResource(images[positionInImgChoices]);
                positionInImgChoices++;
            }else{
                loop++;
            }
        }*/
            //ib.setImageResource(images.getResourceId(imgChoice, R.drawable.ic_android));//ic_android is the default value



       /* final Class drawableClass = R.drawable.class;
        final Field[] fields = drawableClass.getFields();*/


        /*//for eah imagebutton, set a
        for (ImageButton ib : buttonList) {
            ib.setImageResource((images.getResourceId(imageChoice, R.drawable.ic_android)));
            images.recycle();
        }*/
    }

   /* private boolean isFaceUp(){
        drawableInt.get() == R.drawable.ic_android
    }*/

    private boolean checkForMatch(int previousTile, int finalPos, Integer [] images) throws NullPointerException{
        try{
            if(images[previousTile].equals(images[finalPos])){
                int matched = gameLogic.getNumOfMatchedTiles();
                matched = matched + 1;
                gameLogic.setNumOfMatchedTiles(matched);
            }else{
                return false;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return true;
    }


    /**
     *
     * @param finalPos
     * @param buttonList
     * @param drawableInt
     */
    private void setFaceDown(int finalPos, ImageButton[] buttonList,  AtomicInteger drawableInt) {
        buttonList[finalPos].setImageResource(R.drawable.ic_android);
        buttonList[finalPos].setTag(R.drawable.ic_android);
        drawableInt.set((Integer) buttonList[finalPos].getTag());
        int flipped = gameLogic.getNumOfFlippedTiles();
        int newFlipped = flipped - 1;
        gameLogic.setNumOfFlippedTiles(newFlipped);
    }

    /**
     *
     * @param finalPos
     * @param imageButtons
     * @param images
     * @param tag
     */
    private void setFaceUp(int finalPos, ImageButton [] imageButtons, Integer [] images,  AtomicInteger tag) {
        imageButtons[finalPos].setImageResource(images[finalPos]);
        imageButtons[finalPos].setTag(images[finalPos]);
        tag.set((Integer) imageButtons[finalPos].getTag());
        int flipped = gameLogic.getNumOfFlippedTiles();
        int newFlipped = flipped + 1;
        gameLogic.setNumOfFlippedTiles(newFlipped);
    }

    /**
     *
     * @param buttonList
     */
    private void resetView(ImageButton [] buttonList, AtomicInteger drawableInt, int finalPos) {
        /*for (ImageButton ib : imageButtons) {
            if(drawableInt.get() == R.drawable.ic_matched){

            }else{
                ib.setImageResource(R.drawable.ic_android);
                //buttonList[pos].setImageResource(R.drawable.ic_android);
                ib.setTag(R.drawable.ic_android);
            }
        }*/

        buttonList[finalPos].setImageResource(R.drawable.ic_android);
        buttonList[finalPos].setTag(R.drawable.ic_android);
        drawableInt.set((Integer) buttonList[finalPos].getTag());
        int previousTile = gameLogic.getIndexOfPreviousTile();
        buttonList[previousTile].setImageResource(R.drawable.ic_android);
        gameLogic.setNumOfFlippedTiles(0);
    }
}

