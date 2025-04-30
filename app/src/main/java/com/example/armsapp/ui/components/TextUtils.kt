package com.example.armsapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun BorderTexts(
    textLeft: String,
    textRight: String,
    modifierTextLeft: Modifier = Modifier,
    modifierTextRight: Modifier = Modifier,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textLeft,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = modifierTextLeft
        )
        Text(
            text = textRight,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = modifierTextRight
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BorderTextsPreview() {
    ArmsAppTheme {
        BorderTexts(
            textLeft = "Início",
            textRight = "Fim"
        )
    }
}