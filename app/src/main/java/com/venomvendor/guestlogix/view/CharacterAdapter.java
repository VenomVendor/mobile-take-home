/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.venomvendor.guestlogix.R;
import com.venomvendor.guestlogix.episode.model.Character;

import java.util.List;
import java.util.Locale;

/**
 * Custom Grid View
 */
public class CharacterAdapter extends BaseAdapter {

    private final Context mContext;
    private List<Character> mResponse;

    public CharacterAdapter(Context context) {
        this(context, null);
    }

    public CharacterAdapter(Context context, List<Character> response) {
        mContext = context;
        setData(response);
    }

    public void setData(List<Character> response) {
        mResponse = response;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mResponse == null ? 0 : mResponse.size();
    }

    @Override
    public Object getItem(int position) {
        return mResponse == null ? null : mResponse.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mView = convertView;

        ViewHolder holder;
        if (mView == null) {
            holder = new ViewHolder();
            /*
             * LayoutInflater
             */
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            /*
             * Inflate Custom List View
             */
            mView = inflater.inflate(R.layout.custom_episode_view, parent, false);
            holder.name = mView.findViewById(R.id.name);
            holder.id = mView.findViewById(R.id.id);
            holder.dp = mView.findViewById(R.id.dp);

            mView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) mView.getTag();
        }
        Character character = mResponse.get(position);

        setData(character, holder);

        return mView;
    }

    private void setData(Character character, ViewHolder holder) {
        holder.name.setText(character.getName());
        holder.id.setText(
                String.format(Locale.getDefault(), "%d : %s : %s", character.getId(),
                        character.getName(), character.getStatus())
        );
    }

    private class ViewHolder {

        private TextView name;
        private TextView id;
        private ImageView dp;
    }
}
