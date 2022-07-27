package com.pdp.declarative.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pdp.declarative.R
import com.pdp.declarative.ui.theme.Android_declarativeTheme
import com.pdp.imperative.model.TVShow
import androidx.compose.ui.res.painterResource
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun itemTVShow(onItemClick: ((TVShow) -> Unit)?, tvShow: TVShow) {
    Column(
        modifier = Modifier.padding(5.dp)
            .background(Color.Black),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier.height(250.dp)
                .clickable {
                    if (onItemClick != null) {
                        onItemClick(tvShow)
                    }
                },
        ) {
            GlideImage(
                imageModel = tvShow.image_thumbnail_path!!,
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.FillBounds,
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(tvShow.name, color = Color.White)
        Text(tvShow.network!!, color = Color.White)
        Spacer(modifier = Modifier.height(5.dp))
    }
}
