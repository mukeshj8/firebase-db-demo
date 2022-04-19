package gov.in.cgstate.chips.firebasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookVH> {

    private List<Book> mBookList;
    private Context mContext;

    public BookAdapter(Context mContext, List<Book> mBookList) {
        this.mBookList = mBookList;
        this.mContext = mContext;
    }

    public void updateResult(List<Book> mBookList) {
        this.mBookList = mBookList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_book, parent, false);
        return new BookVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookVH holder, int position) {
        Book b = mBookList.get(position);
        if (b != null) {
            holder.mTitleTV.setText(b.getTitle());
            holder.mAuthorTV.setText(b.getAuthor());
            holder.mPriceTV.setText(b.getPrice() + " " + b.getPriceUnit());
            holder.mRatingTV.setText("" + b.getRating());
        }
    }

    @Override
    public int getItemCount() {
        return mBookList == null ? 0 : mBookList.size();
    }

    class BookVH extends RecyclerView.ViewHolder {

        TextView mTitleTV, mAuthorTV, mPriceTV, mRatingTV;

        public BookVH(@NonNull View itemView) {
            super(itemView);
            mTitleTV = itemView.findViewById(R.id.tv_title);
            mAuthorTV = itemView.findViewById(R.id.tv_author);
            mPriceTV = itemView.findViewById(R.id.tv_price);
            mRatingTV = itemView.findViewById(R.id.tv_rating);
        }
    }
}
