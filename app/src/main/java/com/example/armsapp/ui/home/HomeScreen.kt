package com.example.armsapp.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.armsapp.BuildConfig
import com.example.armsapp.R
import com.example.armsapp.data.listProjects
import com.example.armsapp.model.BottomBarNavItem
import com.example.armsapp.ui.components.BorderTexts
import com.example.armsapp.ui.components.ExoPlayerView
import com.example.armsapp.ui.components.LoadImages
import com.example.armsapp.ui.components.ProjectCardLayoutList
import com.example.armsapp.ui.theme.ArmsAppTheme
import com.example.armsapp.ui.viewmodels.ArmsUIViewModel

@Composable
fun HomeScreen(
    onClickWeDoScreen : ()->Unit,
    onClickWeAreScreen : ()->Unit,
    contentPaddingValues: PaddingValues = PaddingValues(),
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPaddingValues)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.title),
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
        )

        Text(
            text = stringResource(R.string.sub_title),
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
        )

        ExoPlayerView(
            BuildConfig.urlsReelsProject,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        BorderTexts(
            textLeft = stringResource(R.string.sub_title2),
            textRight = stringResource(R.string.sub_title3),
            modifier = modifier.padding(top = 10.dp, bottom = 10.dp)
        )

        ProjectCardLayoutList(listProjects.dropLast(4))

        ButtonNavigation(R.string.btn_more_projects) {
            onClickWeDoScreen()
        }

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

        ButtonNavigation(R.string.btn_click_for_more) {
            onClickWeAreScreen()
        }


        LoadImages(ArmsUIViewModel.URL_LOGO_IMAGE, modifier = modifier)

        BorderTexts(
            textLeft = stringResource(R.string.sub_title6),
            textRight = stringResource(R.string.sub_title7),
            modifier = modifier
        )

        ExoPlayerView(
            BuildConfig.urlsReelsSketch,
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

@Preview
@Composable
private fun HomeScreenPreview() {
    ArmsAppTheme {
        HomeScreen({}, {})
    }
}



