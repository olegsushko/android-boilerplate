package uk.co.ribot.androidboilerplate.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.injection.ApplicationContext;

public class RibotsAdapter extends RecyclerView.Adapter<RibotsAdapter.RibotViewHolder> {

    private List<Ribot> mRibots;
    private Context context;

    private RibotItemClickListener itemClickListener;

    @Inject
    public RibotsAdapter(@ApplicationContext final Context context) {
        this.context = context;
        mRibots = new ArrayList<>();
    }

    public void setItemClickListener(RibotItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setRibots(List<Ribot> ribots) {
        mRibots = ribots;
        notifyDataSetChanged();
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ribot, parent, false);
        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RibotViewHolder holder, int position) {
        final Ribot ribot = mRibots.get(position);

        holder.hexColorView.setBackgroundColor(Color.parseColor(ribot.profile().hexColor()));
        holder.nameTextView.setText(String.format("%s %s",
                ribot.profile().name().first(), ribot.profile().name().last()));
        holder.emailTextView.setText(ribot.profile().email());

        holder.loadAvatar(ribot.profile().avatar());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(ribot);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRibots.size();
    }

    class RibotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_hex_color) View hexColorView;
        @BindView(R.id.image_avatar) ImageView avatarView;
        @BindView(R.id.text_name) TextView nameTextView;
        @BindView(R.id.text_email) TextView emailTextView;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void loadAvatar(String url) {
            if (!TextUtils.isEmpty(url)) {
                Picasso.with(context)
                        .load(url)
                        .into(avatarView, new Callback() {
                            @Override
                            public void onSuccess() {
                                hexColorView.setVisibility(View.GONE);
                                avatarView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onError() {
                                Log.e("Oleg", "failed to load an avatar image");
                            }
                        });
            }
        }
    }
}
