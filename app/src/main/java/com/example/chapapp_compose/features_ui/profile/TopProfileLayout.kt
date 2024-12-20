package com.example.chapapp_compose.features_ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.chapapp_compose.R
import com.example.chapapp_compose.features_ui.util.DCodeIcon
import com.example.chapapp_compose.features_ui.util.ImageText
import com.example.chapapp_compose.features_ui.util.MyIcons


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopProfileLayout() {
    val imageTextList = listOf(
        ImageText(DCodeIcon.ImageVectorIcon(MyIcons.Location), "Bharat/India"),
        ImageText(DCodeIcon.ImageVectorIcon(MyIcons.Email), "damahecode@gmail.com"),
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.default_profile_picture),
                    contentDescription = null,
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "David Konan",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }


            FlowRow(modifier = Modifier.padding(vertical = 5.dp)) {
                imageTextList.forEach {
                    ImageTextContent(
                        modifier = Modifier.padding(vertical = 5.dp),
                        icon = {
                            when (it.icon) {
                                is DCodeIcon.ImageVectorIcon -> Icon(
                                    imageVector = it.icon.imageVector,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp)
                                )

                                is DCodeIcon.DrawableResourceIcon -> Icon(
                                    painter = painterResource(id = it.icon.id),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }
                        },
                        text = {
                            Text(
                                text = it.text,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                    )
                }
            }

        }

    }
}

@Composable
fun ImageTextContent(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(modifier = Modifier.width(5.dp))
        text()
        Spacer(modifier = Modifier.width(10.dp))
    }
}