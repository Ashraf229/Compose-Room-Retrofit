package com.example.composeroomretrofitmvi.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp

@Composable
fun SearchBoxTopBar(
    modifier: Modifier = Modifier,
    onBackIconClicked: () -> Unit,
    onClearIconClicked: () -> Unit,
    onSearchValueChange: (String) -> Unit,
    searchQuery: String
) {

    var searchText by rememberSaveable { mutableStateOf(searchQuery) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(50.dp)
            )
    ) {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onSearchValueChange(searchText)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 3.dp),
            leadingIcon = {
                if (searchQuery.isNotEmpty())
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(27.dp)
                            .clickable {
                                onBackIconClicked()
                            }
                    )
                else
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search",
                        modifier = Modifier.size(27.dp)
                    )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "clear",
                        modifier = Modifier
                            .size(27.dp)
                            .clickable {
                                searchText=""
                                onClearIconClicked()
                            }
                    )
                } else {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(27.dp)
                            .clickable {
                                onBackIconClicked()
                            }
                    )
                }
            },
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(text = "Search post here...")
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBoxTopBar1(
    modifier: Modifier = Modifier,
    onBackIconClicked: () -> Unit,
    onClearIconClicked: () -> Unit,
    onSearchValueChange: (String) -> Unit,
    searchQuery: String,
    scrollBehavior: TopAppBarScrollBehavior // Add scroll behavior here
) {
    var searchText by rememberSaveable { mutableStateOf(searchQuery) }

    // Calculate the elevation based on the scroll state overlap fraction
    val overlappedFraction = scrollBehavior.state.overlappedFraction
    val elevation = lerp(0.dp, 8.dp, overlappedFraction)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(50.dp),
        tonalElevation = elevation // Adjust the elevation based on scroll behavior
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    onSearchValueChange(searchText)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 3.dp),
                leadingIcon = {
                    if (searchQuery.isNotEmpty())
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(27.dp)
                                .clickable { onBackIconClicked() }
                        )
                    else
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(27.dp)
                        )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear",
                            modifier = Modifier
                                .size(27.dp)
                                .clickable {
                                    searchText = ""
                                    onClearIconClicked()
                                }
                        )
                    } else {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(27.dp)
                                .clickable { onBackIconClicked() }
                        )
                    }
                },
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(text = "Search post here...")
                }
            )
        }
    }
}
