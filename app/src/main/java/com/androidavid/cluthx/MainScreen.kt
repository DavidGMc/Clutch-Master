package com.androidavid.cluthx

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.androidavid.cluthx.models.DrawerItem
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = listOf(
        DrawerItem(icon = Icons.Default.Favorite, label = "Likes", secondaryLabel = "64"),
        DrawerItem(icon = Icons.Default.Face, label = "Messages", secondaryLabel = "12"),
        DrawerItem(icon = Icons.Default.Email, label = "Mail", secondaryLabel = "64"),
        DrawerItem(icon = Icons.Default.Settings, label = "Settings", secondaryLabel = ""),
    )
    var selectedItem by remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
       // gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet {

                DrawerHeader(modifier = Modifier)

                items.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.label) },
                        selected = item == selectedItem,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem = item
                        },
                        icon = { Icon(imageVector = item.icon, contentDescription = item.label)},
                        badge = { Text(text = item.secondaryLabel)},
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {
            Content(onMenuIconClick = { scope.launch { drawerState.open() } }
            )
        }
    )
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(onMenuIconClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onMenuIconClick) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Open Drawer")
                    }
                },
                title = { Text(text = "Clucth Master")
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "settings")
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Search")
                    }
                }
            )
        },
        bottomBar ={ BottomBarContent()

        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ContentList()

        }
    }
}

@Composable
fun DrawerHeader(modifier: Modifier){
    Column (verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Image(
            painterResource(id = R.drawable.disco_taco_mazdab2000_screen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier= modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer (modifier= Modifier.padding(5.dp))
        Text(text = "Welcome",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color= MaterialTheme.colorScheme.onPrimary)

    }

}
@Composable
fun BottomBarContent() {
    BottomAppBar(
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector =Icons.Default.AddCircle , contentDescription ="notifications" )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector =Icons.Filled.Email , contentDescription ="settings" )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector =Icons.Filled.ShoppingCart, contentDescription ="compartir" )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector =Icons.Filled.Call, contentDescription ="compartir" )
                }

            }
        }
    )



}
@Composable
private fun ContentList() {
    val embragueList = listOf(
        Embrague(
            1,
            "Disco Logan",
            "Disco Embrague para Renault Logan 1.6," +
                    " compatible con varias lineas de Renault como Clio 1.6, Megane 1, ", R.drawable.disco_logan_1_6
        ),
        Embrague(
            2,
            "Prensa Volkswagen ",
            "Prensa Embrague para Volkswagen cross  1.6," +
                    " Mira el Menú para mas informacion ", R.drawable.prensa_wolkswagen_screen
        )
    )
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = " Prensas-Discos Balineras",
            style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center
        )
    }
    LazyColumn( modifier = Modifier.padding(8.dp))
    {


        items(embragueList) { it ->

            Text(text = "${it.name}", textAlign = TextAlign.Center)
            Image(
                painter = painterResource(id = it.image),
                contentDescription = "disco",
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = "${it.description}", textAlign = TextAlign.Center)
            Divider()

        }

    }

}