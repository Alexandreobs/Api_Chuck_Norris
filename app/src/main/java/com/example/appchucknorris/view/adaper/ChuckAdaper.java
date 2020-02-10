package com.example.appchucknorris.view.adaper;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchucknorris.R;
import com.example.appchucknorris.view.activity.DetalheActivity;

import java.util.List;


public class ChuckAdaper extends RecyclerView.Adapter<ChuckAdaper.ViewHolder> {

    public static final String KEYY = "key";

    private List<String> resultList;

    public ChuckAdaper(List<String> resultList) {
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String result = resultList.get(position);
        holder.onBind(result);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetalheActivity.class);
                intent.putExtra(KEYY, resultList.get(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void autalizaLista(List<String> novaLista) {
        if (this.resultList.isEmpty()) {
            this.resultList = novaLista;
        } else {
            this.resultList.addAll(novaLista);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categoria;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoria = itemView.findViewById(R.id.textView_item_recycler);

        }

        public void onBind(String result) {

            categoria.setText(result);

        }
    }
}
