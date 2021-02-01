package ir.vbile.app.taravaz.feautre.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ir.vbile.app.taravaz.R
import ir.vbile.app.taravaz.common.TarAvazActivity
import ir.vbile.app.taravaz.common.setupWithNavController
import ir.vbile.app.taravaz.extentions.margin
import ir.vbile.app.taravaz.extentions.setVisibleOrGone
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : TarAvazActivity() {
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationMain)

        val navGraphIds = listOf(
            R.navigation.genre,
            R.navigation.search,
            R.navigation.home,
            R.navigation.music,
            R.navigation.artist
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navHostContainer,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, { navController ->
            setupNavigationView(controller.value!!)
            //      setupActionBarWithNavController(navController)
        })
        currentNavController = controller
        bottomNavigationMain.selectedItemId = R.id.home
    }

    private fun setupNavigationView(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    runOnUiThread {
                        navHostContainer.margin(
                            top = 0f,
                            bottom = 0f
                        )
                        toolbar.setVisibleOrGone(false)
                        bottomNavigationMain.setVisibleOrGone(false)
                    }
                }
                else -> {
                    runOnUiThread {
                        navHostContainer.margin(
                            top = 56f,
                            bottom = 56f
                        )
                        toolbar.setVisibleOrGone(true)
                        bottomNavigationMain.setVisibleOrGone(true)
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}