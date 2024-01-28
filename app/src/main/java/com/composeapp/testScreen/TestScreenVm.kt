package com.composeapp.testScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestScreenVm:ViewModel() {
    private val _textMessage = MutableLiveData<String>(null)
    val textMessage:LiveData<String> = _textMessage

    fun foo(){
        _textMessage.value  = "Push completed"
    }
}