package you.thiago.restapi.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import you.thiago.restapi.R
import you.thiago.restapi.datasource.Digimon

class DigimonsAdapter(private val dataSet: List<Digimon>) : RecyclerView.Adapter<DigimonsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView
        val textLevel: TextView
        val imageProfile: ImageView

        init {
            textName = view.findViewById(R.id.txt_name)
            textLevel = view.findViewById(R.id.txt_level)
            imageProfile = view.findViewById(R.id.img_profile)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_digimon, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textName.text = dataSet[position].name
        viewHolder.textLevel.text = dataSet[position].level

        Glide.with(viewHolder.imageProfile.context)
            .load(dataSet[position].img)
            .into(viewHolder.imageProfile)
    }

    override fun getItemCount() = dataSet.size
}
