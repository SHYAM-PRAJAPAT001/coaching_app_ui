//package com.example.coaching_app.view.live

package com.example.coaching_app.view.live
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.example.coaching_app.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue
@Composable
fun LiveCard(
    image : Int
){


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ){


        Column{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){

                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Surface(
                    color = Color(0x66000000),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                ){

                    Row(
                        modifier = Modifier
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        
                        AnimatedLiveIndicator()

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "Live",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )

                    }

                }
            }
        }
    }
}

@Composable
fun LiveClassesCarousel() {
    val images = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    )

    val pageCount = 10000
    val pagerState = rememberPagerState(
        initialPage = pageCount / 2,
        pageCount = { pageCount }
    )

    LaunchedEffect(Unit) {

        if(!pagerState.isScrollInProgress){
            while (true) {
                yield()
                delay(3000)
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }

    }


    HorizontalPager(
        state = pagerState,modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(30.dp),
        pageSpacing = 0.dp
    ) { page ->
        val actualIndex = page % images.size
        val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
        val absOffset = pageOffset.absoluteValue

        Box(
            modifier = Modifier
                .zIndex(if (pageOffset > 0) -1f else 1f - absOffset)
                .graphicsLayer {
                    val scale = lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - absOffset.coerceIn(0f, 1f)
                    )
                    scaleX = scale
                    scaleY = scale

                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - absOffset.coerceIn(0f, 1f)
                    )

                    translationX = if (pageOffset < 0) {
                        pageOffset * size.width * 0.88f
                    } else {
                        0f
                    }
                }
        ) {
            LiveCard(image = images[actualIndex])
        }
    }
}


@Composable
fun AnimatedLiveIndicator(){

    val infiniteTransition = rememberInfiniteTransition(label = "live_plus")

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = androidx.compose.animation.core.RepeatMode.Reverse
        )
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = androidx.compose.animation.core.RepeatMode.Reverse
        ),
        label = "alpha"
    )


    Icon(
        Icons.Default.FiberManualRecord,
        contentDescription = null,
        tint = Color.Red,
        modifier = Modifier
            .size(14.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            }
    )

}



@Preview
@Composable

fun LiveScreen(){
    LiveClassesCarousel()
}