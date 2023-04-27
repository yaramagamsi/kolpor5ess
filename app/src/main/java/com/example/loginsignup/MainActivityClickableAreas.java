package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivityClickableAreas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_clickable_areas);
    }
    public boolean onTouch (View v, MotionEvent ev) {
        final int action = ev.getAction();
        // (1)
        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN :
                if (currentResource == R.drawable.p2_ship_default) {
                    nextImage = R.drawable.p2_ship_pressed;
                }
                break;
            case MotionEvent.ACTION_UP :
                // On the UP, we do the click action.
                // The hidden image (image_areas) has three different hotspots on it.
                // The colors are red, blue, and yellow.
                // Use image_areas to determine which region the user touched.
                // (2)
                int touchColor = getHotspotColor (R.id.image_areas, evX, evY);
                // Compare the touchColor to the expected values.
                // Switch to a different image, depending on what color was touched.
                // Note that we use a Color Tool object to test whether the
                // observed color is close enough to the real color to
                // count as a match. We do this because colors on the screen do
                // not match the map exactly because of scaling and
                // varying pixel density.
                ColorTool ct = new ColorTool ();
                int tolerance = 25;
                nextImage = R.drawable.p2_ship_default;
                // (3)
                if (ct.closeMatch (Color.RED, touchColor, tolerance)) {
                    // Do the action associated with the RED region
                    nextImage = R.drawable.p2_ship_alien;
                } else {
                    //...
                }
                break;
        } // end switch
        if (nextImage > 0) {
            imageView.setImageResource (nextImage);
            imageView.setTag (nextImage);
        }
        return true;
    }
}