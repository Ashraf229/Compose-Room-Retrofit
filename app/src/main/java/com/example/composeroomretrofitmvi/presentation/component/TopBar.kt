package com.example.composeroomretrofitmvi.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String = "Search data here",
    scrollBehavior: TopAppBarScrollBehavior,
    imageVector: ImageVector,
    onNavClick: () -> Unit,
    actionOneIcon:ImageVector=Icons.Rounded.Notifications,
    actionOneIconClick: () -> Unit={},
) {
    TopAppBar(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(100.dp)),
        scrollBehavior = scrollBehavior,
        windowInsets = WindowInsets(top = 0.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant //.copy(0.6f)
        ),
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )

        },
        navigationIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(27.dp)
                    .clickable { onNavClick() }
            )
        },
        actions = {
            Icon(
                imageVector = actionOneIcon,
                contentDescription = "Action One",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(30.dp)
                    .clickable { actionOneIconClick() }
            )

            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .size(30.dp)
            )
        }
    )

}