package com.example.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener{


    private List<Portal> mPortals;
    private PortalAdapter mPortal;
    private RecyclerView mRecyclerView;
    GestureDetector mGestureDetector;
    private Portal newPortal;

    public static final String PORTAL_REMINDER = "Portal";
    public static final String WEBPAGE_TEXT = "Webpage";
    public static final int REQUESTCODE = 1234;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.portalRecyclerView);
        mPortals = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mPortal = new PortalAdapter(mPortals);
        mRecyclerView.setAdapter(mPortal);
        newPortal = getIntent().getParcelableExtra(UpdateActivity.EXTRA_PORTAL);

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override

            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        mRecyclerView.addOnItemTouchListener(this);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, WebsiteActivity.class));
                startActivityForResult(new Intent(MainActivity.this, UpdateActivity.class), REQUESTCODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUESTCODE) {
            if(resultCode == RESULT_OK) {
                Portal updatedPortal = data.getParcelableExtra(MainActivity.PORTAL_REMINDER);
                mPortals.add(updatedPortal);
                updateUI();
            }
        }
    }

    private void updateUI() {
        if(mPortal == null) {
             mPortal = new PortalAdapter(mPortals);
             mRecyclerView.setAdapter(mPortal);
        } else {
            mPortal.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

        int mAdapterPosition = recyclerView.getChildAdapterPosition(child);

        if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {

            Portal clickedPortal = mPortals.get(mAdapterPosition);
            Intent intent = new Intent(MainActivity.this, WebviewActivity.class);
            intent.putExtra(WEBPAGE_TEXT, clickedPortal);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {
    }


}
