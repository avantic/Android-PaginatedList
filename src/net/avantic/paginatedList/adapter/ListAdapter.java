package net.avantic.paginatedList.adapter;

import java.util.ArrayList;
import java.util.List;

import net.avantic.paginatedList.R;
import net.avantic.paginatedList.model.Item;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	
	private Context context;
	private List<Item> items;
	
	private class ViewHolder {
		public TextView title;
		public TextView description;
		public View color;
	}
	
	public ListAdapter(Context context) {
		super();
		this.context = context;
		this.items = new ArrayList<Item>();
	}


	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Item getItem(int location) {
		return items.get(location);
	}

	@Override
	public long getItemId(int location) {
		return location;
	}

	@Override
	public View getView(int location, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = layoutInflater.inflate(R.layout.item_list, null);

			// Se utiliza el patrón ViewHolder para optimizar el rendimiento de la aplicación, evitando llamar a findViewById() repetidamente.
			// http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.title = (TextView) rowView.findViewById(R.id.title);
			viewHolder.description = (TextView) rowView.findViewById(R.id.description);
			viewHolder.color = rowView.findViewById(R.id.color);
			rowView.setTag(viewHolder);
		}

		Item item = getItem(location);
		if (item != null) {
			ViewHolder viewHolder = (ViewHolder) rowView.getTag();
			viewHolder.title.setText(item.getTitle());
			viewHolder.title.setTextColor(Color.BLACK);
			viewHolder.description.setText(item.getDescription());
			viewHolder.description.setTextColor(Color.BLACK);
			viewHolder.color.setBackgroundColor(item.getColor());
		}

		return rowView;
	}
	
	public void add(Item item) {
		items.add(item);
	}
	
	public void clear() {
		items.clear();
	}

}
