package co.heri.finace.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.heri.finace.R
import co.heri.finace.models.Offer
import co.heri.finace.models.OffersAdapter
import co.heri.finace.models.Product
import co.heri.finace.models.ProductsAdapter
import kotlinx.android.synthetic.main.fragment_home.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var offers = ArrayList<Offer>()
        offers.add(Offer(1, "Buy 2 Trousers", "Get 1 Shirt Free", "Promo Code: SMART", "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201708/31/00112262432706____5__516x640.jpg"))
        offers.add(Offer(2, "Cool Red Backpack", "Get 20% off", "Promo Code: REDBAG", "https://cdn.shopify.com/s/files/1/0790/7429/products/23510-325-903_530x.jpg?v=1536900845"))

        var products = ArrayList<Product>()
        products.add(Product(1, "Rene Knit Top", "30% Offer", "$54", "$68", "https://coast.btxmedia.com/pws/client/images/catalogue/products/2059580/MD/2059580.jpg"))
        products.add(Product(2, "Minnie Top", "Only for July", "$76", "$58", "https://coast.btxmedia.com/pws/client/images/catalogue/products/2077833/LG/2077833.jpg"))
        products.add(Product(3, "Amendine Maxi Dress", "Black Friday", "$269", "$178", "https://coast.btxmedia.com/pws/client/images/catalogue/products/2077980/LG/2077980_1.jpg"))

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        var finance_offers = view.findViewById<RecyclerView>(R.id.finance_offers)
        var product_offers = view.findViewById<RecyclerView>(R.id.product_recycler)

        finance_offers.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        finance_offers.adapter = OffersAdapter(offers, this.context)

        product_offers.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        product_offers.adapter = ProductsAdapter(products, this.context)

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
