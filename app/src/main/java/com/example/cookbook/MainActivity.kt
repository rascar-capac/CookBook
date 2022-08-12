package com.example.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cookbook.ui.theme.CookBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Screen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen() {
    Scaffold(
        topBar = { Header() },
        floatingActionButton = { NewRecipeButton() },
        drawerContent = { DrawerContent() },
        content = { PageContent() },
        bottomBar = { Footer() }
    )
}

@Composable
fun Header() {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primary,
        elevation = 10.dp
    )
    {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MenuButton()
            SearchBar(Modifier.weight(1f))
            FiltersButton()
            LayoutButton()
        }
    }
}

@Composable
fun MenuButton() {
    IconButton(onClick = { /*TODO*/ }, Modifier.padding(2.dp)) {
        // Je dois préciser la teinte parce qu’à cause du scaffold, ce n’est pas la bonne qui est utilisée par défaut, comme s’il n’utilisait pas localcontentcolor, à cause des élement material2?
        Icon(
            Icons.Rounded.Menu,
            stringResource(R.string.menu),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

// Le textfield se comporte très bizarrement, il est mal agencé, mauvaises couleurs, une barre chelou en dessous. Utiliser un basictextfield?
@Composable
fun SearchBar(modifier: Modifier) {
    TextField(
        stringResource(R.string.search_label),
        { /*TODO*/ },
        modifier.padding(5.dp),
        textStyle = MaterialTheme.typography.titleSmall,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onPrimary
        ),
        singleLine = true,
        placeholder = { Text(stringResource(R.string.search_placeholder)) },
        leadingIcon = {
            Icon(
                Icons.Rounded.Search,
                null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        shape = RoundedCornerShape(40)
    )
}

@Composable
fun FiltersButton() {
    IconButton(onClick = { /*TODO*/ }, Modifier.padding(2.dp)) {
        Icon(
            Icons.Rounded.FilterList,
            stringResource(R.string.filter_label),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun LayoutButton() {
    IconButton(onClick = { /*TODO*/ }, Modifier.padding(2.dp)) {
        Icon(
            Icons.Rounded.GridView,
            stringResource(R.string.grid_display_label),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun DrawerContent() {
    /*TODO*/
}

@Composable
fun PageContent() {
    Column {
        Categories()
        RecipesList()
    }
}

@Composable
fun Categories(categories: List<String> = listOf("Tous", "Petits plats", "Plats", "Desserts")) {
    LazyRow(
        Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items(items = categories) {
            Text(
                text = it,
                Modifier.padding(horizontal = 10.dp),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun RecipesList(recipes: List<String> = listOf("Choucroute", "Couscous", "Spaga bolo")) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(items = recipes) {
            RecipeItem(it)
        }
    }
}

@Composable
fun RecipeItem(title: String) {
    Surface(
        Modifier.fillMaxWidth(),
        tonalElevation = 1.dp
    ) {
        Row(
            Modifier.padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = painterResource(id = R.drawable.default_recipe)
            Image(
                painter = painter, null,
                Modifier
                    .padding(10.dp)
                    .size(50.dp)
            )
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        title,
                        Modifier.padding(horizontal = 5.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Icon(
                        Icons.Rounded.Verified,
                        null,
                        Modifier.padding(horizontal = 5.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        Icons.Outlined.Favorite,
                        null,
                        Modifier.padding(horizontal = 5.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(Modifier.padding(horizontal = 5.dp)) {
                        Row {
                            Icon(
                                Icons.Rounded.Timer,
                                null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Icon(
                                Icons.Rounded.Timer,
                                null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Icon(
                                Icons.Rounded.Timer,
                                null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Icon(Icons.Rounded.Speed, null, Modifier.padding(horizontal = 5.dp))
                    Tags()
                }
            }
        }
    }
}

// Défilement automatique des tags

//private const val DELAY_BETWEEN_SCROLL_MS = 8L
//private const val SCROLL_DX = 1f
//private const val SCROLL_DX_INT = 1

@Composable
fun Tags(tags: List<String> = listOf("sucré", "salé", "asiatique", "végétarien")) {
//    val lazyListState = rememberLazyListState()
//    LazyRow(state = lazyListState) {
    LazyRow {
        items(items = tags) {
            Surface(Modifier.padding(5.dp), tonalElevation = 10.dp) {
                CompositionLocalProvider(
                    LocalContentColor provides LocalContentColor.current.copy(
                        alpha = 0.6f
                    )
                ) {
                    Text(it, Modifier.padding(2.dp))
                }
            }
//            if (it == tags.last()) {
//                val currentList = tags
//                val secondPart = currentList.subList(0, lazyListState.firstVisibleItemIndex)
//                val firstPart =
//                    currentList.subList(lazyListState.firstVisibleItemIndex, currentList.size)
//
//                rememberCoroutineScope().launch {
//                    lazyListState.scrollToItem(0, maxOf(0, lazyListState.firstVisibleItemScrollOffset - SCROLL_DX_INT))
//                }
//            }
        }
    }
//    LaunchedEffect(Unit) {
//        autoScroll(lazyListState)
//    }
}

//private tailrec suspend fun autoScroll(lazyListState: LazyListState) {
//    lazyListState.scroll(MutatePriority.PreventUserInput) {
//        scrollBy(SCROLL_DX)
//    }
//    delay(DELAY_BETWEEN_SCROLL_MS)
//
//    autoScroll(lazyListState)
//}

@Composable
fun Footer() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary,
        elevation = 10.dp
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Rounded.MenuBook,
                    null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.recipes_label),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            selected = selectedIndex.value == 0,
            onClick = { selectedIndex.value = 0 }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Rounded.Kitchen,
                    null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.stock_label),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            selected = selectedIndex.value == 1,
            onClick = { selectedIndex.value = 1 }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Rounded.Storefront,
                    null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.groceries_label),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            selected = selectedIndex.value == 2,
            onClick = { selectedIndex.value = 2 }
        )
    }
}

@Composable
fun NewRecipeButton() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        backgroundColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(
            Icons.Rounded.Add,
            stringResource(R.string.new_recipe_label),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityDefaultPreview() {
    CookBookTheme {
        Screen()
    }
}