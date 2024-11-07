package com.example.composeroomretrofitmvi.presentation.post.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeroomretrofitmvi.domain.model.Post
import com.example.composeroomretrofitmvi.presentation.component.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    post: Post,
    onBackClicked: () -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                title = "Post Details",
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                onNavClick =onBackClicked,
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            /*Id*/
            Text(
                text = "Id :",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "${post.id}",
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(5.dp))

            /*uid*/
            Text(
                text = "User Id :",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "${post.userId}",
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(5.dp))

            /*Title*/
            Text(
                text = "Title :",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = post.title,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(5.dp))

            /*Body*/
            Text(
                text = "Body :",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = post.body,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp
            )

        }

    }

}