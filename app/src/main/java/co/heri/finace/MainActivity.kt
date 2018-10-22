package co.heri.finace

import android.app.Fragment
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigationView();

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
