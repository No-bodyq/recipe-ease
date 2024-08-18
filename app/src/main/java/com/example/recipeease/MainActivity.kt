package com.example.recipeease

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.XmlRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeease.ui.theme.RecipeEaseTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeEaseTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF161616)) {
                    MultiplePages()
                }
            }
        }
    }
}

@Composable
fun MultiplePages(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "page1") {
        composable("page1") { WelcomePage(navController = navController) }
        composable("page2") { LoginPage(navController = navController)}
        composable("page3") { HomePage(navController = navController) }
//        composable("page3") { Page3() }
        composable("page5"){ RecipesPage(navController = navController)}
    }
}

@Composable
fun WelcomePage(modifier: Modifier = Modifier, navController: NavController){
    Column(modifier = modifier.fillMaxSize(), Arrangement.SpaceEvenly) {
        RecipeEaseLogo()
        Title()
        GetStartedButton(modifier = modifier,navController = navController)
    }
}

@Composable
fun LoginPage(modifier: Modifier = Modifier, navController: NavController){
    val email by remember {
        mutableStateOf("")
    }

    val password by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(state = rememberScrollState(), enabled = true)
        .padding(20.dp), verticalArrangement = Arrangement.Center) {
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.pixlr_image_generator_244912cb_80d1_44d2_8a05_5f0528b2a80a), contentDescription = null, modifier = modifier.size(110.dp))
        }
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp), horizontalArrangement = Arrangement.Center){
            Text(text = stringResource(id = R.string.recipe_ease), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(text = "Discover recipes effortlessly!", color = Color.White, fontSize = 20.sp)
        }
        Row(modifier = modifier.padding(top = 20.dp)) {
            MyTextField(textValue = email, type = "Email", modifier = modifier.clip(shape = RoundedCornerShape(32.dp)))
        }
        Row(modifier = modifier.padding(top = 20.dp)) {
            MyTextField(
                textValue = password,
                type = "Password",
                modifier = modifier.clip(shape = RoundedCornerShape(32.dp))
            )
        }
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp), horizontalArrangement = Arrangement.End) {
            Text(text = "Forgot your password?", color = Color.White)
        }
        Row(modifier = modifier.fillMaxWidth()) {
            LoginButton(navController = navController)
        }
        Row(modifier = modifier
            .fillMaxWidth()
            ) {

            Row (modifier = modifier.fillMaxWidth(0.48f).fillMaxHeight().padding(10.dp)){
                HorizontalDivider(modifier = modifier
                    .weight(3f).align(Alignment.CenterVertically))
            }

           Row(modifier = modifier.fillMaxWidth(0.12f)) {
               Text(text = "or", modifier = modifier
                   .align(Alignment.CenterVertically), color = Color.White, fontSize = 20.sp)
           }
            Row (modifier = modifier.fillMaxWidth(1f).fillMaxHeight().padding(10.dp)){
                HorizontalDivider(modifier = modifier
                    .weight(3f)
                    .align(Alignment.CenterVertically))
            }
        }
        Row(modifier = modifier.fillMaxWidth().padding(top = 20.dp)) {
            AuthButtons(modifier = modifier.weight(1f),text = "Google")
            AuthButtons(modifier = modifier.weight(1f),text = "Facebook")
        }
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp), horizontalArrangement = Arrangement.Center) {
            Text(text = "New here? ", color = Color.White, fontWeight = FontWeight.Bold)
            Text(text = "Join Now", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun MyTextField(modifier: Modifier = Modifier, textValue: String, type: String){
    var value by remember {
        mutableStateOf(textValue)
    }

    TextField(value = value, onValueChange = {value = it}, singleLine = true, colors = TextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White,unfocusedContainerColor = Color(0xFF2b2b2b), focusedContainerColor = Color(0xFF2b2b2b), focusedIndicatorColor = Color(0xFF2b2b2b), unfocusedIndicatorColor = Color(0xFF2b2b2b)),placeholder = { Text(text = type, color = Color.White)},modifier = modifier
        .fillMaxWidth())
}

@Composable
fun LoginButton(modifier: Modifier = Modifier, navController: NavController){
    Button(onClick = { login(navController)}, modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 30.dp), colors = ButtonColors(contentColor = Color.White, disabledContentColor = Color.Black, containerColor = Color(0xFF2b2b2b), disabledContainerColor = Color.White)) {
        Text(text = "Log in", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = modifier.padding(4.dp))
    }
}

@Composable
fun AuthButtons(modifier: Modifier = Modifier, text: String){
    Button(onClick = { }, modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp), colors = ButtonColors(contentColor = Color.White, disabledContentColor = Color.Black, containerColor = Color.Black, disabledContainerColor = Color.White)) {
        Text(text = text, fontSize = 18.sp, modifier = modifier.padding(4.dp), textAlign = TextAlign.Center)
    }
}

fun login(navController: NavController){
    navController.navigate("page3")
}

@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavController){
    Column(modifier.fillMaxSize()) {
        ScrollableHomePage(modifier = modifier.verticalScroll(state = ScrollState(0),enabled = true), navController = navController)
        Footer(navController = navController)
    }
}

@Composable
fun RecipesPage(modifier: Modifier = Modifier, navController: NavController){
    Column {
        RecipesHeader(navController = navController,
            modifier = modifier
                .height(110.dp)
                .background(Color(0xFF2b2b2b)))
        RecipeList()
        Footer(navController = navController)
    }
}

@Composable
fun RecipesHeader(modifier: Modifier = Modifier, navController: NavController){
    Column(modifier = modifier
        .fillMaxSize()
        .padding(top = 20.dp, start = 20.dp)) {
        Row (modifier = modifier){
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back button", tint = Color.White, modifier = modifier
                .padding(vertical = 30.dp)
                .clickable { navController.navigateUp() })
            Text(text = "Recipes", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 24.sp, modifier = modifier.padding(30.dp))
        }
        Row {

        }
    }
}

@Composable
fun RecipeList(modifier: Modifier = Modifier){
    Column(modifier = modifier.verticalScroll(state = rememberScrollState(), enabled = true)) {

    }
}

@Composable
fun ScrollableHomePage(modifier: Modifier = Modifier, navController: NavController){
    Column(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 40.dp)
            .background(color = Color(0xFF161616))){
        TopOfHomePage(navController = navController)
        HomePageTitle()
        Tabs(navController = navController)
        DiscoverCard()
    }
}

@Composable
fun TopOfHomePage(modifier: Modifier = Modifier, navController: NavController){
    val profilePic = painterResource(id = R.drawable.pfp)

    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween){
        Text(text = stringResource(id = R.string.home), fontSize = 20.sp, fontWeight = FontWeight.Bold,color = Color.White)
        Row(verticalAlignment = Alignment.CenterVertically){
            Icon(imageVector = Icons.Filled.Search, contentDescription = null, tint = Color.White, modifier = modifier
                .clickable { navController.navigate("page1") }
                .padding(end = 20.dp))
            Image(painter = profilePic, contentDescription = null, modifier = modifier
                .size(40.dp)
                .clip(RoundedCornerShape(100.dp)), contentScale = ContentScale.FillBounds)
        }
    }
}

@Composable
fun Tabs(modifier: Modifier = Modifier, navController: NavController){
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(top = 10.dp)){
        PageButton(text = "Search", navController = navController, color = Color(0xFF2b2b2b))
        Spacer(modifier = modifier.width(16.dp))
        PageButton(text = "Save", navController = navController)
    }
}

@Composable
fun HomePageTitle(modifier: Modifier = Modifier){
    Column(modifier = modifier
        .padding(top = 40.dp)
        .fillMaxWidth()) {
        Text(text = "Welcome to\n\nRecipeEase", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 48.sp)
    }
}

@Composable
fun DiscoverCard(modifier: Modifier = Modifier){
    Box{
        CardBack()
        CardFront()
    }
}

@Composable
fun CardFront(modifier: Modifier = Modifier){
    val images = arrayOf(painterResource(id = R.drawable.nik_6hzhfuivo60_unsplash), painterResource(id = R.drawable.nik_6hzhfuivo60_unsplash), painterResource(id = R.drawable.nik_6hzhfuivo60_unsplash))

    Box (modifier = modifier
        .padding(top = 20.dp)
        .shadow(
            elevation = 5.dp,
            spotColor = Color.Black,
            ambientColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )){
        Box(modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            ){
            Column(modifier = modifier
                .background(color = Color(0xFF2b2b2b))
                .padding(16.dp)) {
                Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Discover New Recipes", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    ImageStack(images = images)
                }
                Row {
                    Text(text = "Explore and save your favorite recipes", color = Color.White)
                }
                Row(modifier = modifier.padding(top = 30.dp)){
                    Text(text = "50%", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                }
                Row(modifier = modifier.padding(top = 10.dp,bottom = 10.dp)){
                    LoadingBar(progress = 0.5f)
                }
            }
        }
    }
}

@Composable
fun CardBack(modifier: Modifier = Modifier){
    Box(modifier = modifier
        .fillMaxHeight()
        .fillMaxWidth(0.95f)
        .padding(top = 20.dp)
        .offset(x = 8.dp)
        .clip(RoundedCornerShape(16.dp))){
        Box(
            modifier
                .background(color = Color(0xFF2b2b2b))
                .fillMaxWidth()
                .size(180.dp)){

        }
    }
}

@Composable
fun LoadingBar(
    progress: Float,
    backgroundColor: Color = Color(0xFF2b2b2b),
    progressColor: Color = Color(0xFF202020),
    height: Dp = 8.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(backgroundColor)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.LightGray,
                spotColor = Color.White
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(height)
                .background(progressColor)
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = Color.White,
                    spotColor = Color.LightGray
                )
        )
    }
}

@Composable
fun ImageStack(modifier: Modifier = Modifier, images: Array<Painter>){
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
        for(image in 0..images.size - 1){
            val offsetVal = if (image % 2 == 0 && image != 0) -20 else if(image == 0) 20 else 0
            Box(modifier = modifier.offset(x = offsetVal.dp)){
                Image(painter = images[image], contentDescription = null, modifier = modifier
                    .size(30.dp)
                    .clip(
                        RoundedCornerShape(100.dp)
                    ), contentScale = ContentScale.FillBounds)
            }
        }
    }
}

@Composable
fun PageButton(modifier: Modifier = Modifier, text: String, navController: NavController, color: Color = Color.Black, route: String = "page1"){
    Button(onClick = { navController.navigate(route)}, colors = ButtonColors(containerColor = color, disabledContainerColor = Color.Gray, contentColor = Color.White, disabledContentColor = Color.Black) ,modifier = modifier.width(160.dp)) {
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier, navController: NavController){
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom){
        Row(modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFF2b2b2b))
            .padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly){
            Column(modifier = modifier.clickable {navController.navigate("page3") }, horizontalAlignment = Alignment.CenterHorizontally){
                Icon(imageVector = Icons.Filled.Home, contentDescription = null, modifier, tint = Color.White)
                Text(text = stringResource(R.string.home), color = Color.White, textAlign = TextAlign.Center)
            }
            Column(modifier = modifier.clickable { navController.navigate("page1") }, horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null, modifier, tint = Color.White)
                Text(text = "Search", color = Color.White, textAlign = TextAlign.Center)
            }
            Column(modifier = modifier.clickable { navController.navigate("page5") }, horizontalAlignment = Alignment.CenterHorizontally){
                Icon(imageVector = Icons.Filled.List, contentDescription = null, modifier, tint = Color.White)
                Text(text = "Recipes", color = Color.White, textAlign = TextAlign.Center)
            }
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null, modifier, tint = Color.White)
                Text(text = "Profile", color = Color.White, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun RecipeEaseLogo(modifier: Modifier = Modifier){
    val image = painterResource(id = R.drawable.vegetables)
    Box(modifier = modifier.fillMaxWidth(), Alignment.Center){
        Image(painter = image, contentDescription = "Recipe Ease Logo", modifier = modifier.size(450.dp))
    }
}

@Composable
fun Title(modifier: Modifier = Modifier){
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.recipe_ease), color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
        Text(text = stringResource(R.string.discover_new_recipes_effortlessly), color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun GetStartedButton(modifier: Modifier = Modifier,navController: NavController){
    Box(modifier = modifier) {
        Button(
            onClick = { navController.navigate("page2") },
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 30.dp),
            colors = ButtonColors(
                containerColor = Color(0xFF000000),
                disabledContainerColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFFFFFFFF),
                disabledContentColor = Color(0xFF000000)
            )
        ) {
            Text(
                text = "Get Started",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = modifier.padding(10.dp)
            )
        }
    }
}