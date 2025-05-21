package com.example.luminara.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.luminara.ui.theme.BackbuttonArrow

@Composable
fun Buttonback(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.Companion,

    ) {
    Button(
        onClick = onClick,
        modifier = Modifier.Companion
            .width(40.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = BackbuttonArrow,
            contentColor = Color.Companion.White,
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 6.dp
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            Icons.Filled.KeyboardArrowLeft,
            contentDescription = "ArrowLeft",
            modifier = Modifier.Companion.size(35.dp)
        )
    }


}


@Preview
@Composable
private fun view() {
    Buttonback(onClick = {})

}