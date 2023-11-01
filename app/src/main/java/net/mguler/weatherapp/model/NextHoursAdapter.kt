package net.mguler.weatherapp.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import net.mguler.weatherapp.R
import net.mguler.weatherapp.databinding.HomeRecylerRowBinding

class NextHoursAdapter(private var nextHoursList: List<NextHours>):
    RecyclerView.Adapter<NextHoursAdapter.NextHoursHolder>() {

    class NextHoursHolder(var binding: HomeRecylerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextHoursHolder {
        val binding = HomeRecylerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NextHoursHolder(binding)
    }

    override fun onBindViewHolder(holder: NextHoursHolder, position: Int) {
        holder.binding.textSmallDegree.text = nextHoursList[position].temperature.toString()
        holder.binding.textTime.text = nextHoursList[position].nextHours

        val nextHoursIcon = nextHoursList[position].icon
        //holder.binding.imageSmallForecast.setImageResource(nextHoursIcon!!)
        holder.binding.imageSmallForecast.load(nextHoursIcon) {
            placeholder(R.drawable.ic_downloading)
        }
    }

    override fun getItemCount(): Int {
        return nextHoursList.size
    }
}