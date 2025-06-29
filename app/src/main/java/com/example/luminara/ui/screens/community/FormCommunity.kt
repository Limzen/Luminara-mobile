package com.example.luminara.ui.screens.community

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.luminara.ui.components.RadioComponent
import com.example.luminara.ui.theme.DarkBrown
import com.example.luminara.ui.theme.LightGray
import com.example.luminara.utils.Dimensions
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCommunity(
    name: String,
    onNameChange: (String) -> Unit,
    selectedReligion: String,
    religions: List<String>,
    onSelectedReligionChange: (String) -> Unit,
    onShowSheet: () -> Unit,
    onHideSheet: () -> Unit,
    sheetState: SheetState,
    showSheet: Boolean,
    whatsappLink: String,
    onwhatsappLinkChange: (String) -> Unit,
    subheading: String,
    onSubheadingChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit,
) {
    FormSection(title="Name",state = name, onStateChange = onNameChange)
    ReligionSection(
        selectedReligion = selectedReligion,
        religions=religions,
        onSelectedReligionChange=onSelectedReligionChange,
        onShowSheet = onShowSheet,
        onHideSheet = onHideSheet,
        sheetState = sheetState,
        showSheet = showSheet
    )
    FormSection(title="Whatsapp Link",state = whatsappLink, onStateChange = onwhatsappLinkChange)
    FormSection(title="Subheading",state = subheading, onStateChange = onSubheadingChange)
    FormSection(title="Content",state = content, onStateChange = onContentChange)
}

@Composable
private fun NameSection(name: String, onNameChange: (String) -> Unit) {
    var isFocused by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding, vertical = 12.dp)
    ) {

        Text(
            text = "Name",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = Color.Black
        )
        Spacer(Modifier.height(8.dp))
        BasicTextField(
            value = name,
            onValueChange = onNameChange,
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

@Composable
private fun FormSection(title:String,state: String, onStateChange: (String)-> Unit) {
    var isFocused by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding, vertical = 12.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = Color.Black
        )
        Spacer(Modifier.height(8.dp))
        BasicTextField(
            value = state,
            onValueChange = onStateChange,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReligionSection(
    selectedReligion: String,
    onSelectedReligionChange: (String) -> Unit,
    religions:List<String>,
    onShowSheet: () -> Unit,
    onHideSheet: () -> Unit,
    sheetState: SheetState,
    showSheet: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.OuterPadding, vertical = 12.dp)
    ) {
        Text(
            text = "Religion",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = Color.Black
        )
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onShowSheet() }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(selectedReligion)
                Spacer(Modifier.width(4.dp))
                Icon(Icons.Outlined.ArrowDropDown, "Show options")
            }
        }

        if(showSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    onHideSheet()
                },
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
                        text = "Religion",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    for (religion in religions) {
                        RadioComponent(
                            text = religion,
                            onClick = { onSelectedReligionChange(religion) },
                            selected = selectedReligion == religion
                        )
                    }
                }
            }
        }
    }


}




