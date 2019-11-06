package com.huynn109.sliderbannerex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.huynn109.sliderbannerex.slider.CenterScrollListener
import com.huynn109.sliderbannerex.slider.ItemAdapter
import com.huynn109.sliderbannerex.slider.LocalDataAdapter
import com.huynn109.sliderbannerex.slider.OverFlyingLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemAdapter by lazy {
        ItemAdapter { position: Int, item: Item ->
            Toast.makeText(this@MainActivity, "Pos ${position}", Toast.LENGTH_LONG).show()
            item_list.smoothScrollToPosition(position)
        }
    }
    private val possibleItems = listOf(
        Item("Airplanes", R.drawable.ic_launcher_background),
        Item("Cars", R.drawable.ic_launcher_background),
        Item("Food", R.drawable.ic_launcher_background),
        Item("Gas", R.drawable.ic_launcher_background),
        Item("Home", R.drawable.ic_launcher_background)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()

//
//        item_list.initialize(itemAdapter)
//        item_list.setViewsToChangeColor(listOf(R.id.list_item_background, R.id.list_item_text))
//        itemAdapter.setItems(getLargeListOfItems())
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var mOverFlyingLayoutManager: OverFlyingLayoutManager
    internal var currentPosition = 0


    private fun initRecycler() {
        recyclerView = findViewById(R.id.item_list)
        mOverFlyingLayoutManager =
            OverFlyingLayoutManager(0.75f, 150, OverFlyingLayoutManager.HORIZONTAL)
        recyclerView.adapter = LocalDataAdapter()
        recyclerView.layoutManager = mOverFlyingLayoutManager

        recyclerView.addOnScrollListener(CenterScrollListener())
        mOverFlyingLayoutManager.setOnPageChangeListener(object :
            OverFlyingLayoutManager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPosition = position
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun getLargeListOfItems(): List<Item> {
        val items = mutableListOf<Item>()
        (0..40).map { items.add(possibleItems.random()) }
        return items
    }


}

data class Item(
    val title: String,
    @DrawableRes val icon: Int
)
