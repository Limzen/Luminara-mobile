package com.example.luminara.ui.screens.review

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.luminara.ui.theme.CreamyBrown
import com.example.luminara.ui.theme.DarkBrown
import com.example.luminara.ui.theme.YellowText
import com.example.luminara.utils.Dimensions

@Composable
fun FormReview(
    reviewText: String,
    onReviewTextChange: (String) -> Unit,
    rating:Float,
    onRatingChange:(Float) -> Unit,
) {
    RatingSection(rating = rating, onRatingChange = onRatingChange)
    ReviewTextSection(reviewText =  reviewText,onReviewTextChange=onReviewTextChange)
}

@Composable
private fun RatingSection(rating: Float, onRatingChange: (Float) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding, vertical = 12.dp)
    ) {
        Text(
            text = "Rating",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = Color.Black
        )
        Spacer(Modifier.height(6.dp))
        Slider(
            value = rating,
            onValueChange = onRatingChange,
            valueRange = 1f..5f,
            steps = 3,
            colors = SliderDefaults.colors(
                inactiveTrackColor = Color.LightGray,
                thumbColor = DarkBrown,
                )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                "$rating",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium, color = Color.Black))
            Spacer(Modifier.width(2.dp))
            Icon(imageVector = Icons.Outlined.Star, contentDescription = "star", tint = YellowText)

        }
    }
}

@Composable
private fun ReviewTextSection(reviewText: String, onReviewTextChange: (String) -> Unit) {
    var isFocused by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding, vertical = 12.dp)
    ) {

        Text(
            text = "Review",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = Color.Black
        )
        Spacer(Modifier.height(8.dp))
        BasicTextField(
            value = reviewText,
            onValueChange = onReviewTextChange,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            singleLine = true,
            decorationBox = { innerTextField ->
                Column {
                    Box(Modifier.padding(bottom = 4.dp)) {
                        innerTextField()
                    }

                    // Underline
                    HorizontalDivider(
                        color = if (isFocused) DarkBrown else Color.Gray,
                        thickness = 1.dp
                    )
                }
            }
        )
    }
}