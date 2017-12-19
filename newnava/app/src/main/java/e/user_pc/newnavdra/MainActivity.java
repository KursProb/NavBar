package e.user_pc.newnavdra;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Toast;

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

                        String packageName = null;
                        switch (position) {
                            case 1:
                                packageName = "e.user_pc.list";
                                break;
                            case 2:
                                packageName = "e.user_pc.calculat";
                                break;
                            case 6:
                                packageName = "e.user_pc.fonarik";
                                break;
                        }
                        if (packageName == null) {
                            showError(MainActivity.this);
                        } else {
                            openApp(MainActivity.this, packageName);
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

        public static boolean openApp(Context context, String packageName) {
            PackageManager manager = context.getPackageManager();
            try {
                Intent intentForPackage = manager.getLaunchIntentForPackage(packageName);
                if (intentForPackage == null) {
                    showError(context);
                    return false;
                }
                intentForPackage.addCategory(Intent.CATEGORY_LAUNCHER);
                context.startActivity(intentForPackage);
                return true;
            } catch (ActivityNotFoundException e) {
                return false;
            }
        }

    private static void showError(Context context) {
        Toast.makeText(context, "Нет приложения", Toast.LENGTH_SHORT).show();
    }
}