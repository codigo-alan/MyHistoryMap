package com.example.grafitismap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.grafitismap.models.Marker

class GrafitisViewModel: ViewModel() {
    var data = MutableLiveData<List<Marker>>().apply { value = listOf() } //temporal markers list
}