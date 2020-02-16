package android.example.com.popularmovies_1.adapters;

import android.content.Context;
import android.example.com.popularmovies_1.MainActivity;
import android.example.com.popularmovies_1.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

//Adapter for recycler view

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ThumbnailAdapterViewHolder>{

    private String[] data;


    public ThumbnailAdapter(String[] data){
        this.data = data;
    }

    @NonNull
    @Override
    public ThumbnailAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();

        int layoutForGridItem = R.layout.image_layout;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutForGridItem, viewGroup, false);
        ThumbnailAdapterViewHolder viewHolder = new ThumbnailAdapterViewHolder(view, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailAdapterViewHolder thumbnailAdapterViewHolder, int position) {

        thumbnailAdapterViewHolder.bind(position);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    class ThumbnailAdapterViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        FrameLayout frameLayout;

    public ThumbnailAdapterViewHolder(View itemView, final Context context) {
        super(itemView);

        imageView = itemView.findViewById(R.id.iv_thumbnail);
        frameLayout = itemView.findViewById(R.id.frame_layout);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).userItemClick(getAdapterPosition());
            }
        });
    }

    void bind (int listIndex){

        Picasso.get().load(data[listIndex]).into(imageView);

    }

}

}

