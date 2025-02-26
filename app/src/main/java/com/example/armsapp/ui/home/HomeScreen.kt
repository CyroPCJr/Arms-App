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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.armsapp.BuildConfig
import com.example.armsapp.R
import com.example.armsapp.data.listProjects
import com.example.armsapp.model.Project
import com.example.armsapp.ui.components.BorderTexts
import com.example.armsapp.ui.components.ExoPlayerView
import com.example.armsapp.ui.components.LoadImages
import com.example.armsapp.ui.theme.ArmsAppTheme
import com.example.armsapp.ui.viewmodels.ArmsUIViewModel

@Composable
fun HomeScreen(
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

        ExoPlayerView(BuildConfig.urlsReelsProject,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp))

        BorderTexts(
            textLeft = stringResource(R.string.sub_title2),
            textRight = stringResource(R.string.sub_title3),
            modifier = modifier.padding(top = 10.dp, bottom = 10.dp)
        )

        ProjectCardList(listProjects.dropLast(4))

        ButtonNavigation(R.string.btn_more_projects) {
            /* TODO: onClick action navigate to 'Fazemos'*/
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
            /* TODO: onClick action navigate to 'Somos'*/
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
            contentDescription = "Localized description",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }
}

@Composable
fun ProjectCardList(projectList: List<Project>) {
    for (project in projectList) {
        ProjectCardLayout(project)
    }
}

@Composable
fun ProjectCardLayout(
    project: Project,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .shadow(
                elevation = 15.dp,
                shape = RectangleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary,
            ),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        )
    ) {
        LoadImages(project.urlImage)
        Row(modifier = modifier) {
            Text(
                text = project.name,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp)
            )
            Text(
                text = project.type,
                modifier = Modifier.padding(end = 20.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ArmsAppTheme {
        HomeScreen()
    }
}

@Preview
@Composable
fun ProjectCardLayoutPreview(modifier: Modifier = Modifier) {
    ArmsAppTheme {
        ProjectCardLayout(listProjects[0])
    }
}

@Preview
@Composable
private fun ProjectCardListPreview() {
    ArmsAppTheme {
        ProjectCardList(listProjects)
    }
}