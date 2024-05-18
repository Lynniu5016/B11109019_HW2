package com.example.b11109019_hw2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.b11109019_hw2.ui.theme.B11109019_HW2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            B11109019_HW2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MapInfo()
                }
            }
        }
    }
}

@Preview
@Composable
fun MapInfo() {
    B11109019_HW2Theme {
        ShowMapInfo()
    }
}

@Composable
fun ShowMapInfo(){
    val images = arrayOf(
        R.drawable._24232993_133562616234598_5737852660114868233_n_1677056548,
        R.drawable._02895843_561585869094582_8193495709862332344_n_648eb96ae7902,
        R.drawable._56968768_233625676103372_7185868833531314345_n_649e7a36eff8b
    )

    val names = arrayOf(
        "【柑橘Shinn】\n\n營業時間：11:30-14:00 / 17:00-20:30 （無公休)\n\n地址：\n\nSoba店｜台北市大安區仁愛路四段228-6號\n\n鴨蔥店｜台北市信義區嘉興街315號\n\n魚水店｜台北市信義區基隆路一段428號",
        "【麵屋壹慶】\n\n營業時間：12:00-14:00、17:00-21:00 （週日、週一公休）\n\n地址：台北市中山區雙城街32巷2號",
        "【雞湯桑】\n\n營業時間：11:00-15:00 ( 14:30 最後點餐 )\n\n17:00-21:00 ( 20:30 最後點餐 )\n\n地址：港墘一號店｜台北市內湖區港墘路157號\n\n電話：(02)8751-2883"
    )

    val infos = arrayOf(
        "店如其名的柑橘Shinn，是少數主打水果系湯頭的拉麵店！第一家柑橘Shinn Soba店開幕以來，一直處於高朋滿座的盛況。讓柑橘Shinn決定再開兩家分店，分別是鴨蔥店和魚水店。",
        "麵屋壹慶位於捷運中山國小站晴光商圈附近，是台灣首間的日本泡系拉麵店！而麵屋壹慶的外觀沒有太明顯的招牌，很容易經過忽略掉，不過可別小看它，每到接近營業時間，幾乎大排長龍，可見其高人氣！麵屋壹慶的裝潢擺設，走日式木頭簡約風，店內大概約10個位子，想一嚐麵屋壹慶拉麵的你，請務必耐心等候！",
        "雞湯桑拉麵店位於捷運港墘站附近，是一間主打精緻、輕奢的拉麵品牌，也是連鎖火鍋「雞湯大叔」的延伸品牌，「雞湯大叔」以多種火鍋湯頭聞名，想必雞湯桑拉麵店的湯頭品質有保證！雞湯桑拉麵店有別於一般的傳統日式拉麵，雞湯桑將法餐感帶入其中，翻轉拉麵給人快速解決一餐的印象，希望來到雞湯桑的饕客，可以好好享受這一碗美味拉麵！"
    )

    val addresses = arrayOf(
        "台北市大安區仁愛路四段228-6號柑橘Shinn",
        "台北市中山區雙城街32巷2號麵屋壹慶",
        "台北市內湖區港墘路157號雞湯桑"
    )

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "HomePage") {
        composable(route = "HomePage"){
            HomePage(images,names, navController)
        }
        composable(route = "DetailScreen/{index}",
            arguments = listOf(
                navArgument(name = "index"){
                    type = NavType.IntType
                }
            )){ index->
                DetailScreen(photos = images,
                    infos = infos,
                    addresses = addresses,
                    foodIndex = index.arguments?.getInt("index"),
                    navController = navController
                )
        }
    }

}

@Composable
fun HomePage(
    images: Array<Int>,
    names: Array<String>,
    navController: NavController,
    modifier: Modifier = Modifier
){
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val quantity = images.size
        items(quantity){
            Item(
                modifier,
                painter = images,
                title = names,
                foodIndex = it,
                navController = navController
            )
        }
    }
}

@Composable
fun Item(
    modifier: Modifier,
    painter: Array<Int>,
    title: Array<String>,
    foodIndex: Int,
    navController: NavController
) {
    Card(
        modifier
            .padding(10.dp)
            .wrapContentSize()
            .clickable {
                navController.navigate(route = "DetailScreen/$foodIndex")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier.fillMaxWidth(),
        ) {
            Column(
                modifier.padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = painter[foodIndex]),
                    contentDescription = title[foodIndex],
                    modifier.size(200.dp)
                )

                Text(text = title[foodIndex], fontSize = 20.sp, fontWeight = FontWeight.Bold)

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    photos: Array<Int>,
    infos: Array<String>,
    addresses: Array<String>,
    foodIndex: Int?,
    navController: NavController
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        PlainTooltipBox(
            tooltip = { Text("回上一頁") }
        ) {
            IconButton(
                onClick = {
                          navController.popBackStack()
                          },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "go back"
                )
            }
        }

        Box(modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = photos[foodIndex!!]),
                contentDescription = infos[foodIndex]
            )
        }
        Text(text = infos[foodIndex!!], fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Button(
            onClick = {
                val address = Uri.parse("geo:0,0?q=${addresses[foodIndex]}")
                val mapIntent = Intent(Intent.ACTION_VIEW, address)
                mapIntent.setPackage("com.google.android.apps.maps")
                navController.context.startActivity(mapIntent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "查看地圖")
        }
    }
}