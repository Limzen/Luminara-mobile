package com.example.luminara.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.ListItinerary
import com.example.luminara.ui.theme.Primary

@Composable
fun ListItinerary(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    itineraryDate: String,
    itineraryName : String,
    destinationItinerary : String,
) {
    Column (modifier = Modifier.fillMaxSize()){
        Text(text = itineraryDate, color = DarkText, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onClick,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ListItinerary,
                contentColor = DarkText,
            )



        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
            ){
                Row (
                ) {
                    Box(modifier = Modifier.fillMaxWidth()){
                        Text(text = itineraryName,
                            color = Primary,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.TopEnd)
                                .offset(x = 7.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary,
                                contentColor = Color.White,
                            ),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Destination:", style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.width(6.dp))

                    val limitedDestination = destinationItinerary
                        .split(" ")
                        .let { words ->
                            if (words.size > 3) {
                                words.take(3).joinToString(" ") + " " +  "..."
                            } else {
                                destinationItinerary
                            }
                        }

                    Text(limitedDestination, style = MaterialTheme.typography.labelLarge)
                }
                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Create on:",  style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text =  "11/05/2025", style = MaterialTheme.typography.labelLarge)
                }


            }





        }
    }

}




