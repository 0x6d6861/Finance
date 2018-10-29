package co.heri.finace.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import co.heri.finace.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_offers_card_layout.view.*

class ProductsAdapter(private val data : ArrayList<Product>, val context: Context?) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.product_offers_card_layout, parent, false)
        )
    }

    override fun getItemCount(): Int{
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(data[position])


    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product) = with(itemView) {

            product_title.text = item.title
            product_offer.text = item.offer
            product_new_price.text = item.new_price
            product_old_price.text = item.old_price


            Picasso.get()
                    .load(item.image).into(product_image);

            setOnClickListener {
                // TODO: Handle on click
                Toast.makeText(itemView.context, item.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}