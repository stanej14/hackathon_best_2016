package cz.borcizfitu.hackbest.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.borcizfitu.hackbest.App;
import cz.borcizfitu.hackbest.R;
import cz.borcizfitu.hackbest.domain.model.Item;

public class ReceivedAdapter extends RecyclerView.Adapter<ReceivedAdapter.ViewHolder> {

    private final List<Item> items;
    private ReceivePackageListener mReceivePackageListener;

    public ReceivedAdapter(@NonNull ReceivePackageListener listener) {
        this.items = new ArrayList<>();
        this.mReceivePackageListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.received_item, parent,
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
        private final ReceivePackageListener itemClickListener;
        @BindView(R.id.img_btn_add)
        ImageButton addIB;
        @BindView(R.id.img_btn_remove)
        ImageButton removeIB;
        @BindView(R.id.tv_countdown)
        TextView countdown;
        @BindView(R.id.text_item_title)
        TextView name;
        @BindView(R.id.tv_sender)
        TextView author;

        public ViewHolder(@NonNull View itemView, @NonNull ReceivePackageListener listener) {
            super(itemView);
            this.itemClickListener = listener;
            ButterKnife.bind(this, itemView);
        }

        public void bind(@NonNull Item item) {
            addIB.setOnClickListener(v -> itemClickListener.onAddClicked(item.getUrl()));
            removeIB.setOnClickListener(v -> itemClickListener.onDeleteClicked(item.getUrl()));
            long timeToExpired = item.getExpired() - System.currentTimeMillis();
            int days = (int) (timeToExpired / (1000*60*60*24));
            countdown.setText(String.format(App.getInstance().getString(R.string.countdown),
                    String.valueOf(days)));
            name.setText(item.getName());
            author.setText(item.getAuthor());
        }
    }

    public interface ReceivePackageListener {
        void onAddClicked(@NonNull String url);

        void onDeleteClicked(@NonNull String url);
    }
}
