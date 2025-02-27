package com.example.armsapp.ui.contact

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.armsapp.R
import com.example.armsapp.ui.components.ButtonNavigation
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun ContactScreen(
    onClickWeAreScreen: () -> Unit,
    onClickWeDoScreen: () -> Unit,
    onClickWeSpeakScreen: () -> Unit,
    onClickSendEmail: () -> Unit,
    contentPaddingValues: PaddingValues = PaddingValues(),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(contentPaddingValues)
            .padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = stringResource(R.string.sub_title8),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(R.string.sub_title9),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        HorizontalDivider(thickness = 1.dp, color = Color.Gray)

        val checkIconDarkMode =
            if (isSystemInDarkTheme()) R.drawable.ic_arms_inverted else R.drawable.ic_arms

        Icon(
            imageVector = ImageVector.vectorResource(checkIconDarkMode),
            contentDescription = null
        )

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "ALLRIGHT RESERVED - ARMS 2024",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(text = "hello@arms.com.br", color = MaterialTheme.colorScheme.onBackground)
                    Text(text = "11 96404.4711", color = MaterialTheme.colorScheme.onBackground)
                }
                Column {
                    ButtonNavigation(textButton = R.string.botton_bar_we_are) {
                        onClickWeAreScreen()
                    }
                    ButtonNavigation(textButton = R.string.botton_bar_we_do) {
                        onClickWeDoScreen()
                    }
                    ButtonNavigation(textButton = R.string.botton_bar_speak) {
                        onClickWeSpeakScreen()
                    }
                }
            }

            //TODO: Trocar os hardcode string por string resources
            Text(
                text = "FIQUE POR DENTRO",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var email by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    singleLine = true,
                    placeholder = {
                        Text(text = "SEU MELHOR E-MAIL")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        onClickSendEmail()
                    }),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Enviar",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactScreenPreview() {
    ArmsAppTheme(darkTheme = false) {
        ContactScreen({}, {}, {}, {})
    }
}

@Preview(showBackground = false)
@Composable
private fun ContactScreenPreviewDark() {
    ArmsAppTheme(darkTheme = true) {
        ContactScreen({}, {}, {}, {})
    }
}