package android.example.com.popularmovies_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{

    private ArrayList<String> nameArray;
    private ArrayList<String> textArray;


    public ReviewAdapter (ArrayList<String> nameArray, ArrayList<String> textArray){
        this.nameArray = nameArray;
        this.textArray = textArray;


    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        int layoutForReview = R.layout.reviewtext_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutForReview, viewGroup, false);

        ReviewViewHolder holder = new ReviewViewHolder(view, context);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, int i) {

    reviewViewHolder.tv_name.setText(nameArray.get(i));
    reviewViewHolder.tv_reviewText.setText(textArray.get(i));

    }

    @Override
    public int getItemCount() {
        return nameArray.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout rl_Review;
        TextView tv_name, tv_reviewText;

        public ReviewViewHolder(View itemView, final Context context) {
            super(itemView);

            rl_Review = itemView.findViewById(R.id.rl_Review);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_reviewText = itemView.findViewById(R.id.tv_reviewText);


        }
    }
}
