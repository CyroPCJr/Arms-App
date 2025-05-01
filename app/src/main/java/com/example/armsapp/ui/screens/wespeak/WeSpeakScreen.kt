package com.example.armsapp.ui.screens.wespeak

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.example.armsapp.data.local.listProjects
import com.example.armsapp.domain.model.Project
import com.example.armsapp.ui.components.ButtonNavigation
import com.example.armsapp.ui.components.CardLayout
import com.example.armsapp.ui.components.ErrorScreen
import com.example.armsapp.ui.components.LoadingScreen
import com.example.armsapp.ui.state.UiState
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun WeSpeakScreen(
    viewModel: WeSpeakScreenViewModel,
    modifier: Modifier = Modifier,
    onClickWeDoScreen: () -> Unit,
    onClickWeAreScreen: () -> Unit,
    contentPaddingValues: PaddingValues = PaddingValues(),
) {

    val uiState by viewModel.projectsUiState.collectAsStateWithLifecycle()
    when (uiState) {
        is UiState.Error -> {
            ErrorScreen(
                message = (uiState as UiState.Error).message
            ) {
                viewModel::projectsUiState
            }
        }

        is UiState.Loading -> {
            LoadingScreen()
        }

        is UiState.Success<List<Project>> -> {
            val projectList = (uiState as UiState.Success<List<Project>>).data
            WeSpeakScreenContent(
                projectList = projectList,
                modifier = modifier,
                contentPaddingValues = contentPaddingValues,
                onClickWeDoScreen = onClickWeDoScreen,
                onClickWeAreScreen = onClickWeAreScreen
            )
        }
    }
}

@Composable
private fun WeSpeakScreenContent(
    projectList: List<Project>,
    modifier: Modifier,
    contentPaddingValues: PaddingValues,
    onClickWeDoScreen: () -> Unit,
    onClickWeAreScreen: () -> Unit
) {
    val scrollState = rememberScrollState()
    val paddingVertical = dimensionResource(R.dimen.padding_vertical)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(contentPaddingValues)
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.we_speak_message1),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = paddingVertical)
        )

        Text(
            text = stringResource(R.string.we_speak_message2),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onBackground
        )

        projectList.getOrNull(5)?.let {
            CardLayout(it)
        }

        ButtonNavigation(
            textButton = R.string.btn_check_all_projects,
            onClick = onClickWeDoScreen,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(R.string.sub_title4),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = stringResource(R.string.sub_title5),
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.End)
        )

        ButtonNavigation(
            textButton = R.string.btn_click_for_more,
            onClick = onClickWeAreScreen,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeSpeakScreen() {
    ArmsAppTheme {
        WeSpeakScreenContent(
            projectList = listProjects,
            modifier = Modifier,
            contentPaddingValues = PaddingValues(),
            onClickWeDoScreen = { },
            onClickWeAreScreen = {})
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewModeWeSpeakScreenDark() {
    ArmsAppTheme(darkTheme = true) {
        ArmsAppTheme {
            WeSpeakScreenContent(
                projectList = listProjects,
                modifier = Modifier,
                contentPaddingValues = PaddingValues(),
                onClickWeDoScreen = { },
                onClickWeAreScreen = {})
        }
    }
}