package com.example.plantv2.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.plantv2.R
import com.example.plantv2.db.ProfileDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //recycler_view_profiles.setHasFixedSize(true)
        //recycler_view_profiles.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                val profiles = ProfileDatabase(it).getProfileDao().getAllProfiles()
                recycler_view_profiles.adapter = ProfileAdapter(profiles)
            }

        }

        button_add.setOnClickListener {
            val action = HomeFragmentDirections.actionAddProfile()
            Navigation.findNavController(it).navigate(action)
        }
    }

}
