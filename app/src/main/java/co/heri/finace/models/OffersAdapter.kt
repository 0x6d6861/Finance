package co.heri.finace.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import co.heri.finace.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.offers_card_layout.view.*

class OffersAdapter : RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {

    private var data: List<Offer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.offers_card_layout, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) = holder.bind(data[position])

    fun swapData(data: List<Offer>) {
        this.data = data
        notifyDataSetChanged()
    }

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