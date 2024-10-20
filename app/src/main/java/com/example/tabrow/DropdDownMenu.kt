package com.example.tabrow

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DropDownMenu() {

    var dropDownMenu by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "DropDownMenu")
        }, navigationIcon = {
            Icon(imageVector = Icons.Default.Home, contentDescription = "")
        }, actions = {
            Icon(imageVector = Icons.Filled.MoreVert,
                contentDescription = "",
                modifier = Modifier.clickable { dropDownMenu = !dropDownMenu })

            DropdownMenu(
                expanded = dropDownMenu,
                onDismissRequest = { dropDownMenu = false },
                modifier = Modifier.background(
                    Color.Green
                )
            ) {
                DropdownMenuItem(
                    text = { Text(text = "New Group") },
                    onClick = { /*TODO*/ },
                    colors = MenuDefaults.itemColors(textColor = Color.White)
                )
                DropdownMenuItem(
                    text = { Text(text = "New broadcast") },
                    onClick = { /*TODO*/ },
                    colors = MenuDefaults.itemColors(textColor = Color.White)
                )
                DropdownMenuItem(
                    text = { Text(text = "Linked device") },
                    onClick = { /*TODO*/ },
                    colors = MenuDefaults.itemColors(textColor = Color.White)
                )
                DropdownMenuItem(
                    text = { Text(text = "Starred message") },
                    onClick = { /*TODO*/ },
                    colors = MenuDefaults.itemColors(textColor = Color.White)
                )
                DropdownMenuItem(
                    text = { Text(text = "Setting") },
                    onClick = { /*TODO*/ },
                    colors = MenuDefaults.itemColors(textColor = Color.White)
                )
            }
        })
    }) {

    }

}