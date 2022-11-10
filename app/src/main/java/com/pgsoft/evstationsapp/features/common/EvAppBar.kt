package com.pgsoft.evstationsapp.features.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun EvAppBar(
    @StringRes titleId: Int,
    @DrawableRes iconId: Int,
    onIconTapped: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp),
                text = stringResource(id = titleId),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )

            IconButton(onClick = onIconTapped) {
                Icon(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(iconId),
                    tint = MaterialTheme.colors.primary,
                    contentDescription = null
                )
            }
        }

        Divider(modifier = Modifier.fillMaxWidth())
    }
}