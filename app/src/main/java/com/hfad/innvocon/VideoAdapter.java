package com.hfad.innvocon;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

  List<youtubev> youtubelist;
  public VideoAdapter(){

  }

    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.vedioview,parent,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder holder, int position) {
      holder.webvideo.loadData(youtubelist.get(position).getVediourl(),"text/html","utf-8");

    }

    @Override
    public int getItemCount() {
        return youtubelist.size();
    }

    public VideoAdapter(List<youtubev> youtubelist){
      this.youtubelist=youtubelist;
  }


    public class VideoViewHolder extends RecyclerView.ViewHolder {
        WebView webvideo;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            webvideo=(WebView)itemView.findViewById(R.id.webvideo);

            webvideo.getSettings().setJavaScriptEnabled(true);
            webvideo.setWebChromeClient(new WebChromeClient() {

            });
        }
    }
}
