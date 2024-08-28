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
import androidx.core.os.bundleOf
import androidx.navigation.navArgument
import java.io.Serializable

val Recipes = arrayOf(
    mutableMapOf(
        "image" to R.drawable.spicy_ramen_5e1f14ab_d7d7_4be8_a907_fb0fd106664e,
        "name" to "Spicy Ramen",
        "description" to "A flavorful ramen with a spicy broth, tender noodles and fresh toppings.",
        "prep" to "Start by heating a large pot over medium heat. Add a tablespoon of vegetable oil (if you have it) to the pot and let it warm up. Next, toss in 1 tablespoon of freshly grated ginger and sauté it for about a minute until it’s aromatic. Now, sprinkle in ½ teaspoon of spicy Korean chili flakes (gochugaru) to add that distinct heat and flavor.\n\nAdd ½ pound of ground pork (or beef, if you prefer) to the pot. Use a spoon to break it up and cook it until it’s fully browned. This should take about 5-7 minutes. Once the meat is cooked, stir in 2 tablespoons of soy sauce, which will infuse the pork with a savory depth.\n\nPour in enough water to cover the meat and create a broth—around 4 cups should do the trick. Bring the mixture to a gentle boil, then reduce the heat to a simmer. Let it cook for about 10 minutes to meld the flavors together. Taste the broth and season with salt and freshly ground black pepper to your liking.\n\nWhile the broth is simmering, bring another pot of water to a boil. Add 8 ounces of ramen noodles and cook according to the package instructions, usually about 2-3 minutes. Once cooked, drain the noodles and set them aside.\n\nIn the last few minutes of simmering the broth, add your choice of garnishes to the pot. Toss in some mushrooms, bok choy, and bean sprouts. These will cook quickly and add a fresh crunch to your ramen. If you’re using kimchi, you can stir it in at this point for an extra punch of flavor.\n\nDivide the cooked noodles between two bowls. Ladle the hot broth with the ground pork and vegetables over the noodles. Garnish each bowl with sliced green onions, a sprinkle of sesame seeds, and additional scallions. Add a handful of bean sprouts and a few slices of bok choy for a fresh crunch.\n\nIf you like, add a spoonful of kimchi on the side for an extra burst of flavor. Your spicy ramen is now ready to be enjoyed.",
        "ingredients" to arrayOf(
            "8 ounces Ramen noodles",
            "⅓ cup Green onion",
            "1 tablespoon Fresh grated ginger",
            "1/2 teaspoon Spicy Korean chili flakes (gochugaru)",
            "1/2 pound Ground pork or beef",
            "2 tablespoons Soy sauce",
            "1 teaspoon Sesame seeds to taste",
            "Salt to taste",
            "Black pepper chopped",
            "Scallions for garnish",
            "Bean sprouts for garnish",
            "Bok choy for garnish",
            "Mushrooms for garnish",
            "Kimchi (optional)"
        )
    ),
    mutableMapOf(
        "image" to R.drawable.fluffy_pancakes_f7e0a530_789e_4d18_8595_d2e7cbac975a,
        "name" to "Fluffy Pancakes",
        "description" to "Light and airy pancakes topped with butter and maple syrup.",
        "prep" to "Start by grabbing a large mixing bowl. In it, whisk together 1 1/2 cups of all-purpose flour, 3 1/2 tablespoons of granulated sugar, 2 1/2 teaspoons of baking powder, and 1/4 teaspoon of salt. This mixture of dry ingredients is the base of your pancake batter and ensures that your pancakes will rise perfectly.\n\nIn a separate bowl, crack open 1 cup of large eggs and beat them lightly. Add 1 cup of milk to the eggs, followed by 2 tablespoons of melted unsalted butter and 1 teaspoon of vanilla extract. Whisk these ingredients together until they’re well combined.\n\nPour the wet ingredients into the bowl with the dry ingredients. Using a whisk or a spatula, gently stir them together. Be careful not to overmix; a few lumps in the batter are perfectly fine. Overmixing can make the pancakes dense rather than fluffy.\n\nHeat a nonstick skillet or griddle over medium heat. Lightly grease the surface with a bit of butter or cooking spray. Once the pan is hot, ladle about 1/4 cup of batter for each pancake onto the skillet. You should see bubbles forming on the surface of the pancakes as they cook.\n\nAfter about 2-3 minutes, when the edges start to look set and the underside is golden brown, flip the pancake gently with a spatula. Cook for another 1-2 minutes on the other side until it’s also golden brown and cooked through.\n\nStack the pancakes on a plate and keep them warm in a low oven if you’re making a big batch. Serve them with your favorite toppings: a drizzle of maple syrup, a pat of butter, fresh fruit, or a dusting of powdered sugar.",
        "ingredients" to arrayOf(
            "1 1/2 cups all-purpose flour",
            "3 1/2 tablespoons granulated sugar",
            "2 1/2 teaspoons baking powder",
            "1/4 teaspoon salt",
            "1 cup large eggs",
            "1 cup milk",
            "2 tablespoons unsalted butter, melted",
            "1 teaspoon vanilla extract",
        )
    ),
    mutableMapOf(
        "image" to R.drawable.garden_salad_d0e5c03b_c7b9_489d_abee_52428ba70ccb,
        "name" to "Garden Salad",
        "description" to "A refreshing salad with crisp vegetables and a tangy vinaigrette.",
        "prep" to "Start by heating a large pot over medium heat. Add a tablespoon of vegetable oil (if you have it) to the pot and let it warm up. Next, toss in 1 tablespoon of freshly grated ginger and sauté it for about a minute until it’s aromatic. Now, sprinkle in ½ teaspoon of spicy Korean chili flakes (gochugaru) to add that distinct heat and flavor.\nAdd ½ pound of ground pork (or beef, if you prefer) to the pot. Use a spoon to break it up and cook it until it’s fully browned. This should take about 5-7 minutes. Once the meat is cooked, stir in 2 tablespoons of soy sauce, which will infuse the pork with a savory depth.\nPour in enough water to cover the meat and create a broth—around 4 cups should do the trick. Bring the mixture to a gentle boil, then reduce the heat to a simmer. Let it cook for about 10 minutes to meld the flavors together. Taste the broth and season with salt and freshly ground black pepper to your liking.\nWhile the broth is simmering, bring another pot of water to a boil. Add 8 ounces of ramen noodles and cook according to the package instructions, usually about 2-3 minutes. Once cooked, drain the noodles and set them aside.\nIn the last few minutes of simmering the broth, add your choice of garnishes to the pot. Toss in some mushrooms, bok choy, and bean sprouts. These will cook quickly and add a fresh crunch to your ramen. If you’re using kimchi, you can stir it in at this point for an extra punch of flavor.\nDivide the cooked noodles between two bowls. Ladle the hot broth with the ground pork and vegetables over the noodles. Garnish each bowl with sliced green onions, a sprinkle of sesame seeds, and additional scallions. Add a handful of bean sprouts and a few slices of bok choy for a fresh crunch.\nIf you like, add a spoonful of kimchi on the side for an extra burst of flavor. Your spicy ramen is now ready to be enjoyed.",
        "ingredients" to arrayOf(
            "4 cups mixed greens (lettuce, arugula, spinach)",
            "1 cup cherry tomatoes, halved",
            "1 cup cucumber, sliced",
            "1 cup carrots, peeled and grated",
            "1/2 cup red bell pepper, sliced",
            "1/2 cup croutons",
            "1/2 cup shredded cheddar cheese (optional)",
            "1/4 cup chopped fresh parsley",
            "1/4 cup chopped fresh basil",
            "2 tablespoons olive oil",
            "2 tablespoons white wine vinegar",
            "Salt and pepper to taste",
        )
    ),

    mutableMapOf(
        "image" to R.drawable.spicy_ramen_5e1f14ab_d7d7_4be8_a907_fb0fd106664e,
        "name" to "Spicy Ramen",
        "description" to "A flavorful ramen with a spicy broth, tender noodles and fresh toppings",
        "prep" to "Start by heating a large pot over medium heat. Add a tablespoon of vegetable oil (if you have it) to the pot and let it warm up. Next, toss in 1 tablespoon of freshly grated ginger and sauté it for about a minute until it’s aromatic. Now, sprinkle in ½ teaspoon of spicy Korean chili flakes (gochugaru) to add that distinct heat and flavor.\nAdd ½ pound of ground pork (or beef, if you prefer) to the pot. Use a spoon to break it up and cook it until it’s fully browned. This should take about 5-7 minutes. Once the meat is cooked, stir in 2 tablespoons of soy sauce, which will infuse the pork with a savory depth.\nPour in enough water to cover the meat and create a broth—around 4 cups should do the trick. Bring the mixture to a gentle boil, then reduce the heat to a simmer. Let it cook for about 10 minutes to meld the flavors together. Taste the broth and season with salt and freshly ground black pepper to your liking.\nWhile the broth is simmering, bring another pot of water to a boil. Add 8 ounces of ramen noodles and cook according to the package instructions, usually about 2-3 minutes. Once cooked, drain the noodles and set them aside.\nIn the last few minutes of simmering the broth, add your choice of garnishes to the pot. Toss in some mushrooms, bok choy, and bean sprouts. These will cook quickly and add a fresh crunch to your ramen. If you’re using kimchi, you can stir it in at this point for an extra punch of flavor.\nDivide the cooked noodles between two bowls. Ladle the hot broth with the ground pork and vegetables over the noodles. Garnish each bowl with sliced green onions, a sprinkle of sesame seeds, and additional scallions. Add a handful of bean sprouts and a few slices of bok choy for a fresh crunch.\nIf you like, add a spoonful of kimchi on the side for an extra burst of flavor. Your spicy ramen is now ready to be enjoyed.",
        "ingredients" to arrayOf(
            "8 ounces Ramen noodles",
            "⅓ cup Green onion",
            "1 tablespoon Fresh grated ginger",
            "1/2 teaspoon Spicy Korean chili flakes (gochugaru)",
            "1/2 pound Ground pork or beef",
            "2 tablespoons Soy sauce",
            "1 teaspoon Sesame seeds to taste",
            "Salt to taste",
            "Black pepper chopped",
            "Scallions for garnish",
            "Bean sprouts for garnish",
            "Bok choy for garnish",
            "Mushrooms for garnish",
            "Kimchi (optional)"
        )
    ),
    mutableMapOf(
        "image" to R.drawable.spicy_ramen_5e1f14ab_d7d7_4be8_a907_fb0fd106664e,
        "name" to "Spicy Ramen",
        "description" to "A flavorful ramen with a spicy broth, tender noodles and fresh toppings",
        "prep" to "Start by heating a large pot over medium heat. Add a tablespoon of vegetable oil (if you have it) to the pot and let it warm up. Next, toss in 1 tablespoon of freshly grated ginger and sauté it for about a minute until it’s aromatic. Now, sprinkle in ½ teaspoon of spicy Korean chili flakes (gochugaru) to add that distinct heat and flavor.\nAdd ½ pound of ground pork (or beef, if you prefer) to the pot. Use a spoon to break it up and cook it until it’s fully browned. This should take about 5-7 minutes. Once the meat is cooked, stir in 2 tablespoons of soy sauce, which will infuse the pork with a savory depth.\nPour in enough water to cover the meat and create a broth—around 4 cups should do the trick. Bring the mixture to a gentle boil, then reduce the heat to a simmer. Let it cook for about 10 minutes to meld the flavors together. Taste the broth and season with salt and freshly ground black pepper to your liking.\nWhile the broth is simmering, bring another pot of water to a boil. Add 8 ounces of ramen noodles and cook according to the package instructions, usually about 2-3 minutes. Once cooked, drain the noodles and set them aside.\nIn the last few minutes of simmering the broth, add your choice of garnishes to the pot. Toss in some mushrooms, bok choy, and bean sprouts. These will cook quickly and add a fresh crunch to your ramen. If you’re using kimchi, you can stir it in at this point for an extra punch of flavor.\nDivide the cooked noodles between two bowls. Ladle the hot broth with the ground pork and vegetables over the noodles. Garnish each bowl with sliced green onions, a sprinkle of sesame seeds, and additional scallions. Add a handful of bean sprouts and a few slices of bok choy for a fresh crunch.\nIf you like, add a spoonful of kimchi on the side for an extra burst of flavor. Your spicy ramen is now ready to be enjoyed.",
        "ingredients" to arrayOf(
            "8 ounces Ramen noodles",
            "⅓ cup Green onion",
            "1 tablespoon Fresh grated ginger",
            "1/2 teaspoon Spicy Korean chili flakes (gochugaru)",
            "1/2 pound Ground pork or beef",
            "2 tablespoons Soy sauce",
            "1 teaspoon Sesame seeds to taste",
            "Salt to taste",
            "Black pepper chopped",
            "Scallions for garnish",
            "Bean sprouts for garnish",
            "Bok choy for garnish",
            "Mushrooms for garnish",
            "Kimchi (optional)"
        )
    ),
    mutableMapOf(
        "image" to R.drawable.spicy_ramen_5e1f14ab_d7d7_4be8_a907_fb0fd106664e,
        "name" to "Spicy Ramen",
        "description" to "A flavorful ramen with a spicy broth, tender noodles and fresh toppings",
        "prep" to "Start by heating a large pot over medium heat. Add a tablespoon of vegetable oil (if you have it) to the pot and let it warm up. Next, toss in 1 tablespoon of freshly grated ginger and sauté it for about a minute until it’s aromatic. Now, sprinkle in ½ teaspoon of spicy Korean chili flakes (gochugaru) to add that distinct heat and flavor.\nAdd ½ pound of ground pork (or beef, if you prefer) to the pot. Use a spoon to break it up and cook it until it’s fully browned. This should take about 5-7 minutes. Once the meat is cooked, stir in 2 tablespoons of soy sauce, which will infuse the pork with a savory depth.\nPour in enough water to cover the meat and create a broth—around 4 cups should do the trick. Bring the mixture to a gentle boil, then reduce the heat to a simmer. Let it cook for about 10 minutes to meld the flavors together. Taste the broth and season with salt and freshly ground black pepper to your liking.\nWhile the broth is simmering, bring another pot of water to a boil. Add 8 ounces of ramen noodles and cook according to the package instructions, usually about 2-3 minutes. Once cooked, drain the noodles and set them aside.\nIn the last few minutes of simmering the broth, add your choice of garnishes to the pot. Toss in some mushrooms, bok choy, and bean sprouts. These will cook quickly and add a fresh crunch to your ramen. If you’re using kimchi, you can stir it in at this point for an extra punch of flavor.\nDivide the cooked noodles between two bowls. Ladle the hot broth with the ground pork and vegetables over the noodles. Garnish each bowl with sliced green onions, a sprinkle of sesame seeds, and additional scallions. Add a handful of bean sprouts and a few slices of bok choy for a fresh crunch.\nIf you like, add a spoonful of kimchi on the side for an extra burst of flavor. Your spicy ramen is now ready to be enjoyed.",
        "ingredients" to arrayOf(
            "8 ounces Ramen noodles",
            "⅓ cup Green onion",
            "1 tablespoon Fresh grated ginger",
            "1/2 teaspoon Spicy Korean chili flakes (gochugaru)",
            "1/2 pound Ground pork or beef",
            "2 tablespoons Soy sauce",
            "1 teaspoon Sesame seeds to taste",
            "Salt to taste",
            "Black pepper chopped",
            "Scallions for garnish",
            "Bean sprouts for garnish",
            "Bok choy for garnish",
            "Mushrooms for garnish",
            "Kimchi (optional)"
        )
    ),
    mutableMapOf(
        "image" to R.drawable.spicy_ramen_5e1f14ab_d7d7_4be8_a907_fb0fd106664e,
        "name" to "Spicy Ramen",
        "description" to "A flavorful ramen with a spicy broth, tender noodles and fresh toppings",
        "prep" to "Start by heating a large pot over medium heat. Add a tablespoon of vegetable oil (if you have it) to the pot and let it warm up. Next, toss in 1 tablespoon of freshly grated ginger and sauté it for about a minute until it’s aromatic. Now, sprinkle in ½ teaspoon of spicy Korean chili flakes (gochugaru) to add that distinct heat and flavor.\nAdd ½ pound of ground pork (or beef, if you prefer) to the pot. Use a spoon to break it up and cook it until it’s fully browned. This should take about 5-7 minutes. Once the meat is cooked, stir in 2 tablespoons of soy sauce, which will infuse the pork with a savory depth.\nPour in enough water to cover the meat and create a broth—around 4 cups should do the trick. Bring the mixture to a gentle boil, then reduce the heat to a simmer. Let it cook for about 10 minutes to meld the flavors together. Taste the broth and season with salt and freshly ground black pepper to your liking.\nWhile the broth is simmering, bring another pot of water to a boil. Add 8 ounces of ramen noodles and cook according to the package instructions, usually about 2-3 minutes. Once cooked, drain the noodles and set them aside.\nIn the last few minutes of simmering the broth, add your choice of garnishes to the pot. Toss in some mushrooms, bok choy, and bean sprouts. These will cook quickly and add a fresh crunch to your ramen. If you’re using kimchi, you can stir it in at this point for an extra punch of flavor.\nDivide the cooked noodles between two bowls. Ladle the hot broth with the ground pork and vegetables over the noodles. Garnish each bowl with sliced green onions, a sprinkle of sesame seeds, and additional scallions. Add a handful of bean sprouts and a few slices of bok choy for a fresh crunch.\nIf you like, add a spoonful of kimchi on the side for an extra burst of flavor. Your spicy ramen is now ready to be enjoyed.",
        "ingredients" to arrayOf(
            "8 ounces Ramen noodles",
            "⅓ cup Green onion",
            "1 tablespoon Fresh grated ginger",
            "1/2 teaspoon Spicy Korean chili flakes (gochugaru)",
            "1/2 pound Ground pork or beef",
            "2 tablespoons Soy sauce",
            "1 teaspoon Sesame seeds to taste",
            "Salt to taste",
            "Black pepper chopped",
            "Scallions for garnish",
            "Bean sprouts for garnish",
            "Bok choy for garnish",
            "Mushrooms for garnish",
            "Kimchi (optional)"
        )
    ),
)

val RecipeMap: Map<String, Map<String, *>> = Recipes.associateBy { it["name"] as String}

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
        composable("page6/{name}"){backStackEntry -> RecipePage(navController = navController, recipeName = backStackEntry.arguments?.getString("name").toString())}
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

            Row (modifier = modifier
                .fillMaxWidth(0.48f)
                .fillMaxHeight()
                .padding(10.dp)){
                HorizontalDivider(modifier = modifier
                    .weight(3f)
                    .align(Alignment.CenterVertically))
            }

           Row(modifier = modifier.fillMaxWidth(0.12f)) {
               Text(text = "or", modifier = modifier
                   .align(Alignment.CenterVertically), color = Color.White, fontSize = 20.sp)
           }
            Row (modifier = modifier
                .fillMaxWidth(1f)
                .fillMaxHeight()
                .padding(10.dp)){
                HorizontalDivider(modifier = modifier
                    .weight(3f)
                    .align(Alignment.CenterVertically))
            }
        }
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp)) {
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
        RecipeList(navController = navController)
    }
}

@Composable
fun RecipesHeader(modifier: Modifier = Modifier, navController: NavController, name: String? = "Recipes"){
    Column(modifier = modifier
        .fillMaxSize()
        .padding(top = 20.dp, start = 20.dp)) {
        Row (modifier = modifier){
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back button", tint = Color.White, modifier = modifier
                .padding(vertical = 30.dp)
                .clickable { navController.navigateUp() })
            Text(text = name.toString(), fontWeight = FontWeight.Bold, color = Color.White, fontSize = 24.sp, modifier = modifier.padding(30.dp))
        }
        Row {

        }
    }
}

@Composable
fun RecipeList(modifier: Modifier = Modifier, navController: NavController){

    Column(modifier = modifier.verticalScroll(state = rememberScrollState(), enabled = true)) {
        for (recipe in 0 .. Recipes.size - 1){
            val currentRecipe = Recipes[recipe]
            Row(modifier = modifier.padding(10.dp)) {
                RecipeCard(image = painterResource(id = currentRecipe.getValue("image") as Int), recipeName = currentRecipe.getValue("name").toString(), description = currentRecipe.getValue("description").toString(), modifier = Modifier.clip(RoundedCornerShape(24.dp)), navController = navController, ingredients = currentRecipe.getValue("ingredients"), id = recipe)
            }
        }
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
                .clickable { navController.navigate("page5") }
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
        PageButton(text = "Search", navController = navController, color = Color(0xFF2b2b2b), route = "page5")
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

            Column(modifier = modifier.clickable { navController.navigate("page5") }, horizontalAlignment = Alignment.CenterHorizontally){
                Icon(imageVector = Icons.Filled.List, contentDescription = null, modifier, tint = Color.White)
                Text(text = "Recipes", color = Color.White, textAlign = TextAlign.Center)
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

@Composable
fun RecipeCard(modifier: Modifier = Modifier, image: Painter, recipeName: String?, description: String, id: Int = 2, navController: NavController, ingredients: Serializable){
    val ingredientsBundle = bundleOf("ingredients" to ingredients)

    Box(modifier = modifier
        .fillMaxWidth()
        .clickable { navController.navigate("page6/${recipeName}") }
        .background(color = Color(0xFF2b2b2b))){
        Row(modifier = modifier.padding(20.dp)) {
            Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
                Image(modifier = modifier.height(140.dp),painter = image, contentDescription = null)
            }
            Column(modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Text(text = recipeName.toString(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(modifier = Modifier.padding(top = 10.dp), text = description, color = Color(0XFFC3C3C3))
            }
        }
    }
}

@Composable
fun RecipePage(modifier: Modifier = Modifier, navController: NavController, recipeName: String = "Spicy Ramen"){
    Column {
        RecipesHeader(navController = navController,
            name = recipeName,
            modifier = modifier
                .height(110.dp)
                .background(Color(0xFF2b2b2b)))
        RecipeBody(modifier = Modifier, prep = RecipeMap[recipeName]!!.getValue("prep").toString(),ingredients = RecipeMap[recipeName]!!.getValue("ingredients") as Array<String>)
    }
}

@Composable
fun RecipeBody(modifier: Modifier = Modifier, ingredients: Array<String>, prep: String){
    Column(modifier.verticalScroll(state = rememberScrollState(), enabled = true)) {
        IngredientsCard(
            ingredients = ingredients
            , modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .padding(20.dp))

        PrepCard(text = prep, modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .padding(20.dp))
    }
}

@Composable
fun IngredientsCard(modifier: Modifier = Modifier, ingredients: Array<String>){
    Column(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .background(color = Color(0XFF2b2b2b))) {
        Row(modifier) {
            Text(text = "Ingredients", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)) {
            for (ingredient in 0 .. ingredients.size - 1){
                Row(Modifier.padding(start = 20.dp)) {
                    Text(text = ingredients[ingredient], color = Color.White, modifier = Modifier.padding(1.dp))
                }
            }
        }
    }
}

@Composable
fun PrepCard(modifier: Modifier = Modifier, text: String){
    Column(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .background(color = Color(0XFF2b2b2b))) {
        Row(modifier) {
            Text(text = "Instructions", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Column(modifier) {
            Text(text = text, color = Color.White)
        }
    }
}