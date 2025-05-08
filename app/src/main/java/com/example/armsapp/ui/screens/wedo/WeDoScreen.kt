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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.armsapp.R
import com.example.armsapp.domain.model.EndPoints
import com.example.armsapp.domain.model.Project
import com.example.armsapp.ui.components.BorderTexts
import com.example.armsapp.ui.components.ButtonNavigation
import com.example.armsapp.ui.components.ErrorScreen
import com.example.armsapp.ui.components.LoadImages
import com.example.armsapp.ui.components.LoadingScreen
import com.example.armsapp.ui.components.ProjectCardLayoutList
import com.example.armsapp.ui.state.UiState
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun WeDoScreen(
    viewModel: WeDoScreenViewModel,
    onClickWeAreScreen: () -> Unit,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(),
) {
    val uiState by viewModel.projectsUiState.collectAsStateWithLifecycle()
    when (uiState) {
        is UiState.Error -> {
            ErrorScreen(
                message = (uiState as UiState.Error).message,
            ) {
                viewModel::projectsUiState
            }
        }

        is UiState.Loading -> {
            LoadingScreen()
        }

        is UiState.Success<List<Project>> -> {
            val projectList = (uiState as UiState.Success).data
            WeDoScreenContent(
                projectList = projectList,
                modifier = modifier,
                contentPaddingValues = contentPaddingValues,
                onClickWeAreScreen = onClickWeAreScreen,
            )
        }
    }
}

@Composable
private fun WeDoScreenContent(
    projectList: List<Project>,
    modifier: Modifier,
    contentPaddingValues: PaddingValues,
    onClickWeAreScreen: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(contentPaddingValues)
                .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = stringResource(R.string.we_do_message1),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier =
                Modifier
                    .align(alignment = Alignment.Start)
                    .padding(
                        top = dimensionResource(R.dimen.padding_vertical),
                        bottom = dimensionResource(R.dimen.padding_vertical),
                        start = 8.dp,
                    ),
        )
        Row(
            modifier =
                Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 8.dp),
        ) {
            Text(
                text = stringResource(R.string.we_do_message2),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
                modifier =
                    Modifier
                        .padding(
                            top = dimensionResource(R.dimen.padding_vertical),
                            bottom = dimensionResource(R.dimen.padding_vertical),
                            start = 8.dp,
                        ),
            )
        }

        ProjectCardLayoutList(projectList = projectList)

        Text(
            text = stringResource(R.string.sub_title4),
            modifier =
                Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 8.dp),
        )
        Text(
            text = stringResource(R.string.sub_title5),
            modifier =
                Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 8.dp),
        )

        Row(
            modifier =
                Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 8.dp),
        ) {
            ButtonNavigation(R.string.btn_check_consult, onClick = { onClickWeAreScreen() })
        }

        LoadImages(imageUrl = EndPoints.CEO_PICTURE)

        BorderTexts(
            textLeft = stringResource(R.string.sub_title8),
            textRight = stringResource(R.string.sub_title9),
            modifier = modifier,
        )
    }
}

@Preview(name = "We Do Screen in Dark Mode", showBackground = false)
@Composable
fun PreviewWeDoScreenDark() {
    ArmsAppTheme(darkTheme = true) {
        WeDoScreenContent(
            projectList = _root_ide_package_.com.example.armsapp.data.local.listProjects,
            modifier = Modifier,
            contentPaddingValues = PaddingValues(16.dp),
            onClickWeAreScreen = {},
        )
    }
}

@Preview(name = "We Do Screen Content Preview", showBackground = true)
@Composable
fun PreviewWeDoScreenContent() {
    ArmsAppTheme {
        WeDoScreenContent(
            projectList = _root_ide_package_.com.example.armsapp.data.local.listProjects,
            modifier = Modifier,
            contentPaddingValues = PaddingValues(16.dp),
            onClickWeAreScreen = {},
        )
    }
}
