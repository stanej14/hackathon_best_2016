package cz.borcizfitu.hackbest.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.domain.model.Item;

public class SentAdapter extends RecyclerView.Adapter<SentAdapter.ViewHolder> {

    private final List<Item> items;
    private SentPackageListener mReceivePackageListener;

    public SentAdapter(@NonNull SentPackageListener listener) {
        this.items = new ArrayList<>();
        this.mReceivePackageListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_item, parent,
                false);
        return new ViewHolder(view, mReceivePackageListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Add item to the adapter.
     *
     * @param item Item.
     */
    public void addItem(@NonNull Item item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    /**
     * Add item to the adapter.
     *
     * @param itemsObject Item.
     */
    public void setItems(@NonNull List<Item> itemsObject) {
        items.clear();
        items.addAll(itemsObject);
        notifyDataSetChanged();
    }

    /**
     * Clear items.
     */
    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private final SentPackageListener itemClickListener;

        @BindView(R.id.img_btn_remove)
        Button removeIB;
        @BindView(R.id.text_item_title)
        TextView name;

        public ViewHolder(@NonNull View itemView, @NonNull SentPackageListener listener) {
            super(itemView);
            this.itemClickListener = listener;
            ButterKnife.bind(this, itemView);
        }

        public void bind(@NonNull Item item) {
            removeIB.setOnClickListener(v -> itemClickListener.onDeleteSentPackageClicked(item.getUrl()));
            name.setText(item.getName());
        }
    }

    public interface SentPackageListener {
        void onDeleteSentPackageClicked(@NonNull String url);
    }
}
