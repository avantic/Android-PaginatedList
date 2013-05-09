package net.avantic.paginatedList;

import java.util.List;

import net.avantic.paginatedList.adapter.ListAdapter;
import net.avantic.paginatedList.dao.ItemDAO;
import net.avantic.paginatedList.model.Item;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class ListFragment extends Fragment implements OnItemClickListener {
	public static final String ARG_PAGE = "net.avantic.paginatedList.ARG_PAGE";
	public static final int HEIGHT_ITEM_DP = 70;
	public static final int HEIGHT_SCREEN_OCCUPATED_DP = 60;

	private int pageNumber;

	private ListAdapter listAdapter;

	private ListFragment() { };

	public ListFragment(int pageNumber) {
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, pageNumber);
		setArguments(args);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pageNumber = getArguments().getInt(ARG_PAGE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

		listAdapter = new ListAdapter(getActivity().getApplicationContext());
		ListView listView = (ListView) rootView.findViewById(R.id.listView);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(this);

		updateViewItems(rootView);

		return rootView;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public static int getTotalPages(DisplayMetrics displayMetrics) {
		return ((Double) Math.ceil((double) ItemDAO.TOTAL_ITEMS	/ (double) getItemsByPage(displayMetrics))).intValue();
	}

	private static int getItemsByPage(DisplayMetrics displayMetrics) {
		return getHeightScreenAvailableForItems(displayMetrics)	/ getHeightItem(displayMetrics);
	}

	private static int getHeightItem(DisplayMetrics displayMetrics) {
		return dpToPixels(HEIGHT_ITEM_DP, displayMetrics);
	}

	private static int getHeightScreenAvailableForItems(DisplayMetrics displayMetrics) {
		int totalHeightScreen = displayMetrics.heightPixels;
		int heightScreenOccupated = dpToPixels(HEIGHT_SCREEN_OCCUPATED_DP, displayMetrics);

		return totalHeightScreen - heightScreenOccupated;
	}

	private static int dpToPixels(int dp, DisplayMetrics displayMetrics) {
		final float scale = displayMetrics.density;
		return (int) (dp * scale + 0.5f);
	}

	private void updateViewItems(View rootView) {
		int currentPage = getPageNumber();
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		if (currentPage >= getTotalPages(displayMetrics))
			return;

		int itemsByPage = getItemsByPage(displayMetrics);
		int initialItem = currentPage * itemsByPage;
		int lastItem = initialItem + itemsByPage - 1;
		List<Item> items = ItemDAO.getItems();
		if (lastItem >= items.size())
			lastItem = items.size() - 1;

		listAdapter.clear();
		for (int i = initialItem; i <= lastItem; i++)
			listAdapter.add(items.get(i));

		listAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, final View view, int location, long id) {
		if (listAdapter.equals(parent.getAdapter())) {
			Item item = listAdapter.getItem(location);
			Toast.makeText(getActivity().getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
		}
	}

}