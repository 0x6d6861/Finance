package co.heri.finace

import android.app.Fragment
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Toast;
import `in`.mrasif.libs.easyqr.EasyQR
import `in`.mrasif.libs.easyqr.QRScanner
import android.app.Activity
import android.content.Intent




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupNavigationView();

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.itemId) {
            R.id.menu_qr -> {
                val intent = Intent(this, QRScanner::class.java)
                intent.putExtra(EasyQR.IS_TOOLBAR_SHOW,true);
                intent.putExtra(EasyQR.TOOLBAR_DRAWABLE_ID,R.drawable.ic_audiotrack_dark);
                intent.putExtra(EasyQR.TOOLBAR_TEXT,"My QR");
                intent.putExtra(EasyQR.TOOLBAR_BACKGROUND_COLOR,"#0588EE");
                intent.putExtra(EasyQR.TOOLBAR_TEXT_COLOR,"#FFFFFF");
                intent.putExtra(EasyQR.BACKGROUND_COLOR,"#000000");
                intent.putExtra(EasyQR.CAMERA_MARGIN_LEFT,50);
                intent.putExtra(EasyQR.CAMERA_MARGIN_TOP,50);
                intent.putExtra(EasyQR.CAMERA_MARGIN_RIGHT,50);
                intent.putExtra(EasyQR.CAMERA_MARGIN_BOTTOM,50);
                intent.putExtra(EasyQR.CAMERA_BORDER,100);
                intent.putExtra(EasyQR.CAMERA_BORDER_COLOR,"#C1000000");
                intent.putExtra(EasyQR.IS_SCAN_BAR,true);
                intent.putExtra(EasyQR.IS_BEEP,true);
                startActivityForResult(intent, EasyQR.QR_SCANNER_REQUEST)
                return true
            }
            R.id.menu_settings -> {
                startSettings()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            EasyQR.QR_SCANNER_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, data!!.getStringExtra(EasyQR.DATA), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun startSettings() {
        Toast.makeText(this, "Settings menu has been clicked", Toast.LENGTH_LONG).show();
    }


    /**
     * Method to push any fragment into given id.
     *
     * @param fragment An instance of Fragment to show into the given id.
     */
    protected fun pushFragment(fragment: Fragment?) {
        if (fragment == null)
            return

        val fragmentManager = fragmentManager
        if (fragmentManager != null) {
            val ft = fragmentManager.beginTransaction()
            if (ft != null) {
                ft.replace(R.id.rootLayout, fragment)
                ft.commit()
            }
        }
    }


    private fun setupNavigationView() {
        val bottomNavigationView = findViewById(R.id.navigation) as BottomNavigationView
        if (bottomNavigationView != null) {

            // Select first menu item by default and show Fragment accordingly.
            val menu = bottomNavigationView.menu
            selectFragment(menu.getItem(0))

            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    object : BottomNavigationView.OnNavigationItemSelectedListener {
                        override fun onNavigationItemSelected(item: MenuItem): Boolean {
                            selectFragment(item)
                            return false
                        }
                    })
        }
    }

    /**
     * Perform action when any item is selected.
     *
     * @param item Item that is selected.
     */
    protected fun selectFragment(item: MenuItem) {

        item.setChecked(true)

        when (item.getItemId()) {

            R.id.navigation_home -> {
                pushFragment(HomeFragment());
            }
            R.id.navigation_profile -> {
                pushFragment(ProfileFragment())
            }
            R.id.navigation_wallet -> {
                pushFragment(WalletFragment())
            }
            R.id.navigation_saving -> {
                pushFragment(SavingsFragment())
            }
        }
    }
}
