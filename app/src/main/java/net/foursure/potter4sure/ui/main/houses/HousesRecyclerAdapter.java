package net.foursure.potter4sure.ui.main.houses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.foursure.potter4sure.R;
import net.foursure.potter4sure.models.House;

import java.util.ArrayList;
import java.util.List;

public class HousesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<House> houses = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_house_item, parent, false);

        return new HouseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HouseViewHolder)holder).bind(houses.get(position));
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
        notifyDataSetChanged();
    }

    public class HouseViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView headOfHouse;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtHouseName);
            headOfHouse = itemView.findViewById(R.id.txtHeadOfHouse);
        }

        public void bind(House house) {
            name.setText(house.getName());
            headOfHouse.setText(house.getHeadOfHouse());
        }
    }
}
