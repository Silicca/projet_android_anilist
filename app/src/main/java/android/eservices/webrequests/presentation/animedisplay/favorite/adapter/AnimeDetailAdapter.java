package android.eservices.webrequests.presentation.animedisplay.favorite.adapter;

import android.eservices.webrequests.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class AnimeDetailAdapter extends RecyclerView.Adapter<AnimeDetailAdapter.AnimeDetailViewHolder> {


    public static class AnimeDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView scoreTextView;
        private TextView typeTextView;
        private TextView synopsisTextView;
        private TextView episodesTextView;
        private TextView startDateTextView;
        private TextView endDateTextView;
        private ImageView imageUrlView;
        private View v;
        private AnimeDetailViewModel animeDetailViewModel;
        private AnimeDetailActionInterface animeDetailActionInterface;
        private Switch favoriteSwitch;

        public AnimeDetailViewHolder(View v, final AnimeDetailActionInterface animeDetailActionInterface) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.anime_title_textview);
            scoreTextView = v.findViewById(R.id.anime_characters_textview);
            typeTextView = v.findViewById(R.id.anime_language_textview);
            synopsisTextView = v.findViewById(R.id.anime_description_textview);
            //episodesTextView = v.findViewById(R.id.anime_episodes_textview);
            startDateTextView = v.findViewById(R.id.anime_published_textview);
            //endDateTextView = v.findViewById(R.id.anime_endate_textview);
            imageUrlView = v.findViewById(R.id.anime_icon_imageview);
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            setupListeners();
            this.animeDetailActionInterface = animeDetailActionInterface;
        }

        private void setupListeners() {
            favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (!b) {
                        animeDetailActionInterface.onRemoveFavorite(animeDetailViewModel.getAnimeId());
                    }
                }
            });
        }

        void bind(AnimeDetailViewModel animeDetailViewModel) {
            this.animeDetailViewModel = animeDetailViewModel;
            titleTextView.setText(animeDetailViewModel.getAnimeTitle());
            scoreTextView.setText(animeDetailViewModel.getAnimeScore());
            typeTextView.setText(animeDetailViewModel.getAnimeType());
            synopsisTextView.setText(animeDetailViewModel.getAnimeSynopsis());
            episodesTextView.setText(animeDetailViewModel.getAnimeEpisodes());
            favoriteSwitch.setChecked(true);
            if (animeDetailViewModel.getAnimeSynopsis() == null) {
                synopsisTextView.setVisibility(View.GONE);
            } else {
                synopsisTextView.setVisibility(View.VISIBLE);
            }
            startDateTextView.setText(animeDetailViewModel.getAnimeStartDate());
            endDateTextView.setText(animeDetailViewModel.getAnimeEndDate());
            Glide.with(v)
                    .load(animeDetailViewModel.getAnimeImageUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageUrlView);

        }

    }

    private List<AnimeDetailViewModel> animeDetailViewModelList;
    private AnimeDetailActionInterface animeDetailActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AnimeDetailAdapter(AnimeDetailActionInterface animeDetailActionInterface) {
        animeDetailViewModelList = new ArrayList<>();
        this.animeDetailActionInterface = animeDetailActionInterface;
    }

    public void bindViewModels(List<AnimeDetailViewModel> animeItemViewModelList) {
        this.animeDetailViewModelList.clear();
        this.animeDetailViewModelList.addAll(animeItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AnimeDetailViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detailed_anime, parent, false);
        AnimeDetailViewHolder animeDetailViewHolder = new AnimeDetailViewHolder(v, animeDetailActionInterface);
        return animeDetailViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AnimeDetailViewHolder holder, int position) {
        holder.bind(animeDetailViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return animeDetailViewModelList.size();
    }


}