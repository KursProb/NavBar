package e.user_pc.newnavdra;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {



    private Drawer.Result drawerResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализируем Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Инициализируем все фрагменты

        // Инициализируем Navigation Drawer
        drawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_note).withIcon(FontAwesome.Icon.faw_list),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_calculate).withIcon(FontAwesome.Icon.faw_calculator),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_alarmclock).withIcon(FontAwesome.Icon.faw_clock_o),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_weather).withIcon(FontAwesome.Icon.faw_sun_o),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.drawer_flashlight).withIcon(FontAwesome.Icon.faw_flash)
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Скрываем клавиатуру при открытии Navigation Drawer
                        InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }


                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }
                })

                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem)  {

                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                intent = new Intent(MainActivity.this, List.class);
                            } /*else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(MainActivity.this, FragmentCalculate.class);
                            }*/
                            /*} else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(DrawerActivity.this, MultiDrawerActivity.class);
                            } else if (drawerItem.getIdentifier() == 4) {
                                intent = new Intent(DrawerActivity.this, NonTranslucentDrawerActivity.class);
                            } else if (drawerItem.getIdentifier() == 5) {
                                intent = new Intent(DrawerActivity.this, AdvancedActivity.class);
                            }
                            if (intent != null) {
                                DrawerActivity.this.startActivity(intent);
                            }*/

                        }
                     /*   return false;*/
                    }
                })
               // .withSavedInstance(savedInstanceState)
                //.withShowDrawerOnFirstLaunch(true)
                //.withShowDrawerUntilDraggedOpened(true)
                .build();
    }

        @Override
        public void onBackPressed () {
            // Закрываем Navigation Drawer по нажатию системной кнопки "Назад" если он открыт
            if (drawerResult.isDrawerOpen()) {
                drawerResult.closeDrawer();
            } else {
                super.onBackPressed();
            }
        }
    }