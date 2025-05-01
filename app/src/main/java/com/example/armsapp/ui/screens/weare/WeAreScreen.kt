package com.example.armsapp.ui.screens.weare

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.armsapp.R
import com.example.armsapp.data.local.listArmsTeam
import com.example.armsapp.data.local.listArmsWeAre
import com.example.armsapp.domain.model.ArmsTeam
import com.example.armsapp.ui.components.BorderTexts
import com.example.armsapp.ui.components.ButtonNavigation
import com.example.armsapp.ui.components.ErrorScreen
import com.example.armsapp.ui.components.LoadImages
import com.example.armsapp.ui.components.LoadingScreen
import com.example.armsapp.ui.state.UiState
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun WeAreScreen(
    viewModel: WeAreScreenViewModel,
    onClickWeDoScreen: () -> Unit,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(),
) {
    val uiState by viewModel.armsTeamUiState.collectAsStateWithLifecycle()
    when (uiState) {
        is UiState.Error -> {
            ErrorScreen(message = (uiState as UiState.Error).message) {
                viewModel::armsTeamUiState
            }
        }

        is UiState.Loading -> {
            LoadingScreen()
        }

        is UiState.Success<*> -> {
            val armsTeamList = (uiState as UiState.Success).data
            WeAreScreenContent(
                armsTeamList = armsTeamList,
                modifier = modifier,
                contentPaddingValues = contentPaddingValues,
                onClickWeDoScreen = onClickWeDoScreen
            )
        }
    }
}

@Composable
private fun WeAreScreenContent(
    armsTeamList: List<ArmsTeam>,
    modifier: Modifier,
    contentPaddingValues: PaddingValues,
    onClickWeDoScreen: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(contentPaddingValues)
            .padding(horizontal = 10.dp),
    ) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            Text(
                text = stringResource(R.string.we_are_title),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 42.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.we_are_sub_title),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }

        listArmsWeAre.forEach { item ->
            CardInfoExpandable(
                title = stringResource(item.title),
                description = stringResource(item.description),
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.we_are_sub_title2),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 42.sp,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.we_are_sub_title3),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )
        }

        armsTeamList.forEach { member ->
            LoadTeamCard(
                name = member.name,
                jobPosition = member.jobPosition,
                instagramLabel = member.instagramLabel,
                instagramUri = member.instagramUrl,
                urlImage = member.imageUrl,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ButtonNavigation(
            textButton = R.string.btn_lets_make_your_dream,
            onClick = onClickWeDoScreen,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        )

        BorderTexts(
            textLeft = stringResource(R.string.sub_title8),
            textRight = stringResource(R.string.sub_title9),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CardInfoExpandable(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "ExpandRotation"
    )

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                expanded = !expanded
            }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotation)
                )
            }

            if (expanded) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun LoadTeamCard(
    name: String,
    jobPosition: String,
    instagramLabel: String,
    instagramUri: String,
    urlImage: String,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LoadImages(imageUrl = urlImage)

            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = jobPosition,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = instagramLabel,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    uriHandler.openUri(uri = instagramUri)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadTeamCardPreview() {
    ArmsAppTheme {
        LoadTeamCard(
            name = "Jean Novaes",
            jobPosition = "Diretor Audiovisual",
            instagramLabel = "@jeanovaes",
            instagramUri = "www",
            urlImage = ""
        )
    }

}

@Preview(name = "WeAreScrenn Content Preview", showBackground = true)
@Composable
private fun BrandingScreenPreview() {
    ArmsAppTheme {
        WeAreScreenContent(armsTeamList = listArmsTeam, modifier = Modifier, PaddingValues()) {}
    }
}

@Preview(name = "Card Info Expandable Preview", showBackground = true)
@Composable
private fun CardInfoExpandablePreview() {
    ArmsAppTheme {
        CardInfoExpandable("Hello", "asd")
    }
}