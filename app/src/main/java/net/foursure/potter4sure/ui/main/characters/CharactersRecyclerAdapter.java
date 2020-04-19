package net.foursure.potter4sure.ui.main.characters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.foursure.potter4sure.R;
import net.foursure.potter4sure.models.Characteri;

import java.util.ArrayList;
import java.util.List;

public class CharactersRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Characteri> characters = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_character_item, parent, false);

        return new CharactersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CharactersViewHolder)holder).bind(characters.get(position));
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public void setCharacters(List<Characteri> characters) {
        this.characters = characters;
        notifyDataSetChanged();
    }

    public class CharactersViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView charactersHouse;
        TextView charactersRole;

        public CharactersViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtCharacterName);
            charactersHouse = itemView.findViewById(R.id.txtCharactersHouse);
            charactersRole = itemView.findViewById(R.id.txtCharacterRole);

        }

        public void bind(Characteri character) {
            name.setText(character.getName());
            charactersHouse.setText(character.getHouse());
            charactersRole.setText(character.getRole());
        }
    }
}
