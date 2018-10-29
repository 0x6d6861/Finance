package co.heri.finace.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import co.heri.finace.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.offers_card_layout.view.*

class OffersAdapter(private val data : ArrayList<Offer>, val context: Context?) : RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.offers_card_layout, parent, false)
        )
    }

    override fun getItemCount(): Int{
        return data.size
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) = holder.bind(data[position])


    class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Offer) = with(itemView) {

            offer_title.text = item.title
            offer_promo.text = item.promo

            Picasso.get()
                    .load(item.image).into(offer_image);

            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}