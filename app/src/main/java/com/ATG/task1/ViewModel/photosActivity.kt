package com.ATG.task1.ViewModel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ATG.task1.R
import com.ATG.task1.domain.photo
import kotlinx.android.synthetic.main.activity_photos.*

class photosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        val photosViewModel: photosViewModel by viewModels()

        photosRecyclerView.adapter = photosViewModel.photosAdapter
        photosRecyclerView.layoutManager = LinearLayoutManager(this)
        photosViewModel.loadPhotos().observe(this,
            Observer<List<photo>> { list ->
                with(photosViewModel.photosAdapter) {
                    photos.clear()
                    photos.addAll(list)
                    notifyDataSetChanged()
                }
            })
    }

}