package com.konzerra.memories.presentation.navigation

import com.konzerra.memories.R


sealed class Screen(
    val route:String,
    val stringResource: Int,
    val drawableResource: Int,
){
    object NewMemoryScreen : Screen(
        "new_memory_screen",
        R.string.new_memory,
        R.drawable.ic_new_memory
    )
    object MemoryScreen : Screen(
        "memory_screen",
        R.string.new_memory,
        R.drawable.ic_new_memory
    )
    object MemoryListScreen : Screen(
        "memory_list_screen",
        R.string.memories,
        R.drawable.ic_memory_list
    )
    object SettingsScreen : Screen(
        "settings_screen",
        R.string.settings,
        R.drawable.ic_settings
    )
    object HelpScreen : Screen(
        "help_screen",
        R.string.help,
        R.drawable.ic_help,
    )
    object AboutUsScreen : Screen(
        "about_us_screen",
        R.string.about_us,
        R.drawable.ic_about_us
    )
    object TagListScreen : Screen(
        "tag_list_screen",
        R.string.tag_list,
        R.drawable.ic_tab
    )
    fun startScreen(): Screen {
        return NewMemoryScreen
    }
    fun withArgs(vararg args:String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}

