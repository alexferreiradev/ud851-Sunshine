package com.example.android.sunshine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sunshine.R;

import java.util.List;

/**
 * Created by alex on 08/04/18.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private List<String> mListWeatherData;

    public ForecastAdapter(List<String> mListWeatherData) {
        this.mListWeatherData = mListWeatherData;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.forecast_list_item, parent, false);
        ForecastViewHolder forecastViewHolder = new ForecastViewHolder(itemView);

        return forecastViewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.bind(mListWeatherData.get(position));
    }

    @Override
    public int getItemCount() {
        return mListWeatherData == null ? 0 : mListWeatherData.size();
    }

    public void setListData(List<String> weatherData) {
        mListWeatherData = weatherData;
        notifyDataSetChanged();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder {

        private final TextView mWeatherDataTv;

        public ForecastViewHolder(View itemView) {
            super(itemView);

            mWeatherDataTv = (TextView) itemView.findViewById(R.id.tv_weather_data);
        }

        public void bind(String weatherData) {
            mWeatherDataTv.setText(weatherData);
        }
    }
}
