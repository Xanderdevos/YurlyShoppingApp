package nl.yurly_jewellery.yurlyshoppingapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import nl.yurly_jewellery.yurlyshoppingapp.MyAccount;

public class HomePage extends AppCompatActivity {

    ViewFlipper v_flipper;

    // declare button
    private Toolbar bottomActionBar;
    private ImageButton menuButton, shoppingButton, accountButton, searchButton, favoriteButton;

    ViewPager viewPager;
    TabLayout indicator;

    List<Integer> picture;
    List<String> pictureName;

    //function called when the activity is started
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        indicator=(TabLayout)findViewById(R.id.indicator);

        int images[] = {R.drawable.armband_man_1, R.drawable.armband_man_2, R.drawable.armband_man_3};

        v_flipper = findViewById(R.id.v_flipper);

        for (int image: images) {
            flipperImages(image);
        }




        //actionbar configurations
        //configure toolbar as actionbar
        bottomActionBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(bottomActionBar);
        bottomActionBar.addView(LayoutInflater.from(this).inflate(R.layout.actionbar, null, false));

        //configure actionbar buttons
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Home Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        accountButton = findViewById(R.id.btn_acc);
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // start other activity
                startActivity(new Intent(HomePage.this, MyAccount.class));

            }


        });


    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            HomePage.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < picture.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
    public void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


}
