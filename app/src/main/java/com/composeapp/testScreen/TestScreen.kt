package com.composeapp.testScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeapp.testScreen.theme.AppTheme

@Composable
fun ShowTestScreen(vm: TestScreenVm) {
    val themeMode = vm.themeMode.observeAsState()
    AppTheme(darkTheme = themeMode.value ?: true) {
        Surface(modifier = Modifier.fillMaxSize()) {
            ShowContent(vm)
        }
    }
}

@Composable
fun ShowContent(vm: TestScreenVm) {
    val msg = vm.textMessage.observeAsState()

    Column {
        ShowInputTxtWithHelper(vm = vm)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)
                .weight(1.0f)
        ) {
            ShowTextMessage(msg = msg.value.toString())
        }
        Switch(checked = vm.themeMode.value ?: true, onCheckedChange = { vm.changeThemeMode() })

    }
}

@Composable
fun ShowTextMessage(msg: String) {
    Text(text = msg)
}

@Composable
fun ShowInputTxtWithHelper(vm: TestScreenVm) {
    val txtState = vm.textInputState.collectAsState()
    Column {
        TextField(
            value = txtState.value.text,
            onValueChange = { vm.inputText(it) },
            label = {
                TxtLabel(string = txtState.value.label)
            },
            isError = txtState.value.isError,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontSize = 18.sp),
            leadingIcon = {
                txtAddButton(isEnabled = !txtState.value.isError) {
                    vm.addTextLog(newLogString = txtState.value.text)
                }
            },
            trailingIcon = {
                txtClearButton(isVisible = txtState.value.text.isNotEmpty()) {
                    vm.clearTextInput()
                }
            }
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = txtState.value.helperText,
            color = if (!txtState.value.isError) Color.Gray else Color.Red,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun TxtLabel(string: String) {
    Text(text = string)
}

@Composable
fun txtAddButton(isEnabled: Boolean, doOnPush: () -> Unit) {
    val color = if (isEnabled) Color.DarkGray else Color.Red
    IconButton(onClick = { doOnPush.invoke() }) {
        Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null, tint = color)
    }
}

@Composable
fun txtClearButton(isVisible: Boolean, doOnPush: () -> Unit) {
    if (isVisible) {
        IconButton(onClick = { doOnPush.invoke() }) {
            Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
        }
    }
}
