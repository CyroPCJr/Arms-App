package com.example.armsapp.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.armsapp.R
import com.example.armsapp.data.listProjects
import com.example.armsapp.model.EndPoints
import com.example.armsapp.ui.components.BorderTexts
import com.example.armsapp.ui.components.ExoPlayerView
import com.example.armsapp.ui.components.LoadImages
import com.example.armsapp.ui.components.ProjectCardLayoutList
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClickWeDoScreen: () -> Unit,
    onClickWeAreScreen: () -> Unit,
    contentPaddingValues: PaddingValues = PaddingValues(),
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPaddingValues)
            .padding(start = 10.dp, end = 10.dp)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.title),
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 42.sp,
            textAlign = TextAlign.Center,
        )

        Text(
            text = stringResource(R.string.sub_title),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onBackground,
        )

        ExoPlayerView(
            EndPoints.PROJECT_REELS,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        Row {
            Text(
                text = stringResource(R.string.sub_title2),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(R.string.sub_title3),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        ProjectCardLayoutList(listProjects.dropLast(4))

        ButtonNavigation(R.string.btn_more_projects) {
            onClickWeDoScreen()
        }

        Text(
            text = stringResource(R.string.sub_title4),
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 42.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(alignment = Alignment.Start)
        )
        Text(
            text = stringResource(R.string.sub_title5),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(alignment = Alignment.End)
        )

        ButtonNavigation(R.string.btn_click_for_more) {
            onClickWeAreScreen()
        }

        LoadImages(EndPoints.CEO_PICTURE, modifier = modifier)

        BorderTexts(
            textLeft = stringResource(R.string.sub_title6),
            textRight = stringResource(R.string.sub_title7),
            modifier = modifier
        )

        ExoPlayerView(
            EndPoints.SKETCH_REELS,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        BorderTexts(
            textLeft = stringResource(R.string.sub_title8),
            textRight = stringResource(R.string.sub_title9),
            modifier = modifier
        )

    }
}

@Composable
private fun ButtonNavigation(
    @StringRes textButton: Int,
    onClick: () -> Unit,
) {
    Button(onClick = onClick) {
        Text(text = stringResource(textButton))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Icon(
            Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ArmsAppTheme {
        HomeScreen(onClickWeDoScreen = {}, onClickWeAreScreen = {})
    }
}



