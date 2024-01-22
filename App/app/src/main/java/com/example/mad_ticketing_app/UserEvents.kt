package com.example.mad_ticketing_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_ticketing_app.databinding.ActivityUserEventsBinding

class UserEvents : AppCompatActivity() {

    private lateinit var binding: ActivityUserEventsBinding

    private val homeFragment = HomeFragment()
    private val eventsFragment = EventsFragment()
    private val favoritesFragment = FavoritesFragment()

    private val fragmentManager = supportFragmentManager
    private var activeFragment: Fragment = homeFragment

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView

        // Set up the item selection listener
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    switchFragment(homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.events -> {
                    switchFragment(eventsFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.favorites -> {
                    switchFragment(favoritesFragment)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }


        setContentView(R.layout.activity_main)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.myRecycler)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.default_event_image, "Item " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

    private fun switchFragment(targetFragment: Fragment) {
        fragmentManager.beginTransaction().hide(activeFragment).show(targetFragment).commit()
        activeFragment = targetFragment
    }
}
