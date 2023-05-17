package com.example.notesapp

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable.Orientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.fragments.*
import com.example.notesapp.models.Notes
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.MaterialSharedAxis.Axis

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment: Fragment? = HomeFragment();
        var name = "home"
        loadFragment(fragment!!, name, true);

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomMenu);

        val floatingAddNoteBtn=findViewById<FloatingActionButton>(R.id.floatingAddNoteBtn);

        floatingAddNoteBtn.setOnClickListener {
            val noteFragment=AddNoteFragment();
            loadFragment(noteFragment, "addNote", false);
        }

        bottomNavigationView.setOnItemSelectedListener {
            fragment = null;
            when (it.itemId) {
                R.id.homeItem -> {
                    Log.d("Practice", "${supportFragmentManager.fragments}");

                    name = "home"
                    fragment = HomeFragment();
                    loadFragment(fragment!!, name, true);
                }
                R.id.categoryItem -> {
                    Log.d("Practice", "${supportFragmentManager.fragments}");
                    name = "category"
                    fragment = CategoryFragment();
                    loadFragment(fragment!!, name);
                }
                R.id.favItem -> {
                    name = "favorite"
                    fragment = FavoritesFragment();
                    loadFragment(fragment!!, name);
                }
                R.id.settingItem -> {
                    name = "setting"
                    fragment = SettingFragment();
                    loadFragment(fragment!!, name)
                }
                else -> {
                    fragment = HomeFragment();
                    loadFragment(fragment!!, "");

                }
            }

            true
        }

    }

    private fun loadFragment(fragment: Fragment, name: String = "home", isParent: Boolean = false) {

        val fragmentManager = supportFragmentManager;
        val transaction = fragmentManager.beginTransaction();

        if (isParent) {
            transaction.add(R.id.notesFrameLayout, fragment);
            fragmentManager.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            transaction.addToBackStack("home");
        } else {
            transaction.replace(R.id.notesFrameLayout, fragment);
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount;
        if (count == 1) {
            finish();
        }
    }

}