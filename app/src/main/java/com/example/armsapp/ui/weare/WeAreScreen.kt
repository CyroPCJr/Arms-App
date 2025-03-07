package com.example.armsapp.ui.weare

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.armsapp.R
import com.example.armsapp.data.listArmsTeam
import com.example.armsapp.data.listArmsWeAre
import com.example.armsapp.ui.components.BorderTexts
import com.example.armsapp.ui.components.ButtonNavigation
import com.example.armsapp.ui.components.LoadImages
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun WeAreScreen(
    modifier: Modifier = Modifier,
    onClickWeDoScreen: () -> Unit,
    contentPaddingValues: PaddingValues = PaddingValues(),
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .padding(contentPaddingValues)
            .padding(start = 10.dp, end = 10.dp),
    ) {
        Column {
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

        listArmsWeAre.forEach { items ->
            CardInfoExpandable(stringResource(items.title), stringResource(items.description))

        }
        Text(
            text = stringResource(R.string.we_are_sub_title2),
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 42.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()

        )
        Text(
            text = stringResource(R.string.we_are_sub_title3),
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        listArmsTeam.forEach { items ->
            LoadTeamCard(
                name = stringResource(items.name),
                jobPosition = stringResource(items.jobPosition),
                instagramLabel = items.instagramLabel,
                instagramUri = items.instagramUri,
                urlImage = items.imageUrl
            )
        }

        ButtonNavigation(textButton = R.string.btn_lets_make_your_dream) {
            onClickWeDoScreen()
        }

        BorderTexts(
            textLeft = stringResource(R.string.sub_title8),
            textRight = stringResource(R.string.sub_title9),
            modifier = modifier
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
    val rotateState by animateFloatAsState(targetValue = if (expanded) 180f else 0f)
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .fillMaxWidth()
            .padding(top = 4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { expanded = !expanded }
            )
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .weight(weight = 6f)
                        .padding(all = 10.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                IconButton(
                    modifier = Modifier
                        .alpha(alpha = 6f)
                        .weight(weight = 1f)
                        .rotate(rotateState),
                    onClick = { expanded = !expanded },
                ) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }

            }
            if (expanded) {
                Text(
                    text = description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(all = 10.dp)
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
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        LoadImages(urlsContent = urlImage)
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = name, fontSize = 24.sp, color = MaterialTheme.colorScheme.onBackground)
            Text(text = jobPosition, fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary)
            Text(
                text = instagramLabel,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.clickable {
                    uriHandler.openUri(uri = instagramUri)
                })
            //Spacer(modifier = Modifier.height(20.dp))
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

@Preview(showBackground = true)
@Composable
private fun BrandingScreenPreview() {
    ArmsAppTheme {
        WeAreScreen(onClickWeDoScreen = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun CardInfoExpandablePreview() {
    ArmsAppTheme {
        CardInfoExpandable("Hello", "asd")
    }
}