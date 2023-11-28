package com.example.clicker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ShopItemAdapter(val viewModel: ClickerViewModel) : RecyclerView.Adapter<ShopItemAdapter.ShopItemViewHolder>() {
    var data = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ShopItemViewHolder = ShopItemViewHolder.inflateFrom(parent, viewModel)
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size

    class ShopItemViewHolder(rootView: CardView, val viewModel: ClickerViewModel) : RecyclerView.ViewHolder(rootView) {
        val itemName = rootView.findViewById<TextView>(R.id.item_name)
        val itemCost = rootView.findViewById<TextView>(R.id.item_cost)
        val itemEffects = rootView.findViewById<TextView>(R.id.item_effects)
        val itemLevel = rootView.findViewById<TextView>(R.id.item_level)
        val itemButton = rootView.findViewById<Button>(R.id.buy_item)
        val maxButton = rootView.findViewById<Button>(R.id.buy_max)

        companion object {
            fun inflateFrom(parent: ViewGroup, viewModel: ClickerViewModel): ShopItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.shop_item, parent, false) as CardView
                return ShopItemViewHolder(view, viewModel)
            }
        }

        fun bind(shopItem: ShopItem) {
            setText(shopItem)
            itemButton.setOnClickListener {
                viewModel.buyItem(shopItem)
                setText(shopItem)
            }
            maxButton.setOnClickListener {
                viewModel.buyMax(shopItem)
                setText(shopItem)
            }
        }
        private fun setText(shopItem: ShopItem) {
            itemName.text = shopItem.name
            itemCost.text = "Cost: ${shopItem.getFullCost()}"
            itemEffects.text = shopItem.getEffects()
            itemLevel.text = "Level: ${shopItem.level}"
        }
    }
}