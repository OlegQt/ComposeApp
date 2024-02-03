package com.composeapp.testScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TestScreenVm : ViewModel() {
    private val _textMessage = MutableLiveData<String>(null)
    val textMessage: LiveData<String> = _textMessage

    private val _textInputState = MutableStateFlow(TextInputState())
    val textInputState = _textInputState as StateFlow<TextInputState>

    fun inputText(newTxt: String) {
        val error = checkTextInput(textLine = newTxt)
        _textInputState.value = TextInputState().apply {
            text = newTxt
            label = when {
                newTxt.isEmpty() -> TextInputState.REGULAR_TXT
                error -> TextInputState.ERROR_MSG
                else -> "${newTxt.length} letters"
            }

            isError = error
        }
    }

    private fun checkTextInput(textLine: String): Boolean {
        val pattern: String = "\\d"
        val regex = Regex(pattern)

        return regex.containsMatchIn(textLine)
    }

    fun clearTextInput() {
        inputText("")
    }

    fun addTextLog(newLogString: String) {
        _textMessage.value = _textMessage.value.plus(newLogString).plus("\n")
    }

    class TextInputState() {
        var isError = false
        var text: String = String()
        var label: String = REGULAR_TXT
        var helperText: String = "Put only String"

        companion object {
            const val ERROR_MSG = "Remove digits"
            const val REGULAR_TXT = "Write letters"
        }
    }
}