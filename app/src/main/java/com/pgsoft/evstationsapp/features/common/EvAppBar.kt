package com.pgsoft.evstationsapp.features.common

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.ui.theme.EvStationsAppTheme

@Composable
fun EvAppBar(
    @StringRes titleId: Int,
    @DrawableRes iconId: Int,
    onIconTapped: () -> Unit,
    modifier: Modifier = Modifier,
    showBackIcon: Boolean = false,
    onBackIconTapped: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (showBackIcon) {
                IconButton(
                    onClick = onBackIconTapped,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        painter = painterResource(R.drawable.ic_back_arraw),
                        tint = MaterialTheme.colors.primary,
                        contentDescription = null
                    )
                }
            }

            Text(
                modifier = Modifier
                    .padding(start = if (showBackIcon) 0.dp else 16.dp)
                    .align(Alignment.CenterVertically),
                text = stringResource(id = titleId),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )

            IconButton(
                onClick = onIconTapped,
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                Icon(
                    painter = painterResource(iconId),
                    tint = MaterialTheme.colors.primary,
                    contentDescription = null
                )
            }
        }

        Divider(modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EvAppBarPreview() = EvStationsAppTheme {
    EvAppBar(
        titleId = R.string.stations_title,
        iconId = R.drawable.ic_settings,
        onIconTapped = { },
        modifier = Modifier,
        showBackIcon = true
    )
}