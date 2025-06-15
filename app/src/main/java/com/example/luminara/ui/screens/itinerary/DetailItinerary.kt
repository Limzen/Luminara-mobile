package com.example.luminara.ui.screens.itinerary

import android.app.Activity
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.luminara.R
import com.example.luminara.navigation.Screen
import com.example.luminara.ui.components.BackButton
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.DarkBrown
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.ErrorColor
import com.example.luminara.ui.theme.LightBrown
import com.example.luminara.ui.theme.LightGray
import com.example.luminara.ui.theme.OnPrimary
import com.example.luminara.ui.theme.Primary
import com.example.luminara.ui.theme.SuccessColor
import com.example.luminara.utils.Dimensions
import com.example.luminara.utils.TransparentStatusBarActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailItinerary(
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val imageHeight = 320.dp

    val scrollState = rememberLazyListState()
    val showTitle = remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 1 ||
                    (scrollState.firstVisibleItemIndex == 1 && scrollState.firstVisibleItemScrollOffset > 0)
        }

    }

    var sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    TransparentStatusBarActivity()

    Box(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {

        LazyColumn(
            state = scrollState
        ) {
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                ) {
                    Image(
                        painter = painterResource(R.drawable.mosque1),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }
            item {
                Column(modifier = Modifier
                    .padding(horizontal = Dimensions.OuterPadding)
                    .padding(top = 16.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "Medan Trip",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Dec 12 – Dec 14, 2023 • A Couple • Luxury",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            items(5) {
                Card(
                    shape = RoundedCornerShape(Dimensions.BoxRadius),
                    modifier = Modifier
                        .padding(horizontal = Dimensions.OuterPadding, vertical = 8.dp)
                        .fillMaxWidth()
                        .border(1.dp, Color.LightGray, RoundedCornerShape(Dimensions.BoxRadius))
                        .clickable {
                            showSheet = true
                            scope.launch { sheetState.show() }
                        },
                    colors = CardDefaults.cardColors(containerColor = Color.White),

                ) {
                    Column {
                        Image(
                            painter = painterResource(R.drawable.mosque1),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Mosque Medan",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(Modifier.height(2.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(vertical = 4.dp)
                            ) {
                                repeat(5) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = Color(0xFFFFC107), // Amber
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Text(
                                    text = " (4.2)  1.573 reviews",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Outlined.DateRange, contentDescription = null)
                                Text(
                                    text = "08.00 - 09.00 AM",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Outlined.ShoppingCart, contentDescription = null)
                                Text(
                                    text = "Rp 30.000",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Outlined.LocationOn, contentDescription = null, tint = DarkBrown)
                                Text(
                                    text = "View on Google Maps",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = DarkBrown,
                                    ),
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        if (!showTitle.value) {
            IconButton(
                onClick = {
                    navController.popBackStack() },
                modifier = Modifier
                    .padding(top = 40.dp, start = Dimensions.OuterPadding) // adjust for status bar
                    .size(40.dp)
                    .zIndex(10f)
                    .align(Alignment.TopStart)
                    .background(color = Color.White.copy(alpha = 0.9f), shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }
        if(showSheet) {
            BottomSheet(
                closeSheet = {showSheet = false},
                sheetState = sheetState,
                onEditClick = {navController.navigate(Screen.EditItinerary.route)},
                onDeleteClick = {}
            )
        }

        LargeTopAppBar(
            modifier = Modifier
                .shadow(
                    elevation = if (showTitle.value) Dimensions.TopBarElevation else 0.dp
                ),
            title = {
                    AnimatedVisibility(visible = showTitle.value) {
                        Text(
                            text = "Medan Trip",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
            },
            navigationIcon = {
                if (showTitle.value) {
                    AnimatedVisibility(visible = showTitle.value) {
                        IconButton(
                            modifier = Modifier.padding(start = Dimensions.BackIconPadding),
                            onClick = {
                                navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            },

            scrollBehavior = scrollBehavior,
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = if (showTitle.value) BackgroundColor else Color.Transparent,
                scrolledContainerColor = if (showTitle.value) BackgroundColor else Color.Transparent
            ),

        )
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.AddItinerary.route)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(Dimensions.OuterPadding),
            containerColor = Primary
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Edit",
                tint = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheet(
    closeSheet : () -> Unit,
    sheetState: SheetState,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { closeSheet() },
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = Color.White,
        dragHandle = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .size(width = 40.dp, height = 4.dp)
                        .background(Color.LightGray, RoundedCornerShape(2.dp))
                )
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = Dimensions.OuterPadding),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedButton(
                onClick = {
                    closeSheet()
                    onEditClick()
                          },
                shape = CircleShape,
                border = BorderStroke(1.dp, Primary),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Primary
                ),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .height(height = 48.dp)
            ) {
                Text("Edit Itinerary")
            }
            Button(
                onClick = {
                    closeSheet()
                    onDeleteClick()
                          },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ErrorColor,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 48.dp)
            ) {
                Text("Delete Itinerary")
            }
        }
    }
}



@Composable
private fun TopHeader(
    navController: NavController
){
    Box(
        modifier = Modifier
            .height(165.dp)
            .width(500.dp)
            .background(color = Primary)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 60.dp)
                .padding(horizontal = 20.dp),
        ) {
            BackButton(onClick = {navController.popBackStack()})
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Road To Heaven",
                style = MaterialTheme.typography.titleLarge,
                color = OnPrimary,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
private fun ImageHeader() {
    Box(
        modifier = Modifier
            .height(240.dp)
            .width(500.dp),

    ){
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.gereja_velangkani_medan),
            contentDescription = "Foto Graha Maria annai velangkanni"
        )

    }

}

@Composable
private fun Detail() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = OnPrimary,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ),


        ){
        LazyColumn (
            Modifier
                .fillMaxSize()
        ){
            item {
                DetailItineraryContent()
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                TravelBudgeting()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Notes()
            }
            item {
                Spacer(modifier = Modifier.height(42.dp))
            }

        }
    }
}

@Composable
private fun DetailItineraryContent() {
    Column (
        modifier = Modifier
            .padding(Dimensions.OuterPadding),

        ){
        Text("Detail Itinerary", style = MaterialTheme.typography.titleSmall, modifier =  Modifier.align(Alignment.CenterHorizontally) )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Graha Maria Annai Velangkanni", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))

        Row (verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.LocationOn,
                contentDescription = "Icon Location",
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text("Jl. Sisingamangaraja No.61, Medan Kota District, Medan City, North Sumatra 20213", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Light)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column {
                Text("Date :", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(6.dp))
                Text("12 May 2025", style = MaterialTheme.typography.bodyMedium)
            }

            Canvas(
                modifier = Modifier
                    .width(1.dp)
                    .height(40.dp)
            ) {
                drawLine(
                    color = DarkText,
                    start = Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(0f, size.height),
                    strokeWidth = size.width
                )
            }


            Column (
                horizontalAlignment = Alignment.End
            ) {
                Text("Time :", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(6.dp))
                Text("15:45 WIB", style = MaterialTheme.typography.bodyMedium)
            }

        }


    }
}

@Composable
private fun TravelBudgeting() {
    Column {
        Box (
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(
                    color = DarkBrown,
                ),
            contentAlignment = Alignment.Center

        ){
            Text(
                text = "Travel Budgeting",
                style = MaterialTheme.typography.titleSmall,
                color = DarkText,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Canvas(
                modifier = Modifier
                    .width(180.dp)
                    .height(2.dp)
            ) {
                drawLine(
                    color = DarkText,
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                    strokeWidth = size.height
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Rp. 350.000,-", style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(8.dp))

            Canvas(
                modifier = Modifier
                    .width(180.dp)
                    .height(2.dp)
            ) {
                drawLine(
                    color = DarkText,
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                    strokeWidth = size.height
                )
            }
        }
    }
}

@Composable
private fun Notes() {

    Box (
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .background(
                color = DarkBrown,
            ),
        contentAlignment = Alignment.Center

    ){
        Text(
            text = "NOTES",
            style = MaterialTheme.typography.titleSmall,
            color = DarkText,
            fontWeight = FontWeight.Bold
        )

    }

    Spacer(modifier = Modifier.height(16.dp))

    Box(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding)
            .clip(RoundedCornerShape(Dimensions.BoxRadius))
            .background(OnPrimary)
            .border(
                width = 1.dp,
                color = LightGray,
                shape = RoundedCornerShape(Dimensions.BoxRadius)
            )
            .padding(16.dp)
            .defaultMinSize(minHeight = 90.dp)

    ) {
        Text(
            text = "Bawalah perlengkapan ibadah dan berpakaian yang sopan saat mengunjungi lokasi ini.",
            style = MaterialTheme.typography.bodySmall,
            color = DarkText
        )
    }
}