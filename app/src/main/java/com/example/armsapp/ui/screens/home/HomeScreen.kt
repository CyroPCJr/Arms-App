package com.example.armsapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.armsapp.R
import com.example.armsapp.data.local.listProjects
import com.example.armsapp.domain.model.EndPoints
import com.example.armsapp.domain.model.Project
import com.example.armsapp.ui.components.BorderTexts
import com.example.armsapp.ui.components.ButtonNavigation
import com.example.armsapp.ui.components.ErrorScreen
import com.example.armsapp.ui.components.LoadImages
import com.example.armsapp.ui.components.LoadingScreen
import com.example.armsapp.ui.components.ProjectCardLayoutList
import com.example.armsapp.ui.components.VideoWithVisibilityHandler
import com.example.armsapp.ui.state.UiState
import com.example.armsapp.ui.viewmodel.PlayerViewModel
import com.example.armsapp.utils.AndroidLogger

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    playerViewModel: PlayerViewModel,
    onClickWeDoScreen: () -> Unit,
    onClickWeAreScreen: () -> Unit,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(),
) {
    val uiState by viewModel.projectsUiStateFirstFour.collectAsStateWithLifecycle()
    when (uiState) {
        is UiState.Error -> {
            ErrorScreen(
                message = (uiState as UiState.Error).message,
            ) {
                viewModel::projectsUiStateFirstFour
            }
        }

        is UiState.Loading -> {
            LoadingScreen()
        }

        is UiState.Success<List<Project>> -> {
            val projects = (uiState as UiState.Success).data
            HomeScreenContent(
                playerViewModel = playerViewModel,
                projects = projects,
                onClickWeDoScreen = onClickWeDoScreen,
                onClickWeAreScreen = onClickWeAreScreen,
                contentPaddingValues = contentPaddingValues,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun HomeScreenContent(
    playerViewModel: PlayerViewModel,
    projects: List<Project>,
    onClickWeDoScreen: () -> Unit,
    onClickWeAreScreen: () -> Unit,
    contentPaddingValues: PaddingValues,
    modifier: Modifier,
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(contentPaddingValues)
                .padding(start = 10.dp, end = 10.dp)
                .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
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

        VideoWithVisibilityHandler(
            viewModel = playerViewModel,
            mediaUrl = EndPoints.PROJECT_REELS,
            tag = "projectReel",
            context = context,
            modifier = Modifier.padding(horizontal = 8.dp),
        )

        BorderTexts(
            textLeft = stringResource(R.string.sub_title2),
            textRight = stringResource(R.string.sub_title3),
            modifier = Modifier.fillMaxWidth(),
        )

        ProjectCardLayoutList(projects)

        ButtonNavigation(textButton = R.string.btn_more_projects, onClick = { onClickWeDoScreen() })

        Text(
            text = stringResource(R.string.sub_title4),
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 42.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(alignment = Alignment.Start),
        )
        Text(
            text = stringResource(R.string.sub_title5),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(alignment = Alignment.End),
        )

        ButtonNavigation(
            textButton = R.string.btn_click_for_more,
            onClick = { onClickWeAreScreen() },
        )

        LoadImages(imageUrl = EndPoints.CEO_PICTURE)

        BorderTexts(
            textLeft = stringResource(R.string.sub_title6),
            textRight = stringResource(R.string.sub_title7),
            modifier = Modifier.fillMaxWidth(),
        )

        VideoWithVisibilityHandler(
            viewModel = playerViewModel,
            mediaUrl = EndPoints.SKETCH_REELS,
            tag = "sketchReel",
            context = context,
            modifier = Modifier.padding(horizontal = 8.dp),
        )
        Spacer(Modifier.height(8.dp))

        BorderTexts(
            textLeft = stringResource(R.string.sub_title8),
            textRight = stringResource(R.string.sub_title9),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    val fakePlayerViewModel = PlayerViewModel(AndroidLogger())

    MaterialTheme {
        HomeScreenContent(
            playerViewModel = fakePlayerViewModel,
            projects = listProjects,
            onClickWeDoScreen = {},
            onClickWeAreScreen = {},
            contentPaddingValues = PaddingValues(0.dp),
            modifier = Modifier,
        )
    }
}
