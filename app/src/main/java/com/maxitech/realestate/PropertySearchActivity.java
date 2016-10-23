package com.maxitech.realestate;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Avyukt on 10/22/2016.
 */
public class PropertySearchActivity extends BaseActivity{

    CollapsingToolbarLayout collapsingToolbarLayout;
    private CoordinatorLayout ll_Body;
    TabLayout tabLayout;
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();
    TextView myspinner;
    AlertDialog.Builder alertDialog;
    AlertDialog alert;
    ListView alertview;
    View view;
    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    public void initial() {
        //getSupportActionBar().hide();
        ll_Body=(CoordinatorLayout)inflater.inflate(R.layout.activity_main,null);
        llMiddle.addView(ll_Body, android.app.ActionBar.LayoutParams.MATCH_PARENT, android.app.ActionBar.LayoutParams.MATCH_PARENT);
        initializeView();
        alertview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myspinner.setText(list.get(position));
                alert.hide();
            }
        });
        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setView(view);
        alert = alertDialog.create();
        alert.getWindow().setBackgroundDrawableResource(android.R.color.white);
        myspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initializeView() {
        collapsingToolbarLayout = (CollapsingToolbarLayout)ll_Body.findViewById(R.id.collapsing_toolbar);
        myspinner = (TextView)ll_Body.findViewById(R.id.spinner);
        addspinnervalues();
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.alertdailogcustom, null);
        alertview = (ListView) view.findViewById(R.id.lvcustom);
        adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.customalertlsit,R.id.customtext,list);
        alertview.setAdapter(adapter);
        Toolbar toolbar = (Toolbar)ll_Body.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setContentInsetsAbsolute(0,0);
        toolbar.getMenu().clear();
        toolbar.setNavigationIcon(null);
        collapsingToolbarLayout = (CollapsingToolbarLayout)ll_Body.findViewById(R.id.collapsing_toolbar);
        dynamicToolbarColor();
        toolbarTextAppernce();
        tabLayout = (TabLayout)ll_Body.findViewById(R.id.mytabs);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        pager = (ViewPager) findViewById(R.id.myviewpager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        //  myPager=new MyPager(getSupportFragmentManager(),tabLayout.getTabCount());
        pager.setAdapter(pagerAdapter);


    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }*/

    private void dynamicToolbarColor() {
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }

    public void addspinnervalues() {
        list.add("Manoj");
        list.add("Mallikarjun");
        list.add("Abinash");
        list.add("Mahesh");
        list.add("Veeru");
        list.add("Manoj");
    }
}
