package com.example.pharmamanufacturer.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.pharmamanufacturer.core.COMPOUNDS_GRAPH_ROUTE
import com.example.pharmamanufacturer.core.COMPOUND_ID_KEY
import com.example.pharmamanufacturer.core.COMPOUND_NAME_KEY
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.compounds.details.CompoundDetailsScreenNavigation
import com.example.pharmamanufacturer.presentation.compounds.entry.AddCompoundScreenNavigation
import com.example.pharmamanufacturer.presentation.compounds.entry.EditCompoundScreenNavigation
import com.example.pharmamanufacturer.presentation.compounds.main.CompoundsScreenNavigation

fun NavGraphBuilder.compoundsNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.CompoundsScreen.route,
        route = COMPOUNDS_GRAPH_ROUTE
    ) {

        composable(route = Screen.CompoundsScreen.route) {
            CompoundsScreenNavigation(navController)
        }

        composable(
            route = Screen.CompoundDetailsScreen.route + "/{$COMPOUND_ID_KEY}",
            arguments = listOf(
                navArgument(COMPOUND_ID_KEY) {
                    type = NavType.IntType
                    nullable = false
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                    animationSpec = tween(700)
                )
            }
        ) {
            CompoundDetailsScreenNavigation(navController)
        }

        composable(route = Screen.AddCompoundScreen.route) {
            AddCompoundScreenNavigation(navController)
        }

        composable(
            route = Screen.EditCompoundScreen.route + "/{$COMPOUND_ID_KEY}" + "/{$COMPOUND_NAME_KEY}",
            arguments = listOf(
                navArgument(COMPOUND_ID_KEY) {
                    type = NavType.IntType
                    nullable = false
                },
                navArgument(COMPOUND_NAME_KEY) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            EditCompoundScreenNavigation(
                selectedId = entry.arguments?.getInt(COMPOUND_ID_KEY),
                navController = navController
            )
        }
    }
}
