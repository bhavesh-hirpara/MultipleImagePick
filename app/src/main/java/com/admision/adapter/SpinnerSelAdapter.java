package com.admision.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.admision.R;
import com.admision.objects.Spinner;
import com.admision.utils.Debug;
import com.admision.utils.Utils;

import org.json.JSONArray;

import java.util.ArrayList;


public class SpinnerSelAdapter extends BaseAdapter implements Filterable {

    // private Context mContext;
    private LayoutInflater infalter;
    private ArrayList<Spinner> data = new ArrayList<Spinner>();
    ArrayList<Spinner> dataSource = new ArrayList<Spinner>();

    public SpinnerSelAdapter(Context c) {
        infalter = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // mContext = c;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Spinner getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    boolean isFilterable = false;

    public void setFilterable(boolean isFilterable) {
        this.isFilterable = isFilterable;
    }

    public void addAll(ArrayList<Spinner> files) {

        try {
            this.data.clear();
            this.data.addAll(files);

            if (isFilterable) {
                this.dataSource.clear();
                this.dataSource.addAll(files);
            }

        } catch (Exception e) {
            Utils.sendExceptionReport(e);
        }

        notifyDataSetChanged();
    }

    public ArrayList<Spinner> getSelectedAll() {
        ArrayList<Spinner> data = new ArrayList<Spinner>();

        for (Spinner spinner : this.data) {
            if (spinner.isSelected) {
                data.add(spinner);
            }
        }

        return data;
    }

    public String getSelectedIds() {
        String str = "";

        for (Spinner spinner : data) {
            if (spinner.isSelected) {
                str = str + spinner.ID + ",";
            }
        }

        if (str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }

        return str;
    }

    public ArrayList<String> getSelectedIdList() {
        ArrayList<String> data = new ArrayList<String>();

        for (Spinner spinner : this.data) {
            if (spinner.isSelected) {
                data.add(spinner.ID);
            }
        }

        return data;
    }

    public JSONArray getSelectedIdArray() {
        JSONArray data = new JSONArray();

        try {
            for (Spinner spinner : this.data) {
                if (spinner.isSelected) {
                    data.put(spinner.ID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public String getSelectedTitle() {
        String str = "";

        for (Spinner spinner : data) {
            if (spinner.isSelected) {
                str = str + spinner.title + ", ";
            }
        }

        str = str.trim();
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }

        return str;
    }

    public ArrayList<Spinner> getAll() {
        return data;
    }

    public boolean isSelectedAll() {

        for (Spinner spinner : this.data) {
            if (!spinner.isSelected) {
                return false;
            }
        }

        return true;
    }

    public int getSelectedCount() {

        int cnt = 0;

        for (Spinner spinner : this.data) {
            if (spinner.isSelected) {
                cnt = cnt + 1;
            }
        }

        return cnt;
    }

    public boolean isSelectedAtleastOne() {

        for (Spinner spinner : this.data) {
            if (spinner.isSelected) {
                return true;
            }
        }

        return false;
    }

    public boolean isSelected(int position) {
        return data.get(position).isSelected;
    }

    public void changeSelection(int position, boolean isMultiSel) {

        for (int i = 0; i < data.size(); i++) {
            if (position == i) {
                data.get(i).isSelected = !data.get(i).isSelected;
            } else if (!isMultiSel) {
                data.get(i).isSelected = false;
            }
        }

        notifyDataSetChanged();
    }

    public void setSelection(int position) {
        for (int i = 0; i < data.size(); i++) {
            if (position == i) {
                data.get(i).isSelected = true;
            } else {
                data.get(i).isSelected = false;
            }
        }

        notifyDataSetChanged();
    }

    public void selectAll(boolean selectall) {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).isSelected = selectall;
        }

        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = infalter.inflate(R.layout.spinner_sel_item, null);
            holder.tvMenuTitle = (TextView) convertView
                    .findViewById(R.id.tvSpinnerTitle);

            holder.chkSpinnetItem = (com.rey.material.widget.CheckBox) convertView
                    .findViewById(R.id.chkSpinnetItem);
            holder.chkSpinnetItem.setFocusable(false);
            holder.chkSpinnetItem.setEnabled(false);
            holder.chkSpinnetItem.setClickable(false);
            holder.chkSpinnetItem.setLongClickable(false);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {

            holder.chkSpinnetItem.setChecked(data.get(position).isSelected);
            holder.tvMenuTitle.setText(data.get(position).title);

//            holder.chkSpinnetItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    changeSelection(position, true);
//                }
//            });

        } catch (Exception e) {
            Utils.sendExceptionReport(e);
        }

        return convertView;
    }

    public class ViewHolder {
        TextView tvMenuTitle;
        com.rey.material.widget.CheckBox chkSpinnetItem;
    }

    @Override
    public Filter getFilter() {

        if (isFilterable) {
            return new PTypeFilter();
        }

        return null;
    }

    private class PTypeFilter extends Filter {

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence prefix, FilterResults results) {
            // NOTE: this function is *always* called from the UI thread.

            data = (ArrayList<Spinner>) results.values;
            if (data != null) {
                notifyDataSetChanged();
            } else {
                data = dataSource;
                notifyDataSetChanged();
            }
        }

        protected FilterResults performFiltering(CharSequence prefix) {
            // NOTE: this function is *always* called from a background thread,
            // and
            // not the UI thread.

            FilterResults results = new FilterResults();
            ArrayList<Spinner> new_res = new ArrayList<Spinner>();
            if (prefix != null && prefix.toString().length() > 0) {
                for (int index = 0; index < dataSource.size(); index++) {

                    try {
                        Spinner si = dataSource.get(index);

                        if (si.title.toLowerCase().contains(
                                prefix.toString().toLowerCase())) {
                            new_res.add(si);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                results.values = new_res;
                results.count = new_res.size();

            } else {
                Debug.e("", "Called synchronized view");

                results.values = dataSource;
                results.count = dataSource.size();

            }

            return results;
        }
    }

}
