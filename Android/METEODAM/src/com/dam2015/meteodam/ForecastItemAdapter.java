package com.dam2015.meteodam;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ForecastItemAdapter extends ArrayAdapter<ForecastItem> {
	
	private Context context;
	private ArrayList<ForecastItem> arrayList;

	public ForecastItemAdapter(Context context, ArrayList<ForecastItem> arrayList) {
		super(context, R.layout.forecast_item_layout, arrayList);
		
		this.context = context;
		this.arrayList = arrayList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// 1. Create inflater 
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.forecast_item_layout, parent, false);
        
        // 3. Get all the fields from the inflated view
        ImageView imgForecastItemIcon = (ImageView) rowView.findViewById(R.id.imgForecastItemIcon);
        TextView lblForecastItemDescription = (TextView) rowView.findViewById(R.id.lblForecastItemDescription);
        TextView lblForecastItemDate = (TextView) rowView.findViewById(R.id.lblForecastItemDate);
        TextView lblForecastItemTemp = (TextView) rowView.findViewById(R.id.lblForecastItemTemp);
        
        // 4. Set all the stuff
        imgForecastItemIcon.setImageBitmap(arrayList.get(position).getIcon());
        lblForecastItemDescription.setText(arrayList.get(position).getDescription());
        lblForecastItemDate.setText(arrayList.get(position).getDate());
        lblForecastItemTemp.setText(arrayList.get(position).getTemperature()+"º");
        
		return rowView;
	}
}
