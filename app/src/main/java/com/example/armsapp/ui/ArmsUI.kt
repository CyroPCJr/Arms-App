package com.example.armsapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.armsapp.R
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun ArmsUI(modifier: Modifier = Modifier) {
    Column {
        Text(text = stringResource(R.string.title))
        Text(text = stringResource(R.string.sub_title))
        //TODO Componente de video aqui

        Row {
            Text(text = stringResource(R.string.sub_title2), modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.sub_title3),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(name = "ArmsUI", showBackground = true)
@Composable
fun ArmsUIPreview() {
    ArmsAppTheme {
        ArmsUI()
    }
}