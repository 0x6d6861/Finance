package co.heri.finace

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Toast
import androidx.fragment.app.Fragment


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
                Toast.makeText(this, "This is where the QR Code should be", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, ScanCodeActivity::class.java)
                startActivity(intent);
                return true
            }
            R.id.menu_settings -> {
                startSettings()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
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

        val fragmentManager = supportFragmentManager
        if (fragmentManager != null) {
            val ft = fragmentManager.beginTransaction()
            if (ft != null) {
                ft.replace(R.id.rootLayout, fragment)
                ft.commit()
            }
        }
    }


    private fun setupNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)

        // Select first menu item by default and show Fragment accordingly.
        val menu = bottomNavigationView.menu
        selectFragment(menu.getItem(0))

        // Set action to perform when any menu-item is selected.
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            selectFragment(item)
            false
        }

    }

    /**
     * Perform action when any item is selected.
     *
     * @param item Item that is selected.
     */
    protected fun selectFragment(item: MenuItem) {

        item.isChecked = true

        when (item.itemId) {

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
