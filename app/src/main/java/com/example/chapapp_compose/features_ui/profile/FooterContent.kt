package com.example.chapapp_compose.features_ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chapapp_compose.R
import com.example.chapapp_compose.features_ui.util.DCodeIcon
import com.example.chapapp_compose.features_ui.util.MyIcons

@Composable
fun FooterContent() {
    val moreOptionsList = listOf(
        profileFeatures("Edit Profile", DCodeIcon.ImageVectorIcon(MyIcons.Edit)),
        profileFeatures("Manage Account", DCodeIcon.ImageVectorIcon(MyIcons.AccountBox)),
        profileFeatures("About", DCodeIcon.ImageVectorIcon(MyIcons.Info)),
        profileFeatures("Share 'Damahe Code'", DCodeIcon.ImageVectorIcon(MyIcons.Share)),
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "Options",
                style = MaterialTheme.typography.titleMedium,
            )
            moreOptionsList.forEach {
                MoreOptionsComp(it)
            }
        }
    }
}

@Composable
fun MoreOptionsComp(
    featureList: profileFeatures,
) {
    Row(
        modifier = Modifier.padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        when (featureList.icon) {
            is DCodeIcon.ImageVectorIcon -> Icon(
                imageVector = featureList.icon.imageVector,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(6.dp)
            )

            is DCodeIcon.DrawableResourceIcon -> Icon(
                painter = painterResource(id = featureList.icon.id),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(6.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f)
        ) {
            Text(
                text = featureList.name,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Icon(
            imageVector = MyIcons.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.padding(4.dp)
        )
    }
}