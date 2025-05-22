package com.example.luminara.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.luminara.R
import com.example.luminara.ui.components.Buttonback
import com.example.luminara.ui.components.SearchTextField
import com.example.luminara.ui.theme.Primary
import com.example.luminara.utils.Dimensions

@Composable
fun SiteDetailScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Primary)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(top = 45.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.mosque1),
                contentDescription = "site detail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            )

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(start = Dimensions.OuterPadding, end = Dimensions.OuterPadding, top = 15.dp)
            ) {
                Buttonback(
                    onClick = {},
                )
            }

        }
    }
}