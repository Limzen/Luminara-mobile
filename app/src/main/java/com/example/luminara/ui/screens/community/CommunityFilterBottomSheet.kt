package com.example.luminara.ui.screens.community

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.luminara.ui.theme.LightGray
import com.example.luminara.utils.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityFilterBottomSheet(
    communityViewModel: CommunityViewModel,
    isVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    selectedStatus: String,
    onStatusChange: (String) -> Unit,
    onApply: () -> Unit,
    onReset: () -> Unit
) {
    val religions = listOf<String>("All", "Buddha", "Islam", "Kristen", "Katolik", "Hindu")


    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            containerColor = Color.White,
            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimensions.OuterPadding, vertical = 16.dp)
            ) {

                Text(
                    text = "Agama",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                for(religion in religions) {
                    RadioComponent(
                        text = religion,
                        onClick = {onStatusChange(religion)},
                        selected = selectedStatus == religion
                    )
                }

                Spacer(Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = onReset,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Reset")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = onApply,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Apply")
                    }
                }
            }
        }
        }
}

@Composable
private fun RadioComponent(
    text: String,
    onClick: () -> Unit,
    selected: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        RadioButton(
            selected = selected,
            onClick = { onClick() }
        )
    }
    HorizontalDivider(Modifier.padding(vertical = 4.dp),  DividerDefaults.Thickness, LightGray)
}