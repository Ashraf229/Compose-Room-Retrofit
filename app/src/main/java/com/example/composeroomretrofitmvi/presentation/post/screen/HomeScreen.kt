package com.example.composeroomretrofitmvi.presentation.post.screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeroomretrofitmvi.domain.model.Post
import com.example.composeroomretrofitmvi.presentation.component.SearchBoxTopBar
import com.example.composeroomretrofitmvi.presentation.component.TopBar
import com.example.composeroomretrofitmvi.presentation.post.event.PostEvent
import com.example.composeroomretrofitmvi.presentation.post.viewmodel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onCardClick: (post: Post) -> Unit,
    viewModel: PostViewModel = hiltViewModel<PostViewModel>()
) {

    val postList = viewModel.state.collectAsState().value.posts
    val loading = viewModel.state.collectAsState().value.loading
    val error = viewModel.state.collectAsState().value.error
    val context = LocalContext.current
    val onEvent = viewModel::onEvent
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    val searchQuery = viewModel.state.collectAsState().value.searchQuery
    val searchExpand = viewModel.state.collectAsState().value.searchExpand

    BackHandler(searchExpand) {
        onEvent(PostEvent.ToggleSearchExpand)
        onEvent(PostEvent.ClearSearch)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {

            if (!searchExpand) {

                TopBar(
                    scrollBehavior = scrollBehavior,
                    title = "Posts",
                    imageVector = Icons.Default.Menu,
                    onNavClick = {},
                    actionOneIcon = Icons.Rounded.Search,
                    actionOneIconClick = {
                        onEvent(PostEvent.ToggleSearchExpand)
                    }
                )
            } else {
                SearchBoxTopBar(
                    searchQuery = searchQuery,
                    onBackIconClicked = {
                        onEvent(PostEvent.ToggleSearchExpand)
                        onEvent(PostEvent.ClearSearch)
                    },
                    onClearIconClicked = {
                        onEvent(PostEvent.ClearSearch)
                    },
                    onSearchValueChange = { query ->
                        onEvent(PostEvent.SearchPost(query))
                    }
                )
            }


        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, "Refreshed!", Toast.LENGTH_SHORT).show()
                    onEvent(PostEvent.RefreshPost)
                }
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {

            if (error.isNotBlank()) {

                Text(
                    text = error,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.align(Alignment.Center)
                )

            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(7.dp),
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding()
                )
            ) {

                items(postList) { item ->
                    PostItem(item) { selPost ->
                        onCardClick(selPost)
                        Toast.makeText(context, "${selPost.id}", Toast.LENGTH_SHORT).show()
                        onEvent(PostEvent.Navigate(selPost))
                    }
                }
            }

            if (loading) {

                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.Center)
                ) {
                    CircularProgressIndicator()
                }
            }
        }

    }


}

@Composable
fun PostItem(post: Post, cardClicked: (selectedPost: Post) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(7.dp)
        .clickable { cardClicked(post) })
    {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            /*Id*/
            Text(
                text = "Id :",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = "${post.id}",
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))

            /*Uid*/
            Text(
                text = "User Id :",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = "${post.userId}",
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))

            /*Title*/
            Text(
                text = "Title :",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = post.title,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))

            /*Body*/
            Text(
                text = "Body :",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = post.body,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}