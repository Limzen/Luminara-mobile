package com.example.luminara.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.OnPrimary
import com.example.luminara.ui.theme.Primary

@Composable
fun ItineraryTextfield(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String,
    label: String,
    singleLine: Boolean = true,
    trailingIcon: (@Composable (() -> Unit))? = null
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = Primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            modifier = if (singleLine)
                modifier.height(55.dp).fillMaxWidth()
            else
                modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            singleLine = singleLine,
            trailingIcon = trailingIcon,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = OnPrimary,
                focusedTextColor = DarkText,
                focusedContainerColor = OnPrimary,
                unfocusedTextColor = DarkText,
                focusedIndicatorColor = Primary,
                unfocusedIndicatorColor = Primary,
            )
        )
    }
}




