package com.example.luminara.ui.screens.itinerary

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.luminara.R
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.ui.theme.DarkBrown
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions
import com.example.luminara.utils.TransparentStatusBarActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditItinerary(
    navController: NavController
) {
    var date by rememberSaveable { mutableStateOf("December 12 2024") }
    var time by rememberSaveable { mutableStateOf("16:00") }
    var budget by rememberSaveable { mutableStateOf("30000") }

    TransparentStatusBarActivity()


    Box() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .background(BackgroundColor)
        ) {
            TopAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp
                    ),
                title = {
                    Text(
                        text = "Edit Itinerary",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.padding(start = Dimensions.BackIconPadding),
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = BackgroundColor
                ),
            )
            FormItinerary(
                dateInput = date,
                onDateChange = { date = it },
                timeInput = time,
                onTimeChange = { time = it },
                budgetInput = budget,
                onBudgetChange = { budget = it }
            )
        }
        Button(
            onClick = { /* handle save */ },
            modifier = Modifier
                .align(Alignment.BottomCenter) // ✅ Only valid inside BoxScope
                .fillMaxWidth()
                .padding(horizontal = Dimensions.OuterPadding, vertical = 24.dp)
                .navigationBarsPadding(), // prevent overlap with system nav bar
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            )
        ) {
            Text("Save Itinerary", color = Color.White)
        }
    }
}
