package com.example.inkspire.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.inkspire.R
import com.example.inkspire.domain.models.SheetMenu
import com.example.inkspire.ui.theme.InkSpireTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBottomSheet(
    onDismiss: () -> Unit,
    onAction: (SheetMenu) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
        containerColor = InkSpireTheme.colors.card
    ) {
        Column {
            MenuContent(
                icon = Icons.Rounded.Edit,
                textId = R.string.edit_note,
                onAction = { onAction(SheetMenu.EDIT) }
            )
            Spacer(modifier = Modifier.height(InkSpireTheme.dimens.space24))
            MenuContent(
                icon = Icons.Rounded.Delete,
                textId = R.string.delete_note,
                onAction = { onAction(SheetMenu.DELETE) }
            )
            Spacer(modifier = Modifier.navigationBarsPadding().height(InkSpireTheme.dimens.space24))
        }
    }
}

@Composable
private fun MenuContent(
    icon: ImageVector,
    textId: Int,
    onAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = InkSpireTheme.dimens.space30)
            .clickable {
                onAction()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = InkSpireTheme.colors.secondary
        )
        Spacer(modifier = Modifier.width(InkSpireTheme.dimens.space12))
        Text(
            text = stringResource(id = textId),
            style = InkSpireTheme.typography.labelLarge.copy(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = InkSpireTheme.colors.secondary
        )
    }
}