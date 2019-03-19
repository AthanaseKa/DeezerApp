package fr.athanase.components

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class UrlImageView@kotlin.jvm.JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ImageView(context, attrs, defStyleAttr) {
    init {
        adjustViewBounds = true
    }

    companion object {
        @JvmStatic
        @BindingAdapter("url")
        fun UrlImageView.bindUrl(url: String) {
            post {
                Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(android.R.drawable.ic_menu_upload)
                    .into(this)
            }
        }
    }
}