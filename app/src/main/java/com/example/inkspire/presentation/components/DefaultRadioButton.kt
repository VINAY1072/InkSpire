package com.example.inkspire.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.inkspire.ui.theme.InkSpireTheme

@Composable
fun DefaultRadioButton(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = InkSpireTheme.dimens.space16)
            .clickable {
                onSelect()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = InkSpireTheme.typography.bodyMedium,
            color = if (selected) InkSpireTheme.colors.primary else InkSpireTheme.colors.secondary
        )
        Spacer(modifier = Modifier.weight(1f))
        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                selectedColor = InkSpireTheme.colors.primary,
                unselectedColor = InkSpireTheme.colors.secondary
            )
        )
    }
}

@Preview
@Composable
fun DefaultRadioButtonPreview() {
    InkSpireTheme {
        Column {
            DefaultRadioButton(
                text = "Test",
                selected = false,
                onSelect = {}
            )
            Spacer(modifier = Modifier.height(InkSpireTheme.dimens.space16))
            DefaultRadioButton(
                text = "Test - 2",
                selected = true,
                onSelect = {}
            )
        }
    }
}