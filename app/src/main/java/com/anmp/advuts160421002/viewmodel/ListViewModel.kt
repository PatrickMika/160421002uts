package com.anmp.advuts160421002.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.anmp.advuts160421002.model.Hobby
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    val hobbysLD = MutableLiveData<ArrayList<Hobby>>()
    val hobbyLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        hobbyLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.53.61/hobby.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                loadingLD.value = false
                Log.d("showvoley", it)
                val sType = object : TypeToken<List<Hobby>>() { }.type
                val result = Gson().fromJson<List<Hobby>>(it, sType)
                hobbysLD.value = result as ArrayList<Hobby>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley", it.toString())
                hobbyLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}