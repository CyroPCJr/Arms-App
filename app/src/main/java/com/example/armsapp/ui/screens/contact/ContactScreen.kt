package com.example.armsapp.ui.screens.contact

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.armsapp.R
import com.example.armsapp.domain.model.EndPoints
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun ContactScreen(
    emailSent: Boolean,
    onClickSendEmail: () -> Unit,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(),
) {
    val context = LocalContext.current
    val currentEmailSent by rememberUpdatedState(emailSent)

    LaunchedEffect(currentEmailSent) {
        if (currentEmailSent) {
            Toast.makeText(context, "Email sent", Toast.LENGTH_LONG).show()
        }
    }

    val uriHandler = LocalUriHandler.current
    var email by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(contentPaddingValues)
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Título
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.sub_title8),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(R.string.sub_title9),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            Spacer(Modifier.height(16.dp))

            // Redes sociais
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf(
                    R.drawable.whatsapp_logo to EndPoints.WHATSAPP,
                    R.drawable.instagram_logo to EndPoints.INSTAGRAM,
                    R.drawable.behance_logo to EndPoints.BEHANCE,
                    R.drawable.telegram_logo to EndPoints.TELEGRAM
                ).forEach { (iconRes, url) ->
                    IconButton(onClick = { uriHandler.openUri(uri = url) }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(iconRes),
                            contentDescription = null
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Column {
                Text(
                    text = stringResource(R.string.contact_all_right),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(R.string.contact_email),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(R.string.contact_phone),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.contact_stay_tune),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                placeholder = {
                    Text(text = stringResource(R.string.contact_your_best_email))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    onClickSendEmail()
                }),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactScreenPreview() {
    ArmsAppTheme(darkTheme = false) {
        ContactScreen(emailSent = false, onClickSendEmail = {})
    }
}

@Preview(showBackground = false)
@Composable
private fun ContactScreenPreviewDark() {
    ArmsAppTheme(darkTheme = true) {
        ContactScreen(emailSent = false, onClickSendEmail = {})
    }
}