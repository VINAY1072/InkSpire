package com.example.inkspire.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.inkspire.domain.models.SortOrder
import com.example.inkspire.ui.theme.InkSpireTheme

@Composable
fun MenuDialog(
    sortOrder: SortOrder = SortOrder.TITLE,
    onDismiss: () -> Unit = {},
    onSave: (SortOrder) -> Unit = {}
) {
    val selectedOrder = remember { mutableStateOf(sortOrder) }

    AlertDialog(
        containerColor = InkSpireTheme.colors.onPrimary,
        onDismissRequest = { onDismiss() },
        title = { Text(
            text = "Sort By",
            style = InkSpireTheme.typography.titleLarge,
            color = InkSpireTheme.colors.secondary
        ) },
        text = {
            Column {
                DefaultRadioButton(
                    text = "Title",
                    selected = selectedOrder.value == SortOrder.TITLE,
                    onSelect = {
                        selectedOrder.value = SortOrder.TITLE
                    }
                )
                DefaultRadioButton(
                    text = "Created Date Ascending",
                    selected = selectedOrder.value == SortOrder.CREATED_DATE_ASC,
                    onSelect = {
                        selectedOrder.value = SortOrder.CREATED_DATE_ASC
                    }
                )
                DefaultRadioButton(
                    text = "Created Date Descending",
                    selected = selectedOrder.value == SortOrder.CREATED_DATE_DESC,
                    onSelect = {
                        selectedOrder.value = SortOrder.CREATED_DATE_DESC
                    }
                )
                DefaultRadioButton(
                    text = "Modified Date Ascending",
                    selected = selectedOrder.value == SortOrder.MODIFIED_DATE_ASC,
                    onSelect = {
                        selectedOrder.value = SortOrder.MODIFIED_DATE_ASC
                    }
                )
                DefaultRadioButton(
                    text = "Modified Date Descending",
                    selected = selectedOrder.value == SortOrder.MODIFIED_DATE_DESC,
                    onSelect = {
                        selectedOrder.value = SortOrder.MODIFIED_DATE_DESC
                    }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (sortOrder != selectedOrder.value) {
                        onSave(selectedOrder.value)
                    }
                    onDismiss()
                }
            ) {
                Text(
                    text = "Save",
                    style = InkSpireTheme.typography.bodyMedium,
                    color = InkSpireTheme.colors.primary
                )
            }
        }
    )
}

@Preview
@Composable
fun MenuDialogPreview() {
    InkSpireTheme {
        MenuDialog()
    }
}