


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Base64;

public class UserAdapter {

    class UserViewHolder extends RecyclerView.ViewHolder {

        UserViewHolder()
    }

    private Bitmap getUserImage(String encodeImage){
        byte[] bytes = Base64.getDecoder().decode(encodeImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
    }
}
