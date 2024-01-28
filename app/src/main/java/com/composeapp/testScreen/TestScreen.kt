package com.composeapp.testScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

@Composable
fun ShowTestScreen(vm: TestScreenVm) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ShowContent(vm)
    }
}

@Composable
fun ShowContent(vm: TestScreenVm) {
    val msg = vm.textMessage.observeAsState()

    Column {
        ShowTextMessage(msg = msg.value ?: "Empty")
        Divider()
        ShowActionButton { vm.foo() }

    }
}

@Composable
fun ShowTextMessage(msg: String) {
    Text(text = msg)
}

@Composable
fun ShowActionButton(foo: () -> Unit) {
    Button(onClick = { foo.invoke() }) {
        Text(text = "Action")
    }
}
