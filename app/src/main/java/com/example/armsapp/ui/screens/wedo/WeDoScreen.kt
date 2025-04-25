package com.example.armsapp.ui.screens.wedo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.armsapp.R
import com.example.armsapp.data.local.listProjects
import com.example.armsapp.domain.model.EndPoints
import com.example.armsapp.ui.components.BorderTexts
import com.example.armsapp.ui.components.ButtonNavigation
import com.example.armsapp.ui.components.LoadImages
import com.example.armsapp.ui.components.ProjectCardLayoutList
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun WeDoScreen(
    onClickWeAreScreen: () -> Unit,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(),
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPaddingValues)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.we_do_message1),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(
                    top = dimensionResource(R.dimen.padding_vertical),
                    bottom = dimensionResource(R.dimen.padding_vertical),
                    start = 8.dp
                )
        )
        Row(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(end = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.we_do_message2),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(R.dimen.padding_vertical),
                        bottom = dimensionResource(R.dimen.padding_vertical),
                        start = 8.dp
                    )
            )
        }

        ProjectCardLayoutList(projectList = listProjects)

        Text(
            text = stringResource(R.string.sub_title4),
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(start = 8.dp)
        )
        Text(
            text = stringResource(R.string.sub_title5),
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(end = 8.dp)
        )

        Row(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(end = 8.dp)
        ) {
            ButtonNavigation(R.string.btn_check_consult) {
                onClickWeAreScreen()
            }
        }

        LoadImages(imageUrl = EndPoints.CEO_PICTURE)

        BorderTexts(
            textLeft = stringResource(R.string.sub_title8),
            textRight = stringResource(R.string.sub_title9),
            modifier = modifier
        )

    }
}

@Preview(name = "We Do Screen Body", showBackground = true)
@Composable
fun PreviewWeDoScreen() {
    ArmsAppTheme {
        WeDoScreen(onClickWeAreScreen = {})
    }
}

@Preview(name = "We Do Screen in Dark Mode", showBackground = false)
@Composable
fun PreviewWeDoScreenDark() {
    ArmsAppTheme(darkTheme = true) {
        WeDoScreen(onClickWeAreScreen = {})
    }
}