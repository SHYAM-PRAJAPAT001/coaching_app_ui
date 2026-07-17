package com.example.coaching_app.view.live


import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.coaching_app.ui.theme.FontSize
import android.net.Uri
import androidx.compose.foundation.layout.Box


@Composable
fun RecordedCard(
    data  : RecordedCardData
){

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .width(screenWidth - 100.dp)
    ){

        Column(
            modifier = Modifier
                .padding(10.dp),

            verticalArrangement = Arrangement.spacedBy(10.dp),
        ){

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
            ){
                AsyncImage(
                    model = "https://img.youtube.com/vi/${data.videoId}/hqdefault.jpg",
                    contentDescription = null,
                )
            }

            HorizontalDivider()

            Text(
                text = data.title,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "1 day ago",
                fontSize = FontSize.small
            )

            Button(
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=${data.videoId}")
                    )

                    context.startActivity(intent)

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = LiveRed,
                    contentColor = Color.White
                )
            ){
                Text("Watch now")
            }
        }
    }
}


data class RecordedCardData(
    val title : String,
    val image : Int,
    val duration : String,
    val videoId : String
)