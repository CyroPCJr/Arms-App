package com.example.armsapp.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.armsapp.R
import com.example.armsapp.data.listProjects
import com.example.armsapp.model.Project
import com.example.armsapp.ui.theme.ArmsAppTheme

@Composable
fun CardLayout(
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

@Composable
fun ButtonNavigation(
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
fun ProjectCardLayoutPreview() {
    ArmsAppTheme {
        CardLayout(listProjects[0])
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProjectCardLayoutPreviewDark() {
    ArmsAppTheme {
        CardLayout(listProjects[0])
    }
}

@Preview
@Composable
private fun ButtonNavigationPreview() {
    ArmsAppTheme {
        ButtonNavigation(textButton = R.string.btn_more_projects) { }
    }
    
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ButtonNavigationPreviewDarkMode() {
    ArmsAppTheme {
        ButtonNavigation(textButton = R.string.btn_more_projects) { }
    }

}