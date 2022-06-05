package tz.co.vanuserve.apicalldemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {
    List<Player> players;
    Context context;

    public PlayersAdapter(List<Player> players, Context context) {
        this.players = players;
        this.context = context;
    }


    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new PlayersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder holder, int position) {
        Glide.with(context)
                .load(players.get(position).getImage())
                .centerCrop()
                .into(holder.playerImage);

        holder.name.setText(players.get(position).getName());
        holder.nationality.setText(players.get(position).getNation());

        holder.itemView.setOnClickListener(view -> {
//            new MaterialDialog.Builder(context)
//                    .title("Welcome")
//                    .content("You cliked position: "+position)
//                    .positiveText("OK")
//                    .negativeText("CANCEL")
//                    .show();
            boolean wrapInScrollView = true;
            new MaterialDialog.Builder(context)
                    .title(players.get(position).getName())
                    .content("Position: Forward")
                    .positiveText("OK")
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    static class PlayersViewHolder extends RecyclerView.ViewHolder{

        ImageView playerImage;
        TextView name;
        TextView nationality;

        public PlayersViewHolder(@NonNull View itemView) {
            super(itemView);
            playerImage=itemView.findViewById(R.id.cso_image);
            name=itemView.findViewById(R.id.text_view_name);
            nationality=itemView.findViewById(R.id.nationality);
        }
    }
}
