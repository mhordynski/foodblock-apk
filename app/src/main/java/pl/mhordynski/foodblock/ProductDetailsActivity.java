package pl.mhordynski.foodblock;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import pl.mhordynski.foodblock.blockchain.models.Product;

public class ProductDetailsActivity extends AppCompatActivity
{
    private GestureDetectorCompat mDetector;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mDetector = new GestureDetectorCompat(this, new GestureListener());


        Intent intent = this.getIntent();
        String productHash = intent.getStringExtra("pl.mhordynski.foodblock.productHash");

        Log.d("FOUND HASH: ", productHash);
        this.product = new Product(productHash);

        setContentView(R.layout.activity_product_details);
        displayProductData();
    }

    private void displayProductData()
    {
        TextView textView = findViewById(R.id.hash_text_view);
        textView.setText(this.product.getName());
        Log.d("PRODUCT NAME: ", this.product.getName());

        ListView listView = findViewById(R.id.event_list);

        ArrayList<String> events = this.product.getEvents();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, events);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener
    {
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 250;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
            {
                return false;
            }
            if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY)
            {
                finish();
                return true;
            }
            return false;
        }
    }
}
