package e.user_pc.testnavbar;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.heinrichreimersoftware.materialdrawer.DrawerActivity;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerFragmentItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;
import com.heinrichreimersoftware.materialdrawer.theme.DrawerTheme;


public class MainActivity3 extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        setDrawerTheme(
                new DrawerTheme(this)
                        .setBackgroundColorRes(R.color.window_background_3)
                        .setTextColorPrimaryRes(R.color.text_color_primary_3)
                        .setTextColorSecondaryRes(R.color.text_color_secondary_3)
        );

        addItems(new DrawerItem()
                        .setTextPrimary(getString(R.string.lorem_ipsum_short))
                        .setTextSecondary(getString(R.string.lorem_ipsum_long)),
                new DrawerFragmentItem()
                        .setFragment(new ListFragment())
                        .setTextPrimary(getString(R.string.lorem_ipsum_medium)),
                new DrawerFragmentItem()
                        .setFragment(new Fragment())
                        .setImage(ContextCompat.getDrawable(this, R.drawable.ic_flag_white))
                        .setTextPrimary(getString(R.string.lorem_ipsum_short))
                        .setTextSecondary(getString(R.string.lorem_ipsum_long))
        );
        setOnItemClickListener(new DrawerItem.OnItemClickListener() {
            @Override
            public void onClick(DrawerItem item, long id, int position) {
                selectItem(position);
                Toast.makeText(MainActivity3.this, "Clicked item #" + position, Toast.LENGTH_SHORT).show();
            }
        });

        addProfile(new DrawerProfile()
                .setId(1)
                .setRoundedAvatar((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.cat_2))
                .setBackground(ContextCompat.getDrawable(this, R.drawable.cat_wide_1))
                .setName(getString(R.string.lorem_ipsum_short))
        );
        addProfile(new DrawerProfile()
                .setId(2)
                .setRoundedAvatar((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.cat_1))
                .setBackground(ContextCompat.getDrawable(this, R.drawable.cat_wide_2))
                .setName(getString(R.string.lorem_ipsum_short))
                .setDescription(getString(R.string.lorem_ipsum_medium))
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_github:
                String url = "https://github.com/HeinrichReimer/material-drawer";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}