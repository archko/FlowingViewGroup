package com.eyebrowssoftware.example.fvg;

import java.util.LinkedList;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @author archko@gmail.com
 */
public class FlowingViewGroup extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flowing);
        
        TagsViewGroup tvg = (TagsViewGroup) findViewById(R.id.tags);
        ArrayAdapter<String> saa = new ArrayAdapter<String>(this, R.layout.text_view, mTitles);
        tvg.setAdapter(saa);
        // Build variations on the titles array to fill the ListView
        LinkedList<String[]> mLibraries = new LinkedList<String[]>();
        for(int i = 0; i < mTitles.length; ++i) {
        	LinkedList<String> sl = new LinkedList<String>();
        	for(int j = i; j < mTitles.length; ++j) {
                sl.add(mTitles[j]);          break; //test one line
        	}
        	String[] empty = {};
            mLibraries.add(sl.toArray(empty));       break; //test one line
        }

       ArrayAdapter<String[]> saaa = new ArrayAdapter<String[]>(this, R.layout.tags_view_group2, mLibraries) {
        	
        	@Override
        	public View getView(int position, View convertView, ViewGroup parent) {
                RelativeLayout layout;
                TagsViewGroup tgv=null;
        		if(convertView == null) {
                    layout=(RelativeLayout) FlowingViewGroup.this.getLayoutInflater().inflate(R.layout.tags_view_group2, null);
                    tgv=(TagsViewGroup) layout.findViewById(R.id.tag);
                } else {
                    layout=(RelativeLayout) convertView;
                    tgv=(TagsViewGroup) layout.findViewById(R.id.tag);
                }

                tgv.setAdapter(new ImageAdapter(FlowingViewGroup.this));
                return layout;
        	}
        };
        this.setListAdapter(saaa);
    }

    private static final String[] mTitles = 
    {
        "Henry IV (1)",   
        "Henry V",
        "Henry VIII",       
        "Richard II",
        "Richard III",
        "Merchant of Venice",  
        "Othello",
        "King Lear",
        "A Path with Heart",
        "Transmetropolitan",
        "Fables",
        "Jack of Fables",
        "Strangers in Paradise",
    };

    public class ImageAdapter extends BaseAdapter {

        private static final int ITEM_WIDTH=136;
        private static final int ITEM_HEIGHT=88;

        //private final int mGalleryItemBackground;
        private final Context mContext;

        private final Integer[] mImageIds={
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.image_loading,
            R.drawable.image_loading,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.image_loading,
            R.drawable.image_loading,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.image_loading,
            R.drawable.image_loading,
        };

        private final float mDensity;
        private final LayoutInflater mInflater;

        public ImageAdapter(Context c) {
            mContext=c;
            mDensity=c.getResources().getDisplayMetrics().density;
            mInflater=LayoutInflater.from(c);
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // A ViewHolder keeps references to children views to avoid unneccessary calls
            // to findViewById() on each row.
            ViewHolder holder;

            // When convertView is not null, we can reuse it directly, there is no need
            // to reinflate it. We only inflate a new View when the convertView supplied
            // by ListView is null.
            if (convertView==null) {
                convertView=mInflater.inflate(R.layout.home_time_line_item_img, null);

                // Creates a ViewHolder and store references to the two children views
                // we want to bind data to.
                holder=new ViewHolder();
                holder.picture=(ImageView) convertView.findViewById(R.id.picture);
                //holder.pictureLay=(ImageView) convertView.findViewById(R.id.status_picture_lay);

                convertView.setTag(holder);
            } else {
                // Get the ViewHolder back to get fast access to the TextView
                // and the ImageView.
                holder=(ViewHolder) convertView.getTag();
            }

            holder.picture.setImageResource(mImageIds[position]);
            return convertView;
        }
    }

    static class ViewHolder {

        ImageView picture;
        ImageView pictureLay;
    }

}