package com.example.armsapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BorderTexts(
    textLeft: String,
    textRight: String,
    modifierTextLeft: Modifier = Modifier,
    modifierTextRight: Modifier = Modifier,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(text = textLeft, modifier = modifierTextLeft
            .weight(1f)
            .padding(start = 20.dp))
        Text(text = textRight, modifier = modifierTextRight.padding(end = 20.dp))
    }
}