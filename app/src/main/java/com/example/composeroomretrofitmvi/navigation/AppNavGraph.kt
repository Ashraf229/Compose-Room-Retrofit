package com.example.composeroomretrofitmvi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.composeroomretrofitmvi.domain.model.Post
import com.example.composeroomretrofitmvi.domain.usecase.CustomNavType
import com.example.composeroomretrofitmvi.presentation.post.screen.DetailScreen
import com.example.composeroomretrofitmvi.presentation.post.screen.HomeScreen
import kotlin.reflect.typeOf

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val startDestination = ScreenList.HomeScreen

    NavHost(navController = navController, startDestination = startDestination) {

        composable<ScreenList.HomeScreen> {
            HomeScreen(
                modifier = modifier,
                onCardClick = { post->
                    navController.navigate(ScreenList.DetailScreen(post=post))
                }
            )
        }

        composable<ScreenList.DetailScreen>(
            typeMap = mapOf(
                typeOf<Post>() to CustomNavType.genericNavType(Post.serializer())
            )
        ) { backStackEntry ->
            val args: ScreenList.DetailScreen = backStackEntry.toRoute()
            DetailScreen(
                modifier = modifier,
                post = args.post,
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }
    }

}