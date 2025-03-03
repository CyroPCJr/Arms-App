package com.example.armsapp.ui.contact

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.armsapp.model.EndPoints
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun ContactScreen(
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

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Row {
                        val uriHandler = LocalUriHandler.current
                        IconButton(onClick = {
                            uriHandler.openUri(uri = EndPoints.WHATSAPP)
                        }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.whatsapp_logo),
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = {
                            uriHandler.openUri(uri = EndPoints.INSTAGRAM)
                        }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.instagram_logo),
                                contentDescription = null
                            )
                        }

                        IconButton(onClick = {
                            uriHandler.openUri(uri = EndPoints.BEHANCE)
                        }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.behance_logo),
                                contentDescription = null
                            )
                        }

                        IconButton(onClick = {
                            uriHandler.openUri(uri = EndPoints.TELEGRAM)
                        }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.telegram_logo),
                                contentDescription = null
                            )
                        }
                    }

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
            }

            Text(
                text = stringResource(R.string.contact_stay_tune),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var email by remember { mutableStateOf("") }
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
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactScreenPreview() {
    ArmsAppTheme(darkTheme = false) {
        ContactScreen({})
    }
}

@Preview(showBackground = false)
@Composable
private fun ContactScreenPreviewDark() {
    ArmsAppTheme(darkTheme = true) {
        ContactScreen({})
    }
}