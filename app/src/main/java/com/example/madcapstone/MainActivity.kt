package com.example.madcapstone

//import android.R

//import android.R
import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.madcapstone.BuildConfig.DEBUG
import com.example.madcapstone.ui.home.HomeFragment
import com.example.madcapstone.ui.packing.PackingFragment
import com.example.madcapstone.ui.pay.PayFragment
import com.google.android.gms.maps.MapFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val  fragments = arrayListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

//        val navController = findNavController(R.id.nav_host_fragment)

        navView.setOnNavigationItemSelectedListener(navListener)
        //I added this if statement to keep the selected fragment when rotating the device
        //I added this if statement to keep the selected fragment when rotating the device
        initFragments()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment,
                HomeFragment()
            ).commit()
        }


    }
    private val navListener =
    BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> selectedFragment = fragments[0]
            R.id.navigation_pay -> selectedFragment = fragments[1]
            R.id.navigation_list -> selectedFragment = fragments[2]
            R.id.navigation_map -> selectedFragment = fragments[3]
        }
        if (selectedFragment != null) {
//            fragments.removeAt(item.itemId)
//            fragments.add(item.itemId, supportFragmentManager.fragments.last())
            Log.d("Same", supportFragmentManager.fragments.last().toString())
            Log.d("test", selectedFragment.toString())
            supportFragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment,
                selectedFragment
            ).commit()
        }
        true
    }
    private fun  initFragments(){
        fragments.add(HomeFragment())
        fragments.add(PayFragment())
        fragments.add(PackingFragment())
        fragments.add(com.example.madcapstone.ui.map.MapFragment())
    }
}
