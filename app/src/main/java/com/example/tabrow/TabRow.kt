package com.example.tabrow

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TabRow(navController: NavController) {
    var bottomSheet by remember { mutableStateOf(false) }
    var dropDownMenu by remember { mutableStateOf(false) }
    val DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()
    ModalNavigationDrawer(drawerState = DrawerState, drawerContent = {
        ModalDrawerSheet(
            drawerContainerColor = Color(0XFF22cb3d),
            drawerContentColor = Color.White
        ) {
            NavigationDrawerItem(
                label = { Text(text = "Drawer Item1") },
                selected = false,
                onClick = { navController.navigate(Screens.AddScreen.route) }
            )
            NavigationDrawerItem(
                label = { Text(text = "Drawer Item2") },
                selected = false,
                onClick = { navController.navigate(Screens.SearchScreen.route) }
            )

            NavigationDrawerItem(
                label = { Text(text = "Drawer Item2") },
                selected = false,
                onClick = { navController.navigate(Screens.SettingScreen.route) }
            )


        }
    }) {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = "Home", color = Color.White)
            }, navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        scope.launch {
                            DrawerState.open()
                        }
                    })
            }, actions = {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.clickable { dropDownMenu = !dropDownMenu }
                )
                DropdownMenu(
                    expanded = dropDownMenu,
                    onDismissRequest = { dropDownMenu = false },
                    modifier = Modifier.background(color = Color(0XFF22cb3d))
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

            }, colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color(0XFF22cb3d)))
        }) { it ->
            var selectedIconIndex by remember { mutableIntStateOf(0) }

            val tabitem = listOf(
                ItemBar(
                    "Chat",
                    selectedicon = Icons.Filled.Home,
                    unselectedicon = Icons.Outlined.Home
                ),
                ItemBar(
                    "Status",
                    selectedicon = Icons.Filled.ShoppingCart,
                    unselectedicon = Icons.Outlined.ShoppingCart
                ),
                ItemBar(
                    "Calls",
                    selectedicon = Icons.Filled.Call,
                    unselectedicon = Icons.Outlined.Call
                ),
            )

            val pagerState = rememberPagerState {
                tabitem.size
            }

            LaunchedEffect(selectedIconIndex) {
                pagerState.animateScrollToPage(selectedIconIndex)
            }
            LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
                if (!pagerState.isScrollInProgress) {
                    selectedIconIndex = pagerState.currentPage
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = it.calculateTopPadding())
            ) {
                androidx.compose.material3.TabRow(
                    selectedTabIndex = selectedIconIndex,
                    containerColor = Color(0XFF22cb3d), indicator = {
                        if (selectedIconIndex < it.size) {
                            TabRowDefaults.SecondaryIndicator(
                                Modifier.tabIndicatorOffset(it[selectedIconIndex]),
                                color = Color.White
                            )
                        }
                    }
                ) {
                    tabitem.forEachIndexed { index, itembar ->
                        Tab(selected = selectedIconIndex == index, onClick = {
                            selectedIconIndex = index
                        }, icon = {
                            if (selectedIconIndex == index) {
                                Icon(imageVector = itembar.selectedicon, contentDescription = "")

                            } else {
                                Icon(imageVector = itembar.unselectedicon, contentDescription = "")
                            }
                        }, text = {
                            Text(text = itembar.title)
                        }, unselectedContentColor = Color.White, selectedContentColor = Color.White)
                    }
                }

                HorizontalPager(state = pagerState) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        when (it) {
                            0 -> {
                                Button(
                                    onClick = { bottomSheet = !bottomSheet },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0XFF22cb3d)
                                    )
                                ) {
                                    Text(text = "Bottom Sheet open")

                                }
                            }

                            1 -> Text("Status Content")
                            2 -> Text("Calls Content")
                        }
                    }
                }
            }

        }

    }
    if (bottomSheet) {
        ModalBottomSheet(onDismissRequest = { bottomSheet = false }) {
            ModalDrawerSheet {
                Text(text = "Bootom Sheet")
            }

        }

    }


}

data class ItemBar(
    val title: String,
    val selectedicon: ImageVector,
    val unselectedicon: ImageVector,

    )