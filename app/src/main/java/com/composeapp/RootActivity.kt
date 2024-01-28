package com.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.composeapp.testScreen.ShowTestScreen
import com.composeapp.testScreen.TestScreenVm

class RootActivity : ComponentActivity() {
    private val viewModel: TestScreenVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShowTestScreen(vm = viewModel)
        }
    }
}

