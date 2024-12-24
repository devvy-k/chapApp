package com.example.chapapp_compose.features_ui.component.molecules

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.chapapp_compose.ui.theme.focusedTextFieldColor
import com.example.chapapp_compose.ui.theme.textFieldContainer
import com.example.chapapp_compose.ui.theme.unfocusedTextFieldColor


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    label: @Composable (() -> Unit),
    applyVisualTransformation: Boolean = false,
    trailing: String,
) {

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        keyboardOptions = keyboardOptions,
        label = label,
        visualTransformation = if (applyVisualTransformation) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextFieldColor,
            focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFieldColor,
            unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
            focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
            cursorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedIndicatorColor = Color.White,
        ),
        trailingIcon = {
            TextButton(onClick = {}) {
                Text(text = trailing, color = Color.White)

            }
        }
    )
}

private class PasswordVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString("*".repeat(text.text.length)),

            /**
             * [OffsetMapping.Identity] is a predefined [OffsetMapping] that can be used for the
             * transformation that does not change the character count.
             */
            OffsetMapping.Identity
        )
    }
}

@Preview
@Composable
fun CustomTextFieldPreview(){
}