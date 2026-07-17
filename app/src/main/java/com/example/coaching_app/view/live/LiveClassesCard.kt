//package com.example.coaching_app.view.live

package com.example.coaching_app.view.live
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Person3
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.example.coaching_app.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

val LiveRed = Color(0xFFE53935)
@Composable
fun LiveCard(
    cardData : LiveCardData
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ){


        Column{
            Box(
                modifier = Modifier
                    .fillMaxWidth()

            ){

                Image(
                    painter = painterResource(cardData.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(250.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillBounds
                )

                Surface(
                    color = Color(0x66000000),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(15.dp)
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

            Text(
                text = cardData.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(10.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                Column {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                        )

                        Spacer(modifier = Modifier.padding(5.dp))

                        Text(
                            text = cardData.teacher,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.SemiBold
                        )
                    }


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        Icon(Icons.Outlined.WatchLater,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.padding(5.dp))

                        Text(
                            text = cardData.time,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                        )
                    }
                }

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LiveRed
                    )
                ) {
                    Text("Join Now",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
fun LiveClassesCarousel() {

    val images = listOf(
        LiveCardData(
            R.drawable.image1,
            "Neet 2028 batch",
            "By Khan sir",
            "10:00 AM"
        ),

        LiveCardData(
            R.drawable.image2,
            "Neet 2028 batch",
            "By Khan sir",
            "4:00 AM"
        ),

        LiveCardData(
            R.drawable.image3,
            "Neet 2028 batch",
            "By Khan sir",
            "12:30 PM"
        )
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
            LiveCard(cardData = images[actualIndex])
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