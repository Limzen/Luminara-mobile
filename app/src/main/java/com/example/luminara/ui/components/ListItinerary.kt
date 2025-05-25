package com.example.luminara.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.ListItinerary
import com.example.luminara.ui.theme.OnPrimary
import com.example.luminara.ui.theme.OnSecondary
import com.example.luminara.ui.theme.Primary

@Composable
fun ListItinerary(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    itineraryDate: String,
    itineraryName: String,
    destinationItinerary: String,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = itineraryDate,
            color = DarkText,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

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
            Column(modifier = Modifier.fillMaxSize()) {
                Row {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = itineraryName,
                            color = Primary,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )

                        Button(
                            onClick = { expanded = true },
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.TopEnd)
                                .offset(x = 16.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = DarkText,
                            ),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Morevert",
                                modifier = Modifier.size(35.dp)
                            )
                            DropdownMenu(
                                expanded = expanded,
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(110.dp),
                                onDismissRequest = { expanded = false },
                                shape = RoundedCornerShape(12.dp),
                                containerColor = OnPrimary,
                                tonalElevation = 4.dp,

                                ) {
                                DropdownMenuItem(
                                    text = {
                                        Row (
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                            ,
                                        ){
                                            Icon(
                                                imageVector = Icons.Filled.Edit,
                                                contentDescription = "Edit",
                                                modifier = Modifier.size(16.dp),
                                                tint = DarkText
                                            )
                                            Spacer(Modifier.width(4.dp))
                                            Text("Edit", style = MaterialTheme.typography.titleSmall, color = DarkText)

                                        }
                                    },
                                    onClick = {
                                        expanded = false
                                        // TODO: aksi edit
                                    },

                                    )
                                Divider(
                                    thickness = 1.dp,
                                    color = OnSecondary, // Atau gunakan warna custom sesuai tema
                                    modifier = Modifier.padding(horizontal = 4.dp)
                                )

                                DropdownMenuItem(
                                    text = {
                                        Row (
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth(),
                                        ){
                                            Icon(
                                                imageVector = Icons.Filled.Delete,
                                                contentDescription = "Delete",
                                                modifier = Modifier.size(16.dp),
                                                tint = DarkText
                                            )
                                            Spacer(Modifier.width(4.dp))
                                            Text("Delete", style = MaterialTheme.typography.titleSmall, color = DarkText)

                                        }  },
                                    onClick = {
                                        expanded = false
                                        // TODO: aksi delete
                                    }
                                )
                            }
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
                                words.take(3).joinToString(" ") + " ..."
                            } else {
                                destinationItinerary
                            }
                        }

                    Text(limitedDestination, style = MaterialTheme.typography.labelLarge)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Create on:", style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "11/05/2025", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Preview
@Composable
private fun view() {
    ListItinerary(
        onClick = {},
        itineraryDate = "11 Mei 2025",
        itineraryName = "Nama Itinerary",
        destinationItinerary = "Masjid nurul huda"
    )
}
