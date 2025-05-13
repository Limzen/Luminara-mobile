package com.example.luminara.ui.components


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.luminara.ui.theme.DarkText
import com.example.luminara.ui.theme.FillTextfield

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        modifier = modifier
            .height(55.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(16.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Email
        ),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = FillTextfield,
            focusedTextColor = DarkText,
            focusedContainerColor = FillTextfield,
            unfocusedTextColor = DarkText,
            focusedIndicatorColor = DarkText,
        )
    )


}
