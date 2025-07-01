package com.example.luminara.ui.screens.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.luminara.data.model.Review
import com.example.luminara.data.model.Trip
import com.example.luminara.ui.components.BottomButton
import com.example.luminara.ui.screens.profile.UserViewModel
import com.example.luminara.ui.theme.BackgroundColor
import com.example.luminara.utils.Dimensions
import com.example.luminara.utils.TransparentStatusBarActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(
    navController: NavController,
    directoryId: Long,
    userViewModel: UserViewModel,
) {
    val reviewViewModel: ReviewViewModel = viewModel()
    val currentUser by userViewModel.currentUser.collectAsState()


    var reviewText by rememberSaveable { mutableStateOf("") }
    var rating by rememberSaveable { mutableFloatStateOf(5f) }

    TransparentStatusBarActivity()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .background(BackgroundColor)
        ) {
            TopAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = Dimensions.TopBarElevation
                    ),
                title = {
                    Text(
                        text = "Add Review",
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundColor
                ),
            )
           FormReview(
               reviewText = reviewText,
               onReviewTextChange = {reviewText = it},
               rating = rating,
               onRatingChange = {rating = it}
           )
        }
        BottomButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "Submit Review",
            onClick = {
                if (reviewText.isBlank()) {

                } else {
                    if (currentUser != null) {
                        val review = Review(
                            userId = currentUser!!.id,
                            directoryId = directoryId,
                            rating = rating,
                            reviewText = reviewText,
                            user = currentUser
                        )
                        reviewViewModel.submitReview(review = review, directoryId = directoryId)
                        navController.popBackStack() // go back to directory detail
                    }
                }
            }
        )
    }
}